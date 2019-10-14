package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DbInfoVO {
	private String driverClassName;
	private String url;
	private String user;
	private String password;
	private int initialSize;
	private int maxWait;
	private int maxTotal;
}
