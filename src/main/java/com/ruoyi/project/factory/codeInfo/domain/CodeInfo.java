package com.ruoyi.project.factory.codeInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 编码表 fac_code_info
 * 
 * @author sj
 * @date 2019-10-31
 */
public class CodeInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编码主键 */
	private Integer id;
	/** 客户编码 */
	private String pnCode;
	/** 编码名称 */
	private String pnName;
	/** 客户主键id */
	private Integer cusId;
	/** 客户名称*/
	private String cusName;
	/** 规格名称 */
	private String pnRemark;
	/** 单价 */
	private Float pnPrice;
	/** 创建日期 */
	private Date pnTime;
	/** 公司id */
	private Integer companyId;
	/** 单位 */
	private String pnUnit;

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setPnCode(String pnCode) 
	{
		this.pnCode = pnCode;
	}

	public String getPnCode() 
	{
		return pnCode;
	}
	public void setPnName(String pnName) 
	{
		this.pnName = pnName;
	}

	public String getPnName() 
	{
		return pnName;
	}
	public void setCusId(Integer cusId) 
	{
		this.cusId = cusId;
	}

	public Integer getCusId() 
	{
		return cusId;
	}
	public void setPnRemark(String pnRemark) 
	{
		this.pnRemark = pnRemark;
	}

	public String getPnRemark() 
	{
		return pnRemark;
	}
	public void setPnPrice(Float pnPrice) 
	{
		this.pnPrice = pnPrice;
	}

	public Float getPnPrice() 
	{
		return pnPrice;
	}
	public void setPnTime(Date pnTime) 
	{
		this.pnTime = pnTime;
	}

	public Date getPnTime() 
	{
		return pnTime;
	}
	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
	}
	public void setPnUnit(String pnUnit) 
	{
		this.pnUnit = pnUnit;
	}

	public String getPnUnit() 
	{
		return pnUnit;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pnCode", getPnCode())
            .append("pnName", getPnName())
            .append("cusId", getCusId())
            .append("pnRemark", getPnRemark())
            .append("pnPrice", getPnPrice())
            .append("pnTime", getPnTime())
            .append("companyId", getCompanyId())
            .append("pnUnit", getPnUnit())
            .toString();
    }
}
