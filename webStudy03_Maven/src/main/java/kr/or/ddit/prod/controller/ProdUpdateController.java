package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.wrapper.MultipartRequestWrapper;
import kr.or.ddit.wrapper.PartWrapper;

@CommandHandler
public class ProdUpdateController {
	IProdService service = new ProdServiceImpl();

	@URIMapping("/prod/prodUpdate.do")
	public String updateForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String prod_id = req.getParameter("what");
		if (StringUtils.isBlank(prod_id)) {
			resp.sendError(400);
			return null;
		}
		ProdVO prod = service.retrieveProd(prod_id);
		req.setAttribute("prod", prod);
		return "prod/prodForm";
	}

	@URIMapping(value = "/prod/prodUpdate.do", method = HttpMethod.POST)
	public String update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ProdVO prod = new ProdVO();

		req.setAttribute("prod", prod);

		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		if (req instanceof MultipartRequestWrapper) {
			PartWrapper partWrapper = ((MultipartRequestWrapper) req).getPartWrapper("prod_image");
			if (partWrapper != null) {
				// 1.저장 위치 설정
				String saveFolderURL = "/prodImages";
				String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL);
				File saveFolder = new File(saveFolderPath);
				if (!saveFolder.exists())
					saveFolder.mkdirs();

				// 2.저장명
				String savename = UUID.randomUUID().toString();
				try (InputStream is = partWrapper.getInputStream();) {
					FileUtils.copyInputStreamToFile(is, new File(saveFolder, savename));
				}
//	         metadata저장
				prod.setProd_img(savename);
			}
		}

		Map<String, String> errors = new HashMap<String, String>();

		req.setAttribute("errors", errors);

		boolean valid = validate(prod, errors);

		String viewName = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case OK:
//				- OK   : redirect -> welcome page
				message = "수정 성공";
				viewName = "redirect:/prod/prodView.do?what=" + prod.getProd_id();
				break;
			default:
				message = "서버 오류";
				viewName = "prod/prodForm";
				break;
			}

		} else {
			viewName = "prod/prodForm";
		}

		req.setAttribute("message", message);

		return viewName;

	}

	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(prod.getProd_id())) {
			valid = false;
			errors.put("prod_id", "상품코드 누락");
		}
		if (StringUtils.isBlank(prod.getProd_name())) {
			valid = false;
			errors.put("prod_name", "상품명 누락");
		}
		if (StringUtils.isBlank(prod.getProd_lgu())) {
			valid = false;
			errors.put("prod_lgu", "분류코드 누락");
		}
		if (StringUtils.isBlank(prod.getProd_buyer())) {
			valid = false;
			errors.put("prod_buyer", "거래처코드 누락");
		}
		if (prod.getProd_cost() <= 0) {
			valid = false;
			errors.put("prod_cost", "구매가 누락");
		}
		if (prod.getProd_price() <= 0) {
			valid = false;
			errors.put("prod_price", "판매가 누락");
		}
		if (prod.getProd_sale() <= 0) {
			valid = false;
			errors.put("prod_sale", "세일가 누락");
		}
		if (StringUtils.isBlank(prod.getProd_outline())) {
			valid = false;
			errors.put("prod_outline", "OUTLINE 누락");
		}
//		if (StringUtils.isBlank(prod.getProd_img())) {
//			valid = false;
//			errors.put("prod_img", "이미지경로? 누락");
//		}
		if (prod.getProd_totalstock() <= 0) {
			valid = false;
			errors.put("prod_totalstock", "상품재고 누락");
		}
		if (prod.getProd_properstock() <= 0) {
			valid = false;
			errors.put("prod_properstock", "적정재고 누락");
		}

		return valid;
	}
}
