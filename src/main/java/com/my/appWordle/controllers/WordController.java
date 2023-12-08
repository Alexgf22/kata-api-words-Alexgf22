package com.my.appWordle.controllers;

import com.my.appWordle.models.Word;
import com.my.appWordle.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")
public class WordController {
    @Autowired
    private WordRepository wordRepository;

    @GetMapping
    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    @GetMapping("/{id}")
    public Word getWordById(@PathVariable Long id) {
        return wordRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Word createWord(@RequestBody Word word) {
        return wordRepository.save(word);
    }

    @PutMapping("/{id}")
    public Word updateWord(@PathVariable Long id, @RequestBody Word word) {
        //word.setIdWord(id);
        return wordRepository.save(word);
    }

    @DeleteMapping("/{id}")
    public void deleteWord(@PathVariable Long id) {
        wordRepository.deleteById(id);
    }
}

