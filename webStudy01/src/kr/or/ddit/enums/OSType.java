package kr.or.ddit.enums;


public enum OSType {

	LINUX("리눅스"), WINDOWS("윈도우"), OTHER("기타OS");

	OSType(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return this.name;
	}

	public static OSType searchOS(String userAgent) {
		OSType result = OTHER;
		for (OSType tmp : values()) {
			if (userAgent.toUpperCase().contains(tmp.name())) {
				result = tmp;
				break;
			}
		}
		return result;
	}
}
