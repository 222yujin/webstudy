package kr.or.ddit.wrapper;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

public class MultipartRequestWrapper extends HttpServletRequestWrapper{

	private Map<String, PartWrapper[]> filePartWrapperMap;
	
	public MultipartRequestWrapper(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		filePartWrapperMap = new HashMap<>();
		parseRequest(request);
	}
	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		//문자파트+파일파트
		Collection<Part> parts= request.getParts();
		
		Iterator<Part> it = parts.iterator();
		
		while(it.hasNext()) {
			Part part = it.next();
			String partName= part.getName();
			String dispostion = part.getHeader("Content-Disposition");
			//문자파트 스킵(parameterMap에 누적되어 있음)
			
			if(!dispostion.contains("filename")) continue;
			if(part.getSize()<=0) continue;
			PartWrapper wrapper = new PartWrapper(part);
			PartWrapper[] array = filePartWrapperMap.get(partName);
			PartWrapper[] target = null;
			if(array==null) {
				target = new PartWrapper[] {wrapper};
			}else {
				target = new PartWrapper[array.length+1];
				System.arraycopy(array, 0, target, 0, array.length);
				target[target.length-1] = wrapper;
			}
			filePartWrapperMap.put(partName, target);
		}
	}

	
	public PartWrapper getPartWrapper(String name){
		PartWrapper[] array =  filePartWrapperMap.get(name);
		if(array!=null && array.length>0) {
			
			return array[0];
		}else {
			return null;
		}
		
	}
	
	public PartWrapper[] getPartWrappers(String name){
		return filePartWrapperMap.get(name);
	}
	
	public Map<String, PartWrapper[]> getFilePartWrapperMap(){
		return filePartWrapperMap;
	}
	
	public void deleteAll() throws IOException {
		for(Entry<String, PartWrapper[]> entry:filePartWrapperMap.entrySet()) {
			PartWrapper[] array =entry.getValue();
			for(PartWrapper wrapper : array) {
				wrapper.delete();
			}
		}
	}
}
