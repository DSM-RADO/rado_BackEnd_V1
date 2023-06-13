package com.example.rado.domain.board.entity;

import com.example.rado.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardComment> commentList = new ArrayList<>();

    @Column(name = "content", length = 100)
    private String content;

    @Builder
    public Board(String content, User user){
        this.user = user;
        this.content = content;
    }

    public void update(String content){
        this.content = content;
    }
}
