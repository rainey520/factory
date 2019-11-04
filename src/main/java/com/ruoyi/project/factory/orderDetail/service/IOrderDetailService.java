package com.ruoyi.project.factory.orderDetail.service;

import com.ruoyi.project.factory.orderDetail.domain.OrderDetail;

import java.util.List;

/**
 * 订单明细 服务层
 *
 * @author sj
 * @date 2019-10-31
 */
public interface IOrderDetailService {
    /**
     * 查询订单明细信息
     *
     * @param id 订单明细ID
     * @return 订单明细信息
     */
    public OrderDetail selectOrderDetailById(Integer id);

    /**
     * 查询订单明细列表
     *
     * @param orderDetail 订单明细信息
     * @return 订单明细集合
     */
    public List<OrderDetail> selectOrderDetailList(OrderDetail orderDetail);

    /**
     * 新增订单明细
     *
     * @param orderDetail 订单明细信息
     * @return 结果
     */
    public int insertOrderDetail(OrderDetail orderDetail);

    /**
     * 修改订单明细
     *
     * @param orderDetail 订单明细信息
     * @return 结果
     */
    public int updateOrderDetail(OrderDetail orderDetail);

    /**
     * 删除订单明细信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderDetailByIds(String ids);

    /**
     * 通过主表id查询订单明细列表
     *
     * @param orderId 订单id
     * @return 明细列表
     */
    List<OrderDetail> selectOrderByOrderId(Integer orderId);

    /**
     * 客户出库选择未关闭的订单
     *
     * @param cusId  客户id
     * @param pnCode 产品编码
     * @return 结果
     */
    List<OrderDetail> selectOrderDetailsByPnCode(Integer cusId, String pnCode);

    /**
     * 客户退货选择订单
     * @param cusId  客户id
     * @param pnCode 产品编码
     * @return 结果
     */
    List<OrderDetail> selectOrderBackByPnCode(Integer cusId, String pnCode);
}
