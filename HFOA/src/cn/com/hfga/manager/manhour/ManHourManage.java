package cn.com.hfga.manager.manhour;

import java.util.List;

import cn.com.hfga.dto.manhour.ManHourDTO;
import cn.com.hfga.dto.manhour.ManhourSearchDTO;
import cn.com.hfga.entity.manhour.ManHourEntity;

public interface ManHourManage {
	
	//插入实体
	public int insetOne(ManHourDTO manHourDTO);
	
	//判断是否已经提交过
	public List<ManHourEntity> isExist(String day);
	
	/**
	 * 个人工时查询
	 * 110 解释  
	 * 最开始1 为个人查询 0为集合查询 
	 * 第二个1为部门是全部 0部门不是全部 
	 * 第三个1是种类为全部 0种类不是全部
	 */
	public List<ManHourEntity> get100(ManhourSearchDTO manhourSearchDTO);
	
	public List<ManHourEntity> get110(ManhourSearchDTO manhourSearchDTO);
	
	public List<ManHourEntity> get101(ManhourSearchDTO manhourSearchDTO);
	
	public List<ManHourEntity> get111(ManhourSearchDTO manhourSearchDTO);
	
	/**
	 * 全部工时查询 
	 */
	public List<ManHourEntity> get000(ManhourSearchDTO manhourSearchDTO);
	
	public List<ManHourEntity> get010(ManhourSearchDTO manhourSearchDTO);
	
	public List<ManHourEntity> get001(ManhourSearchDTO manhourSearchDTO);
	
	public List<ManHourEntity> get011(ManhourSearchDTO manhourSearchDTO);
	
	/**
	 * 得到一条记录
	 */
	public List<ManHourEntity> getOne(String id);
}
