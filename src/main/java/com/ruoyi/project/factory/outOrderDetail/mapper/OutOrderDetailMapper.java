package com.ruoyi.project.factory.outOrderDetail.mapper;

import com.ruoyi.project.factory.outOrderDetail.domain.OutOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 出库单明细 数据层
 *
 * @author sj
 * @date 2019-10-31
 */
public interface OutOrderDetailMapper {
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
     * 删除出库单明细
     *
     * @param id 出库单明细ID
     * @return 结果
     */
    public int deleteOutOrderDetailById(Integer id);

    /**
     * 批量删除出库单明细
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOutOrderDetailByIds(String[] ids);

    /**
     * 确认下单信息，数据不可修改
     *
     * @param outId 出库单信息
     * @return 结果
     */
    List<OutOrderDetail> selectOutOrderDetailByOutId(@Param("outId") Integer outId);

    /**
     * 查询对账明细
     *
     * @param companyId 公司id
     * @param cusId     客户id
     * @param sTime     开始时间
     * @param eTime     结果时间
     * @return 结果
     */
    List<OutOrderDetail> selectRecordExcelList(@Param("companyId") Integer companyId, @Param("cusId") Integer cusId,
                                               @Param("sTime") String sTime, @Param("eTime") String eTime);

    /**
     * 通过出库id删除明细
     * @param ids 出库id集合
     * @return 结果
     */
    int deleteOutOrderDetailByOutIds(String[] ids);
}