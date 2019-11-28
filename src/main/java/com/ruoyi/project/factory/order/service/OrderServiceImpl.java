package com.ruoyi.project.factory.order.service;

import com.ruoyi.common.constant.FileConstants;
import com.ruoyi.common.constant.OrderConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.factory.customer.domain.Customer;
import com.ruoyi.project.factory.customer.mapper.CustomerMapper;
import com.ruoyi.project.factory.order.domain.Order;
import com.ruoyi.project.factory.order.mapper.OrderMapper;
import com.ruoyi.project.factory.orderDetail.domain.OrderDetail;
import com.ruoyi.project.factory.orderDetail.mapper.OrderDetailMapper;
import com.ruoyi.project.factory.outOrderDetail.mapper.OutOrderDetailMapper;
import com.ruoyi.project.iso.filesource.domain.FileSourceInfo;
import com.ruoyi.project.iso.filesource.mapper.FileSourceInfoMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 订单主 服务层实现
 *
 * @author sj
 * @date 2019-10-31
 */
@Service("order")
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private OutOrderDetailMapper outOrderDetailMapper;

    @Autowired
    private FileSourceInfoMapper fileSourceInfoMapper;

    /**
     * 查询订单主信息
     *
     * @param id 订单主ID
     * @return 订单主信息
     */
    @Override
    public Order selectOrderById(Integer id) {
        Order order = orderMapper.selectOrderById(id);
        if (order != null) {
            // 查询客户信息
            Customer customer = customerMapper.selectCustomerById(order.getCusId());
            order.setCusPhone(customer.getCusPhone());
            order.setCusAddress(customer.getCusAddress());
            // 设置订单明细
            order.setDetailList(orderDetailMapper.selectOrderByOrderId(order.getId()));
        }
        return order;
    }

    /**
     * 查询订单主列表
     *
     * @param order 订单主信息
     * @return 订单主集合
     */
    @Override
    public List<Order> selectOrderList(Order order) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        List<Order> orders = orderMapper.selectOrderList(order);
        Integer deliveredNum = null;
        for (Order o : orders) {
            deliveredNum = outOrderDetailMapper.selectOutOrderDetailByCode(user.getCompanyId(), o.getOrderCode(), o.getCusId(), null);
            if (!OrderConstants.DELIVERED.equals(o.getStatus())) {
                if (deliveredNum == null) {
                    o.setStatus(OrderConstants.UN_DELIVERED);
                } else{
                    o.setStatus(OrderConstants.IN_DELIVERY);
                }
            }
            o.setDeliveredNum(deliveredNum == null ? 0 : deliveredNum);
        }
        return orders;
    }

    /**
     * 新增订单主
     *
     * @param order 订单主信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOrder(Order order) {
        User user = JwtUtil.getUser();
        if (user == null) {
            throw new BusinessException(UserConstants.NOT_LOGIN);
        }
        // 查询订单号是否重复
        return insertOrder(order, user);
    }

    /**
     * 修改订单主
     *
     * @param order 订单主信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateOrder(Order order) {
        User user = JwtUtil.getUser();
        if (user == null) {
            throw new BusinessException(UserConstants.NOT_LOGIN);
        }
        // 删除之前订单信息
        orderMapper.deleteOrderById(order.getId());
        orderDetailMapper.deleteOrderDetailByOrderId(order.getId());
        // 新增
        return insertOrder(order, user);
    }

    /**
     * 新增订单信息
     *
     * @param order 订单信息
     * @param user  用户信息
     * @return 结果
     */
    private int insertOrder(Order order, User user) {
        if (order != null && StringUtils.isNotEmpty(order.getOrderCode())) {
            Order uniOrder = orderMapper.selectOrderByOrderCode(user.getCompanyId(), order.getOrderCode());
            if (uniOrder != null) {
                throw new BusinessException("订单重复,请重新输入");
            }
        }
        order.setCompanyId(user.getCompanyId());
        order.setCreateTime(new Date());
        order.setStatus(OrderConstants.UN_DELIVERED);
        // 添加主表
        orderMapper.insertOrder(order);
        //订单总数量
        int totalNum = 0;
        //订单总金额
        float totalPrice = 0F;
        if (StringUtils.isNotEmpty(order.getDetailList())) {
            for (OrderDetail orderDetail : order.getDetailList()) {
                orderDetail.setCompanyId(user.getCompanyId());
                orderDetail.setUndeliveredNum(orderDetail.getTotalNum());
                orderDetail.setOrderId(order.getId());
                totalNum += orderDetail.getTotalNum();
                totalPrice += orderDetail.getTotalPrice();
                orderDetailMapper.insertOrderDetail(orderDetail);
            }
        }
        order.setTotalNumber(totalNum);
        order.setTotalPrice(totalPrice);
        return orderMapper.updateOrder(order);
    }

    /**
     * 删除订单主对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteOrderByIds(String ids) {
        // 查询文件信息
        Integer[] saveIds = Convert.toIntArray(ids);
        List<FileSourceInfo> sourceInfos = null;
        Order order = null;
        // 删除对应文件信息
        for (Integer saveId : saveIds) {
            order = orderMapper.selectOrderById(saveId);
            sourceInfos = fileSourceInfoMapper.selectFileInfoBySaveId(saveId, FileConstants.SAVE_TYPE_IS_ORDER);
            if (StringUtils.isNotEmpty(sourceInfos)) {
                throw new BusinessException(order.getOrderCode() + "存在文件，请先删除文件");
            }
        }
        orderDetailMapper.deleteOrderDetailByOrderIds(Convert.toStrArray(ids));
        return orderMapper.deleteOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过客户id查询对应的订单列表
     *
     * @param cusId 客户id
     * @return 结果
     */
    @Override
    public List<Order> selectOrderListByCusId(Integer cusId) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return orderMapper.selectOrderListByCusId(user.getCompanyId(), cusId);
    }

    /**
     * 校验订单唯一性
     *
     * @param order 订单信息
     * @return 结果
     */
    @Override
    public String checkOrderCode(Order order) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return OrderConstants.NAME_NOT_UNIQUE;
        }
        Order uniOrder = orderMapper.selectOrderByOrderCode(user.getCompanyId(), order.getOrderCode());
        if (uniOrder != null && !uniOrder.getId().equals(order.getId())) {
            return OrderConstants.NAME_NOT_UNIQUE;
        }
        return OrderConstants.NAME_UNIQUE;
    }

    /**
     * 关闭订单
     *
     * @param order 订单信息
     * @return 结果
     */
    @Override
    public int closeOrder(Order order) {
        User user = JwtUtil.getUser();
        if (user == null) {
            throw new BusinessException(UserConstants.NOT_LOGIN);
        }
        if (order != null) {
            Order uniOrder = orderMapper.selectOrderById(order.getId());
            if (uniOrder == null) {
                throw new BusinessException("订单不存在或被删除");
            }
            Integer deliveredNum = outOrderDetailMapper.selectOutOrderDetailByCode(user.getCompanyId(), uniOrder.getOrderCode(), uniOrder.getCusId(), null);
            if (deliveredNum == null) {
                deliveredNum = 0;
            }
            if (uniOrder.getTotalNumber() > deliveredNum && !OrderConstants.DELIVERED.equals(uniOrder.getStatus())) {
                throw new BusinessException("该订单未完全交付,不能关闭");
            }
            if (uniOrder.getTotalNumber() <= deliveredNum && OrderConstants.DELIVERED.equals(uniOrder.getStatus())) {
                throw new BusinessException("该订单已交付完，开启失败");
            }
            return orderMapper.updateOrder(order);
        }
        return 0;
    }

    /**
     * 查询未关闭的订单
     *
     * @return 结果
     */
    public List<Order> selectOrderNoClose() {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        List<Order> orders = orderMapper.selectOrderNoClose(user.getCompanyId(), OrderConstants.DELIVERED);
        Integer deliveredNum = null;
        for (Order o : orders) {
            deliveredNum = outOrderDetailMapper.selectOutOrderDetailByCode(user.getCompanyId(), o.getOrderCode(), o.getCusId(), null);
            if (!OrderConstants.DELIVERED.equals(o.getStatus())) {
                if (deliveredNum == null) {
                    o.setStatus(OrderConstants.UN_DELIVERED);
                } else{
                    o.setStatus(OrderConstants.IN_DELIVERY);
                }
            }
            o.setDeliveredNum(deliveredNum == null ? 0 : deliveredNum);
        }
        return orders;
    }

    /**
     * 查询公司所有的订单信息
     *
     * @return 订单信息
     */
    public List<Order> selectOrderListByComId() {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return orderMapper.selectOrderListByComId(user.getCompanyId());
    }
}
