package com.ruoyi.project.factory.customer.controller;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.poi.ExcelUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.factory.customer.domain.Customer;
import com.ruoyi.project.factory.customer.service.ICustomerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户 信息操作处理
 *
 * @author sj
 * @date 2019-10-31
 */
@Controller
@RequestMapping("/factory/customer")
public class CustomerController extends BaseController {
    private String prefix = "factory/customer";

    @Autowired
    private ICustomerService customerService;

    @RequiresPermissions("factory:customer:list")
    @GetMapping()
    public String customer() {
        return prefix + "/customer";
    }

    /**
     * 查询客户列表
     */
    @RequiresPermissions("factory:customer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Customer customer) {
        startPage();
        List<Customer> list = customerService.selectCustomerList(customer);
        return getDataTable(list);
    }


    /**
     * 导出客户列表
     */
    @RequiresPermissions("factory:customer:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Customer customer) {
        List<Customer> list = customerService.selectCustomerList(customer);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        return util.exportExcel(list, "customer");
    }

    /**
     * 新增客户
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存客户
     */
    @RequiresPermissions("factory:customer:add")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Customer customer) {
        return toAjax(customerService.insertCustomer(customer));
    }

    /**
     * 修改客户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Customer customer = customerService.selectCustomerById(id);
        mmap.put("customer", customer);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户
     */
    @RequiresPermissions("factory:customer:add")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Customer customer) {
        return toAjax(customerService.updateCustomer(customer));
    }

    /**
     * 删除客户
     */
    @RequiresPermissions("factory:customer:remove")
    @Log(title = "客户", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(customerService.deleteCustomerByIds(ids));
    }


    /**
     * 导出客户对账单Excel
     */
    @RequiresPermissions("factory:customer:record")
    @PostMapping("/recordExcel")
    @ResponseBody
    public AjaxResult recordExcel(Integer cusId, String sTime, String eTime) {
        try {
            // 查询客户信息
            StringBuilder sb = new StringBuilder();
            Customer customer = customerService.selectCustomerById(cusId);
            if (customer != null && StringUtils.isNotEmpty(customer.getCusName())) {
                sb.append(customer.getCusName());
            }
            sb.append(sTime).append("至").append(eTime).append("对账单").append(".xlsx");
            Workbook wb = customerService.recordExcel(cusId,sTime,eTime);
            return ExcelUtils.getAjaxResult(wb, sb.toString());
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 校验客户名称唯一性
     */
    @PostMapping("/checkCusName")
    @ResponseBody
    public String checkCusName(Customer customer){
        return customerService.checkCusName(customer);
    }
}
