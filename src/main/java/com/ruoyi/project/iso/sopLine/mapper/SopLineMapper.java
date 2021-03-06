package com.ruoyi.project.iso.sopLine.mapper;

import com.ruoyi.project.iso.sopLine.domain.SopLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 作业指导书  产线 配置 数据层
 * 
 * @author sj
 * @date 2019-06-15
 */
public interface SopLineMapper 
{
	/**
     * 查询作业指导书  产线 配置信息
     * 
     * @param id 作业指导书  产线 配置ID
     * @return 作业指导书  产线 配置信息
     */
	public SopLine selectSopLineById(Integer id);
	
	/**
     * 查询作业指导书  产线 配置列表
     * 
     * @param sopLine 作业指导书  产线 配置信息
     * @return 作业指导书  产线 配置集合
     */
	public List<SopLine> selectSopLineList(SopLine sopLine);
	
	/**
     * 新增作业指导书  产线 配置
     * 
     * @param sopLine 作业指导书  产线 配置信息
     * @return 结果
     */
	public int insertSopLine(SopLine sopLine);


	/**
	 * 批量删除作业指导书  产线 配置
	 * @param companyId 公司id
	 * @param lineId 产线id
	 * @param sopid SOP id
	 * @param sopTag sop配置标记
	 * @return 结果
	 */
	public int deleteSopLine(@Param("companyId")int companyId,
							 @Param("lineId")int lineId,@Param("sopId")Integer sopid,@Param("sopTag") Integer sopTag);

	/**
	 * 根据公司id 产线id SOP id查询所以的产线SOP 配置细心
	 * @param companyId 公司id
	 * @param lineId 产线id
	 * @param sopId SOP id
	 * @param sopTag sop配置标记流水线或者单工位
	 * @return 结果
	 */
	List<SopLine> selectLineAllSopConfig(@Param("companyId") int companyId,@Param("lineId") int lineId,
										 @Param("sopId") int sopId,@Param("sopTag") int sopTag);

	/**
	 * 通过作业指导书id查询产线配置列表信息
	 * @param companyId 公司第
	 * @param isoId 作业指导书id
	 * @return 结果
	 */
	List<SopLine> selectSopLineListBySopId(@Param("companyId") Integer companyId,@Param("sopId") Integer isoId);

    /**
     * 根据公司id 产线id 产品编号查询对应SOP 配置
     * @param companyId 公司id
     * @param lineId 产线id
     * @param code 产品code
     * @return
     */
	SopLine selectSopByCompanyAndLineAndCode(@Param("companyId") int companyId,@Param("lineId") int lineId,
                                             @Param("code")String code);

	/**
	 * 根据公司id，产线id，产品编码，工位id查询对应的配置信息
	 * @param companyId 公司id
	 * @param lineId 产线id
	 * @param pcode 产品编码
	 * @param wid 工位id
	 * @return
	 */
	SopLine selectSopByCidAndLineIdAndPidAndWid(@Param("companyId")int companyId,@Param("lineId")int lineId,
												@Param("pcode")String pcode,@Param("wid")int wid);


	/**
	 * 单工位SOP配置列表
	 * @param sopLine sop信息
	 * @return 结果
	 */
	List<SopLine> selectSopLineList2(SopLine sopLine);

	/**
	 * 根据SOP 产线配置主表id删除对应详情信息
	 * @param sid 主表id
	 * @return
	 */
	int deleteSopLineBySid(@Param("sid")int sid);

	/**
	 * 通过产线id删除sop配置明细
	 * @param companyId 公司id
	 * @param lineId 产线id
	 * @return 结果
	 */
    int deleteSopLineByLineId(@Param("companyId") Integer companyId,@Param("lineId") Integer lineId);

	/**
	 * 通过页信息查询配置列表
	 *
	 * @param companyId 公司id
	 * @param pageId    页id
	 * @return 结果
	 */
    List<SopLine> selectSopLineListByPageId(@Param("companyId") Integer companyId, @Param("pageId") Integer pageId);

	/**
	 * 查询配置过的产品列表
	 * @param sopLine 配置信息
	 * @return 结果
	 */
	List<SopLine> selectCnfPro(SopLine sopLine);

	/**
	 * 查询配置明细
	 * @param companyId 公司id
	 * @param sId 主表id
	 * @return 结果
	 */
	List<SopLine> selectSopLineBySId(@Param("companyId") Integer companyId, @Param("sId") Integer sId);
}