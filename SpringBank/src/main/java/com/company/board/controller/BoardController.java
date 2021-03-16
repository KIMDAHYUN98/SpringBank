package com.company.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.company.board.service.BoardVO;
import com.company.board.service.impl.BoardMapper;
import com.company.common.FileRenamePolicy;

@Controller
public class BoardController {

	@Autowired
	BoardMapper boardMapper;

	@GetMapping("/insertBoard")
	public String insertBoardForm() {
		return "board/insertBoard";
	}

	@PostMapping("/insertBoard")
	public String insertBoardProc(BoardVO vo) throws IllegalStateException, IOException {
		System.out.println("vo : " + vo);
		// 첨부파일 처리
		MultipartFile[] files = vo.getUploadFile();
		String filenames = "";
		boolean start = true;
		for(MultipartFile file : files) {
		//파일명 -> 중복체크
			if (file != null && !file.isEmpty() && file.getSize() > 0) { // 업로드된 파일 크기
				String filename = file.getOriginalFilename(); // 업로드된 파일명
				// 파일 명 중복 체크 -> rename된 파일명을 보낼 것 이다.
				File rename = FileRenamePolicy.rename(new File("C:\\upload", filename));
				// 파일 이름만 filenames에 담는다.
				if(! start) { // true가 아니면
					filenames += ", ";
				} else {
					start = false;
				}
				filenames += rename.getName();
				// 임시폴더에서 업로드 폴더로 파일 이동
				file.transferTo(rename); // 업로드 위치
			}
		}
		vo.setFilename(filenames); // vo 업로드된 파일명만 담아서 DB에 저장
		// 등록 서비스 호출
		boardMapper.insertBoard(vo);
		return "redirect:/getBoard?seq=" + vo.getSeq();
	}

	@GetMapping("/getBoard")
	public String getBoard(BoardVO vo, Model model) {
		model.addAttribute("board", boardMapper.getBoard(vo));
		return "board/getBoard";
	}

	@GetMapping("/fileDown")
	public void fileDown(BoardVO vo, HttpServletResponse response) throws IOException {
		vo = boardMapper.getBoard(vo);
		File file = new File("c:/upload", vo.getFilename());
		
		if (file.exists()) { // 파일이 존재하는 경우
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + URLEncoder.encode(vo.getFilename(), "utf-8") + "\""); // vo에 있는 파일명

			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(file)); // 파일 객체를 담는다. inputStream 으로 읽어서
				out = new BufferedOutputStream(response.getOutputStream()); // outStream으로 복사함 => 다운로드
				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
			} finally {
				in.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}

		} else {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append("<script>")
								.append("alert('file not Found(파일 없음)');")
								.append("history.go(-1);") // 이전 페이지로 이동
								.append("</script>");
		}
	}
	
	@GetMapping("/fileNameDown")
	public void fileNameDown(BoardVO vo, HttpServletResponse response) throws IOException {
		File file = new File("c:/upload", vo.getFilename());
		
		if (file.exists()) { // 파일이 존재하는 경우
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + URLEncoder.encode(vo.getFilename(), "utf-8") + "\""); // vo에 있는 파일명

			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(file)); // 파일 객체를 담는다. inputStream 으로 읽어서
				out = new BufferedOutputStream(response.getOutputStream()); // outStream으로 복사함 => 다운로드
				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
			} finally {
				in.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}

		} else {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append("<script>")
								.append("alert('file not Found(파일 없음)');")
								.append("history.go(-1);") // 이전 페이지로 이동
								.append("</script>");
		}
	}

}
