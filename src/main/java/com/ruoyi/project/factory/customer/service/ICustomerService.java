package com.ruoyi.project.factory.customer.service;

import com.ruoyi.project.factory.customer.domain.Customer;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * 客户 服务层
 * 
 * @author sj
 * @date 2019-10-31
 */
public interface ICustomerService 
{
	/**
     * 查询客户信息
     * 
     * @param id 客户ID
     * @return 客户信息
     */
	public Customer selectCustomerById(Integer id);
	
	/**
     * 查询客户列表
     * 
     * @param customer 客户信息
     * @return 客户集合
     */
	public List<Customer> selectCustomerList(Customer customer);
	
	/**
     * 新增客户
     * 
     * @param customer 客户信息
     * @return 结果
     */
	public int insertCustomer(Customer customer);
	
	/**
     * 修改客户
     * 
     * @param customer 客户信息
     * @return 结果
     */
	public int updateCustomer(Customer customer);
		
	/**
     * 删除客户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCustomerByIds(String ids);

	/**
	 * 查询公司的客户列表
	 * @return 客户列表
	 */
	public  List<Customer> selectCustomerListByComId(Integer cusType);

	/**
	 * 下载客户对账单
	 * @param cusId 客户id
	 * @param sTime 开始时间
	 * @param eTime 结束时间
	 * @return 对账信息
 	 */
    Workbook recordExcel(Integer cusId, String sTime, String eTime);

	/**
	 * 校验客户名称唯一性
	 * @param customer 客户信息
	 * @return 结果
	 */
	String checkCusName(Customer customer);
}
