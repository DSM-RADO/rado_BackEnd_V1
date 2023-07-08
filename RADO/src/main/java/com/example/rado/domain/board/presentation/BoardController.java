package com.example.backend.domain.board.presentation;

import com.example.backend.domain.board.enums.Category;
import com.example.backend.domain.board.presentation.dto.request.CreateBoardRequest;
import com.example.backend.domain.board.presentation.dto.request.UpdateBoardRequest;
import com.example.backend.domain.board.presentation.dto.response.QueryBoardDetailsResponse;
import com.example.backend.domain.board.presentation.dto.response.QueryBoardListResponse;
import com.example.backend.domain.board.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Board", description = "Board API 입니다.")
@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {
    private final CreateBoardService createPostService;
    private final QueryBoardListService queryPostListService;
    private final QueryBoardCategoryListService queryPostCategoryListService;
    private final QueryBoardDetailService queryPostDetailService;
    private final ModifyBoardService modifyPostService;
    private final DeleteBoardService deletePostService;

    @Operation(summary = "글 생성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void createPost(
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @RequestPart(value = "request") @Valid CreateBoardRequest request
    ) {
        createPostService.execute(images, request);
    }

    @Operation(summary = "글 리스트")
    @GetMapping("/list")
    public List<QueryBoardListResponse> postList() {
        return queryPostListService.execute();
    }

    @Operation(summary = "글 카테고리 리스트")
    @GetMapping("/list/category")
    public List<QueryBoardListResponse> postCategoryList(@RequestParam("value") Category category) {
        return queryPostCategoryListService.execute(category);
    }

    @Operation(summary = "글 자세히 보기")
    @GetMapping("/detail/{board_id}")
    public QueryBoardDetailsResponse postDetail(@PathVariable(name = "board_id") Long id) {
        return queryPostDetailService.execute(id);
    }

    @Operation(summary = "글 수정")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/modify/{board_id}")
    public void modifyPost(
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @RequestPart(value = "request") @Valid UpdateBoardRequest request,
            @PathVariable(name = "board_id") Long id
    ) {
        modifyPostService.execute(images, request, id);
    }

    @Operation(summary = "글 삭제")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{board_id}")
    public void deletePost(@PathVariable(name = "board_id") Long id) {
        deletePostService.execute(id);
    }
}
