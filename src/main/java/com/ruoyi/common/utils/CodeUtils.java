package com.ruoyi.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生产各个单据的编号工具类型
 */
public class CodeUtils {

    /**
     * 获取工单号 GD+公司id属性
     * @return
     */
    public static String getWorkOrderCode(){
        return "GD" +getCode();
    }

    /**
     * 自动生成客户出库单
     * @return 出库单
     */
    public static String getCusOutCode(){
        return "KHCK"+getCode()+getRandom();
    }

    /**
     * 自动生成客户退货单
     * @return 退货单
     */
    public static String getCusBackCode(){
        return "KHTH"+getCode()+getRandom();
    }

    /**
     * 自动生成外加工出库单
     * @return 外加工出库单
     */
    public static String getWjgOutCode(){
        return "WJGCK"+getCode()+getRandom();
    }

    /**
     * 自动生成外加工入库单
     * @return 外加工入库单
     */
    public static String getWjgBackCode(){
        return "WJGRK"+getCode()+getRandom();
    }

    /**
     * 获取单号后一段部分
     * @return
     */
    public static String getCode(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return  format.format(new Date());
    }

    /**
     * 获取四位随机数
     * @return
     */
    public static String getRandom(){
        return String.valueOf((int)((Math.random()*9+1)*100));
    }

    /**
     * 获取四位随机英文字母
     * @return 结果
     */
    public static String getRandomChar(){
        String randomChar = "";
        for (int i = 0; i < 6; i++) {
            // 得到随机字母
            char c = (char) ((Math.random() * 26) + 97);
            randomChar += (c + "");
        }
        return String.valueOf(randomChar);
    }

    /**
     * 获取MRP编号
     * @return 结果
     */
    public static String getMrpCode(){
        return "MRP"+getCode()+getRandom();
    }

    /**
     * 生产MES主码
     * @return 结果
     */
    public static String getMesCode(){
        return "MES" + getCode();
    }
}
