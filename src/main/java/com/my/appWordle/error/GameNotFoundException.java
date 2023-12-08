package com.my.appWordle.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 111111111L;

    public GameNotFoundException(Long idGame) {
        super("No se puede encontrar el juego con Id: " + idGame);
    }
}
