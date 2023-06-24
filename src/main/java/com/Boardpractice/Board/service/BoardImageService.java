package com.Boardpractice.Board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Boardpractice.Board.entity.Board;
import com.Boardpractice.Board.entity.BoardImage;
import com.Boardpractice.Board.repository.BoardImageRepository;

@Service
public class BoardImageService {

	@Autowired
	private BoardImageRepository boardImageRepository;
	
	
	public List<BoardImage> imageList(Board board) {
		
		return boardImageRepository.findByBoard(board);
	}
	
	public void saveImage(BoardImage boardImage) {
		
		boardImageRepository.save(boardImage);
	}
	
	public void deleteImagesByBoardId(Long boardId) {
		
		boardImageRepository.deleteByBoard_BoardId(boardId);
	}
	
}
