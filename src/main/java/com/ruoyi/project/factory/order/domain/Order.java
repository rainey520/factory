package com.ruoyi.project.factory.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.factory.orderDetail.domain.OrderDetail;

import java.util.Date;
import java.util.List;

/**
 * 订单主表 fac_order
 * 
 * @author sj
 * @date 2019-10-31
 */
public class Order extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 订单主键 */
	private Integer id;
	/** 公司id */
	private Integer companyId;
	/** 客户id */
	private Integer cusId;
	/** 客户名称 */
	private String cusName;
	/** 订单号 */
	private String orderCode;
	/** 订单状态 */
	private Integer status;
	/** 已交付总数量 */
	private Integer deliveredNum;
	/** 订单总数 */
	private Integer totalNumber;
	/** 订单总金额 */
	private Float totalPrice;
	/** 订单交付日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date deliverTime;
	/** 订单创建日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createTime;
	/** 订单备注 */
	private String remark;
	/** 订单明细 */
	private List<OrderDetail> detailList;
	/** 客户编码 */
	private String pnCode;

	/********************** 客户信息 *******************************/
	/** 客户联系方式 */
	private String cusPhone;
	/** 客户联系人 */
	private String cusPerson;
	/** 客户地址 */
	private String cusAddress;

	public String getPnCode() {
		return pnCode;
	}

	public void setPnCode(String pnCode) {
		this.pnCode = pnCode;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getCusPerson() {
		return cusPerson;
	}

	public void setCusPerson(String cusPerson) {
		this.cusPerson = cusPerson;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public Integer getDeliveredNum() {
		return deliveredNum;
	}

	public void setDeliveredNum(Integer deliveredNum) {
		this.deliveredNum = deliveredNum;
	}

	public List<OrderDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OrderDetail> detailList) {
		this.detailList = detailList;
	}

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
	public void setOrderCode(String orderCode) 
	{
		this.orderCode = orderCode;
	}

	public String getOrderCode() 
	{
		return orderCode;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setTotalNumber(Integer totalNumber) 
	{
		this.totalNumber = totalNumber;
	}

	public Integer getTotalNumber() 
	{
		return totalNumber;
	}
	public void setTotalPrice(Float totalPrice) 
	{
		this.totalPrice = totalPrice;
	}

	public Float getTotalPrice() 
	{
		return totalPrice;
	}
	public void setDeliverTime(Date deliverTime) 
	{
		this.deliverTime = deliverTime;
	}

	public Date getDeliverTime() 
	{
		return deliverTime;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", companyId=" + companyId +
				", cusId=" + cusId +
				", cusName='" + cusName + '\'' +
				", orderCode='" + orderCode + '\'' +
				", status=" + status +
				", deliveredNum=" + deliveredNum +
				", totalNumber=" + totalNumber +
				", totalPrice=" + totalPrice +
				", deliverTime=" + deliverTime +
				", createTime=" + createTime +
				", remark='" + remark + '\'' +
				", detailList=" + detailList +
				'}';
	}
}
