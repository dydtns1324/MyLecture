package com.Boardpractice.Board.service;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 59d2ae2bc3b80f32d432ac5096cc6f491ca54391
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Boardpractice.Board.entity.Board;
import com.Boardpractice.Board.repository.BoardRepository;

@Service
public class BoardService {

<<<<<<< HEAD
	@Autowired
	private BoardRepository boardRepository;
	
=======
	private BoardRepository boardRepository;
	
	@Autowired
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
>>>>>>> 59d2ae2bc3b80f32d432ac5096cc6f491ca54391
	public void insertBoard(Board board) {
		
		boardRepository.save(board);
	}
	
<<<<<<< HEAD
	public void deleteBoard(Long boardNo) { 
=======
	public void deleteBoard(Long boardNo) {
>>>>>>> 59d2ae2bc3b80f32d432ac5096cc6f491ca54391
		boardRepository.deleteById(boardNo);
	}
	
	public Board getBoard(Long boardNo) {
		
		Board board = boardRepository.findById(boardNo)
				.orElseThrow(() -> new NoSuchElementException("존재하지않는 게시글 : " + boardNo)); // optional 형태이기때문에 orElseThrow로 예외처리,
		
<<<<<<< HEAD
		//JPA에서 지원하는 찾는메소드의 형태는 "Optional"형태다.
		
		board.setBoardView(board.getBoardView() + 1);
		
		boardRepository.save(board);
		
		return board;
=======
		board.setBoardView(board.getBoardView() + 1);
		
		boardRepository.save(board); 
		
		return board;
		
		
>>>>>>> 59d2ae2bc3b80f32d432ac5096cc6f491ca54391
	}
	
	public Board updateBoard(Board board, Long boardNo) {
		
		Board updateBoard = boardRepository.findById(boardNo)
<<<<<<< HEAD
				.orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글 : " + boardNo));
=======
				.orElseThrow(() -> new NoSuchElementException("No board found with id: " + boardNo));
>>>>>>> 59d2ae2bc3b80f32d432ac5096cc6f491ca54391
		
		updateBoard.setBoardTitle(board.getBoardTitle());
		updateBoard.setBoardContent(board.getBoardContent());
		
		boardRepository.save(updateBoard);
		
		return updateBoard;
	}
	
	public Page<Board> listBoard(Pageable pageable){
		
		return boardRepository.findAll(pageable);
		
	}
	
	public Page<Board> searchBoard(String searchKeyword, Pageable pageable) {
		
		return boardRepository.findByboardTitleContaining(pageable, searchKeyword);
	}
<<<<<<< HEAD
	
	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
=======

>>>>>>> 59d2ae2bc3b80f32d432ac5096cc6f491ca54391
}
