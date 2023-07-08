package com.example.backend.domain.comment.domain;

import com.example.backend.domain.board.domain.Board;
import com.example.backend.domain.user.domain.User;
import com.example.backend.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Comment extends BaseEntity {

    private String comment;

    private String writer;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Builder
    public Comment(String comment, String writer, Board board, User user){
        this.comment = comment;
        this.writer = writer;
        this.board = board;
        this.user = user;
    }

    public void update(String comment){
        this.comment = comment;
    }
}
