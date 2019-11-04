package com.ruoyi.project.factory.orderDetail.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 订单明细表 fac_order_detail
 * 
 * @author sj
 * @date 2019-10-31
 */
public class OrderDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 订单明细id */
	private Integer id;
	/** 公司id */
	private Integer companyId;
	/** 客户id */
	private Integer cusId;
	/** 客户名称 */
	private String cusName;
	/** 订单主表id */
	private Integer orderId;
	/** 订单编号 */
	private String orderCode;
	/** 客户编码 */
	private String pnCode;
	/** 编码描述 */
	private String pnRemark;
	/** 未交付数量 */
	private Integer undeliveredNum;
	/** 已交付数量 */
	private Integer deliveredNum;
	/** 总交付数量 */
	private Integer totalNum;
	/** 单价 */
	private Float unitPrice;
	/** 单位 */
	private String pnUnit;
	/** 总价 */
	private Float totalPrice;
	/** 退货数量 */
	private Integer backNum;

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getPnUnit() {
		return pnUnit;
	}

	public void setPnUnit(String pnUnit) {
		this.pnUnit = pnUnit;
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
	public void setUndeliveredNum(Integer undeliveredNum) 
	{
		this.undeliveredNum = undeliveredNum;
	}

	public Integer getUndeliveredNum() 
	{
		return undeliveredNum;
	}
	public void setDeliveredNum(Integer deliveredNum) 
	{
		this.deliveredNum = deliveredNum;
	}

	public Integer getDeliveredNum() 
	{
		return deliveredNum;
	}
	public void setTotalNum(Integer totalNum) 
	{
		this.totalNum = totalNum;
	}

	public Integer getTotalNum() 
	{
		return totalNum;
	}
	public void setUnitPrice(Float unitPrice) 
	{
		this.unitPrice = unitPrice;
	}

	public Float getUnitPrice() 
	{
		return unitPrice;
	}
	public void setTotalPrice(Float totalPrice) 
	{
		this.totalPrice = totalPrice;
	}

	public Float getTotalPrice() 
	{
		return totalPrice;
	}
	public void setBackNum(Integer backNum) 
	{
		this.backNum = backNum;
	}

	public Integer getBackNum() 
	{
		return backNum;
	}

	@Override
	public String toString() {
		return "OrderDetail{" +
				"id=" + id +
				", companyId=" + companyId +
				", cusId=" + cusId +
				", orderId=" + orderId +
				", orderCode='" + orderCode + '\'' +
				", pnCode='" + pnCode + '\'' +
				", pnRemark='" + pnRemark + '\'' +
				", undeliveredNum=" + undeliveredNum +
				", deliveredNum=" + deliveredNum +
				", totalNum=" + totalNum +
				", unitPrice=" + unitPrice +
				", pnUnit='" + pnUnit + '\'' +
				", totalPrice=" + totalPrice +
				", backNum=" + backNum +
				'}';
	}
}
