package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.google.gson.Gson;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IVkService;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.responses.GetResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VkService implements IVkService {

    @Value("${vk.app_id}")
    private Integer appId;
    @Value("${vk.client_secret}")
    private String clientSecret;
    @Value("${vk.access_token}")
    private String accessToken;

    public String getPhotoByUserId(Long userId) {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(
                transportClient, new Gson(), 10
        );

        ServiceActor actor = new ServiceActor(appId, clientSecret, accessToken);

        String photoUrl = "";
        try {
            List<GetResponse> response = vk.users().get(actor)
                    .userIds(String.valueOf(userId))
                    .fields(Fields.PHOTO_MAX).execute();
            photoUrl = response.get(0).getPhotoMax().toString();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }

        return photoUrl;
    }
}
