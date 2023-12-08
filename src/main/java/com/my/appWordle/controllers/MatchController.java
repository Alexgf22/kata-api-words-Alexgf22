package com.my.appWordle.controllers;

import com.my.appWordle.models.Match;
import com.my.appWordle.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    @Autowired
    private MatchRepository matchRepository;

    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @GetMapping("/{idMatch}")
    public Match getMatchById(@PathVariable Long idMatch) {
        return matchRepository.findById(idMatch).orElse(null);
    }

    @PostMapping
    public Match createMatch(@RequestBody Match match) {
        return matchRepository.save(match);
    }

    @PutMapping("/{idMatch}")
    public Match updateMatch(@PathVariable Long idMatch, @RequestBody Match match) {
        match.setIdMatch(idMatch);
        return matchRepository.save(match);
    }

    @DeleteMapping("/{idMatch}")
    public void deleteMatch(@PathVariable Long idMatch) {
        matchRepository.deleteById(idMatch);
    }
}

