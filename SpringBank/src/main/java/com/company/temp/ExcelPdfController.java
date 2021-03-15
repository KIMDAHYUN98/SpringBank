package com.company.temp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.temp.service.impl.EmpMapper;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ExcelPdfController {
	
	@Autowired EmpMapper empMapper;
	@Autowired DataSource dataSource;
	
	@RequestMapping("/getEmpExcel")
	public String getEmpExcel(Model model) {
		List<Map<String, Object>> list = empMapper.getEmpList();
		System.out.println(list.get(0));
		model.addAttribute("filename", "empList");
		model.addAttribute("headers", new String[] {"firstName", "lastName", "salary", "email"});
		model.addAttribute("datas", list);
		return "commonExcelView"; 							// jsp가 아닌 CommonExcelView로 이동한다
	}
	
	@RequestMapping("/pdf/empList")
	public String getPdfEmpList(Model model) {
		model.addAttribute("filename", "/reports/empList.jasper");
		return "pdfView";
	}
	
	@RequestMapping("/pdf/empList2")
	public String getPdfEmpList2(Model model, @RequestParam String dept) {
		HashMap<String,Object> map = new HashMap<>(); 
		map.put("P_DEPARTMENT_ID", dept);
		
		model.addAttribute("param", map);
		model.addAttribute("filename", "/reports/empList2.jasper");
		return "pdfView";
	}
	
	@RequestMapping("/pdf/empList3")
	public void getPdfEmpList3(HttpServletResponse response) throws Exception {
		Connection conn = dataSource.getConnection();
		//소스파일 컴파일하여 저장 : compileReportToFile
		 String jrxmlFile = getClass().getResource("/reports/empList3.jrxml").getFile();
		String jasperFile = JasperCompileManager.compileReportToFile( jrxmlFile );
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, null, conn);
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	}
}
