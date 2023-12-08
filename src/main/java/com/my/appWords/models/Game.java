package com.my.appWords.models;

import javax.persistence.*;


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


    // Getters and setters



}

