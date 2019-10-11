package kr.or.ddit.idol.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdolSearchDAO_Oracle implements IIdolSearchDAO {

	public IdolSearchDAO_Oracle() {
		super();
		System.out.printf("%s 객체 생성 \n",getClass().getSimpleName());
	}
	
	Map<String, String[]> idolMap = new HashMap<>();
	{
		idolMap.put("I001", new String[] { "ses", "유진", "슈", "바다" });
		idolMap.put("I002", new String[] { "cool", "김성수", "유리", "이재훈" });
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
