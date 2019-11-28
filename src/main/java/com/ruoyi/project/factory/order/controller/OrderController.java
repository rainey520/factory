package com.ruoyi.project.factory.order.controller;

import com.ruoyi.common.constant.FileConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.factory.order.domain.Order;
import com.ruoyi.project.factory.order.service.IOrderService;
import com.ruoyi.project.factory.orderDetail.domain.OrderDetail;
import com.ruoyi.project.factory.orderDetail.service.IOrderDetailService;
import com.ruoyi.project.factory.outOrderDetail.domain.OutOrderDetail;
import com.ruoyi.project.factory.outOrderDetail.service.IOutOrderDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单主 信息操作处理
 *
 * @author sj
 * @date 2019-10-31
 */
@Controller
@RequestMapping("/factory/order")
public class OrderController extends BaseController {
    private String prefix = "factory/order" ;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IOutOrderDetailService outOrderDetailService;

    @RequiresPermissions("factory:order:list")
    @GetMapping()
    public String order() {
        return prefix + "/order" ;
    }

    /**
     * 查询订单主列表
     */
    @RequiresPermissions("factory:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Order order) {
        startPage();
        List<Order> list = orderService.selectOrderList(order);
        return getDataTable(list);
    }


    /**
     * 导出订单主列表
     */
    @RequiresPermissions("factory:order:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Order order) {
        List<Order> list = orderService.selectOrderList(order);
        ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 新增订单主
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add" ;
    }

    /**
     * 新增保存订单主
     */
    @RequiresPermissions("factory:order:add")
    @Log(title = "订单主", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody Order order) {
        try {
            return toAjax(orderService.insertOrder(order));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 查看订单明细
     */
    @GetMapping("/detail")
    public String detail(String orderCode,Integer id, ModelMap map) {
        List<OrderDetail> detailList = orderDetailService.selectOrderByOrderId(id);
        List<OutOrderDetail> outList = outOrderDetailService.selectOutOrderDetailListByOrderCode(orderCode);
        map.put("detailList", detailList);
        map.put("outList", outList);
        return prefix + "/detail" ;
    }

    /**
     * 修改订单
     */
    @GetMapping("/edit")
    public String edit(Integer id, ModelMap mmap) {
        Order order = orderService.selectOrderById(id);
        mmap.put("order", order);
        return prefix + "/edit" ;
    }

    /**
     * 修改保存订单主
     */
    @RequiresPermissions("factory:order:add")
    @Log(title = "订单主", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody Order order) {
        return toAjax(orderService.updateOrder(order));
    }

    /**
     * 删除订单主
     */
    @RequiresPermissions("factory:order:remove")
    @Log(title = "订单主", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(orderService.deleteOrderByIds(ids));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 通过客户id选择对应的订单列表
     */
    @PostMapping("/selectOrderListByCusId")
    @ResponseBody
    public AjaxResult selectOrderListByCusId(Integer cusId) {
        return AjaxResult.success(orderService.selectOrderListByCusId(cusId));
    }

    /**
     * 校验订单号唯一性
     */
    @PostMapping("/checkOrderCode")
    @ResponseBody
    public String checkOrderCode(Order order) {
        return orderService.checkOrderCode(order);
    }

    /**
     * 关闭订单
     */
    @PostMapping("/closeOrder")
    @ResponseBody
    public AjaxResult closeOrder(Order order) {
        try {
            return toAjax(orderService.closeOrder(order));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 查看订单文件信息
     */
    @GetMapping("/showFileInfo")
    public String showFileInfo(Integer id, ModelMap map) {
        map.put("saveId", id);
        // 订单文件
        map.put("saveType", FileConstants.SAVE_TYPE_IS_ORDER);
        return prefix + "/fileInfo" ;
    }
}
