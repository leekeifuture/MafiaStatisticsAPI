package com.mafia.statistics.MafiaStatisticsAPI.controller;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.StatisticsType;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IExcelService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/upload", produces = "application/json")
public class UploadController {

    private final IExcelService excelService;

    @PostMapping(value = "/excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadExcel( // TODO: Object
                                               @RequestParam StatisticsType statisticsType,
                                               @RequestParam MultipartFile file
    ) throws Exception { // TODO: Exception
        Object statistics = excelService.uploadExcel(file, statisticsType); // TODO: Object
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
