package com.company.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonUtil {

	public String toJson(Map<String, Object> map) {
		StringBuilder result = new StringBuilder();
		result.append("{");
		// map의 Key 값만 Set 타입(중복X, 순서X => iterator로 일렬 정렬)으로 keys에 담는다.
		Iterator<String> keys = map.keySet().iterator();
		boolean start = true;
		while (keys.hasNext()) { // 다음 값이 있으면 true, false면 while 종료
			
			String key = keys.next(); // 해당 값을 읽음
			result.append('"' + key + '"');
			result.append(":");
			String value = (String) map.get(key); // 받아온 값을 String으로 Casting(형변환)
			result.append('"' + value + '"');
			if(keys.hasNext() == true) {
				result.append(",");				
			}
		}
		result.append("}");
		return result.toString();
	}

	public String toJson(List<Map<String, Object>> map) {
		String result = "";

		return result;
	}

	public String toObjectJson(Object vo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String result = "";
		Field[] fields = vo.getClass().getDeclaredFields();
		for (Field field : fields) {
			// field 이름만 출력
			String fieldName = field.getName();
			String method = "get" + field.getName()
							.substring(0, 1).toUpperCase()
							+ field.getName().substring(1);
			Method m = vo.getClass().getDeclaredMethod(method, null);
			String value = (String) m.invoke(vo, null);
			System.out.println(fieldName + ": " + value);
		}
		return result;
	}

	public String toObjectJson(List<Object> vo) {
		String result = "";

		return result;
	}
}
