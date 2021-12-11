package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.StatisticsType;

import org.springframework.web.multipart.MultipartFile;

public interface IExcelService {

    void uploadExcel(MultipartFile file, StatisticsType statisticsType);
}
