package com.my.appWordle.services;

import com.my.appWordle.error.MatchNotFoundException;
import com.my.appWordle.models.Matches;
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

    public List<Matches> getAllMatches() {
        return matchRepository.findAll();
    }

    public Matches getMatchById(Long idMatch) {
        return matchRepository.findById(idMatch)
                .orElseThrow(() -> new MatchNotFoundException(idMatch));
    }

    public Matches createMatch(Matches matches) {
        return matchRepository.save(matches);
    }

    public Matches updateMatch(Long idMatch, Matches updatedMatches) {
        Matches existingMatches = getMatchById(idMatch);

        existingMatches.setWord(updatedMatches.getWord());
        existingMatches.setScore(updatedMatches.getScore());
        existingMatches.setnTries(updatedMatches.getnTries());
        existingMatches.setDateTime(updatedMatches.getDateTime());

        return matchRepository.save(existingMatches);
    }

    public void deleteMatch(Long idMatch) {
        // Comprobación de si el partido existe antes de intentar eliminarlo
        Matches existingMatches = getMatchById(idMatch);

        matchRepository.delete(existingMatches);
    }
}
