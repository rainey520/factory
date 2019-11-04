package com.ruoyi.project.factory.customer.mapper;

import com.ruoyi.project.factory.customer.domain.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户 数据层
 * 
 * @author sj
 * @date 2019-10-31
 */
public interface CustomerMapper 
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
     * 删除客户
     * 
     * @param id 客户ID
     * @return 结果
     */
	public int deleteCustomerById(Integer id);
	
	/**
     * 批量删除客户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCustomerByIds(String[] ids);

	/**
	 * 查询公司的客户列表
	 * @param companyId 公司id
	 * @param cusType 公司类型
	 * @return 结果
	 */
	List<Customer> selectCustomerListByComId(@Param("companyId") Integer companyId,@Param("cusType") Integer cusType);

	/**
	 * 通过客户名称搜索客户信息
	 * @param companyId 公司id
	 * @param cusName 客户名称
	 * @return 结果
	 */
    Customer checkCusName(@Param("companyId") Integer companyId, @Param("cusName") String cusName);
}