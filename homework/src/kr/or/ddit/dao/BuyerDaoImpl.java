package kr.or.ddit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.BuyerVO;

public class BuyerDaoImpl implements IBuyerDao {

	private static BuyerDaoImpl dao = new BuyerDaoImpl();

	public static BuyerDaoImpl getInstance() {
		if (dao == null) {
			dao = new BuyerDaoImpl();
		}
		return dao;

	}

	@Override
	public List<BuyerVO> selectBuyerList() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT buyer_id,buyer_name ");
		sql.append("FROM buyer");

		List<BuyerVO> list = new ArrayList<BuyerVO>();
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BuyerVO vo = new BuyerVO();
				
				vo.setBuyer_id(rs.getString("BUYER_ID"));
				vo.setBuyer_name(rs.getString("BUYER_NAME"));
				
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BuyerVO selectBuyer(BuyerVO buyer) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT buyer_id,buyer_name, buyer_lgu,");
		sql.append("buyer_bank, buyer_bankno,buyer_bankname,");
		sql.append("buyer_zip, buyer_add1,buyer_add2,");
		sql.append("buyer_comtel,buyer_fax,buyer_mail,");
		sql.append("buyer_charger, buyer_telext ");
		sql.append("FROM buyer ");
		sql.append("where buyer_id=?");
		

		BuyerVO vo = new BuyerVO();
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, buyer.getBuyer_id());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setBuyer_add1(rs.getString("BUYER_ADD1"));
				vo.setBuyer_add2(rs.getString("BUYER_ADD2"));
				vo.setBuyer_bank(rs.getString("BUYER_BANK"));
				vo.setBuyer_bankname(rs.getString("BUYER_BANKNAME"));
				vo.setBuyer_bankno(rs.getString("BUYER_BANKNO"));
				vo.setBuyer_charger(rs.getString("BUYER_CHARGER"));
				vo.setBuyer_comtel(rs.getString("BUYER_COMTEL"));
				vo.setBuyer_fax(rs.getString("BUYER_FAX"));
				vo.setBuyer_id(rs.getString("BUYER_ID"));
				vo.setBuyer_lgu(rs.getString("BUYER_LGU"));
				vo.setBuyer_mail(rs.getString("BUYER_MAIL"));
				vo.setBuyer_name(rs.getString("BUYER_NAME"));
				vo.setBuyer_telext(rs.getString("BUYER_TELEXT"));
				vo.setBuyer_zip(rs.getString("BUYER_ZIP"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int insertBuyer(BuyerVO buyer) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO buyer (buyer_id,buyer_name,buyer_lgu,buyer_bank,buyer_bankno, buyer_bankname, ");
		sql.append(" buyer_zip,buyer_add1,buyer_add2,buyer_comtel,buyer_fax,buyer_mail,buyer_charger, buyer_telext) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	
		int result =0;
		BuyerVO vo = new BuyerVO();
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, buyer.getBuyer_id());
			pstmt.setString(2, buyer.getBuyer_name());
			pstmt.setString(3, buyer.getBuyer_lgu());
			pstmt.setString(4, buyer.getBuyer_bank());
			pstmt.setString(5, buyer.getBuyer_bankno());
			pstmt.setString(6, buyer.getBuyer_bankname());
			pstmt.setString(7, buyer.getBuyer_zip());
			pstmt.setString(8, buyer.getBuyer_add1());
			pstmt.setString(9, buyer.getBuyer_add2());
			pstmt.setString(10, buyer.getBuyer_comtel());
			pstmt.setString(11, buyer.getBuyer_fax());
			pstmt.setString(12, buyer.getBuyer_mail());
			pstmt.setString(13, buyer.getBuyer_charger());
			pstmt.setString(14, buyer.getBuyer_telext());
			
			result = pstmt.executeUpdate();
		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE buyer  ");
		sql.append("SET  BUYER_NAME = ?, BUYER_LGU = ?, BUYER_BANK = ?, BUYER_BANKNO = ?, BUYER_BANKNAME = ?,");
		sql.append(" BUYER_ZIP = ?, BUYER_ADD1 = ?, BUYER_ADD2 = ?, BUYER_COMTEL = ?, BUYER_FAX = ?, BUYER_MAIL = ?,");
		sql.append(" BUYER_CHARGER = ?, BUYER_TELEXT = ?");
		sql.append("WHERE buyer_id = ?");

		
		int result=0;
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			int idx=1;
			pstmt.setString(idx++, buyer.getBuyer_name());
			pstmt.setString(idx++, buyer.getBuyer_lgu());
			pstmt.setString(idx++,buyer.getBuyer_bank());
			pstmt.setString(idx++,buyer.getBuyer_bankno());
			pstmt.setString(idx++,buyer.getBuyer_bankname());
			pstmt.setString(idx++,buyer.getBuyer_zip());
			pstmt.setString(idx++,buyer.getBuyer_add1());
			pstmt.setString(idx++,buyer.getBuyer_add2());
			pstmt.setString(idx++, buyer.getBuyer_comtel());
			pstmt.setString(idx++, buyer.getBuyer_fax());
			pstmt.setString(idx++, buyer.getBuyer_mail());
			pstmt.setString(idx++, buyer.getBuyer_charger());
			pstmt.setString(idx++, buyer.getBuyer_telext());
			pstmt.setString(idx++, buyer.getBuyer_id());
			
			result = pstmt.executeUpdate();
		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	@Override
	public int deleteBuyer(BuyerVO buyer) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM buyer ");
		sql.append("WHERE buyer_id =?");
		
		int result=0;
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, buyer.getBuyer_id());
			
			result = pstmt.executeUpdate();
		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
