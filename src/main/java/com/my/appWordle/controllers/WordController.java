package com.my.appWordle.controllers;

import com.my.appWordle.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping
    public ResponseEntity<List<String>> getAllWords() {
        List<String> words = wordService.getAllWords();
        return words.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(words);
    }

    @GetMapping("/{prefix}")
    public ResponseEntity<List<String>> getWordsStartWith(@PathVariable String prefix) {
        List<String> startingWords = wordService.getWordsStartWith(prefix);
        return startingWords.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(startingWords);
    }

    @GetMapping("/ending/{suffix}")
    public ResponseEntity<List<String>> getWordsEndingWith(@PathVariable String suffix) {
        List<String> endingWords = wordService.getWordsEndingWith(suffix);
        return endingWords.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(endingWords);
    }

    @GetMapping("/containing/{substring}")
    public ResponseEntity<List<String>> getWordsContaining(@PathVariable String substring) {
        List<String> containingWords = wordService.getWordsContaining(substring);
        return containingWords.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(containingWords);
    }

    @PostMapping
    public ResponseEntity<String> createWord() {
        String randomWord = wordService.getRandomWord();
        return ResponseEntity.status(HttpStatus.CREATED).body(randomWord);
    }
}
