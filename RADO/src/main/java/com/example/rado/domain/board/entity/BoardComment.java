package com.example.rado.domain.board.entity;

import com.example.rado.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "comment", length = 123)
    private String comment;

    @Builder
    public BoardComment(String comment, Board board, User user){
        this.comment = comment;
        this.board = board;
        this.user = user;
    }

    public void update(String comment){
        this.comment = comment;
    }
}
