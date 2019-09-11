package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {

	@Override
	public MemberVO selectMember(MemberVO member) {
		// 아이디 비번 이름 휴대폰 이메일,.,

		StringBuffer sql = new StringBuffer();
		sql.append("select MEM_ID, MEM_PASS, MEM_NAME, MEM_HP, MEM_MAIL from member");
		sql.append(" where mem_id = ?");
		//sql.append(" and mem_pass= ?");

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

			}
			return memvo;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}
}