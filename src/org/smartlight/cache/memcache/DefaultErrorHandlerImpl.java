package org.smartlight.cache.memcache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.smartlight.cache.memcache.client.ErrorHandler;
import org.smartlight.cache.memcache.client.MemcachedClient;


/**
 * <strong>DefaultErrorHandlerImpl</strong><br>
 * 默认Memecached ErrorHandler 实现<br> 
 * <strong>Create on : 2011-12-12<br></strong>
 * <p>
 * <strong>Copyright (C) Ecointel Software Co.,Ltd.<br></strong>
 * <p>
 * @author peng.shi peng.shi@ecointel.com.cn<br>
 * @version <strong>Ecointel v1.0.0</strong><br>
 */
public class DefaultErrorHandlerImpl implements ErrorHandler
{
	
	private static final Log Logger = LogFactory.getLog(DefaultErrorHandlerImpl.class);


	public void handleErrorOnInit(MemcachedClient client, Throwable error)
	{
		Logger.error("ErrorOnInit",error);
	}


	public void handleErrorOnGet(MemcachedClient client, Throwable error, String cacheKey)
	{
		Logger.error(new StringBuilder("ErrorOnGet, cacheKey: ").append(cacheKey).toString(),error);
	}


	public void handleErrorOnGet(MemcachedClient client, Throwable error, String[] cacheKeys)
	{
		Logger.error(new StringBuilder("ErrorOnGet, cacheKey: ").append(cacheKeys).toString(),error);
	}


	public void handleErrorOnSet(MemcachedClient client, Throwable error, String cacheKey)
	{
		Logger.error(new StringBuilder("ErrorOnSet, cacheKey: ").append(cacheKey).toString(),error);
	}


	public void handleErrorOnDelete(MemcachedClient client, Throwable error, String cacheKey)
	{
		Logger.error(new StringBuilder("ErrorOnDelete, cacheKey: ").append(cacheKey).toString(),error);
	}


	public void handleErrorOnFlush(MemcachedClient client, Throwable error)
	{
		Logger.error("ErrorOnFlush",error);
	}


	public void handleErrorOnStats(MemcachedClient client, Throwable error)
	{
		Logger.error("ErrorOnStats",error);
	}
	
}
