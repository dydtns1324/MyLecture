package com.Boardpractice.Board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;

import com.Boardpractice.Board.entity.Board;
import com.Boardpractice.Board.entity.BoardImage;
import com.Boardpractice.Board.entity.User;
import com.Boardpractice.Board.service.BoardImageService;
import com.Boardpractice.Board.service.BoardService;
import com.Boardpractice.Board.service.UserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardImageService boardImageService;
	
	@Autowired
	private UserService userService;

	
	@GetMapping("/")
	public String main() {   // 메인홈페이지 
		return "main";
	}
	
	@GetMapping("/boardList")									// 게시판페이지에 리스트형식(페이징)로 출력
	public String boardList(Model model, 
			@PageableDefault(page = 0, size = 20, sort = "boardId", direction = Sort.Direction.DESC) 
			Pageable pageable, String searchKeyword) {
		
		Page<Board> boardList = null;
		
		//List의 기능도하면서 페이징의 기능도한다 = Page
		
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
	
	

	
	@GetMapping("/boardInsert")											// 게시글작성 페이지이동
	public String insertBoard() {
		
		return "boardInsert";
	}
	
	@PostMapping("/board/insert")										//게시글 작성후 저장
	public String createBoard(Board board,HttpSession session, MultipartFile file) {
		
		log.info(file);
		
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser != null) {
			board.setBoardWriter(loginUser.getUserName());
			boardService.insertBoard(board);
			
			if(file != null && !file.isEmpty()) {
				
				String fileOrigin = file.getOriginalFilename();
				String fileori = fileOrigin.substring(fileOrigin.lastIndexOf("."));

				String fileName = UUID.randomUUID().toString() + fileori;
				
				System.out.println(fileName);
				String filePath = "C:/images/" + fileName;
			

				
				try {
					
						file.transferTo(new File(filePath));
					
				} catch (IOException ie) {
					ie.getStackTrace();
				}
				
				// boardService.insertBoard(board);
				
				BoardImage boardImage = new BoardImage();
				
				boardImage.setBoard(board);
				boardImage.setBoardFileName(fileName);
				boardImage.setBoardFilePath(filePath);
				boardImage.setBoardFileOrigin(fileOrigin);
				
				boardImageService.saveImage(boardImage);
				
			}
			return "redirect:/boardList";
										
		} else {
			return "redirect:/login";
		}

	}
	
	@GetMapping("/boardDetail/{boardId}")								// 게시글 보기 
	public String getBoard(@PathVariable Long boardId, Model model) {	//@PathVariable이란?
																		// URL 경로에 있는 변수를 메소드의 파라미터로 가져오는데 사용된다.							
		Board board = boardService.getBoard(boardId);
		List<BoardImage> boardImageList = boardImageService.imageList(board);
		
		
		model.addAttribute("board",board);
		model.addAttribute("images", boardImageList);
		
		return "boardDetail";
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
		
//		PathVariable = "url에 직접 파라미터로 들어가는 변수"
//				@PathVariable 타입명 변수명
		
		boardImageService.deleteImagesByBoardId(boardId);
		
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
