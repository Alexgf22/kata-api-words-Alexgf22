package com.my.appWordle.controllers;

import com.my.appWordle.models.Word;
import com.my.appWordle.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")
public class WordController {
    @Autowired
    private WordRepository wordRepository;

    @GetMapping
    public ResponseEntity<List<Word>> getAllWords() {
        List<Word> words = wordRepository.findAll();
        return words.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(words);
    }

    @GetMapping("/{idWord}")
    public ResponseEntity<Word> getWordById(@PathVariable Long idWord) {
        return wordRepository.findById(idWord)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Word> createWord(@RequestBody Word word) {
        Word createdWord = wordRepository.save(word);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWord);
    }

    @PutMapping("/{idWord}")
    public ResponseEntity<Word> updateWord(@PathVariable Long idWord, @RequestBody Word word) {
        word.setIdWord(idWord);
        Word updatedWord = wordRepository.save(word);
        return ResponseEntity.ok(updatedWord);
    }

    @DeleteMapping("/{idWord}")
    public ResponseEntity<Void> deleteWord(@PathVariable Long idWord) {
        try {
            wordRepository.deleteById(idWord);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
