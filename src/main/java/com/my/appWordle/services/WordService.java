package com.my.appWordle.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WordService {

    private static List<String> wordList;

    public WordService() {
        // Cargar palabras desde el archivo al inicializar el servicio
        loadWords();
    }

    private void loadWords() {
        wordList = new ArrayList<>();

        // Obtener la referencia al archivo 'words.txt' desde el classpath
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("allWords.txt")) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                // Leer cada línea del archivo y añadir la palabra a la lista
                String line;
                while ((line = reader.readLine()) != null) {
                    wordList.add(line.trim());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllWords() {
        // Devolver la lista completa de palabras
        return new ArrayList<>(wordList);
    }

    public Page<String> getAllWordsWithPagination(PageRequest pageRequest) {
        // Lógica para obtener las palabras paginadas manualmente
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), wordList.size());

        return new PageImpl<>(wordList.subList(start, end), pageRequest, wordList.size());
    }

    public String getRandomWord() {
        // Obtener una palabra aleatoria de la lista
        Random random = new Random();
        int index = random.nextInt(wordList.size());
        return wordList.get(index);
    }


    public List<String> getRandomWords(Long num) {
        List<String> randomList = new ArrayList<>();
        if (num > wordList.size()) {
            num = (long) wordList.size();
        }

        for (int x = 0; x < num; x++) {
            int random = (int) (Math.random() * wordList.size());
            randomList.add(wordList.get(random));
        }

        return randomList;
    }

    public List<String> getWordsStartingWith(String prefix) {
        List<String> startingWordsList = new ArrayList<>();
        String prefixLowerCase = prefix.toLowerCase();

        for (String word : wordList) {
            if (word.toLowerCase().startsWith(prefixLowerCase)) {
                startingWordsList.add(word);
            }
        }

        return startingWordsList;
    }

    public List<String> getWordsEndingWith(String suffix) {
        List<String> endingWordsList = new ArrayList<>();
        String suffixLowerCase = suffix.toLowerCase();

        for (String word : wordList) {
            if (word.toLowerCase().endsWith(suffixLowerCase)) {
                endingWordsList.add(word);
            }
        }

        return endingWordsList;
    }

    public List<String> getWordsContaining(String substring) {
        List<String> containingWordsList = new ArrayList<>();
        String substringLowerCase = substring.toLowerCase();

        for (String word : wordList) {
            if (word.toLowerCase().contains(substringLowerCase)) {
                containingWordsList.add(word);
            }
        }

        return containingWordsList;
    }

    public List<String> getWordsStartingWithPrefixesFromFile(String prefixesFileName) {
        List<String> startingWordsList = new ArrayList<>();
        List<String> prefixesList = loadPrefixesOrSuffixesFromFile(prefixesFileName);

        System.out.println("Prefixes List: " + prefixesList);

        for (String word : wordList) {
            for (String prefix : prefixesList) {
                if (word.toLowerCase().startsWith(prefix.toLowerCase())) {
                    startingWordsList.add(word);
                    break; // Una vez que se encuentra una coincidencia, se puede salir del bucle interno
                }
            }
        }

        return startingWordsList;
    }

    public List<String> getWordsEndingWithSuffixesFromFile(String suffixesFileName) {
        List<String> endingWordsList = new ArrayList<>();
        List<String> suffixesList = loadPrefixesOrSuffixesFromFile(suffixesFileName);

        System.out.println("Suffixes List: " + suffixesList);

        for (String word : wordList) {
            for (String suffix : suffixesList) {
                if (word.toLowerCase().endsWith(suffix.toLowerCase())) {
                    endingWordsList.add(word);
                    break; // Una vez que se encuentra una coincidencia, se puede salir del bucle interno
                }
            }
        }

        return endingWordsList;
    }

    private List<String> loadPrefixesOrSuffixesFromFile(String fileName) {
        List<String> prefixesOrSuffixesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource(fileName).getInputStream(), StandardCharsets.UTF_8))) {
            // Asegúrate de cerrar el recurso de manera segura utilizando try-with-resources

            // Leer cada línea del archivo y añadir el prefijo o sufijo a la lista
            String line;
            while ((line = reader.readLine()) != null) {
                prefixesOrSuffixesList.add(line.trim());
            }

        } catch (IOException e) {
            // Maneja la excepción adecuadamente, puedes imprimir un registro o lanzar una excepción personalizada
            e.printStackTrace();
        }

        return prefixesOrSuffixesList;
    }



}
