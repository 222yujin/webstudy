package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	IMemberDAO dao = MemberDAOImpl.getinstance();

	@Test
	public void testSelectMember() {
		MemberVO member= dao.selectMember(new MemberVO("c001",null));
		assertNotNull(member);
		//assertNull(member);
	}

	@Test
	public void testInsertMember() {
		//dao.insertMember(member);
	}

}
