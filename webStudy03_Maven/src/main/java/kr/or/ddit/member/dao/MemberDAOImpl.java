package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

public class MemberDAOImpl implements IMemberDAO {

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertMember(MemberVO member) {
		//insert/update/delete 의 경우 autocommit 설정이 없으면 commit필요
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			int cnt= mapper.insertMember(member);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public List<MemberVO> selectMemberList(PagingInfoVO pagingVO) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.selectMemberList(pagingVO);
		}

	}

	@Override
	public MemberVO selectMember(MemberVO member) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.selectMember(member);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			int cnt= mapper.updateMember(member);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int deleteMember(MemberVO member) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			int cnt= mapper.deleteMember(member);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int selectMemberCount(PagingInfoVO<MemberVO> pagingVO) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.selectMemberCount(pagingVO);
		}
	}

}
