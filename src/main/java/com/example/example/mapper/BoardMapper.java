package com.example.example.mapper;

import com.example.example.advice.GlobalAdvice;
import com.example.example.domain.BoardDTO;
import com.example.example.domain.BoardVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("""
            SELECT *
            FROM (
              SELECT
                tbl_board.*,
                ROW_NUMBER() OVER (ORDER BY bno DESC) AS row_num
              FROM tbl_board
            )
            WHERE row_num BETWEEN 1 AND 100
            """)
    List<BoardVO> selectAllList() throws GlobalAdvice;

    @Select("SELECT * FROM tbl_board WHERE bno = #{bno}")
    BoardVO findByBNO(int dno) throws GlobalAdvice;

    @Delete("DELETE FROM tbl_board WHERE bno = #{bno}")
    Integer delete(int bno) throws GlobalAdvice;

    @Update("""
            UPDATE tbl_board
                    SET
                    title = #{title},
                    content = #{content},
                    writer = #{writer},
                    update_ts = CURRENT_DATE
                    WHERE
                    bno = ${bno}
            """)
    Integer update(BoardDTO dto) throws GlobalAdvice;

    @Insert("""
            INSERT INTO tbl_board
                    (title, content, writer)
                    VALUES (#{title}, #{content}, #{writer})
            """)
    Integer insert(BoardDTO dto) throws GlobalAdvice;
}
