package com.ruoyi.project.factory.outOrder.mapper;

import com.ruoyi.project.factory.outOrder.domain.OutOrder;
import java.util.List;	

/**
 * 出库单主 数据层
 * 
 * @author sj
 * @date 2019-10-31
 */
public interface OutOrderMapper 
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
     * 删除出库单主
     * 
     * @param id 出库单主ID
     * @return 结果
     */
	public int deleteOutOrderById(Integer id);
	
	/**
     * 批量删除出库单主
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOutOrderByIds(String[] ids);
	
}