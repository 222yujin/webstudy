package kr.or.ddit.servlet02.service;

import java.io.File;

public class ImageListService  {
//	3.요청 정보를 생성 --> Layered architecture
//	1) raw data 확보
//	2) data 가공해서 infomation으로 생성(logic).
	public String[] getImageList(){
		File imgFolder = new File("d:/contents");

		String[] images = imgFolder.list((dir, name) -> {
			return name.endsWith(".jpg") || name.endsWith(".gif");
		});
		return images;
	}
}
