package com.my.appWordle.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GameHasWordId implements Serializable {
    @Column(name = "Game_idGame")
    private Long gameIdGame;

    @Column(name = "Word_idWord")
    private Long wordIdWord;

    // Getters y setters, equals y hashCode


    public Long getGameIdGame() {
        return gameIdGame;
    }

    public void setGameIdGame(Long gameIdGame) {
        this.gameIdGame = gameIdGame;
    }

    public Long getWordIdWord() {
        return wordIdWord;
    }

    public void setWordIdWord(Long wordIdWord) {
        this.wordIdWord = wordIdWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameHasWordId that = (GameHasWordId) o;
        return Objects.equals(gameIdGame, that.gameIdGame) && Objects.equals(wordIdWord, that.wordIdWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameIdGame, wordIdWord);
    }
}

