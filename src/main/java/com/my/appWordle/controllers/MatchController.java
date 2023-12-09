package com.my.appWordle.controllers;

import com.my.appWordle.models.Match;
import com.my.appWordle.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    @Autowired
    private MatchRepository matchRepository;

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = matchRepository.findAll();

        if (matches.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(matches, HttpStatus.OK);
        }
    }

    @GetMapping("/{idMatch}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long idMatch) {
        Match match = matchRepository.findById(idMatch).orElse(null);

        if (match != null) {
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        Match createdMatch = matchRepository.save(match);
        return new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
    }

    @PutMapping("/{idMatch}")
    public ResponseEntity<Match> updateMatch(@PathVariable Long idMatch, @RequestBody Match match) {
        match.setIdMatch(idMatch);
        Match updatedMatch = matchRepository.save(match);
        return new ResponseEntity<>(updatedMatch, HttpStatus.OK);
    }

    @DeleteMapping("/{idMatch}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long idMatch) {
        if (matchRepository.existsById(idMatch)) {
            matchRepository.deleteById(idMatch);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
