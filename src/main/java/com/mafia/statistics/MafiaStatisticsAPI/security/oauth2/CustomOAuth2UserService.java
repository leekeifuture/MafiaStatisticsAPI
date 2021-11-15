package com.mafia.statistics.MafiaStatisticsAPI.security.oauth2;

import com.mafia.statistics.MafiaStatisticsAPI.dao.player.IPlayerDao;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.PlayerDto;
import com.mafia.statistics.MafiaStatisticsAPI.exception.OAuth2AuthenticationProcessingException;
import com.mafia.statistics.MafiaStatisticsAPI.exception.ResourceNotFoundException;
import com.mafia.statistics.MafiaStatisticsAPI.security.UserPrincipal;
import com.mafia.statistics.MafiaStatisticsAPI.security.oauth2.user.OAuth2UserInfo;
import com.mafia.statistics.MafiaStatisticsAPI.security.oauth2.user.VkOAuth2UserInfo;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequestEntityConverter;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static final String MISSING_USER_INFO_URI_ERROR_CODE = "missing_user_info_uri";
    private static final String INVALID_USER_INFO_RESPONSE_ERROR_CODE = "invalid_user_info_response";
    private static final String MISSING_USER_NAME_ATTRIBUTE_ERROR_CODE = "missing_user_name_attribute";
    private static final ParameterizedTypeReference<Map<String, Object>> PARAMETERIZED_RESPONSE_TYPE =
            new ParameterizedTypeReference<>() {
            };

    private final Converter<OAuth2UserRequest, RequestEntity<?>> requestEntityConverter =
            new OAuth2UserRequestEntityConverter();

    private final IPlayerDao playerDao;

    private final RestOperations restOperations;

    public CustomOAuth2UserService(IPlayerDao playerDao) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
        this.playerDao = playerDao;
        this.restOperations = restTemplate;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Assert.notNull(userRequest, "userRequest cannot be null");

        if (!StringUtils.hasText(
                userRequest
                        .getClientRegistration()
                        .getProviderDetails()
                        .getUserInfoEndpoint()
                        .getUri()
        )) {
            OAuth2Error oauth2Error = new OAuth2Error(
                    MISSING_USER_INFO_URI_ERROR_CODE,
                    "Missing required UserInfo Uri in " +
                            "UserInfoEndpoint for Client Registration: " +
                            userRequest.getClientRegistration().getRegistrationId(),
                    null
            );

            throw new OAuth2AuthenticationException(oauth2Error, oauth2Error.toString());
        }

        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        if (!StringUtils.hasText(userNameAttributeName)) {
            OAuth2Error oauth2Error = new OAuth2Error(
                    MISSING_USER_NAME_ATTRIBUTE_ERROR_CODE,
                    "Missing required \"user name\" attribute name " +
                            "in UserInfoEndpoint for Client Registration: " +
                            userRequest.getClientRegistration().getRegistrationId(),
                    null
            );

            throw new OAuth2AuthenticationException(oauth2Error, oauth2Error.toString());
        }

        RequestEntity<?> request = this.requestEntityConverter.convert(userRequest);
        ResponseEntity<Map<String, Object>> response;

        try {
            response = this.restOperations.exchange(request, PARAMETERIZED_RESPONSE_TYPE);
        } catch (OAuth2AuthorizationException ex) {
            OAuth2Error oauth2Error = ex.getError();

            StringBuilder errorDetails = new StringBuilder();
            errorDetails.append("Error details: [");
            errorDetails.append("UserInfo Uri: ").append(
                    userRequest
                            .getClientRegistration()
                            .getProviderDetails()
                            .getUserInfoEndpoint()
                            .getUri()
            );
            errorDetails.append(", Error Code: ").append(oauth2Error.getErrorCode());

            if (oauth2Error.getDescription() != null) {
                errorDetails.append(", Error Description: ").append(oauth2Error.getDescription());
            }

            errorDetails.append("]");

            oauth2Error = new OAuth2Error(
                    INVALID_USER_INFO_RESPONSE_ERROR_CODE,
                    "An error occurred while attempting to " +
                            "retrieve the UserInfo Resource: " + errorDetails,
                    null
            );

            throw new OAuth2AuthenticationException(oauth2Error, oauth2Error.toString(), ex);
        } catch (RestClientException ex) {
            OAuth2Error oauth2Error =
                    new OAuth2Error(
                            INVALID_USER_INFO_RESPONSE_ERROR_CODE,
                            "An error occurred while attempting to " +
                                    "retrieve the UserInfo Resource: " +
                                    ex.getMessage(),
                            null
                    );
            throw new OAuth2AuthenticationException(oauth2Error, oauth2Error.toString(), ex);
        }

        // Fetching the attributes from the wrapper "response"
        List<Object> valueList = (ArrayList<Object>) response.getBody().get("response");
        Map<String, Object> userAttributes = (LinkedHashMap<String, Object>) valueList.get(0);

        try {
            return processOAuth2User(userAttributes);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger
            // the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(Map<String, Object> userAttributes) {
        OAuth2UserInfo oAuth2UserInfo = new VkOAuth2UserInfo(userAttributes);

        if (StringUtils.isEmpty(oAuth2UserInfo.getVkId())) {
            throw new OAuth2AuthenticationProcessingException("ID not found from OAuth2 provider");
        }

        PlayerDto player = playerDao
                .findByVkId(Long.valueOf(oAuth2UserInfo.getVkId()))
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "VK id", oAuth2UserInfo.getVkId())
                );

        player = updateExistingUser(player, oAuth2UserInfo);

        return UserPrincipal.create(player, userAttributes);
    }

    private PlayerDto updateExistingUser(PlayerDto existingPlayer, OAuth2UserInfo oAuth2UserInfo) {
        existingPlayer.setFirstName(oAuth2UserInfo.getFirstName());
        existingPlayer.setLastName(oAuth2UserInfo.getLastName());
        existingPlayer.setPhotoUrl(oAuth2UserInfo.getImageUrl());
        return playerDao.save(existingPlayer);
    }
}
