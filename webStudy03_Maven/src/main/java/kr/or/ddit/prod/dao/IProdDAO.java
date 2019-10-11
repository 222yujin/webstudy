package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 peristence layer
 * @author PC-18
 *
 */
public interface IProdDAO {
	public int insertProd(ProdVO prod);
	public int selectProdCount(PagingInfoVO pagingVO);
	public List<ProdVO> selectProdList(PagingInfoVO pagingVO);
	public ProdVO selectProd(String prod_id);
	public int updateProd(ProdVO prod);
	public int deleteProd(String prod_id);
}
