package com.example.example.service;

import com.example.example.advice.GlobalAdvice;
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

    public List<BoardVO> getList() throws GlobalAdvice {
        return boardMapper.selectAllList();
    }

    public BoardVO findByID(int bno) throws GlobalAdvice {
        return boardMapper.findByBNO(bno);
    }

    public boolean remove(int bno) throws GlobalAdvice {
        return boardMapper.delete(bno) == 1;
    }

    public boolean modify(BoardDTO dto) throws GlobalAdvice {
        return boardMapper.update(dto) == 1;
    }

    public boolean update(BoardDTO dto) throws GlobalAdvice {
        return boardMapper.insert(dto) == 1;
    }

}
