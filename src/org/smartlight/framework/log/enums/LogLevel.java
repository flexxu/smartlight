/**
 * 
 */
package org.smartlight.framework.log.enums;

import org.smartlight.framework.sys.lang.enums.BaseEnum;

/**
 * @author peng.shi
 *
 */
public enum LogLevel implements BaseEnum<Integer>
{
	INFO("信息",0),ERROR("错误",1);
	
	private String desc;
	private int code;
	
	private LogLevel(String desc,int code)
	{
		this.desc = desc;
		this.code = code;
	}


	public Integer getCode()
	{
		return this.code;
	}


	public String getDesc()
	{
		return this.desc;
	}
	
}
