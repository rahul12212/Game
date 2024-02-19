package com.example.game.controller;



import com.example.game.entity.GameSession;
import com.example.game.service.GameSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/game-sessions")
public class GameSessionController {

    @Autowired
    private GameSessionService gameSessionService;


    @GetMapping("/{name}")
    public ResponseEntity<GameSession> findByNameWithPlayers(@PathVariable String name) {
        log.info("Retrieving game session by name: {}", name);
        try {
            GameSession gameSession = gameSessionService.findByNameWithPlayers(name);
            if (gameSession == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(gameSession, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error retrieving game session: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
