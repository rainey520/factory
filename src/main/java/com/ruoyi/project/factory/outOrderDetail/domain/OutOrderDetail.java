package com.ruoyi.project.factory.outOrderDetail.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 出库单明细表 fac_out_order_detail
 * 
 * @author sj
 * @date 2019-10-31
 */
public class OutOrderDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 出库单明细表 */
	private Integer id;
	/** 公司id */
	private Integer companyId;
	/** 客户id */
	private Integer cusId;
	/** 出货单主表id */
	private Integer outId;
	/** 出货单编号 */
	private String outCode;
	/** 订单明细id */
	private Integer orderId;
	/** 订单编号 */
	private String orderCode;
	/** 出库数量(正数代表出库，负数代表退货数量) */
	private Integer outNumber;
	/** 产品单价 */
	private Float pnPrice;
	/** 产品编码 */
	private String pnCode;
	/** 产品单位 */
	private String pnUnit;
	/** 产品描述 */
	private String pnRemark;
	/** 总金额 */
	private Float totalPrice;
	/** 明细描述信息 */
	private String outRemark;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date outTime;

	public String getPnUnit() {
		return pnUnit;
	}

	public void setPnUnit(String pnUnit) {
		this.pnUnit = pnUnit;
	}

	public String getOutCode() {
		return outCode;
	}

	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
	}
	public void setCusId(Integer cusId) 
	{
		this.cusId = cusId;
	}

	public Integer getCusId() 
	{
		return cusId;
	}
	public void setOutId(Integer outId) 
	{
		this.outId = outId;
	}

	public Integer getOutId() 
	{
		return outId;
	}
	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}

	public Integer getOrderId()
	{
		return orderId;
	}
	public void setOrderCode(String orderCode)
	{
		this.orderCode = orderCode;
	}

	public String getOrderCode() 
	{
		return orderCode;
	}
	public void setOutNumber(Integer outNumber) 
	{
		this.outNumber = outNumber;
	}

	public Integer getOutNumber() 
	{
		return outNumber;
	}
	public void setPnPrice(Float pnPrice) 
	{
		this.pnPrice = pnPrice;
	}

	public Float getPnPrice() 
	{
		return pnPrice;
	}
	public void setPnCode(String pnCode) 
	{
		this.pnCode = pnCode;
	}

	public String getPnCode() 
	{
		return pnCode;
	}
	public void setPnRemark(String pnRemark) 
	{
		this.pnRemark = pnRemark;
	}

	public String getPnRemark() 
	{
		return pnRemark;
	}
	public void setTotalPrice(Float totalPrice) 
	{
		this.totalPrice = totalPrice;
	}

	public Float getTotalPrice() 
	{
		return totalPrice;
	}
	public void setOutRemark(String outRemark) 
	{
		this.outRemark = outRemark;
	}

	public String getOutRemark() 
	{
		return outRemark;
	}
	public void setOutTime(Date outTime) 
	{
		this.outTime = outTime;
	}

	public Date getOutTime() 
	{
		return outTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("cusId", getCusId())
            .append("outId", getOutId())
            .append("orderId", getOrderId())
            .append("orderCode", getOrderCode())
            .append("outNumber", getOutNumber())
            .append("pnPrice", getPnPrice())
            .append("pnCode", getPnCode())
            .append("pnRemark", getPnRemark())
            .append("totalPrice", getTotalPrice())
            .append("outRemark", getOutRemark())
            .append("outTime", getOutTime())
            .toString();
    }
}
