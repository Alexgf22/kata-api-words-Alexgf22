package com.my.appWordle.services;

import com.my.appWordle.error.MatchNotFoundException;
import com.my.appWordle.models.Matches;
import com.my.appWordle.repositories.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchesService {

    private final MatchesRepository matchesRepository;

    @Autowired
    public MatchesService(MatchesRepository matchesRepository) {
        this.matchesRepository = matchesRepository;
    }

    public List<Matches> getAllMatches() {
        return matchesRepository.findAll();
    }

    public Page<Matches> getAllMatchesWithPagination(PageRequest pageRequest) {
        return matchesRepository.findAll(pageRequest);
    }

    public Optional<Matches> getMatchById(Long idMatch) {
        return Optional.ofNullable(matchesRepository.findById(idMatch)
                .orElseThrow(() -> new MatchNotFoundException(idMatch)));
    }

    public Matches createMatch(Matches matches) {
        return matchesRepository.save(matches);
    }

    public Matches updateMatch(Long idMatch, Matches updatedMatches) {
        Matches existingMatches = getMatchById(idMatch)
                .orElseThrow(() -> new MatchNotFoundException(idMatch));

        existingMatches.setWord(updatedMatches.getWord());
        existingMatches.setScore(updatedMatches.getScore());
        existingMatches.setnTries(updatedMatches.getnTries());
        existingMatches.setDateTime(updatedMatches.getDateTime());

        return matchesRepository.save(existingMatches);
    }

    public void deleteMatch(Long idMatch) {
        Matches existingMatches = getMatchById(idMatch)
                .orElseThrow(() -> new MatchNotFoundException(idMatch));

        matchesRepository.delete(existingMatches);
    }



}
