package com.Boardpractice.Board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Boardpractice.Board.entity.Board;
import com.Boardpractice.Board.entity.User;
import com.Boardpractice.Board.service.BoardService;
import com.Boardpractice.Board.service.UserService;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public String main() {   // 메인홈페이지 
		return "main";
	}
	
	@GetMapping("/boardList")									// 게시판페이지에 리스트형식(페이징)로 출력
	public String boardList(Model model, 
			@PageableDefault(page = 0, size = 20, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable, String searchKeyword) {
		
		Page<Board> boardList = null;
		
		if(searchKeyword == null) {
			boardList = boardService.listBoard(pageable);	
		}
		else {
			boardList = boardService.searchBoard(searchKeyword, pageable);
		}
		
		int nowPage = boardList.getNumber() + 1;
		int startPage = Math.max(1, nowPage - 5);
		int endPage = Math.min(nowPage + 5, boardList.getTotalPages());

		model.addAttribute("boardList", boardList);
		model.addAttribute("nowPage",nowPage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		
		return "boardList";
	}
	
	@GetMapping("/boardDetail/{boardId}")								// 게시글 보기 
	public String getBoard(@PathVariable Long boardId, Model model) {	//@PathVariable이란?
																		// URL 경로에 있는 변수를 메소드의 파라미터로 가져오는데 사용된다.
																	
		Board board = boardService.getBoard(boardId);
		
		model.addAttribute("board",board);
		
		return "boardDetail";
	}
	
	@GetMapping("/boardInsert")											// 게시글작성 페이지이동
	public String insertBoard() {
		
		return "boardInsert";
	}
	
	@PostMapping("/board/insert")										//게시글 작성후 저장
	public String createBoard(Board board,HttpSession session) {
		
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser != null) {
			board.setBoardWriter(loginUser.getUserName());
			boardService.insertBoard(board);
			return "redirect:/boardList";
			
		} else {
			return "redirect:/login";
		}

	}

	
	@GetMapping("/boardUpdate/{boardId}")								//게시글 수정 페이지 이동
	public String updateGetBoard(@PathVariable Long boardId,Model model) {
		Board board = boardService.getBoard(boardId);
		
		model.addAttribute("board",board);
		
		return "boardUpdate";
	}
	

	@PostMapping("/board/update")											// 게시글 수정후 저장
	public String updateBoard(Long boardId, Board board) {
		
		boardService.updateBoard(board, boardId);
		
		return "redirect:/boardList";
		
	}
	
	
	@GetMapping("/boardDelete/{boardId}")								// 게시글 삭제
	public String deleteBoard(@PathVariable Long boardId) {
		
		boardService.deleteBoard(boardId);
		
		return "redirect:/boardList";
	}
	
	
	
	// ------------ 유저 컨트롤러
	
	@GetMapping("/register")
	public String registerGetUser() {
		
		return "register";
	}
	
	@GetMapping("/login")
	public String loginGetUser() {
		
		return "login";
	}
	
	
	@PostMapping("/user/register")
	public String registerUser(User user) {
		
		userService.saveUser(user);
		
		return "redirect:/login";
	}
	
	@PostMapping("/user/login")
	public String loginUser(User user, HttpSession session) {
		
		User foundUser = userService.findByUserNameAndUserPw(user.getUserName(), user.getUserPw());
		
		if(foundUser != null) {
			session.setAttribute("loginUser", foundUser);
			return "redirect:/boardList";
			
		}else {
			return "redirect:/login";
		}
		
	}
	
	
	
	
	
	
	
	
	
}
