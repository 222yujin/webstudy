package kr.or.ddit.servlet03;

import java.io.File;

import javax.servlet.ServletContext;

/**
 * (object) Adapter/ Wrapper pattern
 *
 */
public class FileWrapper {

	public FileWrapper(File resource, ServletContext application) {
		this(resource,application,false);
	}
			
	public FileWrapper(File resource, ServletContext application,boolean wildcard) {
		super();
		this.resource = resource;
		this.application = application;
		contextRealPath = application.getRealPath("");
		displayName = resource.getName();
		if(wildcard) displayName="..";
		String absolutePath = resource.getAbsolutePath();
		id = absolutePath.substring(contextRealPath.length() - 1);
		id = id.replace(File.separatorChar, '/');
	}

	private File resource;
	private ServletContext application;
	private String contextRealPath;

	private String displayName; // li태그의 innerText
	private String id; // li태그의 id(서버사이드 경로)
	
	
	public boolean isDirectory() {
		return resource.isDirectory();
	}

	public boolean isFile() {
		return resource.isFile();
	}

	public File getResource() {
		return resource;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getId() {
		return id;
	}

}
