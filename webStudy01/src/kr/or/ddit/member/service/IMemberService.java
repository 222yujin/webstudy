package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.MemberVO;

/**
 * 회원관리를 위한 Business Logic Layer
 * CRUD
 */
public interface IMemberService {
	/**
	 * 신규등록
	 * @param member
	 * @return PKDUPLICATED,OK,FAILED
	 */
	public ServiceResult createMember(MemberVO member);
	
	/**
	 * 회원 정보 상세 조회
	 * @param member
	 * @return 조건에 맞는 사용자가 없으면 ,UserNotFoundException 발생
	 */
	public MemberVO retriveMember(MemberVO member);
	
	/**
	 * 목록조회
	 * @return 없으면 ,size==0
	 */
	public List<MemberVO> retriveMemberList();
	
	/**
	 * 정보 수정
	 * @param member
	 * @return UserNotFoundException,INVALIDPASSWORD,OK,FAILED
	 */
	public ServiceResult modifyMember(MemberVO member);
	
	/**
	 * 
	 * @param member
	 * @return UserNotFoundException,INVALIDPASSWORD,OK,FAILED
	 */
	public ServiceResult removeMember(MemberVO member);
	
}
