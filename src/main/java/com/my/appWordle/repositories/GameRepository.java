package com.my.appWordle.repositories;

import com.my.appWordle.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    void deleteById(Game existingGame);
}

