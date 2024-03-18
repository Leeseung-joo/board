package com.codingrecipe.board.repository;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.BoardFileDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sql; //애를 이용해서 mapper를 호출

    public BoardDTO save(BoardDTO boardDTO) {
        sql.insert("Board.save", boardDTO); //여기서 Board는 mapper의 namespace를 가리킴,save는 쿼리문을 담고있는 태그를 의미 -->id가 save인걸 의미
        return boardDTO;
    }                                    //1개만 넘길수잇음, 넘겨야 하는 객체를 넣을 수 있음.


    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits", id);
    }

    public BoardDTO findById(Long id) { // 반환할게 하나면 이거 쓰고 여러개면 selectList를 사용
        return sql.selectOne("Board.findById", id);
    }

    public void update(BoardDTO boardDTO) {
        sql.update("board.update", boardDTO);
    }

    public void delete(Long id) {
        sql.delete("Board.delete", id);
    }

    public void saveFile(BoardFileDTO boardFileDTO) {
        sql.insert("Board.saveFile", boardFileDTO);
    }

    public List<BoardFileDTO> findFile(Long id) {
        return sql.selectList("Board.findFile", id);


    }
}

