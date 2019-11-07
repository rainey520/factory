package com.ruoyi.project.factory.outOrder.service;

import com.ruoyi.common.constant.OrderConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.mapper.DevCompanyMapper;
import com.ruoyi.project.factory.customer.domain.Customer;
import com.ruoyi.project.factory.customer.mapper.CustomerMapper;
import com.ruoyi.project.factory.order.domain.Order;
import com.ruoyi.project.factory.order.mapper.OrderMapper;
import com.ruoyi.project.factory.orderDetail.domain.OrderDetail;
import com.ruoyi.project.factory.orderDetail.mapper.OrderDetailMapper;
import com.ruoyi.project.factory.outOrder.domain.OutItem;
import com.ruoyi.project.factory.outOrder.domain.OutOrder;
import com.ruoyi.project.factory.outOrder.mapper.OutOrderMapper;
import com.ruoyi.project.factory.outOrderDetail.domain.OutOrderDetail;
import com.ruoyi.project.factory.outOrderDetail.mapper.OutOrderDetailMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 出库单主 服务层实现
 *
 * @author sj
 * @date 2019-10-31
 */
@Service
public class OutOrderServiceImpl implements IOutOrderService {

    @Autowired
    private OutOrderMapper outOrderMapper;

    @Autowired
    private OutOrderDetailMapper outOrderDetailMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private DevCompanyMapper companyMapper;

    @Autowired
    private CustomerMapper customerMapper;


    /**
     * 查询出库单主信息
     *
     * @param id 出库单主ID
     * @return 出库单主信息
     */
    @Override
    public OutOrder selectOutOrderById(Integer id) {
        return outOrderMapper.selectOutOrderById(id);
    }

