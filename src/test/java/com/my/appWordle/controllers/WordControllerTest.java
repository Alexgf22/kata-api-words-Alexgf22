package com.my.appWordle.controllers;

import com.my.appWordle.services.WordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WordControllerTest {

    @Mock
    private WordService wordService;

    @InjectMocks
    private WordController wordController;

    @Test
    void getAllWordsWithPagination() {
        // Arrange
        String word1 = "Word1";
        String word2 = "Word2";
        List<String> words = Arrays.asList(word1, word2);

        // Configura el servicio para devolver una página en lugar de una lista
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<String> pagedWords = new PageImpl<>(words, pageRequest, words.size());
        when(wordService.getAllWordsWithPagination(pageRequest)).thenReturn(pagedWords);

        // Act
        ResponseEntity<Page<String>> responseEntity = wordController.getAllWords(0, 10);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).getContent().containsAll(words));
    }


    @Test
    void getWordsStartWith() {
        // Arrange
        String prefix = "pre";
        List<String> startingWords = Arrays.asList("prefix", "preposition");
        when(wordService.getWordsStartWith(prefix)).thenReturn(startingWords);

        // Act
        ResponseEntity<List<String>> responseEntity = wordController.getWordsStartWith(prefix);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verifica si todas las palabras cumplen con la condición del prefijo
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).stream().allMatch(word -> word.startsWith(prefix)));
    }

    @Test
    void getWordsEndingWith() {
        // Arrange
        String suffix = "tion";
        List<String> endingWords = Arrays.asList("position", "transition");
        when(wordService.getWordsEndingWith(suffix)).thenReturn(endingWords);

        // Act
        ResponseEntity<List<String>> responseEntity = wordController.getWordsEndingWith(suffix);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).stream().allMatch(word -> word.endsWith(suffix)));
    }

    @Test
    void getWordsContaining() {
        // Arrange
        String substring = "an";
        List<String> containingWords = Arrays.asList("banana", "fantastic");
        when(wordService.getWordsContaining(substring)).thenReturn(containingWords);

        // Act
        ResponseEntity<List<String>> responseEntity = wordController.getWordsContaining(substring);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).stream().allMatch(word -> word.contains(substring)));
    }


}
