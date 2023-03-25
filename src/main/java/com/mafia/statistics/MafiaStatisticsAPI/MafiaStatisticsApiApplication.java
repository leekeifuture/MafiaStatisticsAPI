package com.mafia.statistics.MafiaStatisticsAPI;

import com.mafia.statistics.MafiaStatisticsAPI.config.AppProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
public class MafiaStatisticsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MafiaStatisticsApiApplication.class, args);
    }
}
