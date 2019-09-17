package kr.or.ddit.member.service;

import java.util.List;


import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	//결합력 최상 -> HCLC 지향 => Factory Obhect Pattern ,Stategy Pattern
	private IMemberDAO dao = MemberDAOImpl.getinstance();
	
	
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO retriveMember(MemberVO member) {
		MemberVO saved = dao.selectMember(member);
		if(saved ==null) {
			throw new UserNotFoundException(member.getMem_id()+"가 없음");
		}
		return saved;
	}

	@Override
	public List<MemberVO> retriveMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

}
