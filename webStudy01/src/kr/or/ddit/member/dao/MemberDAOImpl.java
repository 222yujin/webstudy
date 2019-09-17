package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {
	private MemberDAOImpl() {}
	
	private static MemberDAOImpl instance;
	
	public static MemberDAOImpl getinstance() {
		if(instance==null) {
			instance=new MemberDAOImpl();
		}
		return instance;
	}

	@Override
	public MemberVO selectMember(MemberVO member) {
		// 아이디 비번 이름 휴대폰 이메일,.,

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT                                                                                          ");
	    sql.append("	MEM_ID,MEM_PASS,MEM_NAME,                                                                   ");
	    sql.append("	MEM_REGNO1,MEM_REGNO2, TO_CHAR(MEM_BIR,'YYYY-MM-DD') MEM_BIR,                               ");
	    sql.append("	MEM_ZIP, MEM_ADD1,MEM_ADD2,                                                                 ");
	    sql.append("	MEM_HOMETEL, MEM_COMTEL,MEM_HP,                                                             ");
	    sql.append("	MEM_MAIL, MEM_JOB,MEM_LIKE,                                                                 ");
	    sql.append("	MEM_MEMORIAL, TO_CHAR(MEM_MEMORIALDAY,'YYYY-MM-DD') MEM_MEMORIALDAY, MEM_MILEAGE, MEM_DELETE ");
	    sql.append("FROM member                                                                                    ");
		sql.append("where mem_id = ?                                                                                ");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, member.getMem_id());
			//pstmt.setString(2, member.getMem_pass());
			ResultSet rs = pstmt.executeQuery();
			MemberVO memvo = null;
			if (rs.next()) {
				memvo= new MemberVO();
				memvo.setMem_id(rs.getString("MEM_ID"));
				memvo.setMem_pass(rs.getString("MEM_PASS"));
				memvo.setMem_name(rs.getString("MEM_NAME"));
				memvo.setMem_hp(rs.getString("MEM_HP"));
				memvo.setMem_mail(rs.getString("MEM_MAIL"));
				
				//reflection 이후 작성

			}
			return memvo;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}

	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> selectMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}
}