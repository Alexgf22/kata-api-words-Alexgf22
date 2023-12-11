package com.my.appWordle.repositories;

import com.my.appWordle.models.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Matches, Long> {

}
