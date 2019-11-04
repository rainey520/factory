package com.ruoyi.project.factory.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 客户表 fac_customer
 * 
 * @author sj
 * @date 2019-10-31
 */
public class Customer extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 客户主键id */
	private Integer id;
	/** 客户名称 */
	private String cusName;
	/** 客户联系方式 */
	private String cusPhone;
	/** 客户联系人 */
	private String cusPerson;
	/** 客户地址 */
	private String cusAddress;
	/** 结款期限 */
	private String cusPayDate;
	/** 公司id */
	private Integer companyId;
	/** 类型(1、客户，2、外加工厂) */
	private Integer cusType;

	public Integer getCusType() {
		return cusType;
	}

	public void setCusType(Integer cusType) {
		this.cusType = cusType;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCusName(String cusName) 
	{
		this.cusName = cusName;
	}

	public String getCusName() 
	{
		return cusName;
	}
	public void setCusPhone(String cusPhone) 
	{
		this.cusPhone = cusPhone;
	}

	public String getCusPhone() 
	{
		return cusPhone;
	}
	public void setCusPerson(String cusPerson) 
	{
		this.cusPerson = cusPerson;
	}

	public String getCusPerson() 
	{
		return cusPerson;
	}
	public void setCusAddress(String cusAddress) 
	{
		this.cusAddress = cusAddress;
	}

	public String getCusAddress() 
	{
		return cusAddress;
	}
	public void setCusPayDate(String cusPayDate) 
	{
		this.cusPayDate = cusPayDate;
	}

	public String getCusPayDate() 
	{
		return cusPayDate;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", cusName='" + cusName + '\'' +
				", cusPhone='" + cusPhone + '\'' +
				", cusPerson='" + cusPerson + '\'' +
				", cusAddress='" + cusAddress + '\'' +
				", cusPayDate='" + cusPayDate + '\'' +
				", companyId=" + companyId +
				", cusType=" + cusType +
				'}';
	}
}
