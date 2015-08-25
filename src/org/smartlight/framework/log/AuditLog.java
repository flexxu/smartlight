/**
 * 
 */
package org.smartlight.framework.log;

import org.smartlight.framework.sys.business.BusinessObject;

/**
 * @author peng.shi
 *
 */
public interface AuditLog extends BusinessObject
{
	String getId();
	
	LogType getLogType();
	
}
