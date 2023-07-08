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

    private String title;

    private String content;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardImage> boardImages;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Builder
    public Board(String title, String content, User user, Category category) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.category = category;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
