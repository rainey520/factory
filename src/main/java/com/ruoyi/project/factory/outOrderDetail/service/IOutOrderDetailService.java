package com.ruoyi.project.factory.outOrderDetail.service;

import com.ruoyi.project.factory.outOrderDetail.domain.OutOrderDetail;
import java.util.List;

/**
 * 出库单明细 服务层
 * 
 * @author sj
 * @date 2019-10-31
 */
public interface IOutOrderDetailService 
{
	/**
     * 查询出库单明细信息
     * 
     * @param id 出库单明细ID
     * @return 出库单明细信息
     */
	public OutOrderDetail selectOutOrderDetailById(Integer id);
	
	/**
     * 查询出库单明细列表
     * 
     * @param outOrderDetail 出库单明细信息
     * @return 出库单明细集合
     */
	public List<OutOrderDetail> selectOutOrderDetailList(OutOrderDetail outOrderDetail);
	
	/**
     * 新增出库单明细
     * 
     * @param outOrderDetail 出库单明细信息
     * @return 结果
     */
	public int insertOutOrderDetail(OutOrderDetail outOrderDetail);
	
	/**
     * 修改出库单明细
     * 
     * @param outOrderDetail 出库单明细信息
     * @return 结果
     */
	public int updateOutOrderDetail(OutOrderDetail outOrderDetail);
		
	/**
     * 删除出库单明细信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOutOrderDetailByIds(String ids);
	
}
