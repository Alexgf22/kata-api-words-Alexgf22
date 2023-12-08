package com.my.appWordle.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 333333333L;

    public PlayerNotFoundException(Long idPlayer) {
        super("No se puede encontrar el jugador con Id: " + idPlayer);
    }
}
