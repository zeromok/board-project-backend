package com.example.example.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.example.domain.BoardDTO;
import com.example.example.domain.BoardVO;
import com.example.example.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	BoardService boardService;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	@DisplayName( "모든 게시물 조회에 성공한다." )
	void list() throws Exception {
		String url = "/board/list";
		BoardVO boardVO = new BoardVO( 1, "title", "content", "writer", new Date(), new Date() );
		List< BoardVO > result = new ArrayList<>();
		result.add( boardVO );

		// Mockito.when() : 특정 메서드가 호출될 때 Mock 객체의 동작을 정의
		// boardService.getList() 가 호출될 때 Mockito 가 호출을 가로채 .thenReturn() 단계의 지정된 값을 반환
		when( boardService.getList() )
			.thenReturn( result );

		ResultActions resultActions = mockMvc.perform( get( url ) )
											 .andExpect( status().isOk() )
											 .andExpect( jsonPath( "$.[*].bno" ).value( 1 ) )
											 .andDo( print() );
	}

	@Test
	@DisplayName( "bno 를 이용한 게시물 조회에 성공한다." )
	void findByID() throws Exception {
		String url = "/board/inquiry/2";
		BoardVO temp = new BoardVO( 2, "title", "content", "writer", new Date(), new Date() );

		when( boardService.findByID( 2 ) )
			.thenReturn( temp );

		ResultActions result = mockMvc.perform( get( url ) )
									  .andExpect( status().isOk() )
									  .andExpect( jsonPath( "$.bno" ).value( 2 ) )
									  .andDo( print() );
	}

	@Test
	@DisplayName( "bno 를 이용해 게시물 삭제에 성공한다." )
	void removeByBNO() throws Exception {
		String url = "/board/remove";
		BoardDTO temp = new BoardDTO();
		temp.setBno( 3 );
		temp.setTitle( "title" );
		temp.setWriter( "writer" );
		temp.setContent( "content" );
		String requestBody = objectMapper.writeValueAsString( temp );

		when( boardService.remove( temp.getBno() ) )
			.thenReturn( true );

		ResultActions result = mockMvc.perform( post( url ).contentType( MediaType.APPLICATION_JSON ).content( requestBody ) )
									  .andExpect( status().isOk() )
									  .andExpect( content().string( "true" ) )
									  .andDo( print() );

	}

	@Test
	@DisplayName( "게시물 등록에 성공한다." )
	void register() throws Exception {
		String url = "/board/register";
		BoardDTO temp = new BoardDTO();
		temp.setBno( 4 );
		temp.setTitle( "title" );
		temp.setWriter( "writer" );
		temp.setContent( "content" );
		String requestBody = objectMapper.writeValueAsString( temp );

		when( boardService.update( temp ) ).thenReturn( true );

		ResultActions result = mockMvc.perform(
										  post( url ).contentType( MediaType.APPLICATION_JSON ).content( requestBody ) )
									  .andExpect( status().isOk() )
									  .andExpect( content().string( "true" ) )
									  .andDo( print() );

	}

	@Test
	@DisplayName( "게시물 수정에 성공한다." )
	void modify() throws Exception {
		String url = "/board/modify";
		BoardDTO temp = new BoardDTO();
		temp.setBno( 5 );
		temp.setTitle( "title" );
		temp.setWriter( "writer" );
		temp.setContent( "content" );
		String requestBody = objectMapper.writeValueAsString( temp );

		when( boardService.modify( temp ) ).thenReturn( true );

		ResultActions result = mockMvc.perform(
										  post( url ).contentType( MediaType.APPLICATION_JSON ).content( requestBody ) )
									  .andExpect( status().isOk() )
									  .andExpect( content().string( "true" ) )
									  .andDo( print() );
	}
}