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

    private String content;


    @Builder
    public Board(String content){
        this.content = content;
    }

    public void update(String content){
        this.content = content;
    }
}
