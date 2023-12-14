package com.my.appWordle.services;

import com.my.appWordle.models.Game;
import com.my.appWordle.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Page<Game> getAllGamesWithPagination(PageRequest pageRequest) {
        return gameRepository.findAll(pageRequest);
    }

    public Optional<Game> getGameById(Long idGame) {
        return gameRepository.findById(idGame);
    }


}
