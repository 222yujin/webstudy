package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {
	private MemberDAOImpl() {
	}

	private static MemberDAOImpl instance;

	public static MemberDAOImpl getinstance() {
		if (instance == null) {
			instance = new MemberDAOImpl();
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
		sql.append(
				"where mem_id = ?  and MEM_DELETE is null                                                                          ");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, member.getMem_id());
			// pstmt.setString(2, member.getMem_pass());
			ResultSet rs = pstmt.executeQuery();
			MemberVO memvo = null;
			if (rs.next()) {
				memvo = new MemberVO();
				memvo.setMem_id(rs.getString("MEM_ID"));
				memvo.setMem_pass(rs.getString("MEM_PASS"));
				memvo.setMem_name(rs.getString("MEM_NAME"));
				memvo.setMem_regno1(rs.getString("MEM_REGNO1"));
				memvo.setMem_regno2(rs.getString("MEM_REGNO2"));
				memvo.setMem_bir(rs.getString("MEM_BIR"));
				memvo.setMem_zip(rs.getString("MEM_ZIP"));
				memvo.setMem_add1(rs.getString("MEM_ADD1"));
				memvo.setMem_add2(rs.getString("MEM_ADD2"));
				memvo.setMem_hometel(rs.getString("MEM_HOMETEL"));
				memvo.setMem_comtel(rs.getString("MEM_COMTEL"));
				memvo.setMem_hp(rs.getString("MEM_HP"));
				memvo.setMem_mail(rs.getString("MEM_MAIL"));
				memvo.setMem_job(rs.getString("MEM_JOB"));
				memvo.setMem_like(rs.getString("MEM_LIKE"));
				memvo.setMem_memorial(rs.getString("MEM_MEMORIAL"));
				memvo.setMem_memorialday(rs.getString("MEM_MEMORIALDAY"));
				memvo.setMem_mileage(rs.getInt("MEM_MILEAGE"));
				memvo.setMem_delete(rs.getString("MEM_DELETE"));

				// reflection 이후 작성

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
		StringBuffer sql = new StringBuffer();
		sql.append("select MEM_ID,MEM_NAME,MEM_ADD1,MEM_ADD2,MEM_HP,MEM_MAIL,MEM_MILEAGE ");
		sql.append("from member");
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			ResultSet rs = pstmt.executeQuery();
			MemberVO memvo = null;
			List<MemberVO> list = new ArrayList<MemberVO>();
			while(rs.next()) {
				memvo = new MemberVO();
				
				memvo.setMem_id(rs.getString("MEM_ID"));
				memvo.setMem_name(rs.getString("MEM_NAME"));
				memvo.setMem_add1(rs.getString("MEM_ADD1"));
				memvo.setMem_add2(rs.getString("MEM_ADD2"));
				memvo.setMem_hp(rs.getString("MEM_HP"));
				memvo.setMem_mail(rs.getString("MEM_MAIL"));
				memvo.setMem_mileage(rs.getInt("MEM_MILEAGE"));
				

				list.add(memvo);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	@Override
	public int updateMember(MemberVO member) {

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE member                                               ");
		sql.append("SET mem_name = ?,              ");
		sql.append("    mem_bir =?,          ");
		sql.append("    mem_zip = ?,mem_add1 = ?,mem_add2 = ?,               ");
		sql.append("    mem_hometel = ?,mem_comtel = ?,mem_hp = ?,           ");
		sql.append("    mem_mail = ?,mem_job = ?,mem_like = ?,               ");
		sql.append("    mem_memorial = ?,mem_memorialday = ?");
		sql.append("WHERE mem_id =?                                      ");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			// 쿼리 파라미터 설정
			int idx = 1;
			pstmt.setString(idx++, member.getMem_name());
			pstmt.setString(idx++, member.getMem_bir());
			pstmt.setString(idx++, member.getMem_zip());
			pstmt.setString(idx++, member.getMem_add1());
			pstmt.setString(idx++, member.getMem_add2());
			pstmt.setString(idx++, member.getMem_hometel());
			pstmt.setString(7, member.getMem_comtel());
			pstmt.setString(8, member.getMem_hp());
			pstmt.setString(9, member.getMem_mail());
			pstmt.setString(10, member.getMem_job());
			pstmt.setString(11, member.getMem_like());
			pstmt.setString(12, member.getMem_memorial());
			pstmt.setString(13, member.getMem_memorialday());
			pstmt.setString(14, member.getMem_id());

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public int deleteMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append("update member ");
		sql.append("set mem_delete='Y' ");
		sql.append("where mem_id=?");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			// 쿼리 파라미터 설정
			pstmt.setString(1, member.getMem_id());

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}