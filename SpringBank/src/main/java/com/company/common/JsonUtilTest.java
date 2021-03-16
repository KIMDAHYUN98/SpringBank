package com.company.common;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.company.board.service.BoardVO;

public class JsonUtilTest {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		JsonUtil json = new JsonUtil();
		Map<String, Object> map = new HashMap<>();
		// map, vo : {}, List : []
		map.put("username", "홍길동");
		map.put("age", "21");
		map.put("dept", "개발");
		String result = json.toJson(map);
		// json 구조로 출력된다.
		System.out.println(result); 
		// result =  { "username" : "홍길동", "age" : 21, "dept" : "개발" }
	
		BoardVO vo = new BoardVO();
		vo.setContent("test");
		vo.setTitle("title");
		vo.setFilename("file");
		result = json.toObjectJson(vo);
		System.out.println(result);
		
	}
}
