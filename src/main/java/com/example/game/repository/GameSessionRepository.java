package com.example.game.repository;

import com.example.game.entity.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface GameSessionRepository extends JpaRepository<GameSession, Long> {

    @Query(nativeQuery = true,
            value = "SELECT gs.id, gs.name, gs.start_time, " +
                    "p1.name AS player1_name, p2.name AS player2_name " +
                    "FROM game_sessions gs " +
                    "JOIN players p1 ON gs.player1_id = p1.id " +
                    "JOIN players p2 ON gs.player2_id = p2.id " +
                    "WHERE gs.name = :name")
    GameSession findByNameWithPlayers(String name);
}
