package com.Boardpractice.Board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Boardpractice.Board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
<<<<<<< HEAD
	
	Page<Board> findByboardTitleContaining(Pageable pageable, String searchKeyword);
}

=======

	Page<Board> findByboardTitleContaining(Pageable pageable, String searchKeyword);
	
}
>>>>>>> 59d2ae2bc3b80f32d432ac5096cc6f491ca54391
