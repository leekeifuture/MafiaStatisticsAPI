package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.StatisticsType;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;
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

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcelService implements IExcelService {

    private final IStatisticsService statisticsService;

    private final IPlayerService playerService;

    @Value("${app.statistics.folder.path}")
    private String statisticsFolderPath;

    @Override
    public Object uploadExcel(MultipartFile multipartFile, StatisticsType statisticsType) // TODO: Object
            throws Exception { // TODO: Exception
        File xlsFile = saveFileToFilesystem(multipartFile);

        Map<Integer, List<String>> table = readExcel(xlsFile);

        List<Statistics> statistics = statisticsService.parseStatistics(table, statisticsType);

        playerService.savePlayers(statistics);

        statisticsService.saveStatistics(statistics);

        return new Object();
    }

    private File saveFileToFilesystem(MultipartFile multipartFile) throws Exception { // TODO: Exception
        if (!multipartFile.getContentType().split("\\.")[1].equals("xls")) { // check if .xls
            throw new Exception(); // TODO: Exception
        }

        File statisticsFolder = new File(statisticsFolderPath);
        statisticsFolder.mkdirs(); // create if not exists

        long unixTime = System.currentTimeMillis() / 1000L;
        File statisticsFile = new File(statisticsFolderPath + "/" + unixTime + ".xls");

        multipartFile.transferTo(statisticsFile.toPath());

        return statisticsFile;
    }

    private Map<Integer, List<String>> readExcel(File file)
            throws IOException, BiffException { // TODO: Exception
        Map<Integer, List<String>> data = new HashMap<>();

        Workbook workbook = Workbook.getWorkbook(file);
        Sheet sheet = workbook.getSheet(0);
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
