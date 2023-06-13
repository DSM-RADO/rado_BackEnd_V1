package com.example.rado.domain.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class BoardComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "comment", length = 100)
    private String comment;

    @Builder
    public BoardComment(String comment, Board board){
        this.comment = comment;
        this.board = board;
    }

    public void update(String comment){
        this.comment = comment;
    }
}
