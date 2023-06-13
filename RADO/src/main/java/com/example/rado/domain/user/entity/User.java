package com.example.rado.domain.user.entity;

import com.example.rado.domain.board.entity.Board;
import com.example.rado.domain.board.entity.BoardComment;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private BoardComment boardComment;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Board> boardList = new  ArrayList<>();

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false, length = 40)
    private String password;

    @Column(name = "name", nullable = false, length = 4)
    private String name;

    @Builder
    public User(String userId, String password, String name){
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public void update(String userId, String password, String name){
        this.userId = userId;
        this.password = password;
        this.name = name;
    }
}
