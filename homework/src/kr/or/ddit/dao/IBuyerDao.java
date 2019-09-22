package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;

public interface IBuyerDao {
	public List<BuyerVO> selectBuyerList();
	public BuyerVO selectBuyer(BuyerVO buyer);
	public int insertBuyer(BuyerVO buyer);
	public int updateBuyer(BuyerVO buyer);
	public int deleteBuyer(BuyerVO buyer);
}
