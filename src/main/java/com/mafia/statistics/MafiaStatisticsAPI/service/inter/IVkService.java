package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.vk.api.sdk.objects.base.Sex;

public interface IVkService {

    String getPhotoByUserId(Long userId);

    Sex getGenderByUserId(Long userId);
}
