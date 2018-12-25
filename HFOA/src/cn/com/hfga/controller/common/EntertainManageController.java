package cn.com.hfga.controller.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hfga.dto.entertain.EntertainAnnualInfoDTO;
import cn.com.hfga.dto.entertain.EntertainApplyInfoDTO;
import cn.com.hfga.dto.entertain.EntertainRegisterInfoDTO;
import cn.com.hfga.dto.entertain.EntertainSearchInfoDTO;
import cn.com.hfga.dto.entertain.EntertainSumInfoDTO;
import cn.com.hfga.entity.entertain.EntertainDepartmentEntity;
import cn.com.hfga.entity.entertain.EntertainAnnualBudgetEntity;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;
import cn.com.hfga.entity.entertain.EntertainInfoEntity;
import cn.com.hfga.entity.entertain.EntertainObjectTypeEntity;
import cn.com.hfga.entity.entertain.EntertainInvoiceUnitEntity;
import cn.com.hfga.entity.entertain.EntertainRegisterInfoEntity;
import cn.com.hfga.entity.user.UserEntity;
import cn.com.hfga.manageimpl.entertain.EntertainDepartmentManageImpl;
import cn.com.hfga.manageimpl.entertain.EntertainAnnualBudgetManageImpl;
//import cn.com.hfga.entity.user.DepartmentEntity;
import cn.com.hfga.manageimpl.entertain.EntertainApplyInfoManageImpl;
import cn.com.hfga.manageimpl.entertain.EntertainInfoManageImpl;
import cn.com.hfga.manageimpl.entertain.EntertainObjectTypeManageImpl;
import cn.com.hfga.manageimpl.entertain.EntertainInvoiceUnitManageImpl;
import cn.com.hfga.manageimpl.entertain.EntertainRegisterInfoManageImpl;
import cn.com.hfga.push.android.AndroidPushEntity;
import cn.com.hfga.push.android.AndroidPushUtil;
import cn.com.hfga.push.ios.IOSPushEntity;
import cn.com.hfga.push.ios.IOSPushUtil;
import cn.com.hfga.util.CommonUtil;

@Controller
public class EntertainManageController {
	
	@Autowired
	private EntertainApplyInfoManageImpl entertainApplyInfoManageImpl;

	@Autowired
	private EntertainRegisterInfoManageImpl entertainRegisterInfoManageImpl;

	@Autowired
	private EntertainInfoManageImpl entertainInfoManageImpl;

	@Autowired
	private EntertainObjectTypeManageImpl entertainObjectTypeManageImpl;

    @Autowired
	private EntertainInvoiceUnitManageImpl entertainInvoiceUnitManageImpl;
	
	@Autowired
	private EntertainDepartmentManageImpl entertainDepartmentManageImpl;
	
	@Autowired
	private EntertainAnnualBudgetManageImpl entertainAnnualBudgetManageImpl;
	
	@Autowired
	private AndroidPushUtil androidPushUtil;
	
	@Autowired
	private IOSPushUtil iOSPushUtil;
	
	// 测试编号生成方法
	@RequestMapping(value = "/entertain/testnumber")
	@ResponseBody
	public Object testnumber(String num) {
		return entertainApplyInfoManageImpl.getNum(num);
	}

	// 测试获得List的方法
	@RequestMapping(value = "/entertain/testList")
	@ResponseBody
	public Object testList(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		return entertainInfoManageImpl.testList(entertainSearchInfoDTO);
	}

	
	/****************************************** 一条可爱的分割线 *********************************************/

	// 查询一张已完成明细表的全部信息
	@RequestMapping(value = "/entertain/getSearchInfo")
	@ResponseBody
	public EntertainInfoEntity getSearchInfo(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		return entertainInfoManageImpl.getSearchInfo1(entertainSearchInfoDTO);
	}

	//修改待审核通过列表
	@RequestMapping(value = "/entertain/approveX")
	@ResponseBody
	public int approveX(String number) {
		return entertainRegisterInfoManageImpl.updateStatusByN1("已完成", number);
	}
	
