package com.my.appWordle.models;

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

    // Getters y setters


    public Long getIdWord() {
        return idWord;
    }

    public void setIdWord(Long idWord) {
        this.idWord = idWord;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}

