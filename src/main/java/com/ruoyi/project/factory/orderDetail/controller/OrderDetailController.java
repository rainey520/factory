package com.ruoyi.project.factory.orderDetail.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.factory.orderDetail.domain.OrderDetail;
import com.ruoyi.project.factory.orderDetail.service.IOrderDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单明细 信息操作处理
 *
 * @author sj
 * @date 2019-10-31
 */
@Controller
@RequestMapping("/factory/orderDetail")
public class OrderDetailController extends BaseController {
    private String prefix = "factory/orderDetail";

    @Autowired
    private IOrderDetailService orderDetailService;

    @RequiresPermissions("factory:orderDetail:list")
    @GetMapping()
    public String orderDetail() {
        return prefix + "/orderDetail";
    }

    /**
     * 查询订单明细列表
     */
    @RequiresPermissions("factory:orderDetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OrderDetail orderDetail) {
        startPage();
        List<OrderDetail> list = orderDetailService.selectOrderDetailList(orderDetail);
        return getDataTable(list);
    }


    /**
     * 导出订单明细列表
     */
    @RequiresPermissions("factory:orderDetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrderDetail orderDetail) {
        List<OrderDetail> list = orderDetailService.selectOrderDetailList(orderDetail);
        ExcelUtil<OrderDetail> util = new ExcelUtil<OrderDetail>(OrderDetail.class);
        return util.exportExcel(list, "orderDetail");
    }

    /**
     * 新增订单明细
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存订单明细
     */
    @RequiresPermissions("factory:orderDetail:add")
    @Log(title = "订单明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OrderDetail orderDetail) {
        return toAjax(orderDetailService.insertOrderDetail(orderDetail));
    }

    /**
     * 修改订单明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        OrderDetail orderDetail = orderDetailService.selectOrderDetailById(id);
        mmap.put("orderDetail", orderDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单明细
     */
    @RequiresPermissions("factory:orderDetail:add")
    @Log(title = "订单明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OrderDetail orderDetail) {
        return toAjax(orderDetailService.updateOrderDetail(orderDetail));
    }

    /**
     * 删除订单明细
     */
    @RequiresPermissions("factory:orderDetail:remove")
    @Log(title = "订单明细", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(orderDetailService.deleteOrderDetailByIds(ids));
    }

    /**
     * 客户出库选择未关闭的订单
     *
     * @return 结果
     */
    @PostMapping("/selectOrderDetailsByPnCode")
    @ResponseBody
    public AjaxResult selectOrderDetailsByPnCode(Integer cusId, String pnCode) {
        return AjaxResult.success(orderDetailService.selectOrderDetailsByPnCode(cusId, pnCode));
    }

    /**
     * 客户退货选择订单
     * @return 结果
     */
    @PostMapping("/selectOrderBackByPnCode")
    @ResponseBody
    public AjaxResult selectOrderBackByPnCode(Integer cusId, String pnCode) {
        return AjaxResult.success(orderDetailService.selectOrderBackByPnCode(cusId, pnCode));
    }
}
