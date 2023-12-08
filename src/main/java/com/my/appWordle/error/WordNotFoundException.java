package com.my.appWordle.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WordNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 555555555L;

    public WordNotFoundException(Long idWord) {
        super("No se puede encontrar la palabra con Id: " + idWord);
    }
}
