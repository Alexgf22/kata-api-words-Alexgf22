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

    @GetMapping("/{idWord}")
    public Word getWordById(@PathVariable Long idWord) {
        return wordRepository.findById(idWord).orElse(null);
    }

    @PostMapping
    public Word createWord(@RequestBody Word word) {
        return wordRepository.save(word);
    }

    @PutMapping("/{idWord}")
    public Word updateWord(@PathVariable Long idWord, @RequestBody Word word) {
        word.setIdWord(idWord);
        return wordRepository.save(word);
    }

    @DeleteMapping("/{idWord}")
    public void deleteWord(@PathVariable Long idWord) {
        wordRepository.deleteById(idWord);
    }
}

