package com.lshop.common.tag;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvShopProduct;

/**
 * metro风格商品列表
 * 
 * @author caicl
 * 
 */
public class MetroProductTag extends BodyTagSupport {
	private static final long serialVersionUID = 5031033107828033610L;
	private List<LvShopProduct> products;// 显示商品列表
	private String currency;//币种
	private Integer maxDisplay = 0;// 最大显示个数,默认全部显示

	@Override
	public int doEndTag() throws JspException {
		maxDisplay = 0==maxDisplay.intValue() ? new Integer(products.size()) : maxDisplay;
		maxDisplay = new Integer(Math.min(maxDisplay.intValue(), products.size()));
		StringBuilder html = new StringBuilder();
		for (int i=0; i<maxDisplay; i++) {
			LvShopProduct product = products.get(i);
			
			//没有活动价
			html.append("<li>")
				.append("<a href='"+product.getUrl()+"'>")
				.append("<div class='rmai'></div>")
				.append("<div class='product_pr'><img src='"+product.getPimage()+"' width='196' height='172'></div>")
				.append("<p class='p_price' f='"+product.getStoreId()+"' p='"+product.getProductCode()+"'>")
				
//				.append("<span class='mingcheng' f='"+product.getStoreId()+"' p='"+product.getProductCode()+"'>"+product.getProductName()+"</span>");
				.append("<span class='mingcheng' >"+product.getProductName()+"</span>");
				
				if(product.getOrignalPrice().equals(product.getProductPrice())){
				  html.append("<span class='price'><span class='price2' ><font class='usd'>"+currency+"</font> "+product.getProductPrice());
				  if(ObjectUtils.isNotEmpty(product.getIsGift())&&product.getIsGift()==1){
					  html.append("<img src='http://res.mtvpad.com/www/res/images/zp_icon.png' width='40' height='22' />");
				  }
				  html.append("</span>" );
				}else{ // <span class="price">原价：<span class="price3"> USD 899</span></span>
				  html.append("<span class='price'>原價：<span class='price3'>"+currency+" "+product.getOrignalPrice()+"</span></span> ");
				  html.append("<span class='price'>活動價:<span class='price2'><font class='usd'>"+currency+"</font> "+product.getProductPrice()+"</span>" );
				}
			    html.append("</span>");
				html.append("</p>").append("</a>").append("</li>");
				System.out.println(html);
		}
		
		JspWriter out = pageContext.getOut();
		try {
			out.write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	public List<LvShopProduct> getProducts() {
		return products;
	}

	public void setProducts(List<LvShopProduct> products) {
		this.products = products;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getMaxDisplay() {
		return maxDisplay;
	}

	public void setMaxDisplay(Integer maxDisplay) {
		this.maxDisplay = maxDisplay;
	}
}
