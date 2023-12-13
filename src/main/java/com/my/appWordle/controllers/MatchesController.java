package com.my.appWordle.controllers;

import com.my.appWordle.models.Matches;
import com.my.appWordle.services.MatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchesController {
    @Autowired
    private MatchesService matchesService;

    @GetMapping
    public ResponseEntity<List<Matches>> getAllMatches() {
        List<Matches> matches = matchesService.getAllMatches();
        return matches.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(matches);
    }

    @GetMapping("/{idMatch}")
    public ResponseEntity<Matches> getMatchById(@PathVariable Long idMatch) {
        return matchesService.getMatchById(idMatch)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Matches> createMatch(@RequestBody Matches matches) {
        Matches createdMatches = matchesService.createMatch(matches);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMatches);
    }

    @PutMapping("/{idMatch}")
    public ResponseEntity<Matches> updateMatch(@PathVariable Long idMatch, @RequestBody Matches matches) {
        matches.setIdMatch(idMatch);
        Matches updatedMatches = matchesService.updateMatch(idMatch, matches);
        return ResponseEntity.ok(updatedMatches);
    }

    @DeleteMapping("/{idMatch}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long idMatch) {
        try {
            matchesService.deleteMatch(idMatch);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
