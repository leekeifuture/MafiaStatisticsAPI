package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.StatisticsType;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;
import com.mafia.statistics.MafiaStatisticsAPI.exception.InternalServerException;
import com.mafia.statistics.MafiaStatisticsAPI.exception.ResourceNotFoundException;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IExcelService;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IPlayerService;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IStatisticsService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ExcelService implements IExcelService {

    private static final Logger logger = LoggerFactory.getLogger(ExcelService.class);

    private final IStatisticsService statisticsService;

    private final IPlayerService playerService;

    @Value("${app.statistics.path.folder}")
    private String statisticsFolderPath;

    @Value("${app.statistics.path.s3}")
    private String statisticsS3Path;

    @Value("${aws.secretsmanager.region}")
    private String AwsRegion;

    @Override
    public void uploadExcelFiles(MultipartFile[] files) {
        for (MultipartFile file : files) {
            String file_name = file.getOriginalFilename();
            String file_extension = Objects.requireNonNull(
                    FilenameUtils.getExtension(file_name)
            ).toLowerCase();

            if (!file_extension.equals("xls")) {
                logger.error("Required file extension is not equals xls. " +
                        "Present value is {}", file_extension);
                continue;
            }

            logger.info("Started processing: {}", file_name);

            File xlsFile = saveFileToFilesystem(file);

            Map<Integer, List<String>> table = readExcel(xlsFile);

            StatisticsType statisticsType = statisticsService
                    .getStatisticsTypeOfFile(table);

            if (statisticsType.equals(StatisticsType.UNKNOWN)) {
                logger.error("Unsupported statistics type!" +
                        "File name is {}", file_name);
                continue;
            }

            saveFileToS3(xlsFile, statisticsType);

            if (statisticsType.equals(StatisticsType.RATING)) {
                saveStatistics(table, StatisticsType.ROLES_HISTORY);
            }

            saveStatistics(table, statisticsType);

            logger.info("File {} is complete!", file_name);
        }
        logger.info("All statistics is uploaded successfully!");
    }

    private File saveFileToFilesystem(MultipartFile file) {
        File statisticsFolder = new File(statisticsFolderPath);
        statisticsFolder.mkdirs(); // create if not exists

        File statisticsFile = new File(
                statisticsFolderPath + "/" + file.getOriginalFilename()
        );

        try {
            file.transferTo(statisticsFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalServerException(e.getMessage());
        }

        return statisticsFile;
    }

    private Map<Integer, List<String>> readExcel(File file) {
        Map<Integer, List<String>> data = new HashMap<>();

        Sheet sheet;
        try {
            WorkbookSettings ws = new WorkbookSettings();
            ws.setEncoding("Cp1251");

            Workbook workbook = Workbook.getWorkbook(file, ws);
            sheet = workbook.getSheet(0);
        } catch (IOException | BiffException e) {
            throw new ResourceNotFoundException("Workbook or Sheet", "index", 0);
        }

        int rows = sheet.getRows();
        int columns = sheet.getColumns();

        for (int i = 0; i < rows; i++) {
            data.put(i, new ArrayList<>());
            for (int j = 0; j < columns; j++) {
                data.get(i).add(sheet.getCell(j, i).getContents());
            }
        }
        return data;
    }

    private void saveFileToS3(File file, StatisticsType statisticsType) {
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withRegion(AwsRegion)
                .build();

        if (!s3client.doesBucketExistV2(statisticsS3Path)) {
            s3client.createBucket(statisticsS3Path);
        }

        long unixTime = System.currentTimeMillis() / 1000L;
        String filename = statisticsType + "_" + unixTime + ".xls";

        s3client.putObject(statisticsS3Path, filename, file);
    }

    private void saveStatistics(
            Map<Integer, List<String>> table,
            StatisticsType statisticsType
    ) {
        logger.info("Processing {}", statisticsType);

        List<Statistics> statistics = statisticsService
                .parseStatistics(table, statisticsType);

        playerService.savePlayers(statistics);

        statisticsService.saveStatistics(statistics);

        logger.info("Successfully processed {}", statisticsType);
    }
}
