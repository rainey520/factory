package com.ruoyi.project.factory.outOrder.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.factory.outOrderDetail.domain.OutOrderDetail;

import java.util.Date;
import java.util.List;

/**
 * 出库单主表 fac_out_order
 * 
 * @author sj
 * @date 2019-10-31
 */
public class OutOrder extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 出库单主表id */
	private Integer id;
	/** 公司id */
	private Integer companyId;
	/** 客户id */
	private Integer cusId;
	/** 客户名称 */
	private String cusName;
	/** 出货单号 */
	private String outCode;
	/** 类型(0、客户出货单，1、客户退货单，2、外加工出库单，3、外加工入库单) */
	private Integer outType;
	/** 交付日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date outTime;
	/** 总数量 */
	private Integer outNum;
	/** 总价格 */
	private Float outPrice;
	/** 状态 */
	private Integer status;
	/** 备注 */
	private String remark;
	/** 创建日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date cTime;
	/** 明细列表 */
	private List<OutOrderDetail> detailList;

	/** 客户编码 */
	private String pnCode;
	/** 订单号 */
	private String orderCode;
	/** 客户联系方式 */
	private String cusPhone;
	/** 客户地址 */
	private String cusAddress;

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getPnCode() {
		return pnCode;
	}

	public void setPnCode(String pnCode) {
		this.pnCode = pnCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public Integer getOutNum() {
		return outNum;
	}

	public void setOutNum(Integer outNum) {
		this.outNum = outNum;
	}

	public Float getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(Float outPrice) {
		this.outPrice = outPrice;
	}

	public List<OutOrderDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OutOrderDetail> detailList) {
		this.detailList = detailList;
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
	public void setOutCode(String outCode) 
	{
		this.outCode = outCode;
	}

	public String getOutCode() 
	{
		return outCode;
	}
	public void setOutType(Integer outType) 
	{
		this.outType = outType;
	}

	public Integer getOutType() 
	{
		return outType;
	}
	public void setOutTime(Date outTime) 
	{
		this.outTime = outTime;
	}

	public Date getOutTime() 
	{
		return outTime;
	}

	@Override
	public String toString() {
		return "OutOrder{" +
				"id=" + id +
				", companyId=" + companyId +
				", cusId=" + cusId +
				", outCode='" + outCode + '\'' +
				", outType=" + outType +
				", outTime=" + outTime +
				", detailList=" + detailList +
				'}';
	}
}
