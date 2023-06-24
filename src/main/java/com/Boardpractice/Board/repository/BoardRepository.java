package com.Boardpractice.Board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Boardpractice.Board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
	
	Page<Board> findByboardTitleContaining(Pageable pageable, String searchKeyword);
}

