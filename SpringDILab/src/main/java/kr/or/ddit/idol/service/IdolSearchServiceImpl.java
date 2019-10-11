package kr.or.ddit.idol.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import kr.or.ddit.idol.dao.IIdolSearchDAO;
import kr.or.ddit.idol.dao.IdolSearchDAO_Mysql;
import kr.or.ddit.idol.dao.IdolSearchDAO_Oracle;

public class IdolSearchServiceImpl implements IIdolSearchService {
	// 1.전통적인 의존관계 형성 : new 인스턴스 직접 생성 -> 결합력 최상
	// IIdolSearchDAO dao = new IdolSearchDAO_Oracle();
	// IIdolSearchDAO dao = new IdolSearchDAO_Mysql();
	// 2.Factory Object[Method] Pattern : factory 와 생성 객체 사이의 결합력 잔존
	// IIdolSearchDAO dao = IdolDAOFactory.getIdolDAO();
	// 3.DI 구조를 기반으로 한 strategy pattern : 전략 주입자 필요(결합력 전체에 대한 책임)
	// 4. 전략패턴을 확장한 DI container 구조

	private IIdolSearchDAO dao;

	public IdolSearchServiceImpl() {
		super();
		System.out.printf("%s 객체 생성,기본생성자 사용 \n", getClass().getSimpleName());

	}

	public IdolSearchServiceImpl(IIdolSearchDAO dao) {
		super();
		this.dao = dao;
		System.out.printf("%s 객체 생성,dao 주입받아 사용 \n", getClass().getSimpleName());
	}

	public void setDao(IIdolSearchDAO dao) {
		this.dao = dao;
	}

	@Override
	public String readIdol(String code) {
		String[] idol = dao.selectIdol(code);
		String infomation = Arrays.toString(idol) + new Date();
		return infomation;
	}

	@Override
	public List<String> readIdols() {
		List<String[]> list = dao.selectIdolList();
		List<String> infomation = new ArrayList<>();
		for (String[] tmp : list) {
			infomation.add(Arrays.toString(tmp));
		}
		return infomation;
	}

}
