package kr.or.ddit.service;


import java.util.List;

import kr.or.ddit.dao.BuyerDaoImpl;
import kr.or.ddit.dao.IBuyerDao;
import kr.or.ddit.vo.BuyerVO;

public class BuyerServiceImpl implements IBuyerService {
	IBuyerDao dao = BuyerDaoImpl.getInstance();
	//IBuyerService service = new BuyerServiceImpl();
	
	@Override
	public int insertBuyer(BuyerVO buyer) {
		// TODO Auto-generated method stub
		return dao.insertBuyer(buyer);
	}

	@Override
	public BuyerVO selectBuyer(BuyerVO buyer) {
		// TODO Auto-generated method stub
		return dao.selectBuyer(buyer);
	}

	@Override
	public List<BuyerVO> selectBuyerList() {
		
		return dao.selectBuyerList();
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		// TODO Auto-generated method stub
		return dao.updateBuyer(buyer);
	}

	@Override
	public int deleteBuyer(BuyerVO buyer) {
		// TODO Auto-generated method stub
		return dao.deleteBuyer(buyer);
	}

}
