package com.my.appWords.models;

import javax.persistence.*;

@Entity
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlayer")
    private Long idPlayer;

    @Column(name = "User_name", length = 45)
    private String userName;

    @Column(name = "Score")
    private Integer score;

    @Lob
    @Column(name = "Avatar_Img")
    private byte[] avatarImg;

    @ManyToOne
    @JoinColumn(name = "Team_idTeam")
    private Team team;

    // Getters and setters
}

