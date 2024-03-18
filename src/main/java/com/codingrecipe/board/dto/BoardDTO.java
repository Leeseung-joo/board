package com.codingrecipe.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class BoardDTO {
    private Long id;  //글번호,게시글 비밀번호,작성자,게시글 제목,내용,조회수,작성시간
    private String boardPass;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String createdAt;
    private int fileAttached; //첨부가 됬는지 확인
    private List<MultipartFile> boardFile; //파일 자체를 담을 공간
}
