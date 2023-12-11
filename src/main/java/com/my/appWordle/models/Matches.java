package com.my.appWordle.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Matches")
public class Matches {
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
    @JoinColumn(name = "Player_idPlayer", referencedColumnName = "idPlayer")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "Player_Team_idTeam", referencedColumnName = "idTeam")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "Game_idGame")
    private Game game;


    // Getters y setters


    public Long getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Long idMatch) {
        this.idMatch = idMatch;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getnTries() {
        return nTries;
    }

    public void setnTries(Integer nTries) {
        this.nTries = nTries;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}

