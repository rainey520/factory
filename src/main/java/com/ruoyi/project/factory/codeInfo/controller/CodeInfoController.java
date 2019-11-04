package com.ruoyi.project.factory.codeInfo.controller;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.factory.codeInfo.domain.CodeInfo;
import com.ruoyi.project.factory.codeInfo.service.ICodeInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 编码 信息操作处理
 *
 * @author sj
 * @date 2019-10-31
 */
@Controller
@RequestMapping("/factory/codeInfo")
public class CodeInfoController extends BaseController {
    private String prefix = "factory/codeInfo";

    @Autowired
    private ICodeInfoService codeInfoService;

    @RequiresPermissions("factory:codeInfo:list")
    @GetMapping()
    public String codeInfo() {
        return prefix + "/codeInfo";
    }

    /**
     * 查询编码列表
     */
    @RequiresPermissions("factory:codeInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CodeInfo codeInfo) {
        startPage();
        List<CodeInfo> list = codeInfoService.selectCodeInfoList(codeInfo);
        return getDataTable(list);
    }


    /**
     * 导出编码列表
     */
    @RequiresPermissions("factory:codeInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CodeInfo codeInfo) {
        List<CodeInfo> list = codeInfoService.selectCodeInfoList(codeInfo);
        ExcelUtil<CodeInfo> util = new ExcelUtil<CodeInfo>(CodeInfo.class);
        return util.exportExcel(list, "codeInfo");
    }

    /**
     * 新增编码
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存编码
     */
    @RequiresPermissions("factory:codeInfo:add")
    @Log(title = "编码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CodeInfo codeInfo) {
        return toAjax(codeInfoService.insertCodeInfo(codeInfo));
    }

    /**
     * 修改编码
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        CodeInfo codeInfo = codeInfoService.selectCodeInfoById(id);
        mmap.put("codeInfo", codeInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存编码
     */
    @RequiresPermissions("factory:codeInfo:add")
    @Log(title = "编码", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CodeInfo codeInfo) {
        return toAjax(codeInfoService.updateCodeInfo(codeInfo));
    }

    /**
     * 删除编码
     */
    @RequiresPermissions("factory:codeInfo:remove")
    @Log(title = "编码", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(codeInfoService.deleteCodeInfoByIds(ids));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }


    /**
     * 查询对应客户的产品列表
     * @return 产品列表
     */
    @RequestMapping("/selectPnCodeListByCusId")
    @ResponseBody
    public AjaxResult selectPnCodeListByCusId(Integer cusId){
        return AjaxResult.success(codeInfoService.selectPnCodeListByCusId(cusId));
    }


    /**
     * 查询所有的产品列表
     */
    @RequestMapping("/selectPnCodeList")
    @ResponseBody
    public AjaxResult selectPnCodeList(){
        return AjaxResult.success(codeInfoService.selectPnCodeList());
    }

    /**
     * 校验客户编码唯一性
     */
    @PostMapping("/checkPnCode")
    @ResponseBody
    public String checkPnCode(CodeInfo codeInfo){
        return codeInfoService.checkPnCode(codeInfo);
    }

    /**
     * 查看文件管理
     */
    @GetMapping("/showFileInfo")
    public String showFileInfo(Integer id,ModelMap map){
        map.put("saveId",id);
        map.put("saveType",1);
        return prefix + "/fileInfo";
    }
}
