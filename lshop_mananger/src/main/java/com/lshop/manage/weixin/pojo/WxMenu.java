/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.pojo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;
import com.gv.core.util.DateConvertUtils;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public class WxMenu extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //菜单名称       db_column: name 
    
	private java.lang.String name;
	
      //菜单类型：click-单击菜单后推送消息,view-URL链接       db_column: menu_type 
    
	private java.lang.String menuType;
	
      //菜单的对外标识key       db_column: menu_key 
    
	private java.lang.String menuKey;
	
      //url地址       db_column: menu_url 
    
	private java.lang.String menuUrl;
	
      //素材类型：1-文本，2-图片，3-语音，4-视频，5-音乐，6-图文       db_column: material_type 
    
	private java.lang.Integer materialType;
	
      //对应素材的id值       db_column: material_id 
    
	private java.lang.Integer materialId;
	
      //父菜单的id       db_column: menu_parent 
    
	private java.lang.Integer menuParent;
	
      //公众号配置id       db_column: wxh_config_id 
    
	private java.lang.Integer wxhConfigId;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	
	/**
	 * 菜单排序值
	 */
	private java.lang.Integer orderValue;

	/**
	 * id       db_column: id 
	 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	/**
	 * id       db_column: id 
	 */
	public java.lang.Integer getId() {
		return this.id;
	}
	/**
	 * 菜单名称       db_column: name 
	 */
	public void setName(java.lang.String value) {
		this.name = value;
	}
	/**
	 * 菜单名称       db_column: name 
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * 菜单类型：click-单击菜单后推送消息,view-URL链接       db_column: menu_type 
	 */
	public void setMenuType(java.lang.String value) {
		this.menuType = value;
	}
	/**
	 * 菜单类型：click-单击菜单后推送消息,view-URL链接       db_column: menu_type 
	 */
	public java.lang.String getMenuType() {
		return this.menuType;
	}
	/**
	 * 菜单的对外标识key       db_column: menu_key 
	 */
	public void setMenuKey(java.lang.String value) {
		this.menuKey = value;
	}
	/**
	 * 菜单的对外标识key       db_column: menu_key 
	 */
	public java.lang.String getMenuKey() {
		return this.menuKey;
	}
	/**
	 * url地址       db_column: menu_url 
	 */
	public void setMenuUrl(java.lang.String value) {
		this.menuUrl = value;
	}
	/**
	 * url地址       db_column: menu_url 
	 */
	public java.lang.String getMenuUrl() {
		return this.menuUrl;
	}
	/**
	 * 素材类型：1-文本，2-图片，3-语音，4-视频，5-音乐，6-图文       db_column: material_type 
	 */
	public void setMaterialType(java.lang.Integer value) {
		this.materialType = value;
	}
	/**
	 * 素材类型：1-文本，2-图片，3-语音，4-视频，5-音乐，6-图文       db_column: material_type 
	 */
	public java.lang.Integer getMaterialType() {
		return this.materialType;
	}
	/**
	 * 对应素材的id值       db_column: material_id 
	 */
	public void setMaterialId(java.lang.Integer value) {
		this.materialId = value;
	}
	/**
	 * 对应素材的id值       db_column: material_id 
	 */
	public java.lang.Integer getMaterialId() {
		return this.materialId;
	}
	/**
	 * 父菜单的id       db_column: menu_parent 
	 */
	public void setMenuParent(java.lang.Integer value) {
		this.menuParent = value;
	}
	/**
	 * 父菜单的id       db_column: menu_parent 
	 */
	public java.lang.Integer getMenuParent() {
		return this.menuParent;
	}
	/**
	 * 公众号配置id       db_column: wxh_config_id 
	 */
	public void setWxhConfigId(java.lang.Integer value) {
		this.wxhConfigId = value;
	}
	/**
	 * 公众号配置id       db_column: wxh_config_id 
	 */
	public java.lang.Integer getWxhConfigId() {
		return this.wxhConfigId;
	}
	/**
	 * 创建时间       db_column: create_time 
	 */
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	/**
	 * 创建时间       db_column: create_time 
	 */
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	/**
	 * 创建时间       db_column: create_time 
	 */
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	/**
	 * 创建时间       db_column: create_time 
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public java.lang.Integer getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(java.lang.Integer orderValue) {
		this.orderValue = orderValue;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("MenuType",getMenuType())
			.append("MenuKey",getMenuKey())
			.append("MenuUrl",getMenuUrl())
			.append("MaterialType",getMaterialType())
			.append("MaterialId",getMaterialId())
			.append("MenuParent",getMenuParent())
			.append("WxhConfigId",getWxhConfigId())
			.append("CreateTime",getCreateTime())
			.append("OrderValue",getOrderValue())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxMenu == false) return false;
		if(this == obj) return true;
		WxMenu other = (WxMenu)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
}

