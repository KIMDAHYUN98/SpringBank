package com.company.board.service;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardVO {
	
	private String seq;
	private String title;
	private String writer;
	private String content;
	private String filename;
	// multiple로 사용할 경우
	private MultipartFile[] uploadFile;
}
