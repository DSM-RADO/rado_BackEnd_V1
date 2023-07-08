package com.example.rado.domain.board.domain;

import com.example.rado.domain.comment.domain.Comment;
import com.example.rado.domain.user.domain.User;
import com.example.rado.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "tbl_board")
public class Board extends BaseEntity {

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Builder
    public Board(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public void updatePost( String content) {
        this.content = content;
    }
}
