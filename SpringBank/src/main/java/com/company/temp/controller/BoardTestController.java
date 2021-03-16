package com.company.temp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.temp.vo.BoardTestVO;

@Controller
public class BoardTestController {
	// 1. 파라미터를 vo에 담아 view에서 title, writer를 출력되는 핸들러 작성하기
	
	@RequestMapping("/insertBoardTest")
	public String insertBoard(BoardTestVO vo) {
		System.out.println(vo);
		return "insertBoardResult";
	}
	
	// 2. 파라미터를 vo에 담아 vo 자체를 리턴하는 핸들러 작성 
	@RequestMapping("/AjaxInsertBoard")
	@ResponseBody
	public BoardTestVO AjaxInsertBoard(BoardTestVO vo) {
		return vo;
	}
	
	// 3. 
}
