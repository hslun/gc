package cn.com.hfga.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hfga.controller.file.VersionInfo;
import cn.com.hfga.controller.file.XmlDocument;
import cn.com.hfga.dao.car.CarBaseInfoDAO;
import cn.com.hfga.dao.entertain.EntertainDepartmentDAO;
import cn.com.hfga.dto.car.ApproveDTO;
import cn.com.hfga.dto.car.CarApplyInfoDTO;
import cn.com.hfga.dto.car.CarApporveInfoDTO;
import cn.com.hfga.dto.car.CarBackDTO;
import cn.com.hfga.dto.car.CarBaseInfoDTO;
import cn.com.hfga.dto.car.CarCollectInfoDTO;
import cn.com.hfga.dto.car.CarKindDTO;
import cn.com.hfga.dto.car.CarOutDTO;
import cn.com.hfga.dto.car.CarUseDetailDTO;
import cn.com.hfga.dto.privatecar.PrivateCarSearchDTO;
import cn.com.hfga.dto.user.FindLeaderDTO;
import cn.com.hfga.dto.user.UserDTO;
import cn.com.hfga.dto.user.UserModifyDTO;
import cn.com.hfga.entity.car.CarApplyInfoEntity;
import cn.com.hfga.entity.car.CarBaseInfoEntity;
import cn.com.hfga.entity.car.GasCarInfoEntity;
import cn.com.hfga.entity.car.InsuranceCarInfoEntity;
import cn.com.hfga.entity.car.ProtectCarInfoEntity;
import cn.com.hfga.entity.car.PunishCarInfoEntity;
import cn.com.hfga.entity.car.TrafficCarInfoEntity;
import cn.com.hfga.entity.entertain.EntertainDepartmentEntity;
import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;
import cn.com.hfga.entity.user.DepartmentEntity;
import cn.com.hfga.entity.user.UserEntity;
import cn.com.hfga.manageimpl.car.CarApplyInfoManageImpl;
import cn.com.hfga.manager.car.CarApplyInfoManage;
import cn.com.hfga.manager.car.CarBaseInfoManage;
import cn.com.hfga.manager.car.GasCarInfoManage;
import cn.com.hfga.manager.car.InsuranceCarInfoManage;
import cn.com.hfga.manager.car.ProtectCarInfoManage;
import cn.com.hfga.manager.car.PunishCarInfoManage;
import cn.com.hfga.manager.car.TrafficCarInfoManage;
import cn.com.hfga.manager.user.DepartmentManager;
import cn.com.hfga.manager.user.UserManager;
import cn.com.hfga.push.android.AndroidPushEntity;
import cn.com.hfga.push.android.AndroidPushUtil;
import cn.com.hfga.push.ios.IOSPushEntity;
import cn.com.hfga.push.ios.IOSPushUtil;
import cn.com.hfga.util.common.BeanUtils;
import cn.com.hfga.util.common.LogUtil;
import cn.com.hfga.util.common.MD5Util;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author xinyc
 * @since 2014-11-13 登录功能
 */
@Controller
public class LoginController {

	@Autowired
	private UserManager userManager;
	@Autowired
	private CarApplyInfoManage carApplyInfoManage;
	@Autowired
	private CarBaseInfoManage carBaseInfoManage;
	@Autowired
	private GasCarInfoManage gasCarInfoManage;
	@Autowired
	private PunishCarInfoManage punishCarInfoManage;
	@Autowired
	private TrafficCarInfoManage trafficCarInfoManage;
	@Autowired
	private ProtectCarInfoManage protectCarInfoManage;
	@Autowired
	private XmlDocument xmlDocument;
	@Autowired
	private InsuranceCarInfoManage insuranceCarInfoManage;
	@Autowired
	
	

	private DepartmentManager departmentManager;

	private CarBaseInfoDAO carBaseInfoDAO;
	
	@Autowired
	private VersionInfo versionInfo;
	
	@Autowired
	private CarApplyInfoManageImpl carApplyInfoManageImpl;
	// 登录
	@RequestMapping(value = "/Login")
	@ResponseBody
	// 自动将放回list对象的结果转换为json
	public List<UserEntity> doLogin(UserDTO UserDTO,HttpServletRequest request) {
		UserDTO.setPassword(MD5Util.toMD5(UserDTO.getPassword()));
		String content = UserDTO.getUsername() + "登陆了系统";
		// 将程序上传至服务器时将d盘改为c盘
		//LogUtil.writeFile("d:\\log.txt", content);
		return userManager.findOne(UserDTO);
	}

	//获取版本信息
	@RequestMapping(value = "/parseXmlLogin")
	@ResponseBody
	public JSONObject parseXmlLogin(HttpServletRequest httpServletRequest){
		List<Map<String, Object>> list = versionInfo.parserXmlLogin(httpServletRequest);
		//将list转换为json对象
		JSONObject jsonObj = new JSONObject();
		for(Map<String, Object> map:list){
			jsonObj.putAll(map);
		}
		return jsonObj;
	}
	
