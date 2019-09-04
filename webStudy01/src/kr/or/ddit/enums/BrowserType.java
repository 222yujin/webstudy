package kr.or.ddit.enums;

public enum BrowserType {
	CHROME("크롬"), FIREFOX("파이어폭스"), MSIE("익스플로어"), OTHER("기타OS");

	private String name;

	BrowserType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static BrowserType searchBrowser(String userAgent) {
		BrowserType result = OTHER;
		for (BrowserType tmp : values()) {
			if (userAgent.toUpperCase().contains(tmp.name())) {
				result = tmp;
				break;
			}
		}
		return result;
	}

}
