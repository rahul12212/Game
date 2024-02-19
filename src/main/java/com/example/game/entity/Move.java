package com.example.game.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "moves")
@Getter
@Setter
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int moveColumn;


    private Integer row;

    @ManyToOne
    @JoinColumn(name = "game_session_id")
    private GameSession gameSession;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
}
