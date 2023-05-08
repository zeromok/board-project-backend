package com.example.example.domain;

import lombok.Data;

@Data
public class BoardDTO {
    /*
        DTO(Data Transfer Object) : 데이터를 전달하는게 목적인 객체
        => 웹브라우저의 화면에서 사용자가 입력한 전송파라미터들을 수집해서 이를 필요로하는 어떤 계(웹3계층 : 표현/비지니스/영속성)에서든 전달할 목적
        => 하나의 객체로 수집 후 전달
        => 방향 : 표현계층에서 수집 -> 비즈니스계층, 영속성계층으로 전달
    */


    private Integer bno;
    /*
        값을 받아오는 컬럼이 아니다..PK -> 인서트할 때 필요없다.
        쓰지는 않지만 필드로 존재할 필요가 있다.
    */

    private String title;
    private String content;
    private String writer;
}
