package cn.com.hfga.manager.car;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.hfga.dto.car.ApproveDTO;
import cn.com.hfga.dto.car.CarApplyInfoDTO;
import cn.com.hfga.dto.car.CarApporveInfoDTO;
import cn.com.hfga.dto.car.CarCollectInfoDTO;
import cn.com.hfga.dto.car.CarKindDTO;
import cn.com.hfga.dto.car.CarUseDetailDTO;
import cn.com.hfga.dto.car.SearchResultDTO;
import cn.com.hfga.entity.car.CarApplyInfoEntity;

/**
 * @author xinyc
 * @since 2014-11-14
 * 公车申请信息管理接口
 * */

public interface CarApplyInfoManage {
	
	public List<CarApplyInfoEntity> getInfo(String ApplyMan);
	
	public int getDepartmentId(String  department);
	
	public String CreateId(String department);
	
	public List<CarApplyInfoEntity> getInofGargeInfo(String state,String approvestate);
	
	public List<CarApplyInfoEntity> getOutofGargeInfo(String approvestate);
	
	public int  insertCarApplyInfo(CarApplyInfoDTO carApplyInfoDTO);
	
	public double countplantime(String startTime,String endTime);
	
	public int ApproveInfo(ApproveDTO approveDTO);
	
	//借车出库
	public int outgarage(String ApplyId,String begintime,String legnthbegin);
	
	//换车入库
	public int ingarage(String ApplyId,String endtime,String reallength,String realtime,String lengthend);
	
	//借车出库信息
	
	public List<CarApplyInfoEntity> getOIInfo(String State);
	
	//返回当前日期的字符串
	public String getDateTime();
	
	//查询某个人 使用明细
	public List<CarApplyInfoEntity> getDetailInfo(CarUseDetailDTO carUseDetailDTO);
	
	//查询某个部门的使用情况
	public  List<Object []> getCollectInfo1(CarCollectInfoDTO carCollectInfoDTO);
	
	//查询部门是全部  cartype 不是全部 
	public List<Object []> getCollectInfo2(CarCollectInfoDTO carCollectInfoDTO);
	
	//查询 车市全部    部门 是单个部门
	public  List<Object []> getCollectInfo3(CarCollectInfoDTO carCollectInfoDTO);
	
	
	//车市全部    部门是全部
	public List<Object []>  getColectInfo4(CarCollectInfoDTO carCollectInfoDTO);
	
	//管理审批
	public  List<CarApplyInfoEntity>  getApproveInfo1(CarApporveInfoDTO carApporveInfoDTO);
	
	//部门领导审批
	public List<CarApplyInfoEntity> getApproveInfo2(CarApporveInfoDTO carApporveInfoDTO);
	
	//公司领导审批其他部分的（由业务主管申请的）
	public List<CarApplyInfoEntity> getApproveInfo3(CarApporveInfoDTO carApporveInfoDTO);
	
	//获取全部申请信息
	public List<CarApplyInfoEntity> getAllApply(String carid);
	
	//获取全部申请信息
		public List<CarApplyInfoEntity> getAllApplyDetail(String carnum);
	
	//获取申请信息
	public List<CarApplyInfoEntity> getAllOrder(String carid);
	
	// 当前天数加1
	public String addDay();
	
	//当前天数减1
	public String delDay();
	
	//否决审批
	public  int denyApprove(ApproveDTO approveDTO);
	
	//服务器时间与
	//获取一个实体信息
	
	public List<CarApplyInfoEntity> getOne(String applyid);
		
	
	//修改申请单
	public int  modifyInfo(CarApplyInfoDTO carApplyInfoDTO);
	
	
	//部门全部 车不全部--个人
    public List<CarApplyInfoEntity> getDetail1(CarUseDetailDTO carUseDetailDTO);
		
	//部门是全部  车是全部--个人
	public List<CarApplyInfoEntity> getDetail2(CarUseDetailDTO carUseDetailDTO);
		
	//部门不是全部  车是全部--个人
	public List<CarApplyInfoEntity> getDetail3(CarUseDetailDTO carUseDetailDTO);
	
	
	//判断这个时间段内是否有申请
	public  List<CarApplyInfoEntity> getExist(CarApplyInfoDTO carApplyInfoDTO);
	
	//查找申请记录的条数
	public int  getCount();
	
	//获取最新一条记录
	public List<CarApplyInfoEntity> getTopOne();
	
	/**
	 * 不写人名
	 */
	public List<CarApplyInfoEntity> getDetail00(CarUseDetailDTO carUseDetailDTO);
	
	public List<CarApplyInfoEntity> getDetail01(CarUseDetailDTO carUseDetailDTO);
	
	public List<CarApplyInfoEntity> getDetail10(CarUseDetailDTO carUseDetailDTO);
	
	public List<CarApplyInfoEntity> getDetail11(CarUseDetailDTO carUseDetailDTO);
	
	//获取全部借车申请信息
    public List<CarApplyInfoEntity> getAll();
    
    //更改借车人 去掉其中的空格
    public int modifyApplyMan(String applyman,String applyid);
    
    //accountlength
    public int modifyLength();
    
    //accountTime
    public int modifyTime();
    
    //删除一个申请
    public int delApply(String applyid);

    //Web-根据部门、申请人、车牌、申请时间查询
	public List<CarApplyInfoEntity> getSearchInfo(CarUseDetailDTO carUseDetailDTO);

	//Web-显示公车相关信息
	public List<CarApplyInfoEntity> carDisplay(int start,int number);

	//Web-获得公车信息的总条数
	public int getAllCount();

	List<CarKindDTO> getCarKind();
	
	List<CarApplyInfoEntity> getSearchInfoByPage(CarUseDetailDTO carUseDetailDTO,int start,int number);
	
	int getSearchInfoCount(CarUseDetailDTO carUseDetailDTO);

	int selectIfReturn(String applyman);
	
}
