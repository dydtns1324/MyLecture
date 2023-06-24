package com.Boardpractice.Board.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Boardpractice.Board.entity.Board;
import com.Boardpractice.Board.entity.BoardImage;

@Repository
public interface BoardImageRepository extends JpaRepository<BoardImage, Long>{

	List<BoardImage> findByBoard(Board board);
	
	
	@Transactional
	void deleteByBoard_BoardId(Long boardId);
}
