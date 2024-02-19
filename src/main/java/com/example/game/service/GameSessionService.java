package com.example.game.service;

import com.example.game.entity.GameSession;
import com.example.game.entity.Move;
import com.example.game.entity.Player;
import com.example.game.repository.GameSessionRepository;
import com.example.game.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameSessionService {

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public GameSession findByNameWithPlayers(String name) {
        log.info("Finding game session by name: {}", name);
        return gameSessionRepository.findByNameWithPlayers(name);
    }

    public GameSession createGameSession(String name, Long player1Id, Long player2Id) {
//        log.info("Creating game session: {}", name);
        System.out.println("Test");
        Player player1 = playerRepository.findById(player1Id)
                .orElseThrow(() -> new IllegalArgumentException("Player with ID " + player1Id + " not found"));
        Player player2 = playerRepository.findById(player2Id)
                .orElseThrow(() -> new IllegalArgumentException("Player with ID " + player2Id + " not found"));

        GameSession gameSession = new GameSession();
        gameSession.setName(name);
//        gameSession.setPlayer1(player1);
//        gameSession.setPlayer2(player2);


        return gameSessionRepository.save(gameSession);
    }



    public void addMove(Long gameSessionId, Move move) {
        log.info("Adding move to game session with ID: {}", gameSessionId);
        GameSession gameSession = gameSessionRepository.findById(gameSessionId)
                .orElseThrow(() -> new IllegalArgumentException("Game session not found with ID: " + gameSessionId));
        move.setGameSession(gameSession);
        gameSession.getMoves().add(move);
        gameSessionRepository.save(gameSession);
    }

    public void addPlayer(Player player) {
        log.info("Adding player: {}", player.getName());
        playerRepository.save(player);
    }

    public void createGameSession(GameSession gameSession) {
        log.info("Creating game session: {}", gameSession.getName());
        System.out.println(gameSession);
        gameSessionRepository.save(gameSession);

    }
}
