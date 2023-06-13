package com.example.rado.domain.board.entity;

import com.example.rado.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "content")
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
