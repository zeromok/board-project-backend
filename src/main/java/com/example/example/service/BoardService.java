package com.example.example.service;

import com.example.example.domain.BoardDTO;
import com.example.example.domain.BoardVO;
import com.example.example.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardService {
    // 1. 게시물 전체조회, 선택조회, 삭제, 업데이트(수정), 등록

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<BoardVO> getList() {
        List<BoardVO> data = boardMapper.selectAllList();
        return data;
    }

    public BoardVO findByID(int bno) {
        BoardVO data = boardMapper.findByBNO(bno);
        return data;
    }

    public boolean remove(int bno) {
        return boardMapper.delete(bno) == 1;
    }

    public boolean modify(BoardDTO dto) {
        return boardMapper.update(dto) == 1;
    }

    public boolean update(BoardDTO dto) {
        return boardMapper.insert(dto) == 1;
    }

}
