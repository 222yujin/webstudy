package kr.or.ddit;

public enum OperatorType {
	PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"),OTHER("");

	OperatorType(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return this.name;
	}

	public static OperatorType searchParams(String params) {
		OperatorType result = OTHER;
		for (OperatorType tmp : values()) {
			if (params.contains(tmp.name())) {
				result = tmp;
				break;
			}
		}
		return result;
	}
}