	// 根据部门获取该部门剩余的金额
	@RequestMapping(value = "/entertain/getLastSum")
	@ResponseBody
	public Object getLastSum(String department) {
		return entertainAnnualBudgetManageImpl.getLastSum(department);
	}
		
	// 根据查询条件获得列表
	@RequestMapping(value = "/entertain/getList")
	@ResponseBody
	public List<EntertainInfoEntity> getList(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		return entertainInfoManageImpl.getList(entertainSearchInfoDTO);
	}
	
	// 获取事前通过，事后待审核的List
	@RequestMapping(value = "/entertain/getAPassList")
	@ResponseBody
	public List<EntertainInfoEntity> getAPassList() {
		return entertainInfoManageImpl.getAPassList();
	}

	// 获取招待客户名称 √
	@RequestMapping(value = "/entertain/getType")
	@ResponseBody
	public List<EntertainObjectTypeEntity> getType() {
		return entertainObjectTypeManageImpl.getType();
	}

	// 存储新的招待客户名称 √
	@RequestMapping(value = "/entertain/saveType")
	@ResponseBody
	public int saveType(String objectName) {
		return entertainObjectTypeManageImpl.saveType(objectName);
	}
	
	// 获取发票出具单位名称
    @RequestMapping(value = "/entertain/getInvoiceUnitType")
	@ResponseBody
	public List<EntertainInvoiceUnitEntity> getInvoiceUnitType() {
		return entertainInvoiceUnitManageImpl.getInvoiceUnitType();
	}
	
    // 存储新的发票出具单位名称 
	@RequestMapping(value = "/entertain/saveInvoiceUnitType")
	@ResponseBody
	public int saveInvoiceUnitType(String InvoiceUnitName) {
		return entertainInvoiceUnitManageImpl.saveInvoiceUnitType(InvoiceUnitName);
	} 

	// 事前-保存申请 √
	@RequestMapping(value = "/entertain/saveApplyInfo")
	@ResponseBody
	public int saveApplyInfo(EntertainApplyInfoDTO entertainApplyInfoDTO,HttpServletRequest request) throws Exception {
		int flag = entertainApplyInfoManageImpl.saveEntertainApplyInfo(entertainApplyInfoDTO,request);
		if(flag==1){
			//需要进行推送
			AndroidPushEntity pushEntity=new AndroidPushEntity();
			pushEntity.setTriker("事前审批");
			pushEntity.setTitle("业务招待");
			pushEntity.setText("您有待审批消息");
			pushEntity.setAfter_open("com.hfga.hfgaoa.businessentertain.activity.BusinessPriorApprove");
			//IOS
			IOSPushEntity iOSEntity=new IOSPushEntity();
			iOSEntity.setAlert("事前审批");
			iOSEntity.setSound("您有待审批消息");
			//Android推送
			//for(int i=0;i<userList.size();i++){
			pushEntity.setAlias(entertainApplyInfoDTO.getApprover());
			androidPushUtil.setPushEntity(pushEntity);
			androidPushUtil.sendCustomizedcast();
				
			iOSEntity.setAlias(entertainApplyInfoDTO.getApprover());
			iOSPushUtil.setiOSPushEntity(iOSEntity);
			iOSPushUtil.sendCustomizedcast();
		}
		return flag;
	}
	
	// 测试推送
	@RequestMapping(value = "/entertain/testTuisong")
	@ResponseBody
	public void  testTuisong() {
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("事前审批");
		iOSEntity.setSound("您有待审批消息");
		iOSEntity.setAlias("肖乾帅");
		iOSPushUtil.setiOSPushEntity(iOSEntity);
		iOSPushUtil.sendCustomizedcast();
	}
	
	

	// 事前-删除申请 √
	@RequestMapping(value = "/entertain/deleteApplyInfo")
	@ResponseBody
	public int deleteApplyInfo(String ID) {
		System.out.println(ID);
		return entertainApplyInfoManageImpl.delete(ID);
	}

	// 事前-获取所有的申请信息 √
	@RequestMapping(value = "/entertain/getAll")
	@ResponseBody
	public List<EntertainApplyInfoEntity> getAll() {
		return entertainApplyInfoManageImpl.getAll();
	}
	
