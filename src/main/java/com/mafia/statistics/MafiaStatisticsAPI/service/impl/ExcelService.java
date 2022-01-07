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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcelService implements IExcelService {

    private final IStatisticsService statisticsService;

    private final IPlayerService playerService;

    @Value("${app.statistics.path.folder}")
    private String statisticsFolderPath;

    @Value("${app.statistics.path.s3}")
    private String statisticsS3Path;

    @Value("${aws.secretsmanager.region}")
    private String AwsRegion;

    @Override
    public void uploadExcel(MultipartFile multipartFile, StatisticsType statisticsType) {
        File xlsFile = saveFileToFilesystem(multipartFile, statisticsType);

        saveFileToS3(xlsFile);

        Map<Integer, List<String>> table = readExcel(xlsFile);

        List<Statistics> statistics = statisticsService.parseStatistics(table, statisticsType);

        playerService.savePlayers(statistics);

        statisticsService.saveStatistics(statistics);
    }

    private void saveFileToS3(File file) {
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withRegion(AwsRegion)
                .build();

        if (!s3client.doesBucketExistV2(statisticsS3Path)) {
            s3client.createBucket(statisticsS3Path);
        }

        s3client.putObject(statisticsS3Path, file.getName(), file);
    }

    private File saveFileToFilesystem(
            MultipartFile multipartFile,
            StatisticsType statisticsType
    ) {
        if (!Objects.requireNonNull(multipartFile.getContentType())
                .split("\\.")[1].equals("xls")) {
            throw new ResourceNotFoundException("File", "extension", ".xls");
        }

        File statisticsFolder = new File(statisticsFolderPath);
        statisticsFolder.mkdirs(); // create if not exists

        long unixTime = System.currentTimeMillis() / 1000L;
        String filename = statisticsType + "_" + unixTime + ".xls";

        File statisticsFile = new File(
                statisticsFolderPath + "/" + filename
        );

        try {
            multipartFile.transferTo(statisticsFile.toPath());
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
}
