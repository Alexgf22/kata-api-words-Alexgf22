package com.my.appWordle.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGame")
    private Long idGame;

    @Column(name = "Max_tries")
    private Integer maxTries;

    @Column(name = "Description", length = 80)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "Difficulty", nullable = false)
    private Difficulty difficulty;


    // Getters y setters


    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    public Integer getMaxTries() {
        return maxTries;
    }

    public void setMaxTries(Integer maxTries) {
        this.maxTries = maxTries;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}

