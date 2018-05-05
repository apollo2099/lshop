package com.lshop.common.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.gv.core.datastructure.page.Pagination;

@SuppressWarnings("serial")
public class SimplePaginate extends TagSupport {

	@Override
	public int doEndTag() throws JspException {

		JspWriter out = pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();

		try {
			out
					.write("<select onchange='gotoPage(this.options[this.selectedIndex].value)'>");
			out.print("\n");

			Pagination pagination = (Pagination) request
					.getAttribute("pagination");

			if (pagination != null) {
				for (int i = 1; i <= pagination.getTotalPage(); i++) {
					if (i == pagination.getPageNum()) {
						out.write("<option selected='selected' value="
								+ pagination.getPageNum() + ">第"
								+ pagination.getPageNum() + "頁</option>");
						out.write("\n");
						continue;
					}
					out.write("<option value='" + i + "'>第" + i + "頁</option>");
					out.write("\n");

				}
				out.write("</select>");
			} else {
				out.write("<option selected='selected' value=" + 1 + ">第" + "1"
						+ "頁</option>");
				out.write("\n");
				out.write("\n");
				out.write("</select>");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return super.doEndTag();
	}

}
