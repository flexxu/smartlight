/**
 * 
 */
package org.smartlight.framework.log;

import org.smartlight.framework.sys.business.BusinessObject;

/**
 * @author peng.shi
 *
 */
public interface LogType extends BusinessObject
{
	String getId();
	
	String getDefine();
	
	void setDefine();
	
	String getName();
	
	void setName();
	
}
