package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.BoardFileDTO;
import com.codingrecipe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor //final이 붙은 필드만 가지고 생성자를 만들어주는 어노테이션
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save") //데이터 조회나 검색(읽기작업)에 자주 사용됨. 게시글 작성 화면 조회
    public String save(){
        return "save";
    }

    @PostMapping("/save")//데이터의 생성이나 수정과 같은 쓰기 작업에 사용됨, 게시글 작성 데이터 전송
    public String save(BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "redirect:/list"; //->게시글을 작성하고나면 목록으로 바로가기, 이럴 때 redirect라는 방식을 사용, 다시 재요청 다시/list 애를 요청하도록
    }
    //게시글 목록 출력 메서드
    @GetMapping("/list") //data를 화면으로 가져다줄수있는 객체, 가져다줄게있으면 model을 매개변수로 주면됨.
    public String findAll(Model model){ //model을 쓰는 이유는 컨트롤러에 메서드리턴타입을 String으로 가져갈수잇어서, restfulapi인경우에는 타입이 다 다를 것임.
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        System.out.println("boardDTOList = " + boardDTOList);

        return "list";

    }
    //게시글 조회 메서드
    @GetMapping("/{id}") //글번호가 계속 달라지므로 중괄호로 묶음. 게시글 조회할때 조회수를 처리해줘야함!
    public String findById(@PathVariable("id") Long id, Model model){
        //조회수 처리  예외처리도 나중에 해보기!
        boardService.updateHits(id);
        //상세내용 가져옴
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        System.out.println("boardDTO = " + boardDTO);
        //첨부파일이 있으면 내용을 가져오기
        if(boardDTO.getFileAttached() == 1){
            List<BoardFileDTO> boardFileDTOList = boardService.findFile(id);
            model.addAttribute("boardFileList", boardFileDTOList);

        }

        return "detail";

    }
//    먼저 데이터베이스에서 게시글을 조회하여 모델에 넣고, 이 정보를 수정 폼으로 전달하여 사용자에게 보여주는 것
    @GetMapping("update/{id}") //게시글 수정 메서드
    public String update(@PathVariable("id") Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "update";
    }

    @PostMapping("update/{id}") //사용자가 요청한걸 처리해주는 메서드, 게시글 수정한 내용을 반영하는 메서드
    public String update(BoardDTO boardDTO, Model model){
        boardService.update(boardDTO);
        BoardDTO dto = boardService.findById(boardDTO.getId()); //update가 반영된 데이터를 db에서 다시 가져와서 모델에 담고 디테일에 가져감.
        model.addAttribute("board", dto);
        return "detail";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.delete(id);
        return "redirect:/list";
    }
}
