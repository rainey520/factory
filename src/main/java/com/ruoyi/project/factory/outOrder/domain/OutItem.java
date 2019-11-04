package com.ruoyi.project.factory.outOrder.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.factory.customer.domain.Customer;
import com.ruoyi.project.factory.outOrderDetail.domain.OutOrderDetail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 出库入库单打印明细封装
 * @Author: Rainey
 * @Date: 2019/11/1 15:11
 * @Version: 1.0
 **/
public class OutItem implements Serializable {
    private static final long serialVersionUID = -5132292641263590154L;
    /** 公司信息 */
    private DevCompany company;
    /** 客户信息 */
    private Customer customer;
    /** 确认标记 0、未确认不可打印，1、已确认可打印 */
    private Integer sign;
    /** 类型展示 */
    private String outMsg;
    /** 单号 */
    private String outCode;
    /** 打印日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date outDate;
    /** 总金额 */
    private Float totalPrice;
    /** 明细 */
    private List<OutOrderDetail> detailList;

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public DevCompany getCompany() {
        return company;
    }

    public void setCompany(DevCompany company) {
        this.company = company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOutMsg() {
        return outMsg;
    }

    public void setOutMsg(String outMsg) {
        this.outMsg = outMsg;
    }

    public String getOutCode() {
        return outCode;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OutOrderDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<OutOrderDetail> detailList) {
        this.detailList = detailList;
    }

    @Override
    public String toString() {
        return "OutItem{" +
                "company=" + company +
                ", customer=" + customer +
                ", outMsg='" + outMsg + '\'' +
                ", outCode='" + outCode + '\'' +
                ", outDate=" + outDate +
                ", totalPrice=" + totalPrice +
                ", detailList=" + detailList +
                '}';
    }
}
