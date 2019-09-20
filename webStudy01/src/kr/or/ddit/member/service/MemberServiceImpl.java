package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.exception.NotAuthenticatedException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	// 결합력 최상 -> HCLC 지향 => Factory Obhect Pattern ,Stategy Pattern
	private IMemberDAO dao = MemberDAOImpl.getinstance();
	private IAuthenticateService service = new AuthenticateServiceImpl();

	@Override
	public ServiceResult createMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO retriveMember(MemberVO member) {
		MemberVO saved = dao.selectMember(member);
		if (saved == null) {
			throw new UserNotFoundException(member.getMem_id() + "가 없음");
		}
		return saved;
	}

	@Override
	public List<MemberVO> retriveMemberList() {
		
		return dao.selectMemberList();
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult result = null;
		try {
			service.authenticate(member);
			int cnt = dao.updateMember(member);
			if (cnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		} catch (NotAuthenticatedException e) {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result = null;
		try {
			service.authenticate(member);
			int cnt = dao.deleteMember(member);
			if (cnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		} catch (NotAuthenticatedException e) {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

}