	//获取版本信息
	@RequestMapping(value = "/demo")
	@ResponseBody
	public JSONObject demo(){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "杨世业");
		jsonObj.put("age", "25");
		return jsonObj;
	}
	
	// 获取所有的部门信息
	@RequestMapping(value = "/Department")
	@ResponseBody
	public List<DepartmentEntity> getDepartmentManagers() {
		return departmentManager.getAll();
	}

	// 查询借车信息
	@RequestMapping(value = "/ApplyInfo")
	@ResponseBody
	public List<CarApplyInfoEntity> doApplyInfo(String applyname) {
		List<CarApplyInfoEntity> list = carApplyInfoManage.getInfo(applyname);
		return list;
	}

	// 车辆基本信息
	@RequestMapping(value = "/CarBaseInfo")
	@ResponseBody
	public List<CarBaseInfoEntity> doCarBaseInfo() throws APIRequestException {
		List<CarBaseInfoEntity> list = carBaseInfoManage.getInfo();
		List<CarBaseInfoEntity> list1 = new ArrayList<CarBaseInfoEntity>();
		for(CarBaseInfoEntity car:list){
			if(!"废弃".equals(car.getCarState())){
				list1.add(car);
			}
		}
		return list1;
	}

	// 测试推送函数
	public void tuiSong(String username, String info) throws APIConnectionException, APIRequestException {
	}

	/**
	 * 推送平台是android tag()平台是 Android， 目标是 tag 为 "tag1" 的设备， 内容是 Android 通知
	 * ALERT，并且标题为 TITLE。
	 * 
	 * @return
	 */

	public static PushPayload buildPushObject_android_tag_alertWithTitle() {
		return PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.tag("tag1"))
				.setNotification(Notification.android("有新的审批信息", "信心", null)).build();
	}

	public static PushPayload buildPushObject_all_alias_alert2(String username, String Info) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(username))
				.setNotification(Notification.alert(Info)).build();
	}

	public static PushPayload buildPushObject_all_all_alert1() {
		return PushPayload.alertAll("哈哈");
	}

	// 还车入库
	@RequestMapping(value = "/InApplyInfo")
	@ResponseBody
	public List<CarApplyInfoEntity> doInApplyInfo() {
		return carApplyInfoManage.getInofGargeInfo("已借出", "通过");
	}

	// 借车出库--信息
	@RequestMapping(value = "/OutApplyInfo")
	@ResponseBody
	public List<CarApplyInfoEntity> doOutApplyInfo() {
		
		return carApplyInfoManage.getInofGargeInfo("已预约", "通过");
	}

	// 保存借车申请信息
	@RequestMapping(value = "/SaveApplyInfo")
	@ResponseBody
	public int doSaveApplyInfo(CarApplyInfoDTO carApplyInfoDTO) throws APIConnectionException, APIRequestException {
		// carApplyInfoDTO.setBegintimeplan("2014-12-11 12:21");
		// carApplyInfoDTO.setEndtimeplan("2014-12-11 17:00");
		// carApplyInfoDTO.setAccountlength("1");
		// carApplyInfoDTO.setAccountplantime("1.3");
		// carApplyInfoDTO.setAccountrealtime("2");
		// carApplyInfoDTO.setApplyid("2015070306100");
		// carApplyInfoDTO.setApplytime("2015-07-03 11:10:12");
		// carApplyInfoDTO.setApplyusername("zhangyi");
		// carApplyInfoDTO.setAppplyman("张毅");
		// carApplyInfoDTO.setApproveman("郭爱斌");
		// carApplyInfoDTO.setBegintimeplan("2015-07-03 11:10:12");
		//// carApplyInfoDTO.setCarbeginexamin("");
		// carApplyInfoDTO.setCarcode("京QMQ381");
		//// carApplyInfoDTO.setCarendexamin("");
		//// carApplyInfoDTO.setCarid("4");
		//// carApplyInfoDTO.setCatype("奔驰");
		// carApplyInfoDTO.setComparemannum("2");
		// carApplyInfoDTO.setDepartment("信息技术部");
		// carApplyInfoDTO.setDriverString("张毅");
		// carApplyInfoDTO.setEndaddress("永丰");
		// carApplyInfoDTO.setEndtimeplan("2015-07-03 14:10:12");
		// // carApplyInfoDTO.setLengthbegin("56232");
		// carApplyInfoDTO.setUsecarreason("ccc");
		// carApplyInfoDTO.setStartaddress("海丰");
		// 首先判断这个时间段内是否已经有申请
		List<CarApplyInfoEntity> carApplyInfoEntities = carApplyInfoManage.getExist(carApplyInfoDTO);
		if (carApplyInfoEntities.size() == 0) {
			String content = carApplyInfoDTO.getAppplyman() + "申请了" + carApplyInfoDTO.getCatype() + " "
					+ carApplyInfoDTO.getCarcode();
			String record = carApplyInfoDTO.getApplytime() + " " + carApplyInfoDTO.getApplyusername() + " "
					+ carApplyInfoDTO.getAppplyman() + " " + carApplyInfoDTO.getApproveman() + " "
					+ carApplyInfoDTO.getBegintimeplan() + " " + carApplyInfoDTO.getCarcode() + " "
					+ carApplyInfoDTO.getCatype() + " " + carApplyInfoDTO.getDepartment() + " "
					+ carApplyInfoDTO.getEndaddress() + " " + carApplyInfoDTO.getEndtimeplan() + " "
					+ carApplyInfoDTO.getUsecarreason() + " " + carApplyInfoDTO.getStartaddress();
			//LogUtil.writeFile("d:\\log.txt", content + record);
			//此处需要推
			//Android
			AndroidPushEntity pushEntity=new AndroidPushEntity();
			pushEntity.setTriker("借车审批");
			pushEntity.setTitle("公车管理");
			pushEntity.setText("您有待审批消息");
			pushEntity.setAfter_open("com.hfga.hfgaoa.borrowcar.activity.BorrowCarApprove");
			//IOS
			IOSPushEntity iOSEntity=new IOSPushEntity();
			iOSEntity.setAlert("借车审批");
			iOSEntity.setSound("您有待审批消息");
			//Android推送
			pushEntity.setAlias(carApplyInfoDTO.getApproveman());
			androidPushUtil.setPushEntity(pushEntity);
			androidPushUtil.sendCustomizedcast();
				
			iOSEntity.setAlias(carApplyInfoDTO.getApproveman());
			iOSPushUtil.setiOSPushEntity(iOSEntity);
			iOSPushUtil.sendCustomizedcast();
			
			List<CarBaseInfoEntity> list = carBaseInfoManage.getOneInfo(carApplyInfoDTO.getCarcode());
			String state = list.get(0).getCarState();
			if(state.equals("暂停使用")){
				return -1;
			}else{
				return carApplyInfoManage.insertCarApplyInfo(carApplyInfoDTO);
			}
		} else {
			return -1;
		}

	}

	// 判断是否可申请借车
	@RequestMapping(value = "/ifCanApplyCar")
	@ResponseBody
	public int ifApplyCar(String username){
		int b = carApplyInfoManage.selectIfReturn(username);
		if(b>0){
			return 0;
		}
		return 1;
	}
	
	// 修改借车申信息
	@RequestMapping(value = "/ModifyApplyInfo")
	@ResponseBody
	public int doModifyApplyInfo(CarApplyInfoDTO carApplyInfoDTO) {
		// carApplyInfoDTO.setApplyid("2014121107001");
		// carApplyInfoDTO.setCarcode("京HV3507");
		String content = carApplyInfoDTO.getApplyusername() + "修改了借车申请";
		//LogUtil.writeFile("d:\\log.txt", content);
		return carApplyInfoManage.modifyInfo(carApplyInfoDTO);
	}

	// 借车申请 找到自己部门领导
	/*@RequestMapping(value = "/FindLeader")
	@ResponseBody
	public List<UserEntity> doFindLeader(String department) {
		System.out.print(department);
		System.out.println(userManager.findLeader(department));
		return userManager.findLeader(department);
	}*/
	// 借车申请 找到自己部门领导
	@RequestMapping(value = "/FindLeader")
	@ResponseBody
	public List<UserEntity> doFindLeader(String department) {
		System.out.print(department);
		System.out.println(userManager.findLeader(department));
		return userManager.findLeader(department);
	}
	
	

	// 修改的查找领导方法，部门经理将返回他的业务主管
	@RequestMapping(value = "/FindLeader2")
	@ResponseBody
	public List<UserEntity> doFindLeader2(HttpServletRequest request,FindLeaderDTO leaderDTO) {
		System.out.println("传过来的数据"+leaderDTO);
		// 获取传过来的DTO
		String department = leaderDTO.getDepartment();
		String roleid = leaderDTO.getRoleid();
		String workgroupid = leaderDTO.getWorkgroupid();
		String username = leaderDTO.getUsername();
		
		UserEntity user = userManager.findUserByNameAndDept(department, username).get(0);
		String secondManager = user.getWorkCategory();
		String userLeader = user.getWorkgroupName();
		String leaderId = user.getMobile();
		//部门副经理 WorkCategory=1说明是部门副经理
		if("1".equals(secondManager)){
			List<UserEntity> users = userManager.findLeader1(department,username);
			return users;
		}
		//普通员工
		if ("1".equals(roleid)) {
			List<UserEntity> findLeader = userManager.findLeader(department);
				return findLeader;
		//部门经理
		} else if ("2".equals(roleid)) {
			System.out.println("查询id是"+workgroupid);
			List<UserEntity> findLeader2 = userManager.findLeader2(workgroupid);
			System.out.println("部门经理"+findLeader2);
				return findLeader2;
		//公司领导 roleid==3&&department==公司领导
		} else if ("3".equals(roleid)&&"公司领导".equals(department)) {
			System.out.println("leaderId是="+leaderId);
			System.out.println("userLeader是="+userLeader);
			List<UserEntity> findUserByNameAndId = userManager.findUserByNameAndId(leaderId, userLeader);
			return findUserByNameAndId;
		}
		return userManager.findLeader(department);
	}
	/**
	 * 查找业务招待时审批人
	 * @param leaderDTO
	 * @return
	 */
	@RequestMapping(value = "/FindLeaderYWZD")
	@ResponseBody
	public List<UserEntity> doFindLeaderYWZD(FindLeaderDTO leaderDTO) {
		// 获取传过来的DTO
		String department = leaderDTO.getDepartment();
		System.out.println("department是"+department);
		String roleid = leaderDTO.getRoleid();
		System.out.println("roid是"+roleid);
		String workgroupid = leaderDTO.getWorkgroupid();
		System.out.println("workgroupid是"+workgroupid);
		String username = leaderDTO.getUsername();
		UserEntity user = userManager.findUserByNameAndDept(department, username).get(0);
		System.out.println(user);
		if ("1".equals(roleid)) {//roleid=3普通员工
			if(userManager.NeedDeng("1", leaderDTO.getUsername()).size()!=0){
				return userManager.findDeng(department);
			}
			else{
				return userManager.findLeader(department);
			}
			
		} else if ("2".equals(roleid)) {
			if("公司领导".equals(leaderDTO.getDepartment())){//不知道
				return userManager.findZhang();
			}else if(userManager.NeedDeng("1", leaderDTO.getUsername()).size()!=0){//不知道
				return userManager.findDeng(department);
			}
//			else if((leaderDTO.getUsername()).equals("张荣")){//不知道
//				return userManager.findJZ();
//			}
			else if("1".equals(user.getWorkCategory())&&"2".equals(roleid)){//部门副经理的判断 roleid=2 && WorkCategory=1
				List<UserEntity> users = userManager.findLeader1(department,username);
				return users;
			}else{
				return userManager.findLeader2(workgroupid);
			}
			
		}else if("3".equals(roleid)){
			if("公司领导".equals(leaderDTO.getDepartment())&&"3".equals(roleid)){//公司领导的判断 roleid=3 && Department=公司领导
				System.out.println("Modeile是="+user.getMobile()+"eorkName是"+user.getWorkgroupName());
				List<UserEntity> findUserByNameAndId = userManager.findUserByNameAndId(user.getMobile(), user.getWorkgroupName());
				return findUserByNameAndId;
			}else{//查询roleid=3的员工的上级领导
				return userManager.findLeader(department);
			}
		}
		return null;
	}
	
	
	// 审批
	@RequestMapping(value = "/Approve")
	@ResponseBody
	public int doApprove(ApproveDTO approveDTO) throws APIConnectionException, APIRequestException {
		List<CarApplyInfoEntity> list = carApplyInfoManage.getOne(approveDTO.getApplyid());
		AndroidPushEntity pushEntity=new AndroidPushEntity();
		pushEntity.setTriker("审批完毕");
		pushEntity.setTitle("公车管理");
		pushEntity.setText("您的借车申请已审批通过");
		pushEntity.setAfter_open("com.hfga.hfgaoa.borrowcar.main.HomePage");
		pushEntity.setAlias(list.get(0).getApplyMan());
		androidPushUtil.setPushEntity(pushEntity);
		androidPushUtil.sendCustomizedcast();
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("审批完毕");
		iOSEntity.setSound("您的借车申请已审批通过");
		//Android推送
		iOSEntity.setAlias(list.get(0).getApplyMan());
		iOSPushUtil.setiOSPushEntity(iOSEntity);
		iOSPushUtil.sendCustomizedcast();
		String content = approveDTO.getApplyid() + "车辆申请信息被允许" + "审批人是:" + approveDTO.getApproveman();
		//LogUtil.writeFile("d:\\log.txt", content);
		return carApplyInfoManage.ApproveInfo(approveDTO);
	}

	// 审批否决
	@RequestMapping(value = "/DenyApprove")
	@ResponseBody
	public int doDenyApporve(ApproveDTO approveDTO) throws APIConnectionException, APIRequestException {
		List<CarApplyInfoEntity> list = carApplyInfoManage.getOne(approveDTO.getApplyid());
		AndroidPushEntity pushEntity=new AndroidPushEntity();
		pushEntity.setTriker("借车申请被否决");
		pushEntity.setTitle("公车管理");
		pushEntity.setText("您的借车申请已被否决");
		pushEntity.setAfter_open("com.hfga.hfgaoa.borrowcar.main.HomePage");
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("借车申请被否决");
		iOSEntity.setSound("您的借车申请已被否决");
		//Android推送
		pushEntity.setAlias(list.get(0).getApplyMan());
		androidPushUtil.setPushEntity(pushEntity);
		androidPushUtil.sendCustomizedcast();
		
		iOSEntity.setAlias(list.get(0).getApplyMan());
		iOSPushUtil.setiOSPushEntity(iOSEntity);
		iOSPushUtil.sendCustomizedcast();
		String content = approveDTO.getApplyid() + "车辆申请信息被允许";
		//LogUtil.writeFile("d:\\log.txt", content);
		return carApplyInfoManage.denyApprove(approveDTO);
	}

	// 获取 审批 信息
	@RequestMapping(value = "/ApproveInfo")
	@ResponseBody
	public List<CarApplyInfoEntity> doGetApproveInfo(CarApporveInfoDTO carApporveInfoDTO) {
		// carApporveInfoDTO.setDepartment("信息技术部");
		// carApporveInfoDTO.setUserid("157");
		// carApporveInfoDTO.setUsername("张毅");
		List<CarApplyInfoEntity> list = new ArrayList<CarApplyInfoEntity>();
		List<UserEntity> user = new ArrayList<UserEntity>();
		System.out.print(carApporveInfoDTO.getDepartment());
		System.out.print(carApporveInfoDTO.getUsername());
		System.out.print(carApporveInfoDTO.getUserid());
		String departmemt=carApporveInfoDTO.getDepartment();
		user = userManager.findroleid(carApporveInfoDTO.getUserid());
		switch (user.get(0).getRoleId()) {
		case 2:
			list = carApplyInfoManage.getApproveInfo2(carApporveInfoDTO);
			break;
		case 3:
			list = carApplyInfoManage.getApproveInfo1(carApporveInfoDTO);
			break;
		default:
			break;
		}
		if("公司领导".equals(departmemt)){
			List<CarApplyInfoEntity> leadList = new ArrayList<CarApplyInfoEntity>();
			leadList=carApplyInfoManage.getApproveInfo3(carApporveInfoDTO);
			if(leadList.size()>0){
				list.addAll(leadList);
			}
		}
		System.out.print(list.toString());
		return list;
	}

	// 借车出库 操作
	@RequestMapping(value = "/OutDeal")
	@ResponseBody
	public int doOutGarage(CarOutDTO carOutDTO) throws APIConnectionException, APIRequestException {
		List<CarApplyInfoEntity> list = carApplyInfoManage.getOne(carOutDTO.getApplyid());
		// 更新开始的路程
		int i = 0;
		List<CarBaseInfoEntity> list1 = null;
		for (CarApplyInfoEntity carApplyInfoEntity : list) {
			list1 = carBaseInfoManage.getBeginlength(carApplyInfoEntity.getCarCode());
			break;
		}

		for (CarBaseInfoEntity carBaseInfoEntity : list1) {
			i = carApplyInfoManage.outgarage(carOutDTO.getApplyid(), carOutDTO.getBegintime(),
					String.valueOf(carBaseInfoEntity.getCarDistance()));
			break;
		}

		if (i == 1) {
			tuiSong(list.get(0).getApplyMan(), "您可以去拿着钥匙开车了！");
			for (CarApplyInfoEntity carApplyInfoEntity : list) {
				// List<CarApplyInfoEntity>
				// list2=carApplyInfoManage.getOne(carApplyInfoEntity.getApplyId());
				String content = carApplyInfoEntity.getApplyId() + "借车出库," + "车牌号是" + carApplyInfoEntity.getCarCode()
						+ "借车人是:" + carApplyInfoEntity.getApplyUserName();
				//LogUtil.writeFile("d:\\log.txt", content);
				carBaseInfoManage.modifyState("已借出", carApplyInfoEntity.getCarCode());
				break;
			}

		} else {

		}
		return i;

	}

	// 还车入库 操作
	@RequestMapping(value = "/InDeal")
	@ResponseBody
	public int doInGarage(CarBackDTO carBackDTO) throws APIConnectionException, APIRequestException {

		// 还车操作
		int i = carApplyInfoManage.ingarage(carBackDTO.getApplyid(), carBackDTO.getEndtime(),
				carBackDTO.getReallength(), carBackDTO.getRealtime(), carBackDTO.getLengthend());
		// 更新车辆状态基本信息
		if (i == 1) {
			List<CarApplyInfoEntity> list = carApplyInfoManage.getOne(carBackDTO.getApplyid());
			for (CarApplyInfoEntity carApplyInfoEntity : list) {
				carBaseInfoManage.modifyLen(carApplyInfoEntity.getCarCode(), carApplyInfoEntity.getLengthEnd());
				carBaseInfoManage.modifyState("在库", carApplyInfoEntity.getCarCode());
				String content = carApplyInfoEntity.getApplyId() + "还车入库," + "车牌号是" + carApplyInfoEntity.getCarCode();
				//LogUtil.writeFile("d:\\log.txt", content);
				tuiSong(carApplyInfoEntity.getApplyMan(), "还车成功！");
				break;
			}
		}

		return i;
	}

	// 获取借车出库的信息
	@RequestMapping(value = "/OutInfo")
	@ResponseBody
	public List<CarApplyInfoEntity> doGetOutInfo(String State) {
		return carApplyInfoManage.getOIInfo("已预约");
	}

	// 获取还车入库的信息
	@RequestMapping(value = "/InInfo")
	@ResponseBody
	public List<CarApplyInfoEntity> doGetInInfo(String State) {
		return carApplyInfoManage.getOIInfo("已借出");
	}

	// 汇总信息
	@RequestMapping(value = "/SearchCarInfo")
	@ResponseBody
	public List<Map<String, Object>> doGetResult(CarCollectInfoDTO carCollectInfoDTO) {
		List<Map<String, Object>> o = new ArrayList<Map<String,Object>>();
		List<Object[]> list = new ArrayList<Object[]>();
		System.out.println(carCollectInfoDTO.getCarinfo());
		System.out.println(carCollectInfoDTO.getDepartment());
		System.out.println(carCollectInfoDTO.getEndtime());
		System.out.println(carCollectInfoDTO.getStarttime());
		System.out.print(carCollectInfoDTO.getKind());
		switch (Integer.valueOf(carCollectInfoDTO.getKind())) {
		case 1:
			list = carApplyInfoManage.getColectInfo4(carCollectInfoDTO);
			break;
		case 2:
			list = carApplyInfoManage.getCollectInfo2(carCollectInfoDTO);
			break;
		case 3:
			list = carApplyInfoManage.getCollectInfo3(carCollectInfoDTO);
			break;
		case 4:
			list = carApplyInfoManage.getCollectInfo1(carCollectInfoDTO);
			break;
		default:
			break;
		}
		System.out.print(list.toString());
		if (list.size() != 0) {
			o = new ArrayList<Map<String, Object>>();
			for (Object[] ob : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("department", ob[0]);
				map.put("carCode", ob[1]);
				map.put("accountLength", ob[2]);
				map.put("accountRealTime", ob[3]);
				o.add(map);
			}
			System.out.println(o.toString());
		} else {
			//先获取所有的车牌
			List<String> carNums = carBaseInfoManage.getCarNum();
			//查询某一个部门所有的车型都为空
			if(carCollectInfoDTO.getCarinfo().equals("全部")){
				//遍历所有车型
				for(int i=0;i<carNums.size();i++){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("department", carCollectInfoDTO.getDepartment());
					map.put("carCode", carNums.get(i));
					map.put("accountLength", 0);
					o.add(map);
				}
			}else{
				//否则的话则是某一个部门查询某一辆车
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("department", carCollectInfoDTO.getDepartment());
				map.put("carCode", carCollectInfoDTO.getCarinfo());
				map.put("accountLength", 0);
				o.add(map);
			}
		}

		return o;
	}

	// 使用明细
	@RequestMapping(value = "/CarInfoDetail")
	@ResponseBody
	public List<CarApplyInfoEntity> doGetDetailInfo(CarUseDetailDTO carUseDetailDTO) {
		if (!carUseDetailDTO.getApplyman().equals("")) {
			if (carUseDetailDTO.getDepatment().equals("全部") && carUseDetailDTO.getCarinfo().equals("全部")) {
				return carApplyInfoManage.getDetail2(carUseDetailDTO);
			} else if (!carUseDetailDTO.getDepatment().equals("全部") && !carUseDetailDTO.getCarinfo().equals("全部")) {
				String[] car = carUseDetailDTO.getCarinfo().split(" ");
				carUseDetailDTO.setCarinfo(car[1]);
				return carApplyInfoManage.getDetailInfo(carUseDetailDTO);
			} else if (!carUseDetailDTO.getDepatment().equals("全部") && carUseDetailDTO.getCarinfo().equals("全部")) {
				return carApplyInfoManage.getDetail3(carUseDetailDTO);
			} else if (carUseDetailDTO.getDepatment().equals("全部") && !carUseDetailDTO.getCarinfo().equals("全部")) {
				// 部门写了全部 人写了 车的信息部分
				String[] car = carUseDetailDTO.getCarinfo().split(" ");
				carUseDetailDTO.setCarinfo(car[1]);
				return carApplyInfoManage.getDetail1(carUseDetailDTO);
			} else {
				return null;
			}
		} else if (carUseDetailDTO.getApplyman().equals("")) {
			if (carUseDetailDTO.getDepatment().equals("全部") && carUseDetailDTO.getCarinfo().equals("全部")) {
				return carApplyInfoManage.getDetail11(carUseDetailDTO);
			} else if (!carUseDetailDTO.getDepatment().equals("全部") && !carUseDetailDTO.getCarinfo().equals("全部")) {
				String[] car = carUseDetailDTO.getCarinfo().split(" ");
				carUseDetailDTO.setCarinfo(car[1]);
				return carApplyInfoManage.getDetail00(carUseDetailDTO);
			} else if (!carUseDetailDTO.getDepatment().equals("全部") && carUseDetailDTO.getCarinfo().equals("全部")) {
				return carApplyInfoManage.getDetail10(carUseDetailDTO);
			} else if (carUseDetailDTO.getDepatment().equals("全部") && !carUseDetailDTO.getCarinfo().equals("全部")) {
				String[] car = carUseDetailDTO.getCarinfo().split(" ");
				carUseDetailDTO.setCarinfo(car[1]);
				return carApplyInfoManage.getDetail01(carUseDetailDTO);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	// 公车借用状态
	@RequestMapping(value = "/CarStatus")
	@ResponseBody
	public List<CarBaseInfoDTO> doGetCarStatus() {
		// 用others 字段暂时存储车辆借阅信息
		List<CarBaseInfoEntity> list = carBaseInfoManage.getInfo();
		List<CarBaseInfoDTO> carBaseInfoDTOs = new ArrayList<CarBaseInfoDTO>();
		for (CarBaseInfoEntity carBaseInfoEntity : list) {
			if(!"废弃".equals(carBaseInfoEntity.getCarState())){
				CarBaseInfoDTO carBaseInfoDTO = new CarBaseInfoDTO();
				List<CarApplyInfoEntity> listall = carApplyInfoManage
						.getAllApply(String.valueOf(carBaseInfoEntity.getID()));
				int lenall = 0;
				int lenapply = 0;
				List<CarApplyInfoEntity> listapply = carApplyInfoManage
						.getAllOrder(String.valueOf(carBaseInfoEntity.getID()));
				if (listall == null) {
					lenall = 0;
				} else {
					lenall = listall.size();
				}
				if (listapply == null) {
					lenapply = 0;
				} else {
					lenapply = listapply.size();
				}
				BeanUtils.copyProperties(carBaseInfoEntity, carBaseInfoDTO);
				carBaseInfoDTO.setOthers(getStatus(lenapply, lenall - lenapply));
				carBaseInfoDTOs.add(carBaseInfoDTO);
			}
		}
		return carBaseInfoDTOs;
	}

	// 测试日期时间专用
	@RequestMapping(value = "/add")
	@ResponseBody
	public String doAdday() {

		return carApplyInfoManage.delDay();
	}

	// 查看某个车的申请和预约信息
	@RequestMapping(value = "/CarStatusDetail")
	@ResponseBody
	public List<CarApplyInfoEntity> doGetStatusDetail(String carnum) throws UnsupportedEncodingException {
		System.out.print(carnum);
		System.out.print(carApplyInfoManage.getAllApplyDetail(carnum));
		return carApplyInfoManage.getAllApplyDetail(carnum);
	}

	// 用户修改用户名和密码
	@RequestMapping(value = "/ChangePassWord")
	@ResponseBody
	public int doChangePassword(UserModifyDTO userModifyDTO) {
		System.out.print(userManager.getId(userModifyDTO).toString());
		if (userManager.getId(userModifyDTO).size() == 0) {
			return -1;
		} else {
			userModifyDTO.setNewpassword(MD5Util.toMD5(userModifyDTO.getNewpassword()));
			System.out.print(userModifyDTO.getNewpassword());
			return userManager.changeInfo(userModifyDTO);
		}

	}

	// 获取一个实体的详细信息
	@RequestMapping(value = "/DetailInfo")
	@ResponseBody
	public List<CarApplyInfoEntity> doGetOne(String applyid) {
		System.out.print(applyid);
		return carApplyInfoManage.getOne(applyid);
	}

	/*
	 * 车辆情况详细信息界面
	 */

	// 获取车辆的加油信息--carid
	@RequestMapping(value = "/GetGasInfo")
	@ResponseBody
	public List<GasCarInfoEntity> doGetGasInfo(String carid) {
		System.out.println(carid);
		System.out.print(gasCarInfoManage.getAll(carid));
		return gasCarInfoManage.getAll(carid);
	}

	// 获取车辆的处罚信息--车牌号
	@RequestMapping(value = "/GetPunishInfo")
	@ResponseBody
	public List<PunishCarInfoEntity> doGetPunishInfo(String carnum) throws UnsupportedEncodingException {
		System.out.print(carnum);
		System.out.print(punishCarInfoManage.getAll(carnum));
		return punishCarInfoManage.getAll(carnum);
	}

	// 获取车辆事故信息--车牌号
	@RequestMapping(value = "/GetAccidentInfo")
	@ResponseBody
	public List<TrafficCarInfoEntity> doGetAccidentInfo(String carnum) throws UnsupportedEncodingException {
		System.out.print(carnum);
		System.out.print(trafficCarInfoManage.getAll(carnum));
		return trafficCarInfoManage.getAll(carnum);
	}

	// 获取车辆的保险信息
	@RequestMapping(value = "/GetInsuranceInfo")
	@ResponseBody
	public List<InsuranceCarInfoEntity> doGetInsuranceInfo(String carnum) throws UnsupportedEncodingException {
		// carnum="京HV3507";
		System.out.print(insuranceCarInfoManage.getAll(carnum));
		System.out.print("chepaihao" + carnum);
		List<InsuranceCarInfoEntity> list = insuranceCarInfoManage.getAll(carnum);
		return list;
	}

	// 获取车辆的保养维修信息
	@RequestMapping(value = "/GetProtectInfo")
	@ResponseBody
	public List<ProtectCarInfoEntity> doGetProtectCarInfo(String carnum) throws UnsupportedEncodingException {

		System.out.println(protectCarInfoManage.getAll(carnum));
		return protectCarInfoManage.getAll(carnum);

	}

	// 删除一个申请
	@RequestMapping(value = "/DelApply")
	@ResponseBody
	public int doDelApply(String applyid) {
		String content = applyid + "被删除";
		//LogUtil.writeFile("d:\\log.txt", content);
		return carApplyInfoManage.delApply(applyid);
	}

	/**
	 * 通用函数 返回公车借用状态信息
	 */
	public String getStatus(int a, int b) {
		String resultString = String.valueOf(a) + "人申请/" + String.valueOf(b) + "人预约";
		System.out.println(resultString);
		return resultString;
	}

	// 获取版本信息
	@RequestMapping(value = "/GetVersion")
	@ResponseBody
	public List<Map<String, Object>> doGetVersion(HttpServletRequest httpServletRequest) {
		String path = httpServletRequest.getSession().getServletContext().getRealPath("/version.xml");
		return xmlDocument.parserXml(path);
	}

	/*
	 * 下面是一些特殊操作的路由----管理员可用
	 * 
	 * 
	 */

	// 更改郭博士的密码的代码
	@RequestMapping(value = "/ChangePassWor11d")
	@ResponseBody
	public int doChangeguo() {
		return userManager.changeGuo(MD5Util.toMD5("guoab"), "guoab");
	}

	// 所有人的密码进行MD5 加密
	@RequestMapping(value = "/ChangeToMD5")
	@ResponseBody
	public int doChangeToMD5() {
		List<UserEntity> list = userManager.getAll();
		for (UserEntity userEntity : list) {
			int i = userManager.passwordToMD5(MD5Util.toMD5(userEntity.getUserPassword()),
					userEntity.getId().toString());
		}
		return 1;
	}

	// 去掉借车申请人的空格
	@RequestMapping(value = "DeleteSpace")
	@ResponseBody
	public int doDeleteSpace() {
		List<CarApplyInfoEntity> list = carApplyInfoManage.getAll();
		for (CarApplyInfoEntity carApplyInfoEntity : list) {
			carApplyInfoEntity.setApplyMan(carApplyInfoEntity.getApplyMan().replace(" ", ""));
			carApplyInfoManage.modifyApplyMan(carApplyInfoEntity.getApplyMan(), carApplyInfoEntity.getApplyId());
		}
		carApplyInfoManage.modifyLength();
		carApplyInfoManage.modifyTime();
		return 1;
	}

	@RequestMapping(value = "ResetPassword")
	@ResponseBody
	public int doResetPassword(String username) {
		return userManager.ResetPassword(username);
	}

	/**
	 * 测试 最大id
	 * 
	 * @return 最大Id
	 */
	@RequestMapping(value = "/Apply")
	@ResponseBody
	public String doApply() {
		return carApplyInfoManage.CreateId("机电产品部");
	}

	@RequestMapping(value = "ResetPassword1")
	@ResponseBody
	public int doResetPassword1() {
		return userManager.ResetPassword("张毅");

	}

	// 测试借车申请
	@RequestMapping(value = "SaveCarApplyInfoEntity1")
	@ResponseBody
	public int SaveCarApplyInfoEntity1() {
		CarApplyInfoDTO carApplyInfoDTO = new CarApplyInfoDTO();
		carApplyInfoDTO.setAccountlength("1");
		carApplyInfoDTO.setAccountplantime("1.3");
		carApplyInfoDTO.setAccountrealtime("2");
		carApplyInfoDTO.setApplyid("2015070306100");
		carApplyInfoDTO.setApplytime("2015-07-03 11:10:12");
		carApplyInfoDTO.setApplyusername("zhangyi");
		carApplyInfoDTO.setAppplyman("张毅");
		carApplyInfoDTO.setApproveman("郭爱斌");
		carApplyInfoDTO.setBegintimeplan("2015-07-03 11:10:12");
		carApplyInfoDTO.setCarbeginexamin("");
		carApplyInfoDTO.setCarcode("京QMQ381");
		carApplyInfoDTO.setCarendexamin("");
		carApplyInfoDTO.setCarid("4");
		carApplyInfoDTO.setCatype("奔驰");
		carApplyInfoDTO.setComparemannum("2");
		carApplyInfoDTO.setDepartment("信息技术部");
		carApplyInfoDTO.setDriverString("张毅");
		carApplyInfoDTO.setEndaddress("永丰");
		carApplyInfoDTO.setEndtimeplan("2015-07-03 14:10:12");
		carApplyInfoDTO.setLengthbegin("56232");
		carApplyInfoDTO.setUsecarreason("ccc");
		carApplyInfoDTO.setStartaddress("海丰");
		return carApplyInfoManage.insertCarApplyInfo(carApplyInfoDTO);
	}
	
	//安卓推送类
	@Autowired
	private AndroidPushUtil androidPushUtil;
	
	//IOS推送类
	@Autowired
	private IOSPushUtil iOSPushUtil;
	
	@RequestMapping(value = "Test")
	public String TestSpring() {
		 androidPushUtil.test();
		 return "success";

	}
	@RequestMapping(value = "Test2")
	public String TestSpring2() {
		iOSPushUtil.test();
		 return "success";

	}
/****************************一条可爱的分割线*******************************/

	//Web-显示公车相关信息
	@RequestMapping("/CommonCar/display")
	@ResponseBody
	public Object carDisplay(HttpServletRequest request){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<CarApplyInfoEntity> list = new ArrayList<CarApplyInfoEntity>();
		list = carApplyInfoManage.carDisplay(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = carApplyInfoManage.getAllCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
		}
	
	//Web-获取车辆信息列表
	@RequestMapping("/CommonCar/carKind")
	@ResponseBody
	public List<CarKindDTO> getCarKind(){
		//List<CarKindDTO> list=new ArrayList<CarKindDTO>();
	//	list = carBaseInfoDAO.getCarKind();
		return carApplyInfoManage.getCarKind();
		
	}
	
	//Web-根据部门、申请人、车牌、申请时间查询√
	@RequestMapping(value = "/CommonCar/searchInfo")
	@ResponseBody
	public List<CarApplyInfoEntity> searchInfo(CarUseDetailDTO carUseDetailDTO){
		List<CarApplyInfoEntity> p= carApplyInfoManage.getSearchInfo(carUseDetailDTO);
		return p;
	
	}
	
	//Web-显示公车相关信息
	@RequestMapping("/CommonCar/searchInfoByPage")
	@ResponseBody
	public Object carDisplayByPage(HttpServletRequest request,CarUseDetailDTO carUseDetailDTO){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<CarApplyInfoEntity> list = new ArrayList<CarApplyInfoEntity>();
		list = carApplyInfoManage.getSearchInfoByPage(carUseDetailDTO,start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = carApplyInfoManage.getSearchInfoCount(carUseDetailDTO);
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
		}
	
	//Web-导出Excel√
		@RequestMapping(value = "/CommonCar/exportExcel")
		public void exportExcel(HttpServletRequest request, HttpServletResponse response, String number) {
			String[] nlist = number.split(","); // 获得传递过来的number列表
			// 获取导出文件夹
			String path = request.getSession().getServletContext().getRealPath("/");
			// 生成导出的文件名
			Date dt = new Date();
			SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
			String date = matter.format(dt);
			String fileName = "公车使用信息" + date + ".xlsx";
			String filePath = path + "/" + fileName;
			int flag = carApplyInfoManageImpl.export(nlist, filePath);
			if (flag != 1) {
				return;
			}
			try {
				// 根据不同的浏览器处理下载文件名乱码问题
				String userAgent = request.getHeader("User-Agent");
				// 针对IE或者是以ie为内核的浏览器
				if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				} else {
					// 非IE浏览器的处理：
					fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				}
				// 获取一个流
				InputStream in = new FileInputStream(new File(filePath));
				// 设置下载的响应头
				response.setHeader("content-disposition", "attachment;fileName=" + fileName);
				response.setCharacterEncoding("UTF-8");
				// 获取response字节流
				OutputStream out = response.getOutputStream();
				byte[] b = new byte[1024];
				int len = -1;
				while ((len = in.read(b)) != -1) {
					out.write(b, 0, len);
				}
				// 关闭
				out.close();
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	
	
}
