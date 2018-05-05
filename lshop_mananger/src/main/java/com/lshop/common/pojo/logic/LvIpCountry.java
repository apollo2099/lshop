package com.lshop.common.pojo.logic;

import com.gv.core.datastructure.Key;

/**
 * LvIpCountry entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LvIpCountry extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String ipStart;
	private String ipEnd;
	private Long longIpStart;
	private Long longIpEnd;
	private String countrycode;
	private String country;
	private Integer countryid;

	// Constructors

	/** default constructor */
	public LvIpCountry() {
	}

	/** full constructor */
	public LvIpCountry(String ipStart, String ipEnd, Long longIpStart,
			Long longIpEnd, String countrycode, String country,
			Integer countryid) {
		this.ipStart = ipStart;
		this.ipEnd = ipEnd;
		this.longIpStart = longIpStart;
		this.longIpEnd = longIpEnd;
		this.countrycode = countrycode;
		this.country = country;
		this.countryid = countryid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpStart() {
		return this.ipStart;
	}

	public void setIpStart(String ipStart) {
		this.ipStart = ipStart;
	}

	public String getIpEnd() {
		return this.ipEnd;
	}

	public void setIpEnd(String ipEnd) {
		this.ipEnd = ipEnd;
	}

	public Long getLongIpStart() {
		return this.longIpStart;
	}

	public void setLongIpStart(Long longIpStart) {
		this.longIpStart = longIpStart;
	}

	public Long getLongIpEnd() {
		return this.longIpEnd;
	}

	public void setLongIpEnd(Long longIpEnd) {
		this.longIpEnd = longIpEnd;
	}

	public String getCountrycode() {
		return this.countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getCountryid() {
		return this.countryid;
	}

	public void setCountryid(Integer countryid) {
		this.countryid = countryid;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}