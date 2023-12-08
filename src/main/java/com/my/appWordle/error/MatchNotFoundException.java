package com.my.appWordle.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MatchNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 222222222L;

    public MatchNotFoundException(Long id) {
        super("No se puede encontrar el partido con Id: " + id);
    }
}
