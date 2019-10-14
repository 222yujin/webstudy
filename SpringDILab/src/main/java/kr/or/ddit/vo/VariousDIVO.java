package kr.or.ddit.vo;

import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // constructor injection 사용 가능
@NoArgsConstructor 
@Data //setter injection 사용 가능
public class VariousDIVO {
   private int num;
   private char ch;
   private boolean bool;
   private String str;
   
   private File file;
}