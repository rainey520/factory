package com.ruoyi.project.factory.order.service;

import com.ruoyi.project.factory.order.domain.Order;
import java.util.List;

/**
 * 订单主 服务层
 * 
 * @author sj
 * @date 2019-10-31
 */
public interface IOrderService 
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
     * 删除订单主信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrderByIds(String ids);

	/**
	 * 通过客户id查询对应的订单列表
	 * @param cusId 客户id
	 * @return 结果
	 */
	public List<Order> selectOrderListByCusId(Integer cusId);

	/**
	 * 校验订单唯一性
	 * @param order 订单信息
	 * @return 结果
	 */
	String checkOrderCode(Order order);

	/**
	 * 关闭订单
	 * @param order 订单信息
	 * @return 结果
	 */
    int closeOrder(Order order);
}
