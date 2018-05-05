/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.common.pojo.logic;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;
import com.gv.core.util.DateConvertUtils;

/**
 * @author 好视网络-网站研发部：tangd
 * @version 1.0
 * @since 1.0
 */
 public class LvPatternCountry extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATED_DATE = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_LAST_UPDATED_DATE = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //VERSION       db_column: version 
    
	private java.lang.Integer version;
	
      //规格(1=美规,2=澳规,3=英规,4=欧规,5=韩规)       db_column: pattern_code 
    
	private Short patternCode;
	
      //国家名称       db_column: country_name 
    
	private java.lang.String countryName;
	
      //国家编码       db_column: country_code 
    
	private java.lang.String countryCode;
	
	private java.lang.Integer countryId;
	//-1=删除,1=正常       db_column: status 
    
	private java.lang.Integer status;
	
      //创建人       db_column: created_by 
    
	private java.lang.String createdBy;
	
      //创建时间       db_column: created_date 
    
	private java.util.Date createdDate;
	
      //最后更新人       db_column: last_updated_by 
    
	private java.lang.String lastUpdatedBy;
	
      //最后更新时间       db_column: last_updated_date 
    
	private java.util.Date lastUpdatedDate;
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
	 * VERSION       db_column: version 
	 */
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	/**
	 * VERSION       db_column: version 
	 */
	public java.lang.Integer getVersion() {
		return this.version;
	}
	/**
	 * 规格(1=美规,2=澳规,3=英规,4=欧规,5=韩规)       db_column: pattern_code 
	 */
	public void setPatternCode(Short value) {
		this.patternCode = value;
	}
	/**
	 * 规格(1=美规,2=澳规,3=英规,4=欧规,5=韩规)       db_column: pattern_code 
	 */
	public Short getPatternCode() {
		return this.patternCode;
	}
	/**
	 * 国家名称       db_column: country_name 
	 */
	public void setCountryName(java.lang.String value) {
		this.countryName = value;
	}
	/**
	 * 国家名称       db_column: country_name 
	 */
	public java.lang.String getCountryName() {
		return this.countryName;
	}
	/**
	 * 国家编码       db_column: country_code 
	 */
	public void setCountryCode(java.lang.String value) {
		this.countryCode = value;
	}
	
	
	public java.lang.Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(java.lang.Integer countryId) {
		this.countryId = countryId;
	}
	/**
	 * 国家编码       db_column: country_code 
	 */
	public java.lang.String getCountryCode() {
		return this.countryCode;
	}
	/**
	 * -1=删除,1=正常       db_column: status 
	 */
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	/**
	 * -1=删除,1=正常       db_column: status 
	 */
	public java.lang.Integer getStatus() {
		return this.status;
	}
	/**
	 * 创建人       db_column: created_by 
	 */
	public void setCreatedBy(java.lang.String value) {
		this.createdBy = value;
	}
	/**
	 * 创建人       db_column: created_by 
	 */
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}
	/**
	 * 创建时间       db_column: created_date 
	 */
	public String getCreatedDateString() {
		return DateConvertUtils.format(getCreatedDate(), FORMAT_CREATED_DATE);
	}
	/**
	 * 创建时间       db_column: created_date 
	 */
	public void setCreatedDateString(String value) {
		setCreatedDate(DateConvertUtils.parse(value, FORMAT_CREATED_DATE,java.util.Date.class));
	}
	
	/**
	 * 创建时间       db_column: created_date 
	 */
	public void setCreatedDate(java.util.Date value) {
		this.createdDate = value;
	}
	/**
	 * 创建时间       db_column: created_date 
	 */
	public java.util.Date getCreatedDate() {
		return this.createdDate;
	}
	/**
	 * 最后更新人       db_column: last_updated_by 
	 */
	public void setLastUpdatedBy(java.lang.String value) {
		this.lastUpdatedBy = value;
	}
	/**
	 * 最后更新人       db_column: last_updated_by 
	 */
	public java.lang.String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}
	/**
	 * 最后更新时间       db_column: last_updated_date 
	 */
	public String getLastUpdatedDateString() {
		return DateConvertUtils.format(getLastUpdatedDate(), FORMAT_LAST_UPDATED_DATE);
	}
	/**
	 * 最后更新时间       db_column: last_updated_date 
	 */
	public void setLastUpdatedDateString(String value) {
		setLastUpdatedDate(DateConvertUtils.parse(value, FORMAT_LAST_UPDATED_DATE,java.util.Date.class));
	}
	
	/**
	 * 最后更新时间       db_column: last_updated_date 
	 */
	public void setLastUpdatedDate(java.util.Date value) {
		this.lastUpdatedDate = value;
	}
	/**
	 * 最后更新时间       db_column: last_updated_date 
	 */
	public java.util.Date getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Version",getVersion())
			.append("PatternCode",getPatternCode())
			.append("CountryName",getCountryName())
			.append("CountryCode",getCountryCode())
			.append("Status",getStatus())
			.append("CreatedBy",getCreatedBy())
			.append("CreatedDate",getCreatedDate())
			.append("LastUpdatedBy",getLastUpdatedBy())
			.append("LastUpdatedDate",getLastUpdatedDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvPatternCountry == false) return false;
		if(this == obj) return true;
		LvPatternCountry other = (LvPatternCountry)obj;
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

