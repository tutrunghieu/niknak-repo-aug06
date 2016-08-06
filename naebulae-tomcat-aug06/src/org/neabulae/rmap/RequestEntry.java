package org.neabulae.rmap;

import java.lang.reflect.Method;

public class RequestEntry 
{
	public String japiNumber;
	
	public String sourceGroup;
	public String sourceAction;
	
	public Class<?> targetClass;
	public Method targetMethod;
	
	
	public String toString()
	{
		String res = "";
		res += japiNumber + " >> ";
		
		res += sourceGroup + " >> ";
		res += sourceAction + " >> ";
		
		res += targetClass.getSimpleName() + " >> ";
		res += (targetMethod==null ? " - " : targetMethod.getName());
		
		return res;
	}

}
