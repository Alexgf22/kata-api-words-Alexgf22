package com.my.appWordle.services;

import com.my.appWordle.error.MatchNotFoundException;
import com.my.appWordle.models.Match;
import com.my.appWordle.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatchById(Long idMatch) {
        return matchRepository.findById(idMatch)
                .orElseThrow(() -> new MatchNotFoundException(idMatch));
    }

    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    public Match updateMatch(Long idMatch, Match updatedMatch) {
        Match existingMatch = getMatchById(idMatch);

        existingMatch.setWord(updatedMatch.getWord());
        existingMatch.setScore(updatedMatch.getScore());
        existingMatch.setnTries(updatedMatch.getnTries());
        existingMatch.setDateTime(updatedMatch.getDateTime());

        return matchRepository.save(existingMatch);
    }

    public void deleteMatch(Long idMatch) {
        // Comprobación de si el partido existe antes de intentar eliminarlo
        Match existingMatch = getMatchById(idMatch);

        matchRepository.delete(existingMatch);
    }
}