    /**
     * 查询出库单主列表
     *
     * @param outOrder 出库单主信息
     * @return 出库单主集合
     */
    @Override
    public List<OutOrder> selectOutOrderList(OutOrder outOrder) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        outOrder.setCompanyId(user.getCompanyId());
        return outOrderMapper.selectOutOrderList(outOrder);
    }

    /**
     * 新增出库单主
     *
     * @param outOrder 出库单主信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOutOrder(OutOrder outOrder) {
        User user = JwtUtil.getUser();
        if (user == null) {
            throw new BusinessException(UserConstants.NOT_LOGIN);
        }
        // 客户出货退货类型
        Integer outType = outOrder.getOutType();
        // 自动生成客户出库单
        String outCode = CodeUtils.getCusOutCode();
        // 属于客户退货情况
        if (OrderConstants.OUT_TYPE_CUS_BACK.equals(outType)) {
            outCode = CodeUtils.getCusBackCode();
            // 属于外加工出库单情况
        } else if (OrderConstants.OUT_TYPE_WJG_OUTHOUSE.equals(outType)) {
            outCode = CodeUtils.getWjgOutCode();
            // 属于外加工入库单情况
        } else if (OrderConstants.OUT_TYPE_WJG_INHOUSE.equals(outType)) {
            outCode = CodeUtils.getWjgBackCode();
        }
        outOrder.setOutCode(outCode);
        outOrder.setCompanyId(user.getCompanyId());
        outOrder.setOutTime(new Date());
        outOrder.setStatus(OrderConstants.OUT_ORDER_NO_CONFIRM);
        outOrderMapper.insertOutOrder(outOrder);
        //订单总数量
        int totalNum = 0;
        //订单总金额
        float totalPrice = 0F;
        if (StringUtils.isNotEmpty(outOrder.getDetailList())) {
            for (OutOrderDetail outOrderDetail : outOrder.getDetailList()) {
                outOrderDetail.setOutId(outOrder.getId());
                outOrderDetail.setOutCode(outCode);
                outOrderDetail.setCompanyId(user.getCompanyId());
                outOrderDetail.setOutTime(new Date());
                totalNum += outOrderDetail.getOutNumber();
                totalPrice += outOrderDetail.getTotalPrice();
                // 属于客户退货或者外加工入库情况
                if (OrderConstants.OUT_TYPE_CUS_BACK.equals(outType) || OrderConstants.OUT_TYPE_WJG_INHOUSE.equals(outType)) {
                    outOrderDetail.setOutNumber(-outOrderDetail.getOutNumber());
                    outOrderDetail.setTotalPrice(-outOrderDetail.getTotalPrice());
                }
                outOrderDetailMapper.insertOutOrderDetail(outOrderDetail);
            }
        }
        // 属于客户退货或者外加工入库情况
        if (OrderConstants.OUT_TYPE_CUS_BACK.equals(outType) || OrderConstants.OUT_TYPE_WJG_INHOUSE.equals(outType)) {
            totalNum = -totalNum;
            totalPrice = -totalPrice;
        }
        outOrder.setOutNum(totalNum);
        outOrder.setOutPrice(totalPrice);
        return outOrderMapper.updateOutOrder(outOrder);
    }

    /**
     * 修改出库单主
     *
     * @param outOrder 出库单主信息
     * @return 结果
     */
    @Override
    public int updateOutOrder(OutOrder outOrder) {
        return outOrderMapper.updateOutOrder(outOrder);
    }

    /**
     * 删除出库单主对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteOutOrderByIds(String ids) {
        outOrderDetailMapper.deleteOutOrderDetailByOutIds(Convert.toStrArray(ids));
        return outOrderMapper.deleteOutOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 确认下单信息，数据不可修改
     *
     * @param outOrder 下单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int confirmOutOrder(OutOrder outOrder) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return 0;
        }
        OutOrder outOrderInfo = outOrderMapper.selectOutOrderById(outOrder.getId());
        if (outOrderInfo == null) {
            return 0;
        }
        // 设置为已经确认
        outOrderInfo.setStatus(OrderConstants.OUT_ORDER_CONFIRMED);
        // 确认日期
        outOrderInfo.setOutTime(new Date());

        // 出入库属于客户类型才进行订单相关操作
        if (OrderConstants.OUT_TYPE_CUS_OUT.equals(outOrderInfo.getOutType()) || OrderConstants.OUT_TYPE_CUS_BACK.equals(outOrderInfo.getOutType())) {
            // 针对客户出库操作
            Order order = null;
            OrderDetail orderDetail = null;
            // 查询出库单明细
            List<OutOrderDetail> outOrderDetailList = outOrderDetailMapper.selectOutOrderDetailByOutId(outOrderInfo.getId());
            /**
             * 操作相关订单列表
             */
            for (OutOrderDetail outDetail : outOrderDetailList) {
                // 查询订单主表
                order = orderMapper.selectOrderByOrderCode(user.getCompanyId(), outDetail.getOrderCode());
                if (order != null) {
                    order.setDeliveredNum(order.getDeliveredNum() + outDetail.getOutNumber());
                    // 交付中
                    order.setStatus(OrderConstants.IN_DELIVERY);
                    orderMapper.updateOrder(order);
                }
                // 查询订单主表的明细
                orderDetail = orderDetailMapper.selectOrderDetailById(outDetail.getOrderId());
                if (orderDetail != null) {
                    orderDetail.setDeliveredNum(orderDetail.getDeliveredNum() + outDetail.getOutNumber());
                    orderDetail.setUndeliveredNum(orderDetail.getTotalNum() - orderDetail.getDeliveredNum());
                    orderDetailMapper.updateOrderDetail(orderDetail);
                }
            }
        }
        return outOrderMapper.updateOutOrder(outOrderInfo);
    }


    /**
     * 查看打印明细
     *
     * @param outId 主表id
     * @return 结果
     */
    @Override
    public OutItem showOutOrderDetail(Integer outId) {

        OutItem outItem = new OutItem();
        outItem.setOutDate(new Date());
        OutOrder outOrder = outOrderMapper.selectOutOrderById(outId);
        if (outOrder == null) {
            return outItem;
        }
        outItem.setOutDate(outOrder.getOutTime());
        outItem.setSign(outOrder.getStatus());
        outItem.setOutCode(outOrder.getOutCode());
        if (OrderConstants.OUT_TYPE_CUS_OUT.equals(outOrder.getOutType())) {
            outItem.setOutMsg("客户出库单");
        } else if (OrderConstants.OUT_TYPE_CUS_BACK.equals(outOrder.getOutType())) {
            outItem.setOutMsg("客户退货单");
        } else if (OrderConstants.OUT_TYPE_WJG_OUTHOUSE.equals(outOrder.getOutType())) {
            outItem.setOutMsg("外加工出库单");
        } else {
            outItem.setOutMsg("外加工入库单");
        }

        // 查询公司信息
        DevCompany company = companyMapper.selectDevCompanyById(outOrder.getCompanyId());
        outItem.setCompany(company);

        // 查询客户信息
        Customer customer = customerMapper.selectCustomerById(outOrder.getCusId());
        outItem.setCustomer(customer);
        float totalPrice = 0.0F;
        // 查询明细
        List<OutOrderDetail> outOrderDetailList = outOrderDetailMapper.selectOutOrderDetailByOutId(outOrder.getId());
        String pnRemark = null;
        for (OutOrderDetail outOrderDetail : outOrderDetailList) {
            pnRemark = outOrderDetail.getPnRemark();
            if (StringUtils.isNotEmpty(pnRemark) && pnRemark.length() >= 19) {
                outOrderDetail.setPnRemark(pnRemark.substring(0,19)+"..");
            } else {
                outOrderDetail.setPnRemark(pnRemark);
            }
            totalPrice += outOrderDetail.getTotalPrice();
        }
        outItem.setTotalPrice(totalPrice);
        outItem.setDetailList(outOrderDetailList);
        return outItem;
    }
}
