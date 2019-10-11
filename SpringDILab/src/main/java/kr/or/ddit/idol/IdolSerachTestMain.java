package kr.or.ddit.idol;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IdolSerachTestMain {
	public static void main(String[] args) {
		Map<String, String[]> idolMap = new HashMap<>();
		idolMap.put("I001", new String[] { "ses", "유진", "슈", "바다" });
		idolMap.put("I002", new String[] { "cool", "김성수", "유리", "이재훈" });

		String code = "I002";
		String[] data = idolMap.get(code);
		System.out.println(Arrays.deepToString(data));

	}
}
