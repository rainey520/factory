package com.ruoyi.project.factory.outOrder.service;

import com.ruoyi.project.factory.outOrder.domain.OutItem;
import com.ruoyi.project.factory.outOrder.domain.OutOrder;
import java.util.List;

/**
 * 出库单主 服务层
 * 
 * @author sj
 * @date 2019-10-31
 */
public interface IOutOrderService 
{
	/**
     * 查询出库单主信息
     * 
     * @param id 出库单主ID
     * @return 出库单主信息
     */
	public OutOrder selectOutOrderById(Integer id);
	
	/**
     * 查询出库单主列表
     * 
     * @param outOrder 出库单主信息
     * @return 出库单主集合
     */
	public List<OutOrder> selectOutOrderList(OutOrder outOrder);
	
	/**
     * 新增出库单主
     * 
     * @param outOrder 出库单主信息
     * @return 结果
     */
	public int insertOutOrder(OutOrder outOrder);
	
	/**
     * 修改出库单主
     * 
     * @param outOrder 出库单主信息
     * @return 结果
     */
	public int updateOutOrder(OutOrder outOrder);
		
	/**
     * 删除出库单主信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOutOrderByIds(String ids);

	/**
	 * 确认下单信息，数据不可修改
	 * @param outOrder 下单信息
	 * @return 结果
	 */
    int confirmOutOrder(OutOrder outOrder);

	/**
	 * 查看打印明细
	 * @param outId 主表id
	 * @return 结果
	 */
	OutItem showOutOrderDetail(Integer outId);
}
