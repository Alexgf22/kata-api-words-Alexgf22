package com.my.appWords.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTeam")
    private Long idTeam;

    @Column(name = "Score")
    private Integer score;

    @Column(name = "Team_name", length = 45)
    private String teamName;

    @Lob
    @Column(name = "Badge")
    private byte[] badge;

    // Getters and setters
}

