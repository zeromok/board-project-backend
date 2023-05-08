package com.example.example.domain;

import lombok.Value;

import java.util.Date;

@Value
public class BoardVO {
    /*
        VO(Value Object) : 값 객체라고 부르며 데이터를 전달하는게 목적인 객체
        => 데이터베이스의 1개의 테아블의 1개의 레코드를 저장(immutable, read-only)하는게 역할
        => 이를 필요로하는 어떤 계층(웹3계층 : 표현/비지니스/영속성)에서든 전달할 목적
        => 방향 : 영속성계층에서 수집 -> 비즈니스계층, 표현계층으로 전달
    */

    private Integer bno;
    private String title;
    private String content;
    private String writer;

    /*
        정보통신법의 요구사항에 따라, 중요 데이터 테이블에는
        아래와 같이, 레코드가 최초 생성된 시각과 최종 수정된 시각을 유지할 수 있게 컬럼 정의
    */
    private Date insertTs;
    private Date updateTs;
}
