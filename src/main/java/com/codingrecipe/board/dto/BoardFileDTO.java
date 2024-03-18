package com.codingrecipe.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDTO {
    private Long id;
    private Long boardId; //이 파일은 어떤 게시글에 저장된 파일인지 같이 기록해야 하므로 작성.
    private String originalFileName; //원본 파일 이름
    private String storedFileName; ;//저장을 위한 파일 이름
    //백엔드 입장에서 이 내사진이 언제 내사진인지 모르므로 이런 방식으로 사용
}
