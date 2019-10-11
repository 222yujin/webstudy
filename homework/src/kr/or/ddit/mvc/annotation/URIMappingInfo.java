package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

public class URIMappingInfo {

	private URIMappingCondition mappingCondition;
	private Object commandHandler;
	private Method handlermethod;
	
	public URIMappingInfo(URIMappingCondition mappingCondition, Object commandHandler, Method handlermethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlermethod = handlermethod;
	}

	public URIMappingCondition getMappingCondition() {
		return mappingCondition;
	}

	public Object getCommandHandler() {
		return commandHandler;
	}

	public Method getHandlermethod() {
		return handlermethod;
	}

	@Override
	public String toString() {
		return "URIMappingInfo [mappingCondition=" + mappingCondition + ", commandHandler=" + commandHandler.getClass().getName()
				+ "." + handlermethod.getName() + "]";
	}
	

	
	
}
