package com.gv.appstore.util;

import org.apache.commons.logging.Log;

public class LogUtil {

	public static void log(Log logger,String log){
		if (logger.isInfoEnabled()) {
			logger.info(log);
		}
	}
}
