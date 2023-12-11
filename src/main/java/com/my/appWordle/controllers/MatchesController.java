package com.my.appWordle.controllers;

import com.my.appWordle.models.Matches;
import com.my.appWordle.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchesController {
    @Autowired
    private MatchRepository matchRepository;

    @GetMapping
    public ResponseEntity<List<Matches>> getAllMatches() {
        List<Matches> matches = matchRepository.findAll();

        if (matches.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(matches, HttpStatus.OK);
        }
    }

    @GetMapping("/{idMatch}")
    public ResponseEntity<Matches> getMatchById(@PathVariable Long idMatch) {
        Matches matches = matchRepository.findById(idMatch).orElse(null);

        if (matches != null) {
            return new ResponseEntity<>(matches, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Matches> createMatch(@RequestBody Matches matches) {
        Matches createdMatches = matchRepository.save(matches);
        return new ResponseEntity<>(createdMatches, HttpStatus.CREATED);
    }

    @PutMapping("/{idMatch}")
    public ResponseEntity<Matches> updateMatch(@PathVariable Long idMatch, @RequestBody Matches matches) {
        matches.setIdMatch(idMatch);
        Matches updatedMatches = matchRepository.save(matches);
        return new ResponseEntity<>(updatedMatches, HttpStatus.OK);
    }

    @DeleteMapping("/{idMatch}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long idMatch) {
        if (matchRepository.existsById(idMatch)) {
            System.out.println("El recurso existe en la base de datos.");
            matchRepository.deleteById(idMatch);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            System.out.println("El recurso NO existe en la base de datos.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
