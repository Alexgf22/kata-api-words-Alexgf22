package com.my.appWordle.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordService {

    private List<String> wordList;

    public WordService() {
        // Cargar palabras desde el archivo al inicializar el servicio
        loadWords();
    }

    private void loadWords() {
        wordList = new ArrayList<>();

        // Obtener la referencia al archivo 'words.txt' desde el classpath
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("words.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            // Leer cada línea del archivo y añadir la palabra a la lista
            String line;
            while ((line = reader.readLine()) != null) {
                wordList.add(line.trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRandomWord() {
        // Obtener una palabra aleatoria de la lista
        Random random = new Random();
        int index = random.nextInt(wordList.size());
        return wordList.get(index);
    }


    public List<String> getAllWords() {
        // Devolver la lista completa de palabras
        return new ArrayList<>(wordList);
    }


    public static void main(String[] args) {
        // Prueba de uso del servicio
        WordService wordService = new WordService();
        String randomWord = wordService.getRandomWord();

        System.out.println("Palabra aleatoria: " + randomWord);
    }
}
