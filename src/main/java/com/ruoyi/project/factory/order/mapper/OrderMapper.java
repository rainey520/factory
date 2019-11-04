package com.ruoyi.project.factory.order.mapper;

import com.ruoyi.project.factory.order.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单主 数据层
 * 
 * @author sj
 * @date 2019-10-31
 */
public interface OrderMapper 
{
	/**
     * 查询订单主信息
     * 
     * @param id 订单主ID
     * @return 订单主信息
     */
	public Order selectOrderById(Integer id);
	
	/**
     * 查询订单主列表
     * 
     * @param order 订单主信息
     * @return 订单主集合
     */
	public List<Order> selectOrderList(Order order);
	
	/**
     * 新增订单主
     * 
     * @param order 订单主信息
     * @return 结果
     */
	public int insertOrder(Order order);
	
	/**
     * 修改订单主
     * 
     * @param order 订单主信息
     * @return 结果
     */
	public int updateOrder(Order order);
	
	/**
     * 删除订单主
     * 
     * @param id 订单主ID
     * @return 结果
     */
	public int deleteOrderById(Integer id);
	
	/**
     * 批量删除订单主
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrderByIds(String[] ids);

	/**
	 * 通过客户id查询对应的订单列表
	 * @param companyId  公司id
	 * @param cusId 客户id
	 * @return 结果
	 */
    List<Order> selectOrderListByCusId(@Param("companyId") Integer companyId, @Param("cusId") Integer cusId);

	/**
	 * 通过订单号查询订单主表
	 * @param companyId 公司id
	 * @param orderCode 订单号
	 * @return 结果
	 */
	Order selectOrderByOrderCode(@Param("companyId") Integer companyId, @Param("orderCode") String orderCode);

	/**
	 * 查询未关闭的订单
	 * @param companyId 公司id
	 * @param status 订单状态
	 * @return 结果
	 */
    List<Order> selectOrderNoClose(@Param("companyId") Integer companyId, @Param("status") Integer status);

	/**
	 * 查询公司的所有订单信息
	 * @param companyId 公司id
	 * @return 结果
	 */
	List<Order> selectOrderListByComId(@Param("companyId") Integer companyId);
}