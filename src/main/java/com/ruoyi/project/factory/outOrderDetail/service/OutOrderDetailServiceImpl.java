package com.ruoyi.project.factory.outOrderDetail.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.factory.outOrderDetail.domain.OutOrderDetail;
import com.ruoyi.project.factory.outOrderDetail.mapper.OutOrderDetailMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 出库单明细 服务层实现
 *
 * @author sj
 * @date 2019-10-31
 */
@Service
public class OutOrderDetailServiceImpl implements IOutOrderDetailService {
    @Autowired
    private OutOrderDetailMapper outOrderDetailMapper;

    /**
     * 查询出库单明细信息
     *
     * @param id 出库单明细ID
     * @return 出库单明细信息
     */
    @Override
    public OutOrderDetail selectOutOrderDetailById(Integer id) {
        return outOrderDetailMapper.selectOutOrderDetailById(id);
    }

    /**
     * 查询出库单明细列表
     *
     * @param outOrderDetail 出库单明细信息
     * @return 出库单明细集合
     */
    @Override
    public List<OutOrderDetail> selectOutOrderDetailList(OutOrderDetail outOrderDetail) {
        return outOrderDetailMapper.selectOutOrderDetailList(outOrderDetail);
    }

    /**
     * 新增出库单明细
     *
     * @param outOrderDetail 出库单明细信息
     * @return 结果
     */
    @Override
    public int insertOutOrderDetail(OutOrderDetail outOrderDetail) {
        return outOrderDetailMapper.insertOutOrderDetail(outOrderDetail);
    }

    /**
     * 修改出库单明细
     *
     * @param outOrderDetail 出库单明细信息
     * @return 结果
     */
    @Override
    public int updateOutOrderDetail(OutOrderDetail outOrderDetail) {
        return outOrderDetailMapper.updateOutOrderDetail(outOrderDetail);
    }

    /**
     * 删除出库单明细对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOutOrderDetailByIds(String ids) {
        return outOrderDetailMapper.deleteOutOrderDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过订单号查询所有的出入库明细
     * @param orderCode 订单号
     * @return 结果
     */
    @Override
    public List<OutOrderDetail> selectOutOrderDetailListByOrderCode(String orderCode) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return outOrderDetailMapper.selectOutOrderDetailListByOrderCode(user.getCompanyId(),orderCode);
    }
}
