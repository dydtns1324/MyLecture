package com.Boardpractice.Board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	private LocalDateTime boardRegdate = LocalDateTime.now();
	
	private int boardView = 0;
	
}
