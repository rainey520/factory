package com.ruoyi.project.factory.orderDetail.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.factory.orderDetail.domain.OrderDetail;
import com.ruoyi.project.factory.orderDetail.mapper.OrderDetailMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 订单明细 服务层实现
 *
 * @author sj
 * @date 2019-10-31
 */
@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    /**
     * 查询订单明细信息
     *
     * @param id 订单明细ID
     * @return 订单明细信息
     */
    @Override
    public OrderDetail selectOrderDetailById(Integer id) {
        return orderDetailMapper.selectOrderDetailById(id);
    }

    /**
     * 查询订单明细列表
     *
     * @param orderDetail 订单明细信息
     * @return 订单明细集合
     */
    @Override
    public List<OrderDetail> selectOrderDetailList(OrderDetail orderDetail) {
        return orderDetailMapper.selectOrderDetailList(orderDetail);
    }

    /**
     * 新增订单明细
     *
     * @param orderDetail 订单明细信息
     * @return 结果
     */
    @Override
    public int insertOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.insertOrderDetail(orderDetail);
    }

    /**
     * 修改订单明细
     *
     * @param orderDetail 订单明细信息
     * @return 结果
     */
    @Override
    public int updateOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.updateOrderDetail(orderDetail);
    }

    /**
     * 删除订单明细对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrderDetailByIds(String ids) {
        return orderDetailMapper.deleteOrderDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过主表id查询订单明细列表
     *
     * @param orderId 订单id
     * @return 明细列表
     */
    @Override
    public List<OrderDetail> selectOrderByOrderId(Integer orderId) {
        return orderDetailMapper.selectOrderByOrderId(orderId);
    }

    /**
     * 客户出库选择未关闭的订单
     *
     * @param cusId  客户id
     * @param pnCode 产品编码
     * @return 结果
     */
    @Override
    public List<OrderDetail> selectOrderDetailsByPnCode(Integer cusId, String pnCode) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return orderDetailMapper.selectOrderDetailsByPnCode(user.getCompanyId(), cusId, pnCode);
    }

    /**
     * 客户退货选择订单
     * @param cusId  客户id
     * @param pnCode 产品编码
     * @return 结果
     */
    @Override
    public List<OrderDetail> selectOrderBackByPnCode(Integer cusId, String pnCode) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        List<OrderDetail> details = null;
        // 查询未交付完成的订单信息
        details = orderDetailMapper.selectOrderDetailByDelNum(user.getCompanyId(),cusId,pnCode);
        if (StringUtils.isEmpty(details)) {
            // 查询已经交付完的订单信息
            details = orderDetailMapper.selectOrderDetailByUnDelNum(user.getCompanyId(),cusId,pnCode);
        }
        return details;
    }
}
