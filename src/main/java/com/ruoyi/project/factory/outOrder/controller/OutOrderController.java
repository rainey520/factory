package com.ruoyi.project.factory.outOrder.controller;

import com.ruoyi.common.constant.OrderConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.factory.outOrder.domain.OutItem;
import com.ruoyi.project.factory.outOrder.domain.OutOrder;
import com.ruoyi.project.factory.outOrder.service.IOutOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出库单主 信息操作处理
 *
 * @author sj
 * @date 2019-10-31
 */
@Controller
@RequestMapping("/factory/outOrder")
public class OutOrderController extends BaseController {
    private String prefix = "factory/outOrder";

    @Autowired
    private IOutOrderService outOrderService;

    @RequiresPermissions("factory:outOrder:list")
    @GetMapping()
    public String outOrder() {
        return prefix + "/outOrder";
    }

    /**
     * 查询出库单主列表
     */
    @RequiresPermissions("factory:outOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OutOrder outOrder) {
        startPage();
        List<OutOrder> list = outOrderService.selectOutOrderList(outOrder);
        return getDataTable(list);
    }


    /**
     * 导出出库单主列表
     */
    @RequiresPermissions("factory:outOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OutOrder outOrder) {
        List<OutOrder> list = outOrderService.selectOutOrderList(outOrder);
        ExcelUtil<OutOrder> util = new ExcelUtil<OutOrder>(OutOrder.class);
        return util.exportExcel(list, "outOrder");
    }

    /**
     * 新增客户出库单
     */
    @GetMapping("/cusOut")
    public String cusOut(Integer outType, ModelMap map) {
        map.put("outType", outType);
        return prefix + "/cusOut";
    }

    /**
     * 新增客户退货单
     */
    @GetMapping("/cusBack")
    public String cusBack(Integer outType, ModelMap map) {
        map.put("outType", outType);
        return prefix + "/cusBack";
    }

    /**
     * 新增外加工出库入库处理
     */
    @GetMapping("/handOut")
    public String handOut(Integer outType, ModelMap map) {
        map.put("outType", outType);
        return prefix + "/handOut";
    }

    /**
     * 新增保存出库单主
     */
    @RequiresPermissions("factory:outOrder:add")
    @Log(title = "出库单主", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody OutOrder outOrder) {
        try {
            return toAjax(outOrderService.insertOutOrder(outOrder));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改明细信息
     */
    @GetMapping("/edit")
    public String edit(Integer id, ModelMap mmap) {
        OutOrder outOrder = outOrderService.selectOutOrderById(id);
        mmap.put("outOrder", outOrder);
        // 加工厂
        if (OrderConstants.OUT_TYPE_WJG_OUTHOUSE.equals(outOrder.getOutType()) || OrderConstants.OUT_TYPE_WJG_INHOUSE.equals(outOrder.getOutType())) {
            return prefix + "/editHandOut";
            // 客户出库
        } else if (OrderConstants.OUT_TYPE_CUS_OUT.equals(outOrder.getOutType())) {
            return prefix + "/editCusOut";
            // 客户退货
        } else {
            return prefix + "/editCusBack";
        }
    }

    /**
     * 修改保存出库单主
     */
    @RequiresPermissions("factory:outOrder:add")
    @Log(title = "出库单主", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody OutOrder outOrder) {
        return toAjax(outOrderService.updateOutOrder(outOrder));
    }

    /**
     * 删除出库单主
     */
    @RequiresPermissions("factory:outOrder:remove")
    @Log(title = "出库单主", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(outOrderService.deleteOutOrderByIds(ids));
    }

    /**
     * 确认下单信息
     */
    @PostMapping("/confirmOutOrder")
    @ResponseBody
    public AjaxResult confirmOutOrder(OutOrder outOrder){
        return toAjax(outOrderService.confirmOutOrder(outOrder));
    }

    /**
     * 打印明细
     */
    @GetMapping("/showOutOrderDetail")
    public String showOutOrderDetail(Integer id,ModelMap map){
        OutItem outItem = outOrderService.showOutOrderDetail(id);
        map.put("company",outItem.getCompany());
        map.put("customer",outItem.getCustomer());
        map.put("outItem",outItem);
        return prefix + "/details";
    }
}
