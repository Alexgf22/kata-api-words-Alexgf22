package com.my.appWordle.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 444444444L;

    public TeamNotFoundException(Long idTeam) {
        super("No se puede encontrar el equipo con Id: " + idTeam);
    }
}
