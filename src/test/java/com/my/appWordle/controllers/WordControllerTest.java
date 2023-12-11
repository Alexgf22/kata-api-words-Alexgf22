package com.my.appWordle.controllers;

import com.my.appWordle.models.Word;
import com.my.appWordle.repositories.WordRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WordControllerTest {

    @Mock
    private WordRepository wordRepository;

    @InjectMocks
    private WordController wordController;

    @Test
    void getAllWords() {
        // Arrange
        Word word1 = createTestWord("Word1");
        Word word2 = createTestWord("Word2");

        List<Word> words = Arrays.asList(word1, word2);
        when(wordRepository.findAll()).thenReturn(words);

        // Act
        ResponseEntity<List<Word>> responseEntity = wordController.getAllWords();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(words, responseEntity.getBody());
    }

    @Test
    void getWordById() {
        // Arrange
        Long idWord = 1L;
        Word word = createTestWord("Word1");
        when(wordRepository.findById(idWord)).thenReturn(Optional.of(word));

        // Act
        ResponseEntity<Word> responseEntity = wordController.getWordById(idWord);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(word, responseEntity.getBody());
    }

    @Test
    void getWordById_NotFound() {
        // Arrange
        Long idWord = 500L;
        when(wordRepository.findById(idWord)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Word> responseEntity = wordController.getWordById(idWord);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void createWord() {
        // Arrange
        Word testWord = createTestWord("Word3");
        when(wordRepository.save(any(Word.class))).thenReturn(testWord);

        // Act
        ResponseEntity<Word> responseEntity = wordController.createWord(testWord);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(testWord, responseEntity.getBody());
    }

    @Test
    void updateWord() {
        // Arrange
        Long idWord = 2L;
        Word existingWord = createTestWord("Word4");
        Word updatedWord = createTestWord("UpdatedWord");

        lenient().when(wordRepository.findById(idWord)).thenReturn(Optional.of(existingWord));
        lenient().when(wordRepository.save(any(Word.class))).thenReturn(updatedWord);

        // Act
        ResponseEntity<Word> responseEntity = wordController.updateWord(idWord, updatedWord);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedWord, responseEntity.getBody());
    }

    @Test
    void deleteWord() {
        // Arrange
        Long idWord = 2L;
        Word existingWord = createTestWord("Word5");

        lenient().when(wordRepository.existsById(idWord)).thenReturn(true);
        lenient().when(wordRepository.findById(idWord)).thenReturn(Optional.of(existingWord));

        // Act
        ResponseEntity<Void> responseEntity = wordController.deleteWord(idWord);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(wordRepository, times(1)).deleteById(idWord);
    }

    @Test
    void deleteWord_NotFound() {
        // Arrange
        Long idWord = 400L;
        lenient().when(wordRepository.findById(idWord)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> responseEntity = wordController.deleteWord(idWord);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(wordRepository, never()).deleteById(idWord);
    }

    private Word createTestWord(String word) {
        Word testWord = new Word();
        testWord.setWord(word);
        return testWord;
    }
}
