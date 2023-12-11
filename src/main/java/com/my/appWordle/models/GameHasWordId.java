package com.my.appWordle.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class GameHasWordId implements Serializable {
    @Column(name = "Game_idGame")
    private Long gameId;

    @Column(name = "Word_idWord")
    private Long wordId;

    // Getters y setters, equals y hashCode


    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameHasWordId that = (GameHasWordId) o;
        return Objects.equals(gameId, that.gameId) && Objects.equals(wordId, that.wordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, wordId);
    }
}

