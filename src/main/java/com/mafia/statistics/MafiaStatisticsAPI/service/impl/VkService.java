package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.google.gson.Gson;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IVkService;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.base.Sex;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VkService implements IVkService {

    @Value("${app.vk.appId}")
    private Integer appId;
    @Value("${app.vk.clientSecret}")
    private String clientSecret;
    @Value("${app.vk.accessToken}")
    private String accessToken;

    private VkApiClient vkApiClient = new VkApiClient(
            HttpTransportClient.getInstance(), new Gson(), 10
    );

    @Override
    public String getPhotoByUserId(Long userId) {
        String photoUrl = "";
        try {
            List<GetResponse> response = vkApiClient.users().get(getActor())
                    .userIds(String.valueOf(userId))
                    .fields(Fields.PHOTO_MAX).execute();
            photoUrl = response.get(0).getPhotoMax().toString();
        } catch (ApiException | ClientException e) {
            refreshVkClient();
            e.printStackTrace();
        }

        return photoUrl;
    }

    @Override
    public Sex getGenderByUserId(Long userId) {
        Sex gender = Sex.UNKNOWN;
        try {
            List<GetResponse> response = vkApiClient.users().get(getActor())
                    .userIds(String.valueOf(userId))
                    .fields(Fields.SEX).execute();
            gender = response.get(0).getSex();
        } catch (ApiException | ClientException e) {
            refreshVkClient();
            e.printStackTrace();
        }

        return gender;
    }

    private ServiceActor getActor() {
        return new ServiceActor(appId, clientSecret, accessToken);
    }

    private void refreshVkClient() {
        vkApiClient = new VkApiClient(
                HttpTransportClient.getInstance(), new Gson(), 10
        );
    }
}
