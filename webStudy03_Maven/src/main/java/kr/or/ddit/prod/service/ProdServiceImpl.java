package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.member.exception.NotAuthenticatedException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {

	IProdDAO dao = new ProdDAOImpl();

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = null;

		try {
			int cnt = dao.insertProd(prod);
			if (cnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		} catch (CommonException e) {
			result = ServiceResult.FAILED;
		}

		return result;
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingInfoVO pagingVO) {
		// TODO Auto-generated method stub
		return dao.selectProdList(pagingVO);
	}

	@Override
	public ProdVO retrieveProd(String prod_id) {
		// TODO Auto-generated method stub
		return dao.selectProd(prod_id);
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		ServiceResult result = null;
		try {
			int cnt = dao.updateProd(prod);
			if (cnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		} catch (CommonException e) {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteProd(String prod_id) {
		ServiceResult result = null;
		try {
			int cnt = dao.deleteProd(prod_id);
			if (cnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		} catch (CommonException e) {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public int retrieveProdCount(PagingInfoVO pagingVO) {
		// TODO Auto-generated method stub
		return dao.selectProdCount(pagingVO);
	}

}
