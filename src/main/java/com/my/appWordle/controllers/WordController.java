package com.my.appWordle.controllers;

import com.my.appWordle.error.WordNotFoundException;
import com.my.appWordle.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/words")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping
    public ResponseEntity<Page<String>> getAllWords(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<String> words = wordService.getAllWordsWithPagination(pageRequest);
        return words.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(words);
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllWords() {
        List<String> words = wordService.getAllWords();
        return words.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(words);
    }

    @GetMapping("/starting/{prefix}")
    public ResponseEntity<List<String>> getWordsStartingWith(@PathVariable String prefix) {
        List<String> startingWords = wordService.getWordsStartingWith(prefix);
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

    @GetMapping("/random")
    public ResponseEntity<String> getRandomWord() {
        String randomWord = wordService.getRandomWord();
        return ResponseEntity.ok(randomWord);
    }

    @GetMapping("/random/{num}")
    public ResponseEntity<List<String>> getRandomWords(@PathVariable Long num) {
        List<String> randomWords = wordService.getRandomWords(num);
        return ResponseEntity.ok(randomWords);
    }

    @GetMapping("/startingWithPrefixesFromFile/{prefixesFileName}")
    public ResponseEntity<List<String>> getWordsStartingWithPrefixesFromFile(@PathVariable String prefixesFileName) {
        try {
            List<String> startingWords = wordService.getWordsStartingWithPrefixesFromFile(prefixesFileName);
            return ResponseEntity.ok(startingWords);
        } catch (WordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/endingWithSuffixesFromFile/{suffixesFileName}")
    public ResponseEntity<List<String>> getWordsEndingWithSuffixesFromFile(@PathVariable String suffixesFileName) {
        try {
            List<String> endingWords = wordService.getWordsEndingWithSuffixesFromFile(suffixesFileName);
            return ResponseEntity.ok(endingWords);
        } catch (WordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonList(e.getMessage()));
        }
    }


}
