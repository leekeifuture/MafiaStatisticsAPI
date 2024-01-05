package com.mafia.statistics.MafiaStatisticsAPI.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

    private final String message;

    public InternalServerException(String message) {
        super(message);
        this.message = message;
    }
}
