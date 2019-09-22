package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;

public interface IBuyerService {
		public int insertBuyer(BuyerVO buyer);

		public BuyerVO selectBuyer(BuyerVO buyer);

		public List<BuyerVO> selectBuyerList();

		public int updateBuyer(BuyerVO buyer);

		public int deleteBuyer(BuyerVO buyer);
		
	

}
