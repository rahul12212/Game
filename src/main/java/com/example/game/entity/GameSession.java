package com.example.game.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "game_sessions")
@Getter
@Setter
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "player1_id")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2_id")
    private Player player2;

    @OneToMany(mappedBy = "gameSession")
    private List<Move> moves;

    public void setStatus(String newStatus) {

    }

    public boolean isActive() {
        return false;
    }
}
