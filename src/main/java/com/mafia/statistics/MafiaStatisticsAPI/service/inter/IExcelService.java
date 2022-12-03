package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import org.springframework.web.multipart.MultipartFile;

public interface IExcelService {

    void uploadExcelFiles(MultipartFile[] file);
}
