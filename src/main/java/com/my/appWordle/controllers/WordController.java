package com.my.appWordle.controllers;

import com.my.appWordle.models.Word;
import com.my.appWordle.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        if (words.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(words, HttpStatus.OK);
        }
    }

    @GetMapping("/{idWord}")
    public ResponseEntity<Word> getWordById(@PathVariable Long idWord) {
        Word word = wordRepository.findById(idWord).orElse(null);

        if (word != null) {
            return new ResponseEntity<>(word, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Word> createWord(@RequestBody Word word) {
        Word createdWord = wordRepository.save(word);
        return new ResponseEntity<>(createdWord, HttpStatus.CREATED);
    }

    @PutMapping("/{idWord}")
    public ResponseEntity<Word> updateWord(@PathVariable Long idWord, @RequestBody Word word) {
        word.setIdWord(idWord);
        Word updatedWord = wordRepository.save(word);
        return new ResponseEntity<>(updatedWord, HttpStatus.OK);
    }

    @DeleteMapping("/{idWord}")
    public ResponseEntity<Void> deleteWord(@PathVariable Long idWord) {
        if (wordRepository.existsById(idWord)) {
            wordRepository.deleteById(idWord);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
