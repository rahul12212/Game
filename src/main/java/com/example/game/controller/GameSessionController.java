package com.example.game.controller;

import com.example.game.entity.GameSession;
import com.example.game.entity.Move;
import com.example.game.entity.Player;
import com.example.game.service.GameSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game-sessions")
public class GameSessionController {

    private static final Logger log = LoggerFactory.getLogger(GameSessionController.class);

    @Autowired
    private GameSessionService gameSessionService;

    @GetMapping("/{name}")
    public ResponseEntity<?> findByNameWithPlayers(@PathVariable String name) {
        log.info("Retrieving game session by name: {}", name);
        try {
            GameSession gameSession = gameSessionService.findByNameWithPlayers(name);
            if (gameSession == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(gameSession);
        } catch (Exception e) {
            log.error("Error retrieving game session: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving game session");
        }
    }

    @PostMapping("/players")
    public ResponseEntity<?> addPlayer(@RequestBody Player player) {
        try {
            gameSessionService.addPlayer(player);
//            System.out.println("print");
            return ResponseEntity.status(HttpStatus.CREATED).body("Player added successfully");
        } catch (Exception e) {
            log.error("Error adding player: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding player");
        }
    }

    @PostMapping
    public ResponseEntity<?> createGameSession(@RequestBody GameSession gameSession) {
        try {
//            System.out.println(gameSession);
            gameSessionService.createGameSession(gameSession);

            return ResponseEntity.status(HttpStatus.CREATED).body("Game session created successfully");
        } catch (Exception e) {
            log.error("Error creating game session: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating game session");
        }
    }

    @PostMapping("/{gameSessionId}/moves")
    public ResponseEntity<?> addMove(@PathVariable Long gameSessionId, @RequestBody Move move) {
        try {
            gameSessionService.addMove(gameSessionId, move);
            return ResponseEntity.status(HttpStatus.CREATED).body("Move added successfully");
        } catch (Exception e) {
            log.error("Error adding move: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding move");
        }
    }
}
