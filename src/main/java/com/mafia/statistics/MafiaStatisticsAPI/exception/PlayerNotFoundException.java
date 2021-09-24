package com.mafia.statistics.MafiaStatisticsAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Player not found")
public class PlayerNotFoundException extends Exception {

    private String message;
}
