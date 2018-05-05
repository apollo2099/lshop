package com.gv.html.component;

import java.util.List;

import org.apache.cxf.ws.rm.v200702.SequenceAcknowledgement.Final;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.lshop.common.pojo.logic.LvLinkUrl;
import com.lshop.common.pojo.logic.LvStore;


public interface HtmlComponent {
	/**
	 * 静态化链接
	 * @param url
	 * @param abHtml
	 * @return
	 */
	boolean doStatic(String url, String abHtml);
	/**
	 * 若是动态文件对应的静态链接,则生成静态文件并返回文件的绝对路径;否则,返回null
	 * @param huri
	 * @param shopFlag
	 * @return
	 */
	String processHtmlRequest(String huri, String shopFlag);
	/**
	 * 新建线程异步执行html单个文件更新
	 * @param huri
	 * @param shopFlag
	 */
	void asyncProcessHtml(final String huri, final String shopFlag);
}
