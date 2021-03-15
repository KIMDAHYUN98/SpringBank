package com.company.temp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.company.temp.vo.TestVO;

@Controller
public class TestController {

	// [파라미터를 받을 경우]

	// 1. get, Command 객체에 담는다.(=vo)
	@RequestMapping("/getTest1")
	public String getTest1(TestVO vo) {
		System.out.println("vo의 값 : " + vo);
		return "home";
	}

	// 2. vo 대신에 파라미터만 넘길 경우 request.getParameter()
	@RequestMapping("/getTest2")
	public String getTest2(@RequestParam String firstName, @RequestParam int salary) {

		System.out.println(firstName + " : " + salary);
		return "home";
	}

	// 3. 배열로 파라미터 값을 받을 경우 request.getParameterValues()
	@RequestMapping("/getTest3")
	public String getTest3(@RequestParam("hobby") String[] hobbys) {

		for (String hobby : hobbys) {
			System.out.println("취미 : " + hobby);
		}
		return "home";
	}

	// 4. JSON 구조로 값을 받을 경우
	@RequestMapping("/getTest4")
	public String getTest4(@RequestBody List<Map> vo) {
		System.out.println("json : " + vo);
		return "home";
	}

	// 5. PathVariable
	@RequestMapping("/getTest5/{firstName}/{salary}")
	public String getTest5(@PathVariable String firstName, 
						   @PathVariable int salary, 
						   @ModelAttribute("tvo") TestVO vo,
			Model model) {
		vo.setFirstName(firstName);
		vo.setSalary(salary);
		model.addAttribute("firstName", firstName);
		return "test";
	}

	// 6. ModelAndView
	@RequestMapping("/getTest6/{firstName}/{salary}")
	public ModelAndView getTest6(@PathVariable String firstName, 
								 @PathVariable int salary,
								 @ModelAttribute("tvo") TestVO vo) {
		vo.setFirstName(firstName);
		vo.setSalary(salary);
		//ModelAndView mv = new ModelAndView(); // model과 view 두 개를 동시에 보유하고 있다.
		//mv.addObject("firstName", firstName); // 속성 추가
		//mv.setViewName("test");
		//return mv; // 리턴타입이 ModelAndView
		return new ModelAndView("test", "firstName", firstName);
	}

	// 7. 응답결과가 JSON
	@RequestMapping("/getTest7/{firstName}/{salary}")
	@ResponseBody
	public TestVO getTest7(@PathVariable String firstName, 
						   @PathVariable int salary,
						   TestVO vo) {
		vo.setFirstName(firstName);
		vo.setSalary(salary);
		
		return vo; // vo 객체만 보낼 것이다.
	}
	
	// 8. 응답결과가 JSON
		@RequestMapping("/getTest8")
		@ResponseBody
		public List<Map> getTest8() {
			List list = new ArrayList<>();
			Map<String, String> map = new HashMap<>();
			map.put("name", "park");
			map.put("sal", "20000");
			list.add(map);
			
			map = new HashMap<>();
			map.put("name", "Lee");
			map.put("sal", "10000");
			list.add(map);
			
			return list; // vo 객체만 보낼 것이다.
		}
	
}
