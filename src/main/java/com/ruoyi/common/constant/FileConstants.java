package com.ruoyi.common.constant;

/**
 * 文件体系ISO常量信息
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.common.constant
 * @Author: Administrator
 * @Date: 2019/6/13 15:09
 * @Description //TODO
 * @Version: 1.0
 **/
public class FileConstants {
    /**
     * 文件类型为文件夹 0 <br>
     *  文件夹、0 <br>
     *  文件、1 <br>
     */
    public static final Integer ITYPE_FOLDER = 0;
    /**
     * 文件类型为文件 1 <br>
     *  文件夹、0 <br>
     *  文件、1 <br>
     */
    public static final Integer ITYPE_FILE = 1;

    /**
     * 文件类别 为普通文件夹 0 <br>
     *  普通文件夹、0 <br>
     *  SOP、1 <br>
     *  ECN、2 <br>
     *  DCN、3 <br>
     *  SOP_FOLDER、4 SOP文件夹下的文件夹
     *  SOP_FILE、5 SOP文件夹下的文件
     */
    public static final Integer CATEGORY_COMMOM = 0;
    public static final Integer CATEGORY_SOP = 1;
    public static final Integer CATEGORY_ECN = 2;
    public static final Integer CATEGORY_DCN = 3;
    public static final Integer CATEGORY_SOP_FOLDER = 4;
    public static final Integer CATEGORY_SOP_FILE = 5;

    /**
     * 各个主要文件夹id
     * 作业指导书 7
     */
    public static final Integer FOLDER_SOP = 7;

    /**
     * 作业指导书sop配置标记
     * 默认0、流水线配置，1、车间单工位配置
     */
    public static final Integer SOP_TAG_LINE = 0;
    public static final Integer SOP_TAG_SINGWORK = 1;


    /**
     * 文件保存类型分类 </br>
     *  0 为公司轮播图 ,1、为产品文件,2、为订单文件
     */
    public static final Integer SAVE_TYPE_COM_PHOTO = 0;
    public static final Integer SAVE_TYPE_IS_PRODUCT = 1;
    public static final Integer SAVE_TYPE_IS_ORDER = 2;
}
