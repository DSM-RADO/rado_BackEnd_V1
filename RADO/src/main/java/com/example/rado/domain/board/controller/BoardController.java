package com.example.rado.domain.board.controller;

import com.example.rado.domain.board.controller.dto.request.BoardAddRequest;
import com.example.rado.domain.board.controller.dto.request.BoardRequest;
import com.example.rado.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public void addBoard(
            @RequestBody @Valid BoardAddRequest request
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
            @RequestBody @Valid BoardRequest request,
            @PathVariable Long boardId
            ) {
        boardService.boardModify(request, boardId);
    }



}
