package com.my.appWords.models;

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

    // Getters and setters
}

