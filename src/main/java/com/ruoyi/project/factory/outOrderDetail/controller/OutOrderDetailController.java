package com.ruoyi.project.factory.outOrderDetail.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.factory.outOrderDetail.domain.OutOrderDetail;
import com.ruoyi.project.factory.outOrderDetail.service.IOutOrderDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出库单明细 信息操作处理
 * 
 * @author sj
 * @date 2019-10-31
 */
@Controller
@RequestMapping("/factory/outOrderDetail")
public class OutOrderDetailController extends BaseController
{
    private String prefix = "factory/outOrderDetail";
	
	@Autowired
	private IOutOrderDetailService outOrderDetailService;
	
	@RequiresPermissions("factory:outOrderDetail:list")
	@GetMapping()
	public String outOrderDetail()
	{
	    return prefix + "/outOrderDetail";
	}
	
	/**
	 * 查询出库单明细列表
	 */
	@RequiresPermissions("factory:outOrderDetail:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(OutOrderDetail outOrderDetail)
	{
		startPage();
        List<OutOrderDetail> list = outOrderDetailService.selectOutOrderDetailList(outOrderDetail);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出出库单明细列表
	 */
	@RequiresPermissions("factory:outOrderDetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OutOrderDetail outOrderDetail)
    {
    	List<OutOrderDetail> list = outOrderDetailService.selectOutOrderDetailList(outOrderDetail);
        ExcelUtil<OutOrderDetail> util = new ExcelUtil<OutOrderDetail>(OutOrderDetail.class);
        return util.exportExcel(list, "outOrderDetail");
    }
	
	/**
	 * 新增出库单明细
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存出库单明细
	 */
	@RequiresPermissions("factory:outOrderDetail:add")
	@Log(title = "出库单明细", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OutOrderDetail outOrderDetail)
	{		
		return toAjax(outOrderDetailService.insertOutOrderDetail(outOrderDetail));
	}

	/**
	 * 修改出库单明细
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		OutOrderDetail outOrderDetail = outOrderDetailService.selectOutOrderDetailById(id);
		mmap.put("outOrderDetail", outOrderDetail);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存出库单明细
	 */
	@RequiresPermissions("factory:outOrderDetail:add")
	@Log(title = "出库单明细", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OutOrderDetail outOrderDetail)
	{		
		return toAjax(outOrderDetailService.updateOutOrderDetail(outOrderDetail));
	}
	
	/**
	 * 删除出库单明细
	 */
	@RequiresPermissions("factory:outOrderDetail:remove")
	@Log(title = "出库单明细", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(outOrderDetailService.deleteOutOrderDetailByIds(ids));
	}
	
}
