package com.mafia.statistics.MafiaStatisticsAPI.security.oauth2.user;

import java.util.Map;

public class VkOAuth2UserInfo extends OAuth2UserInfo {

    public VkOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public Integer getVkId() {
        return (Integer) attributes.get("id");
    }

    @Override
    public String getFirstName() {
        return (String) attributes.get("first_name");
    }

    @Override
    public String getLastName() {
        return (String) attributes.get("last_name");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("photo_max");
    }
}
