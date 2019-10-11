package kr.or.ddit.idol.dao;

import java.util.List;

public interface IIdolSearchDAO {
	public String[] selectIdol(String code);
	public List<String[]> selectIdolList();
}
