/*
package com.my.appWordle.controllers;

import com.my.appWordle.services.WordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WordControllerTest {

    @Mock
    private WordService wordService;  // Corrected: Change from wordRepository to wordService

    @InjectMocks
    private WordController wordController;

    @Test
    void getAllWords() {
        // Arrange
        String word1 = "Word1";
        String word2 = "Word2";

        List<String> words = Arrays.asList(word1, word2);
        when(wordService.getAllWords()).thenReturn(words);  // Corrected: Change from wordRepository to wordService

        // Act
        ResponseEntity<List<String>> responseEntity = wordController.getAllWords();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(words, responseEntity.getBody());
    }

    @Test
    void getWordById() {
        // Arrange
        Long idWord = 1L;
        String word = "Word1";
        when(wordService.getWordById(idWord)).thenReturn(Optional.of(word));  // Corrected: Change from wordRepository to wordService

        // Act
        ResponseEntity<String> responseEntity = wordController.getWordById(idWord);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(word, responseEntity.getBody());
    }

    @Test
    void getWordById_NotFound() {
        // Arrange
        Long idWord = 500L;
        when(wordService.getWordById(idWord)).thenReturn(Optional.empty());  // Corrected: Change from wordRepository to wordService

        // Act
        ResponseEntity<String> responseEntity = wordController.getWordById(idWord);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void createWord() {
        // Arrange
        String testWord = "Word3";
        when(wordService.createWord(any(String.class))).thenReturn(testWord);  // Corrected: Change from wordRepository to wordService

        // Act
        ResponseEntity<String> responseEntity = wordController.createWord();

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(testWord, responseEntity.getBody());
    }

    @Test
    void updateWord() {
        // Arrange
        Long idWord = 2L;
        String existingWord = "Word4";
        String updatedWord = "UpdatedWord";

        lenient().when(wordService.getWordById(idWord)).thenReturn(Optional.of(existingWord));  // Corrected: Change from wordRepository to wordService
        lenient().when(wordService.updateWord(idWord, updatedWord)).thenReturn(updatedWord);  // Corrected: Change from wordRepository to wordService

        // Act
        ResponseEntity<String> responseEntity = wordController.updateWord(idWord, updatedWord);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedWord, responseEntity.getBody());
    }

    @Test
    void deleteWord() {
        // Arrange
        Long idWord = 2L;
        String existingWord = "Word5";

        lenient().when(wordService.wordExists(idWord)).thenReturn(true);  // Corrected: Change from wordRepository to wordService
        lenient().when(wordService.getWordById(idWord)).thenReturn(Optional.of(existingWord));  // Corrected: Change from wordRepository to wordService

        // Act
        ResponseEntity<Void> responseEntity = wordController.deleteWord(idWord);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(wordService, times(1)).deleteWord(idWord);  // Corrected: Change from wordRepository to wordService
    }

    @Test
    void deleteWord_NotFound() {
        // Arrange
        Long idWord = 400L;
        lenient().when(wordService.getWordById(idWord)).thenReturn(Optional.empty());  // Corrected: Change from wordRepository to wordService

        // Act
        ResponseEntity<Void> responseEntity = wordController.deleteWord(idWord);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(wordService, never()).deleteWord(idWord);  // Corrected: Change from wordRepository to wordService
    }
}
*/
