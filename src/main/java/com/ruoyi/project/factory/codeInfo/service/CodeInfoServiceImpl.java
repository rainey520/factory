package com.ruoyi.project.factory.codeInfo.service;

import com.ruoyi.common.constant.OrderConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.factory.codeInfo.domain.CodeInfo;
import com.ruoyi.project.factory.codeInfo.mapper.CodeInfoMapper;
import com.ruoyi.project.iso.filesource.domain.FileSourceInfo;
import com.ruoyi.project.iso.filesource.mapper.FileSourceInfoMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 编码 服务层实现
 *
 * @author sj
 * @date 2019-10-31
 */
@Service("codeInfo")
public class CodeInfoServiceImpl implements ICodeInfoService {

    @Autowired
    private CodeInfoMapper codeInfoMapper;

    @Autowired
    private FileSourceInfoMapper fileSourceInfoMapper;

    /**
     * 查询编码信息
     *
     * @param id 编码ID
     * @return 编码信息
     */
    @Override
    public CodeInfo selectCodeInfoById(Integer id) {
        return codeInfoMapper.selectCodeInfoById(id);
    }

    /**
     * 查询编码列表
     *
     * @param codeInfo 编码信息
     * @return 编码集合
     */
    @Override
    public List<CodeInfo> selectCodeInfoList(CodeInfo codeInfo) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        codeInfo.setCompanyId(user.getCompanyId());
        return codeInfoMapper.selectCodeInfoList(codeInfo);
    }

    /**
     * 新增编码
     *
     * @param codeInfo 编码信息
     * @return 结果
     */
    @Override
    public int insertCodeInfo(CodeInfo codeInfo) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return 0;
        }
        codeInfo.setCompanyId(user.getCompanyId());
        codeInfo.setPnTime(new Date());
        return codeInfoMapper.insertCodeInfo(codeInfo);
    }

    /**
     * 修改编码
     *
     * @param codeInfo 编码信息
     * @return 结果
     */
    @Override
    public int updateCodeInfo(CodeInfo codeInfo) {
        return codeInfoMapper.updateCodeInfo(codeInfo);
    }

    /**
     * 删除编码对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCodeInfoByIds(String ids) {
        List<FileSourceInfo> sourceInfos = null;
        CodeInfo codeInfo = null;
        Integer[] saveIds = Convert.toIntArray(ids);
        // 删除对应文件信息
        for (Integer saveId : saveIds) {
            codeInfo = codeInfoMapper.selectCodeInfoById(saveId);
            sourceInfos = fileSourceInfoMapper.selectFileInfoBySaveId(saveId,1);
            if (StringUtils.isNotEmpty(sourceInfos)) {
                throw new BusinessException(codeInfo.getPnCode() + "存在文件，请先删除文件");
            }
        }
        return codeInfoMapper.deleteCodeInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过客户id查询关联的产品列表
     * @param cusId 客户id
     * @return 结果
     */
    @Override
    public List<CodeInfo> selectPnCodeListByCusId(Integer cusId) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return codeInfoMapper.selectPnCodeListByCusId(user.getCompanyId(),cusId);
    }

    /**
     * 校验客户编码的唯一性
     * @param codeInfo 编码信息
     * @return 结果
     */
    @Override
    public String checkPnCode(CodeInfo codeInfo) {
        CodeInfo uniCode = codeInfoMapper.selectCodeInfoByPnCode(codeInfo.getPnCode());
        if (uniCode != null && !uniCode.getId().equals(codeInfo.getId())) {
            return OrderConstants.NAME_NOT_UNIQUE;
        }
        return OrderConstants.NAME_UNIQUE;
    }

    /**
     * 查看所有的客户编码列表
     * @return 结果
     */
    @Override
    public List<CodeInfo> selectPnCodeList() {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return codeInfoMapper.selectPnCodeListByCusId(user.getCompanyId(),null);
    }
}
