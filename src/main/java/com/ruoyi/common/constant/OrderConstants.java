package com.ruoyi.common.constant;

/**
 * 订单常量信息
 * @Author: Rainey
 * @Date: 2019/10/31 16:54
 * @Version: 1.0
 **/
public class OrderConstants {

    /**
     * 交付状态: <br>
     *     0;未交付 <br>
     *     1;交付中 <br>
     *     2;已交付 关闭订单 <br>
     */
    public static final Integer UN_DELIVERED = 0;
    public static final Integer IN_DELIVERY = 1;
    public static final Integer DELIVERED = 2;

    /** 校验返回结果，0 唯一，1、不唯一 */
    public final static String NAME_UNIQUE = "0";
    public final static String NAME_NOT_UNIQUE = "1";

    /**
     * 出库单状态常量描述: <br>
     *     0;未确认 <br>
     *     1;已确认 <br>
     */
    public static final Integer OUT_ORDER_NO_CONFIRM = 0;
    public static final Integer OUT_ORDER_CONFIRMED = 1;

    /**
     * 类型(0、客户出货单，1、客户退货单，2、外加工出库单，3、外加工入库单)
     */
    public static final Integer OUT_TYPE_CUS_OUT = 0;
    public static final Integer OUT_TYPE_CUS_BACK = 1;
    public static final Integer OUT_TYPE_WJG_OUTHOUSE = 2;
    public static final Integer OUT_TYPE_WJG_INHOUSE = 3;

    /** 校验返回结果，0 唯一，1、不唯一 */
    public final static Integer CUS_TYPE_KEHU = 1;
    public final static Integer CUS_TYPE_WJG = 2;

}
