package com.example.example.controller;

import com.example.example.advice.GlobalAdvice;
import com.example.example.domain.BoardDTO;
import com.example.example.domain.BoardVO;
import com.example.example.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Board Controller", description = "게시판")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @Operation(summary = "모든 게시물 조회", description = "모든 게시물을 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "조회성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BoardVO.class)))),
            @ApiResponse(responseCode = "400",
            description = "잘못된 요청"),
            @ApiResponse(responseCode = "500",
            description = "서버오류")
    })
    @GetMapping("/list")
    public ResponseEntity<List<BoardVO>> list() throws GlobalAdvice {
        return new ResponseEntity<>(boardService.getList(), HttpStatus.OK);
    }


    @Operation(summary = "bno를 이용한 게시물 조회", description = "bno를 이용한 게시물 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "조회성공",
                    content = @Content(schema = @Schema(implementation = BoardVO.class))),
            @ApiResponse(responseCode = "400",
                    description = "잘못된 요청"),
            @ApiResponse(responseCode = "500",
                    description = "서버오류")
    })
    @GetMapping("/inquery/{bno}")
    public ResponseEntity<BoardVO> findByID(@PathVariable("bno") int bno) throws GlobalAdvice {
        return new ResponseEntity<>(boardService.findByID(bno), HttpStatus.OK);
    }


    @Operation(summary = "bno를 이용한 게시물 삭제", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "삭제성공",
                    content = @Content(schema = @Schema(implementation = boolean.class))),
            @ApiResponse(responseCode = "400",
                    description = "잘못된 요청"),
            @ApiResponse(responseCode = "500",
                    description = "서버오류")
    })
    @PostMapping("/remove")
    public ResponseEntity<Boolean> removeByBNO(@RequestBody BoardDTO dto) throws GlobalAdvice {
        return new ResponseEntity<>(boardService.remove(dto.getBno()), HttpStatus.OK);
    }


    @Operation(summary = "게시물 등록", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "등록성공",
                    content = @Content(schema = @Schema(implementation = boolean.class))),
            @ApiResponse(responseCode = "400",
                    description = "잘못된 요청"),
            @ApiResponse(responseCode = "500",
                    description = "서버오류")
    })
    @PostMapping("/regster")
    public ResponseEntity<Boolean> register(@RequestBody BoardDTO dto) throws GlobalAdvice {
        return new ResponseEntity<>(boardService.update(dto), HttpStatus.OK);
    }


    @Operation(summary = "게시물 수정", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "수정성공",
                    content = @Content(schema = @Schema(implementation = boolean.class))),
            @ApiResponse(responseCode = "400",
                    description = "잘못된 요청"),
            @ApiResponse(responseCode = "500",
                    description = "서버오류")
    })
    @PostMapping("/modify")
    public ResponseEntity<Boolean> modify(@RequestBody BoardDTO dto) throws GlobalAdvice {
        return new ResponseEntity<>(boardService.modify(dto), HttpStatus.OK);
    }

}
