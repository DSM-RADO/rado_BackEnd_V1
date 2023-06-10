package com.example.rado.domain.board.controller;

import com.example.rado.domain.board.controller.dto.request.BoardAddRequest;
import com.example.rado.domain.board.controller.dto.request.BoardRequest;
import com.example.rado.domain.board.controller.dto.response.BoardListResponse;
import com.example.rado.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("")
    public void addBoard(
            @RequestBody BoardAddRequest request
    ) {
        boardService.boardAdd(request);
    }

    @DeleteMapping("/remove/{boardId}")
    public void removeBoard(
            @PathVariable Long boardId
    ) {
        boardService.boardRemove(boardId);
    }

    @PutMapping("/modify/{boardId}")
    public void modifyBoard(
            @RequestBody BoardRequest request,
            @PathVariable Long boardId
            ) {
        boardService.boardModify(request, boardId);
    }

    @GetMapping("/list")
    public BoardListResponse listBoard(){
        return boardService.boardList();
    }
}
