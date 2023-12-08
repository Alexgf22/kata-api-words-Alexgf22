package com.my.appWords.models;

import javax.persistence.*;

@Entity
@Table(name = "Word")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWord")
    private Long idWord;

    @Column(name = "Word", length = 45)
    private String word;

    // Getters and setters
}

