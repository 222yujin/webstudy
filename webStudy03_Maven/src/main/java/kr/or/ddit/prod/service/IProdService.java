package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 
 * 상품관리 Business Logic Layer
 *
 */
public interface IProdService {
	/**
	 * 
	 * @param prod
	 * @return OK,FAILED
	 */
	public ServiceResult createProd(ProdVO prod);
	public int retrieveProdCount(PagingInfoVO pagingVO);
	public List<ProdVO> retrieveProdList(PagingInfoVO pagingVO);
	public ProdVO retrieveProd(String prod_id);
	/**
	 * 
	 * @param prod
	 * @return CommonException,OK,FAILED
	 */
	public ServiceResult modifyProd(ProdVO prod);
	
	public ServiceResult deleteProd(String prod_id);
}
