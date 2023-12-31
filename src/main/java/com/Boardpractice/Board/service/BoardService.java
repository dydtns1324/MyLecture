package com.Boardpractice.Board.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Boardpractice.Board.entity.Board;
import com.Boardpractice.Board.repository.BoardRepository;

@Service
public class BoardService {


	@Autowired
	private BoardRepository boardRepository;
	

	public void insertBoard(Board board) {
		
		boardRepository.save(board);
	}
	
	public void deleteBoard(Long boardNo) { 
		boardRepository.deleteById(boardNo);
	}
	
	public Board getBoard(Long boardNo) {
		
		Board board = boardRepository.findById(boardNo)
				.orElseThrow(() -> new NoSuchElementException("존재하지않는 게시글 : " + boardNo)); // optional 형태이기때문에 orElseThrow로 예외처리,
	
		//JPA에서 지원하는 찾는메소드의 형태는 "Optional"형태다.
		
		board.setBoardView(board.getBoardView() + 1);
		
		boardRepository.save(board);
		
		return board;

	}
	
	public Board updateBoard(Board board, Long boardNo) {
		
		Board updateBoard = boardRepository.findById(boardNo)
				.orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글 : " + boardNo));
		
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
	
}
