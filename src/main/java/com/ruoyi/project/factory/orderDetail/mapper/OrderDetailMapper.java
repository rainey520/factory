package com.ruoyi.project.factory.orderDetail.mapper;

import com.ruoyi.project.factory.orderDetail.domain.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单明细 数据层
 *
 * @author sj
 * @date 2019-10-31
 */
public interface OrderDetailMapper {
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
     * 删除订单明细
     *
     * @param id 订单明细ID
     * @return 结果
     */
    public int deleteOrderDetailById(Integer id);

    /**
     * 批量删除订单明细
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderDetailByIds(String[] ids);

    /**
     * 通过主表id查询订单明细列表
     *
     * @param orderId 订单id
     * @return 明细列表
     */
    List<OrderDetail> selectOrderByOrderId(@Param("orderId") Integer orderId);

    /**
     * 客户出库选择未关闭的订单
     *
     * @param cusId  客户id
     * @param pnCode 产品编码
     * @return 结果
     */
    List<OrderDetail> selectOrderDetailsByPnCode(@Param("companyId") Integer companyId,
                                                 @Param("cusId") Integer cusId,
                                                 @Param("pnCode") String pnCode);

    /**
     * 查询未交付完的订单信息
     * @param companyId 公司id
     * @param cusId 客户id
     * @param pnCode 产品编码
     * @return 结果
     */
    List<OrderDetail> selectOrderDetailByDelNum(@Param("companyId") Integer companyId,
                                                @Param("cusId") Integer cusId,
                                                @Param("pnCode") String pnCode);

    /**
     * 查询已交付完的订单信息
     * @param companyId 公司id
     * @param cusId 客户id
     * @param pnCode 产品编码
     * @return 结果
     */
    List<OrderDetail> selectOrderDetailByUnDelNum(@Param("companyId") Integer companyId,
                                                  @Param("cusId") Integer cusId,
                                                  @Param("pnCode") String pnCode);

    /**
     * 通过订单id删除明细
     * @param ids 订单id
     * @return 结果
     */
    int deleteOrderDetailByOrderIds(String[] ids);

    /**
     * 通过订单id删除订单信息
     * @param orderId 订单id
     * @return 结果
     */
    int deleteOrderDetailByOrderId(@Param("orderId") Integer orderId);
}