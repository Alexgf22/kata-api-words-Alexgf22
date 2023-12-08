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

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Match createMatch(@RequestBody Match match) {
        return matchRepository.save(match);
    }

    @PutMapping("/{id}")
    public Match updateMatch(@PathVariable Long id, @RequestBody Match match) {
        match.setIdMatch(id);
        return matchRepository.save(match);
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable Long id) {
        matchRepository.deleteById(id);
    }
}

