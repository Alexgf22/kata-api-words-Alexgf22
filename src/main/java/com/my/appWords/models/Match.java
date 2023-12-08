package com.my.appWords.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMatch")
    private Long idMatch;

    @Column(name = "Word", length = 45)
    private String word;

    @Column(name = "Score")
    private Integer score;

    @Column(name = "N_tries")
    private Integer nTries;

    @Column(name = "Date_time")
    private Date dateTime;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "Player_idPlayer", referencedColumnName = "idPlayer"),
            //@JoinColumn(name = "Player_Team_idTeam", referencedColumnName = "idTeam")
    })
    private Player player;

    @ManyToOne
    @JoinColumn(name = "Game_idGame")
    private Game game;


    // Getters and setters
}