	// 事前-获取所有的申请信息 √
	@RequestMapping(value = "/entertain/getUnSubRecord")
	@ResponseBody
	public Object getUnSubRecord(HttpServletRequest request, HttpServletResponse response) {
		
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
		list = entertainApplyInfoManageImpl.getUnSubRecord(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = entertainApplyInfoManageImpl.getUnSubRecordCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}

	// 事前-获取个人申请信息 √
	@RequestMapping(value = "/entertain/getPersonal")
	@ResponseBody
	public List<EntertainApplyInfoEntity> getPersonal(String manager) {
		return entertainApplyInfoManageImpl.getPersonal(manager);
	}

	// 事前-获取个人通过的事前信息
	@RequestMapping(value = "/entertain/getPassApply")
	@ResponseBody
	public List<EntertainApplyInfoEntity> getPassApply(String manager) {
		return entertainApplyInfoManageImpl.getPassApply(manager);
	}

	// 事前-获取所有通过的事前信息
	@RequestMapping(value = "/entertain/getAllPassApply")
	@ResponseBody
	public List<EntertainApplyInfoEntity> getAllPassApply() {
		return entertainApplyInfoManageImpl.getAllPassApply();
	}

	// 事前-获取需要审批的信息（根据不 同审批人） √
	@RequestMapping(value = "/entertain/getApproveInfo")
	@ResponseBody
	public List<EntertainApplyInfoEntity> getApproveInfo(String approver) {
		System.out.println(approver);
		System.out.println(entertainApplyInfoManageImpl.getByApprove(approver).size());
		return entertainApplyInfoManageImpl.getByApprove(approver);
	}

	// 事前-审批操作 √
	@RequestMapping(value = "/entertain/approve")
	@ResponseBody
	public int approveInfo(String approver, String number) {
		
		//获取当前时间
		String t = CommonUtil.getInstance().getTime();
		String time = t.substring(0,10);
		
		//根据Number获取申请信息
		List<EntertainApplyInfoEntity> list=entertainApplyInfoManageImpl.get(number);
		AndroidPushEntity pushEntity=new AndroidPushEntity();
		pushEntity.setTriker("审批通过");
		pushEntity.setTitle("业务招待");
		pushEntity.setText("您的招待申请已审批通过");
		pushEntity.setAfter_open("com.hfga.hfgaoa.businessentertain.main.BusinessEntertainHomePage");
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("审批通过");
		iOSEntity.setSound("您的招待申请已审批通过");
		
		
		//Android推送
		//for(int i=0;i<userList.size();i++){
		pushEntity.setAlias(list.get(0).getManager());
		androidPushUtil.setPushEntity(pushEntity);
		androidPushUtil.sendCustomizedcast();
			
		iOSEntity.setAlias(list.get(0).getManager());
		iOSPushUtil.setiOSPushEntity(iOSEntity);
		iOSPushUtil.sendCustomizedcast();
		return entertainApplyInfoManageImpl.updateApprove("通过", approver, number,time);
	}

	// 事前-审批否决 √
	@RequestMapping(value = "/entertain/denyApply")
	@ResponseBody
	public int denyApply(String applyId) {
		System.out.println(applyId);
		//获取当前时间
		String t = CommonUtil.getInstance().getTime();
		String time = t.substring(0,10);
		int i = entertainApplyInfoManageImpl.updateStatusDeny("未通过", applyId, time);
		return i;
	}

	// 修改招待申请 √
	@RequestMapping(value = "/entertain/modifyOne")
	@ResponseBody
	public int modifyOne(EntertainApplyInfoDTO entertainApplyInfoDTO) throws Exception{
		return entertainApplyInfoManageImpl.modifyOne(entertainApplyInfoDTO);
	}
 
	// web-根据审批单号查询相关信息
	@RequestMapping(value = "/entertain/applyDetail")
	@ResponseBody
	public Object applyDetail(String number) {
		return entertainApplyInfoManageImpl.applyDetail(number);
	}

	// web-已审核招待明细-查询
	@RequestMapping(value = "/entertain/searchApply")
	@ResponseBody
	public Object searchApply(HttpServletRequest request, EntertainSearchInfoDTO entertainSearchInfoDTO) {
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
		list = entertainApplyInfoManageImpl.searchApply(start, number, entertainSearchInfoDTO);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = entertainApplyInfoManageImpl.getAllCompletedApplyCount(entertainSearchInfoDTO);
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}

	/****************************************** 一条可爱的分割线 *********************************************/
	// 事后-保存登记 √
	@RequestMapping(value = "/register/saveRegister")
	@ResponseBody
	public int saveRegisterInfo(EntertainRegisterInfoDTO entertainRegisterInfoDTO) {
		/*
		AndroidPushEntity pushEntity=new AndroidPushEntity();
		pushEntity.setTriker("事后审批");
		pushEntity.setTitle("业务招待");
		pushEntity.setText("您有待审批消息");
		pushEntity.setAfter_open("com.hfga.hfgaoa.businessentertain.activity.BusinessPriorApprove");
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("事后审批");
		iOSEntity.setSound("您有待审批消息");
		//Android推送
		//for(int i=0;i<userList.size();i++){
		pushEntity.setAlias("刘静");
		androidPushUtil.setPushEntity(pushEntity);
		androidPushUtil.sendCustomizedcast();
			
		iOSEntity.setAlias("刘静");
		iOSPushUtil.setiOSPushEntity(iOSEntity);
		iOSPushUtil.sendCustomizedcast();
		*/
		return entertainRegisterInfoManageImpl.saveEntertainRegisterInfo(entertainRegisterInfoDTO);
	}

	// 事后-获取所有的登记信息 √
	@RequestMapping(value = "/register/getAll")
	@ResponseBody
	public List<EntertainRegisterInfoEntity> getAllRegister() {
		return entertainRegisterInfoManageImpl.getAllRegister();
	}

	// 事后-获取所有待审核登记信息 √
	@RequestMapping(value = "/register/getReadyApproveInfo")
	@ResponseBody
	public List<EntertainRegisterInfoEntity> getReadyApproveInfo() {
		return entertainRegisterInfoManageImpl.getAllReady();
	}

	// 事后-录入凭证号，审核通过-根据ID（权限→刘静） √
	@RequestMapping(value = "/register/insertVoucherNum")
	@ResponseBody
	public int insertVoucherNum(String ID, String paidTime, String voucherNum) {
		
		return entertainRegisterInfoManageImpl.insertVoucherNum(ID, paidTime, voucherNum);
	}

	// 事后-录入凭证号、报销时间，审核通过-根据Number（权限→刘静）
	@RequestMapping(value = "/register/insertAllVoucherNum")
	@ResponseBody
	public int insertAllVoucherNum(String number, String paidTime, String voucherNum) {
		List<EntertainRegisterInfoEntity> list=entertainRegisterInfoManageImpl.get(number);
		/*
		AndroidPushEntity pushEntity=new AndroidPushEntity();
		pushEntity.setTriker("财务审核");
		pushEntity.setTitle("业务招待");
		pushEntity.setText("您的招待申请已审核通过");
		pushEntity.setAfter_open("com.hfga.hfgaoa.businessentertain.main.BusinessEntertainHomePage");
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("财务审核");
		iOSEntity.setSound("您的招待申请已审核通过");
		//Android推送
		//for(int i=0;i<userList.size();i++){
		pushEntity.setAlias(list.get(0).getRegisterMan());
		androidPushUtil.setPushEntity(pushEntity);
		androidPushUtil.sendCustomizedcast();
			
		iOSEntity.setAlias(list.get(0).getRegisterMan());
		iOSPushUtil.setiOSPushEntity(iOSEntity);
		iOSPushUtil.sendCustomizedcast();
		*/
		return entertainRegisterInfoManageImpl.insertAllVoucherNum(number, paidTime, voucherNum);
	}

	// 事后-财务审核未通过
	@RequestMapping(value = "/register/notThrough")
	@ResponseBody
	public int notThrough(String applyId) {
		return entertainRegisterInfoManageImpl.updateStatusUnapproved(applyId);
	}

	// 事后-取消审核（退回重填）-根据ID √
	@RequestMapping(value = "/register/cancelRegister")
	@ResponseBody
	public int cancelRegister(String applyId) {
		return entertainRegisterInfoManageImpl.updateStatus("待审核", applyId);
	}

	// 事后-取消审核（退回重填）-根据Number √
	@RequestMapping(value = "/register/cancelRegisterByN")
	@ResponseBody
	public int cancelRegisterByN(String number) {
		return entertainRegisterInfoManageImpl.updateStatusByN("待审核", number);
	}

	// 事后-个人撤回登记信息（从数据库删除）
	@RequestMapping(value = "/register/deleteRegister")
	@ResponseBody
	public int deleteRegister(String ID, String invoiceSum) {
		return entertainRegisterInfoManageImpl.deleteRegister(ID,invoiceSum);
	}

	// 事后-修改登记信息 √
	@RequestMapping(value = "/register/modifyRegiser")
	@ResponseBody
	public int modifyRegister(EntertainRegisterInfoDTO entertainRegisterInfoDTO) {
		return entertainRegisterInfoManageImpl.modifyRegister(entertainRegisterInfoDTO);
	}

	// 事后-获取个人申请信息 √
	@RequestMapping(value = "/register/getPersonalRegister")
	@ResponseBody
	public List<EntertainRegisterInfoEntity> getPersonalRegister(String registerman) {
		return entertainRegisterInfoManageImpl.getPersonalRegister(registerman);
	}

	// web-根据审批单号查询相关信息
	@RequestMapping(value = "/entertain/registerDetail")
	@ResponseBody
	public Object registerDetail(String number) {
		return entertainRegisterInfoManageImpl.registerDetail(number);
	}

	@RequestMapping(value = "/entertain/getOneRegister")
	@ResponseBody
	public Object getOneRegister(String number){
		return entertainRegisterInfoManageImpl.get(number);
	}
	/****************************************** 一条可爱的分割线 *********************************************/
	// Web-首页-已审核招待明细-显示
	@RequestMapping(value = "/entertain/wGetAllApproved")
	@ResponseBody
	public Object wGetAllApproved(HttpServletRequest request) {
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
		list = entertainInfoManageImpl.wGetAllApproved(start, number);
		System.out.println("查询的数据是"+list);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = entertainInfoManageImpl.getAllCompletedCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
		// return list;
	}
	// Web-首页-已审核招待明细-显示
	@RequestMapping(value = "/entertain/wGetSearchApprovedByPage")
	@ResponseBody
	public Object wGetAllApprovedByPage(HttpServletRequest request,EntertainSearchInfoDTO entertainSearchInfoDTO) {
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
		list = entertainApplyInfoManageImpl.wGetSearchApprovedByPage(entertainSearchInfoDTO,start, number);
		System.out.println("查询的数据"+list);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = entertainApplyInfoManageImpl.wGetSearchApprovedCount(entertainSearchInfoDTO);
		/*int total = list.size();*/
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
		// return list;
	}
	
	

	// Web-首页-待审核事后信息-显示
	@RequestMapping(value = "/entertain/wRGetAllApproved")
	@ResponseBody
	public Object wRGetAllApproved(HttpServletRequest request) {
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
		list = entertainInfoManageImpl.wRGetAllApproved(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = entertainInfoManageImpl.getAllRCompletedCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}
	
	// Web-首页-待审核事后信息-显示
	@RequestMapping(value = "/entertain/wRGetAllEntertain")
	@ResponseBody
	public Object wRGetAllEntertain(HttpServletRequest request) {
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
		list = entertainInfoManageImpl.wRGetAllEntertain(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = entertainInfoManageImpl.getAllEntertainCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}

	// Web-已审核招待明细-导出表单
	@RequestMapping(value = "/entertain/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, String number) {
		System.out.println("穿过来的数据"+number);
		String[] nlist = number.split(","); // 获得传递过来的number列表
		// 获取导出文件夹
		String path = request.getSession().getServletContext().getRealPath("/");
		// 生成导出的文件名
		Date dt = new Date();
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
		String date = matter.format(dt);
		String fileName = "业务招待明细" + date + ".xlsx";
		String filePath = path + "/" + fileName;
		int flag = entertainInfoManageImpl.export(nlist, filePath);
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
			response.setCharacterEncoding("UTF- 8");
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

	// web-首页-未审核招待明细-显示 √
	@RequestMapping(value = "/entertain/wGetAllUnapprovedRegister")
	@ResponseBody
	public Object wGetAllUnapprovedRegister(HttpServletRequest request) {
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<EntertainRegisterInfoEntity> list = new ArrayList<EntertainRegisterInfoEntity>();
		list = entertainRegisterInfoManageImpl.wGetAllUnapprovedRegister(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = entertainRegisterInfoManageImpl.getAllUnapprovedRegisterCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}

	// web-获得招待对象列表
	@RequestMapping("/entertain/getObject")
	@ResponseBody
	public List<EntertainObjectTypeEntity> getAllObject() {
		return entertainObjectTypeManageImpl.getAllObject();
	}

	// web-获得部门列表
	@RequestMapping("/entertain/getDepartment")
	@ResponseBody
	public List<EntertainDepartmentEntity> getAllDepartment() {
		return entertainDepartmentManageImpl.getAllDepartment();
	}
	
	@RequestMapping(value = "/entertain/wGetSearchApproved") 
	@ResponseBody
	public Object wGetSearchApproved(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		return entertainApplyInfoManageImpl.wGetSearchApproved(entertainSearchInfoDTO);
	}

	/****************************************** 一条可爱的分割线 *********************************************/
	
	// web-获得近十年的年份
	@RequestMapping("/entertain/getYear")
	@ResponseBody
	public Object getYear(){
		return entertainInfoManageImpl.getYear();
	}
	
	// web-招待年度预算-显示
	@RequestMapping("/entertain/wGetAnnualBudget")
	@ResponseBody
	public List<EntertainAnnualInfoDTO> wGetAnnualBudget(){
		return entertainAnnualBudgetManageImpl.wGetAnnualBudget();
		}
	
	// web-显示预算执行情况列表
	@RequestMapping("/entertain/wGetNowAnnual")
	@ResponseBody
	public List<EntertainAnnualBudgetEntity> wGetNowAnnual(){
		return entertainAnnualBudgetManageImpl.wGetNowAnnual();
		}
	// web-显示预算执行情况列表
	@RequestMapping("/entertain/wGetNowAnnual1")
	@ResponseBody
	public List<EntertainAnnualBudgetEntity> wGetNowAnnual1(String year){
		return entertainAnnualBudgetManageImpl.wGetNowAnnual1(year);
		}
	
	// web-插入修改的年度预算值
	@RequestMapping("/entertain/wSetAdjust")
	@ResponseBody
	public int wSetAdjust(String param){
		return entertainAnnualBudgetManageImpl.wSetAdjust(param);
		}
	
	// web-获得当前每个部门的发生额
	@RequestMapping("/entertain/wGetAllUsedNow")
	@ResponseBody
	public Object wGetAllUsed(){
		return entertainInfoManageImpl.wGetAllUsed();
		}
	
	// web-按照年份查询相信息
		@RequestMapping("/entertain/wGetSearchAnnual")
		@ResponseBody
		public Object wGetSearchAnnual(String year){
			return entertainInfoManageImpl.wGetSearchAnnual(year);
			}
		
	// web-获得选中年份的预算执行情况列表
		@RequestMapping("/entertain/wGetSearchImplementation")
		@ResponseBody
		public List<EntertainAnnualBudgetEntity> wGetSearchImplementation(String year){
			return entertainAnnualBudgetManageImpl.wGetSearchImplementation(year);
			}
		
	// web-获得选中年份每个部门的发生额
		@RequestMapping("/entertain/wGetSelectedUsed")
		@ResponseBody
		public Object wGetSelectedUsed(String year){
			return entertainInfoManageImpl.wGetSelectedUsed(year);
		}
	
}