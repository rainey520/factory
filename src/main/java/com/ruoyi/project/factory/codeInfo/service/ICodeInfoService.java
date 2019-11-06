package com.ruoyi.project.factory.codeInfo.service;

import com.ruoyi.project.factory.codeInfo.domain.CodeInfo;
import java.util.List;

/**
 * 编码 服务层
 * 
 * @author sj
 * @date 2019-10-31
 */
public interface ICodeInfoService 
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
     * 删除编码信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCodeInfoByIds(String ids);

	/**
	 * 通过客户id查询关联的产品列表
	 * @param cusId 客户id
	 * @return 结果
	 */
    List<CodeInfo> selectPnCodeListByCusId(Integer cusId);

	/**
	 * 校验客户编码的唯一性
	 * @param codeInfo 编码信息
	 * @return 结果
	 */
	String checkPnCode(CodeInfo codeInfo);

	/**
	 * 查看所有的客户编码列表
	 * @return 结果
	 */
	List<CodeInfo> selectPnCodeList();

	/**
	 * 检索条件查询所有的规格名称
	 * @return 结果
	 */
	List<CodeInfo> selectPnNameList();
}
