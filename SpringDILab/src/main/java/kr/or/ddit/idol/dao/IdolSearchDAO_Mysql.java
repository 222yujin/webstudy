package kr.or.ddit.idol.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdolSearchDAO_Mysql implements IIdolSearchDAO {

	public IdolSearchDAO_Mysql() {
		super();
		System.out.printf("%s 객체 생성 \n", getClass().getSimpleName());
	}

	Map<String, String[]> idolMap = new HashMap<>();
	{
		idolMap.put("I001", new String[] { "ses", "유진", "슈", "바다" });
		idolMap.put("I002", new String[] { "cool", "김성수", "유리", "이재훈" });
		System.out.println("코드블럭");
	}

	public void init() {
			System.out.println("lifecycle callback");
	}

	@Override
	public String[] selectIdol(String code) {
		return idolMap.get(code);
	}

	@Override
	public List<String[]> selectIdolList() {
		List<String[]> list = new ArrayList<>(idolMap.values());
		return list;
	}

}
