package com.example.game.service;

import com.example.game.entity.GameSession;
import com.example.game.entity.Player;
import com.example.game.repository.GameSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameSessionService {

    @Autowired
    private GameSessionRepository gameSessionRepository;



    public GameSession findByNameWithPlayers(String name) {
        log.info("Finding game session by name: {}", name);
        GameSession gameSession = gameSessionRepository.findByNameWithPlayers(name);
        if (gameSession == null) {
            log.warn("Game session not found: {}", name);
            return null;
        }

        return gameSession;
    }

    public void createGameSession(String name, Player player1, Player player2) {
        log.info("Creating game session: {}", name);


        GameSession gameSession = new GameSession();
        gameSession.setName(name);
        gameSession.setPlayer1(player1);
        gameSession.setPlayer2(player2);


        gameSessionRepository.save(gameSession);


    }


}
