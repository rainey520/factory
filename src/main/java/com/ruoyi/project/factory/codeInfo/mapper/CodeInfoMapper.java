package com.ruoyi.project.factory.codeInfo.mapper;

import com.ruoyi.project.factory.codeInfo.domain.CodeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 编码 数据层
 * 
 * @author sj
 * @date 2019-10-31
 */
public interface CodeInfoMapper 
{
	/**
     * 查询编码信息
     * 
     * @param id 编码ID
     * @return 编码信息
     */
	public CodeInfo selectCodeInfoById(Integer id);
	
	/**
     * 查询编码列表
     * 
     * @param codeInfo 编码信息
     * @return 编码集合
     */
	public List<CodeInfo> selectCodeInfoList(CodeInfo codeInfo);
	
	/**
     * 新增编码
     * 
     * @param codeInfo 编码信息
     * @return 结果
     */
	public int insertCodeInfo(CodeInfo codeInfo);
	
	/**
     * 修改编码
     * 
     * @param codeInfo 编码信息
     * @return 结果
     */
	public int updateCodeInfo(CodeInfo codeInfo);
	
	/**
     * 删除编码
     * 
     * @param id 编码ID
     * @return 结果
     */
	public int deleteCodeInfoById(Integer id);
	
	/**
     * 批量删除编码
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCodeInfoByIds(String[] ids);

	/**
	 * 通过客户id查询关联的产品列表
	 * @param companyId  公司id
	 * @param cusId 客户id
	 * @return 结果
	 */
    List<CodeInfo> selectPnCodeListByCusId(@Param("companyId") Integer companyId, @Param("cusId") Integer cusId);

	/**
	 * 通过客户编码查询编码信息
	 * @param pnCode 客户编码
	 * @return 结果
	 */
	CodeInfo selectCodeInfoByPnCode(@Param("pnCode") String pnCode);

	/**
	 * 检索条件查询所有的规格名称
	 * @param companyId 公司id
	 * @return 结果
	 */
	List<CodeInfo> selectPnNameList(@Param("companyId") Integer companyId);
}