package com.ruoyi.project.factory.customer.service;

import com.ruoyi.common.constant.OrderConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.mapper.DevCompanyMapper;
import com.ruoyi.project.factory.customer.domain.Customer;
import com.ruoyi.project.factory.customer.mapper.CustomerMapper;
import com.ruoyi.project.factory.outOrderDetail.domain.OutOrderDetail;
import com.ruoyi.project.factory.outOrderDetail.mapper.OutOrderDetailMapper;
import com.ruoyi.project.system.user.domain.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 客户 服务层实现
 *
 * @author sj
 * @date 2019-10-31
 */
@Service("customer")
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private DevCompanyMapper companyMapper;

    @Autowired
    private OutOrderDetailMapper outOrderDetailMapper;


    private String getFloat3(float val){
        return new DecimalFormat(".##").format(val);
    }

    /**
     * 查询客户信息
     *
     * @param id 客户ID
     * @return 客户信息
     */
    @Override
    public Customer selectCustomerById(Integer id) {
        return customerMapper.selectCustomerById(id);
    }

    /**
     * 查询客户列表
     *
     * @param customer 客户信息
     * @return 客户集合
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        customer.setCompanyId(user.getCompanyId());
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增客户
     *
     * @param customer 客户信息
     * @return 结果
     */
    @Override
    public int insertCustomer(Customer customer) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return 0;
        }
        customer.setCompanyId(user.getCompanyId());
        return customerMapper.insertCustomer(customer);
    }

    /**
     * 修改客户
     *
     * @param customer 客户信息
     * @return 结果
     */
    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 删除客户对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCustomerByIds(String ids) {
        return customerMapper.deleteCustomerByIds(Convert.toStrArray(ids));
    }

    /**
     * 根据类型查询客户列表
     *
     * @param cusType 类型
     * @return 结果
     */
    @Override
    public List<Customer> selectCustomerListByComId(Integer cusType) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return customerMapper.selectCustomerListByComId(user.getCompanyId(), cusType);
    }

    /**
     * 校验客户名称唯一性
     *
     * @param customer 客户信息
     * @return 结果
     */
    @Override
    public String checkCusName(Customer customer) {
        User user = JwtUtil.getUser();
        if (user == null) {
            return OrderConstants.NAME_NOT_UNIQUE;
        }
        Customer uniCus = customerMapper.checkCusName(user.getCompanyId(), customer.getCusName());
        if (uniCus != null && !uniCus.getId().equals(customer.getId())) {
            return OrderConstants.NAME_NOT_UNIQUE;
        }
        return OrderConstants.NAME_UNIQUE;
    }

    /**
     * 下载客户对账单
     *
     * @param cusId 客户id
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return 对账信息
     */
    @Override
    public Workbook recordExcel(Integer cusId, String sTime, String eTime) {
        // 查询客户信息
        Customer customer = customerMapper.selectCustomerById(cusId);
        if (customer == null) {
            throw new BusinessException("对账客户不存在或被删除");
        }
        boolean cusType = true;
        if (customer.getCusType().equals(OrderConstants.CUS_TYPE_WJG)) {
            cusType = false;
        }
        // 查询用户以及公司信息
        User user = JwtUtil.getUser();
        if (user == null) {
            throw new BusinessException(UserConstants.NOT_LOGIN);
        }
        DevCompany company = companyMapper.selectDevCompanyById(user.getCompanyId());
        if (company == null) {
            throw new BusinessException("公司不存在或被删除");
        }
        // 创建工作簿对象
        Workbook wb = ExcelUtils.createWorkbook();
        Sheet sheet = ExcelUtils.createSheet(wb);

        // 设置列宽7列
        sheet.setColumnWidth(0, 252 * 4 + 323);
        sheet.setColumnWidth(1, 252 * 14 + 323);
        sheet.setColumnWidth(2, 252 * 16 + 323);
        sheet.setColumnWidth(3, 252 * 16 + 323);
        sheet.setColumnWidth(4, 252 * 14 + 323);
        sheet.setColumnWidth(5, 252 * 14 + 323);
        sheet.setColumnWidth(6, 252 * 14 + 323);
        sheet.setColumnWidth(7, 252 * 14 + 323);
        sheet.setColumnWidth(8, 252 * 10 + 323);

        // 合并单元格
        // 合并第一行、第六行
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 8));

        int rowNum = 1;
        while (rowNum <= 4) {
            sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 1));
            sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 2, 4));
            sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 6, 8));
            rowNum++;
        }

        // 表头，公司名称
        Row row = sheet.createRow(0);
        row.setHeight((short) (16 * 36));
        Cell cell = row.createCell(0);
        cell.setCellValue(company.getComName());

        /**
         * 单元格样式
         */
        // 创建表头单元格样式、设置单元格水平方向对其方式、设置单元格垂直方向对其方式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 表尾单元格样式
        CellStyle cellEndStyle = wb.createCellStyle();
        cellEndStyle.setAlignment(HorizontalAlignment.LEFT);
        cellEndStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        /**
         * 字体样式
         */
        // 18号加粗
        Font font18b = wb.createFont();
        font18b.setBold(true);
        font18b.setFontHeightInPoints((short) 18);

        // 12号加粗
        Font font12b = wb.createFont();
        font12b.setBold(true);
        font12b.setFontHeightInPoints((short) 12);

        // 12号不加粗
        Font font12 = wb.createFont();
        font12.setFontHeightInPoints((short) 12);

        cellStyle.setFont(font18b);
        cell.setCellStyle(cellStyle);

        // 第二行至第五行客户以及公司相关信息
        Row row2 = sheet.createRow(1);
        Cell cell2_0 = row2.createCell(0);
        cell2_0.setCellValue("客户：");
        Cell cell2_2 = row2.createCell(2);
        cell2_2.setCellValue(customer.getCusName());
        Cell cell2_5 = row2.createCell(5);
        cell2_5.setCellValue("供方：");
        Cell cell2_6 = row2.createCell(6);
        cell2_6.setCellValue(company.getComName());
        // 第三行
        Row row3 = sheet.createRow(2);
        Cell cell3_0 = row3.createCell(0);
        cell3_0.setCellValue("联系电话：");
        Cell cell3_2 = row3.createCell(2);
        cell3_2.setCellValue(customer.getCusPhone());
        Cell cell3_5 = row3.createCell(5);
        cell3_5.setCellValue("联系电话：");
        Cell cell3_6 = row3.createCell(6);
        cell3_6.setCellValue(company.getComPhone());
        // 第四行
        Row row4 = sheet.createRow(3);
        Cell cell4_0 = row4.createCell(0);
        cell4_0.setCellValue("联系人：");
        Cell cell4_2 = row4.createCell(2);
        cell4_2.setCellValue(customer.getCusPerson());
        Cell cell4_5 = row4.createCell(5);
        cell4_5.setCellValue("地址：");
        Cell cell4_6 = row4.createCell(6);
        cell4_6.setCellValue(company.getComAddress());
        // 第五行
        Row row5 = sheet.createRow(4);
        Cell cell5_0 = row5.createCell(0);
        cell5_0.setCellValue("对账日期：");
        Cell cell5_2 = row5.createCell(2);
        cell5_2.setCellValue(DateUtils.getDate());
        Cell cell5_5 = row5.createCell(5);
        cell5_5.setCellValue("结款期限：");
        Cell cell5_6 = row5.createCell(6);
        cell5_6.setCellValue(customer.getCusPayDate());

        // 第六行对账日期信息
        Row row6 = sheet.createRow(5);
        row6.setHeight((short) (14 * 36));
        Cell cell6 = row6.createCell(0);
        cell6.setCellValue(sTime + "至" + eTime + "对账单");
        cellStyle.setFont(font12b);
        cell6.setCellStyle(cellStyle);

        /**
         * 对账明细列表
         */
        // 表头
        Row rowDetailHead = sheet.createRow(6);
        Cell cellDetailHead = rowDetailHead.createCell(0);
        cellDetailHead.setCellValue("序号");
        cellDetailHead.setCellStyle(cellStyle);
        cellDetailHead = rowDetailHead.createCell(1);
        cellDetailHead.setCellValue("送货日期");
        cellDetailHead.setCellStyle(cellStyle);
        cellDetailHead = rowDetailHead.createCell(2);
        cellDetailHead.setCellValue("订单号");
        cellDetailHead.setCellStyle(cellStyle);
        cellDetailHead = rowDetailHead.createCell(3);
        cellDetailHead.setCellValue("客户编码");
        cellDetailHead.setCellStyle(cellStyle);
        cellDetailHead = rowDetailHead.createCell(4);
        cellDetailHead.setCellValue("规格描述");
        cellDetailHead.setCellStyle(cellStyle);
        cellDetailHead = rowDetailHead.createCell(5);
        cellDetailHead.setCellValue("送货数量");
        cellDetailHead.setCellStyle(cellStyle);
        cellDetailHead = rowDetailHead.createCell(6);
        cellDetailHead.setCellValue("含税单价");
        cellDetailHead.setCellStyle(cellStyle);
        cellDetailHead = rowDetailHead.createCell(7);
        cellDetailHead.setCellValue("总金额");
        cellDetailHead.setCellStyle(cellStyle);
        cellDetailHead = rowDetailHead.createCell(8);
        cellDetailHead.setCellValue("备注");
        cellDetailHead.setCellStyle(cellStyle);

        // 行数索引值
        int index = 7;
        // 查询检索条件出入库信息
        float totalPrice = 0.00F;
        List<OutOrderDetail> detailList = outOrderDetailMapper.selectRecordExcelList(user.getCompanyId(), cusId, sTime, eTime);
        if (StringUtils.isNotEmpty(detailList)) {
            Row rowDetail = null;
            Cell cellDetail = null;
            CellStyle cellStyleDetail = wb.createCellStyle();
            // 设置单元格水平方向对其方式
            cellStyleDetail.setAlignment(HorizontalAlignment.CENTER);
            // 设置单元格垂直方向对其方式
            cellStyleDetail.setVerticalAlignment(VerticalAlignment.CENTER);
            // 排序
            int num = 1;
            String pnPrice = "0.00";
            String pnTotalPrice = "0.00";
            for (OutOrderDetail detail : detailList) {
                rowDetail = sheet.createRow(index);
                cellDetail = rowDetail.createCell(0);
                cellDetail.setCellValue(num);
                cellDetail.setCellStyle(cellStyleDetail);
                cellDetail = rowDetail.createCell(1);
                cellDetail.setCellValue(DateUtils.dateTime(detail.getOutTime() != null ? detail.getOutTime() : new Date()));
                cellDetail.setCellStyle(cellStyleDetail);
                cellDetail = rowDetail.createCell(2);
                cellDetail.setCellValue(detail.getOrderCode());
                cellDetail.setCellStyle(cellStyleDetail);
                cellDetail = rowDetail.createCell(3);
                cellDetail.setCellValue(detail.getPnCode());
                cellDetail.setCellStyle(cellStyleDetail);
                cellDetail = rowDetail.createCell(4);
                cellDetail.setCellValue(detail.getPnRemark());
                cellDetail.setCellStyle(cellStyleDetail);
                cellDetail = rowDetail.createCell(5);
                cellDetail.setCellValue(detail.getOutNumber());
                cellDetail.setCellStyle(cellStyleDetail);
                cellDetail = rowDetail.createCell(6);
                pnPrice = getFloat3(detail.getPnPrice());
                cellDetail.setCellValue(pnPrice);
                cellDetail.setCellStyle(cellStyleDetail);
                cellDetail = rowDetail.createCell(7);
                if (cusType) {
                    pnTotalPrice = getFloat3(detail.getTotalPrice());
                    cellDetail.setCellValue(pnTotalPrice);
                } else {
                    cellDetail.setCellValue("");
                }
                cellDetail.setCellStyle(cellStyleDetail);
                cellDetail = rowDetail.createCell(8);
                cellDetail.setCellValue("");
                cellDetail.setCellStyle(cellStyleDetail);
                num++;
                index++;
                if (cusType) {
                    totalPrice += detail.getTotalPrice();
                }
            }
        }

        // 合计金额
        Row sunPrice = sheet.createRow(index);
        cellStyle.setFont(font12b);
        cellDetailHead = sunPrice.createCell(6);
        cellDetailHead.setCellValue("合计金额：");
        cellDetailHead.setCellStyle(cellStyle);
        cellDetailHead = sunPrice.createCell(7);
        cellDetailHead.setCellValue(getFloat3(totalPrice));
        cellDetailHead.setCellStyle(cellStyle);
        index++;
        // 空白行
        sheet.addMergedRegion(new CellRangeAddress(index, index, 0, 8));
        index++;
        // 表尾
        cellEndStyle.setFont(font12);
        Row endRow = sheet.createRow(index);
        endRow.setHeight((short) (13 * 36));
        sheet.addMergedRegion(new CellRangeAddress(index, index, 0, 4));
        sheet.addMergedRegion(new CellRangeAddress(index, index, 5, 8));
        cellDetailHead = endRow.createCell(5);
        cellDetailHead.setCellValue("客户确认：");
        cellDetailHead.setCellStyle(cellEndStyle);
        index++;
        endRow = sheet.createRow(index);
        endRow.setHeight((short) (12 * 36));
        sheet.addMergedRegion(new CellRangeAddress(index, index, 0, 4));
        sheet.addMergedRegion(new CellRangeAddress(index, index, 5, 8));
        cellDetailHead = endRow.createCell(5);
        cellDetailHead.setCellValue("确认日期：");
        cellDetailHead.setCellStyle(cellEndStyle);
        return wb;
    }
}
