package com.my.appWords.models;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class GameHasWordId implements Serializable {
    @Column(name = "Game_idGame")
    private Long gameIdGame;

    @Column(name = "Word_idWord")
    private Long wordIdWord;

    // Getters and setters, equals, and hashCode
}

