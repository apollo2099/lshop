/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.common.pojo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public class WxKeywordsReply extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MODIFY_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //规则名称       db_column: name 
    
	private java.lang.String name;
	
      //多个关键词使用英文逗号隔开       db_column: keywords 
    
	private java.lang.String keywords;
	
      //素材类型：1-文本，2-图片，3-语音，4-视频，5-音乐，6-图文       db_column: material_type 
    
	private java.lang.Integer materialType;
	
      //对应素材的id值       db_column: material_id 
    
	private java.lang.Integer materialId;
	
      //公众号配置id       db_column: wxh_config_id 
    
	private java.lang.Integer wxhConfigId;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //修改时间       db_column: modify_time 
    
	private java.util.Date modifyTime;
	//columns END


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
	 * 规则名称       db_column: name 
	 */
	public void setName(java.lang.String value) {
		this.name = value;
	}
	/**
	 * 规则名称       db_column: name 
	 */
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * 多个关键词使用英文逗号隔开       db_column: keywords 
	 */
	public void setKeywords(java.lang.String value) {
		this.keywords = value;
	}
	/**
	 * 多个关键词使用英文逗号隔开       db_column: keywords 
	 */
	public java.lang.String getKeywords() {
		return this.keywords;
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
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	/**
	 * 创建时间       db_column: create_time 
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 修改时间       db_column: modify_time 
	 */
	public void setModifyTime(java.util.Date value) {
		this.modifyTime = value;
	}
	/**
	 * 修改时间       db_column: modify_time 
	 */
	public java.util.Date getModifyTime() {
		return this.modifyTime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Keywords",getKeywords())
			.append("MaterialType",getMaterialType())
			.append("MaterialId",getMaterialId())
			.append("WxhConfigId",getWxhConfigId())
			.append("CreateTime",getCreateTime())
			.append("ModifyTime",getModifyTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxKeywordsReply == false) return false;
		if(this == obj) return true;
		WxKeywordsReply other = (WxKeywordsReply)obj;
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

