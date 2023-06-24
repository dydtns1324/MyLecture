package com.Boardpractice.Board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Long boardId;

	@Column(name = "board_title" ,nullable = false, length = 100)
	private String boardTitle; 
	
	@Column(name ="board_content" ,nullable = false, length = 500)
	private String boardContent;
	
	@Column(name = "board_writer", nullable = false)
	private String boardWriter;

=======

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardId;
	
	@Column(nullable = false, length = 50)
	private String boardTitle;
	
	@Column(nullable = false, length = 500)
	private String boardContent;
	
	@Column(length = 30)
	private String boardWriter;
	
>>>>>>> 59d2ae2bc3b80f32d432ac5096cc6f491ca54391
	private LocalDateTime boardRegdate = LocalDateTime.now();
	
	private int boardView = 0;
	
<<<<<<< HEAD
	
}































=======
}
>>>>>>> 59d2ae2bc3b80f32d432ac5096cc6f491ca54391
