package com.my.appWordle.models;

import javax.persistence.*;

@Entity
@Table(name = "Game_has_Word")
public class GameHasWord {
    @EmbeddedId
    private GameHasWordId id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Difficulty", nullable = false)
    private Difficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "Game_idGame", insertable = false, updatable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "Word_idWord", insertable = false, updatable = false)
    private Word word;

    // Getters y setters


    public GameHasWordId getId() {
        return id;
    }

    public void setId(GameHasWordId id) {
        this.id = id;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}

