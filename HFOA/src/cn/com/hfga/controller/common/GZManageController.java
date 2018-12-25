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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hfga.dto.gz.GZApplyInfoDTO;
import cn.com.hfga.dto.gz.GZSearchInfoDTO;
import cn.com.hfga.entity.gz.GZApplyInfoEntity;
import cn.com.hfga.entity.gz.GZKindEntity;
import cn.com.hfga.entity.user.DepartmentEntity;
import cn.com.hfga.manageimpl.gz.GZApplyInfoManageImpl;
import cn.com.hfga.manageimpl.user.DepartmentImpl;
import cn.com.hfga.push.android.AndroidPushEntity;
import cn.com.hfga.push.android.AndroidPushUtil;
import cn.com.hfga.push.ios.IOSPushEntity;
import cn.com.hfga.push.ios.IOSPushUtil;

@Controller("gZManageController")
public class GZManageController {

	@Autowired
	private DepartmentImpl departmentImpl;

	@Autowired
	private GZApplyInfoManageImpl gZApplyInfoManageImpl;

	// 测试请求
	@RequestMapping(value = "/gz/getByName")
	@ResponseBody
	public List<DepartmentEntity> getByName(String departmentName) throws UnsupportedEncodingException {

		byte bb[];
		bb = departmentName.getBytes("ISO-8859-1");
		departmentName = new String(bb, "UTF-8");
		System.out.println(departmentName);
		System.out.println(departmentImpl.getByName(departmentName));
		return departmentImpl.getByName(departmentName);
	}

	// 获取所有公章信息
	@RequestMapping(value = "/gz/getAll")
	@ResponseBody
	public List<GZApplyInfoEntity> getAll() {
		return gZApplyInfoManageImpl.getAll();
	}
	//安卓端推送消息
	@Autowired
	private AndroidPushUtil androidPushUtil;
	
	
	
//	public AndroidPushUtil getAndroidPushUtil() {
//		return androidPushUtil;
//	}
//
//	public void setAndroidPushUtil(AndroidPushUtil androidPushUtil) {
//		this.androidPushUtil = androidPushUtil;
//	}
	//IOS端推送消息
	@Autowired
	private IOSPushUtil iOSPushUtil;
	
	
	// 保存公章申请信息
	@RequestMapping(value = "/gz/saveApplyInfo")
	@ResponseBody
	public int saveApplyInfo(GZApplyInfoDTO gzApplyInfoDTO) {
		AndroidPushEntity pushEntity=new AndroidPushEntity();
		pushEntity.setTriker("借章审批");
		pushEntity.setTitle("公章管理");
		pushEntity.setText("您有待审批消息");
		pushEntity.setAfter_open("com.hfga.hfgaoa.borrowstamp.activity.BorrowStampApprove");
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("借章审批");
		iOSEntity.setSound("您有待审批消息");
		//Android推送
		//for(int i=0;i<userList.size();i++){
			pushEntity.setAlias(gzApplyInfoDTO.getApproveMan());
			androidPushUtil.setPushEntity(pushEntity);
			androidPushUtil.sendCustomizedcast();
			
			iOSEntity.setAlias(gzApplyInfoDTO.getApproveMan());
			iOSPushUtil.setiOSPushEntity(iOSEntity);
			iOSPushUtil.sendCustomizedcast();
		return gZApplyInfoManageImpl.saveGZApplyInfo(gzApplyInfoDTO);
	}

	// 删除公章申请信息
	@RequestMapping(value = "/gz/deleteApplyInfo")
	@ResponseBody
	public int deleteApplyInfo(String applyId) {
		System.out.println(applyId);
		return gZApplyInfoManageImpl.delete(applyId);
	}

	// 小殷姐
	@RequestMapping(value = "/gz/getComplete")
	@ResponseBody
	public List<GZApplyInfoEntity> getComplete() {
		return gZApplyInfoManageImpl.getNeedYin();
	}

	// 合同章
	@RequestMapping(value = "/gz/getHT")
	@ResponseBody
	public List<GZApplyInfoEntity> getHT() {
		return gZApplyInfoManageImpl.getNeedChuan();
	}

	// 法人章
	@RequestMapping(value = "/gz/getFR")
	@ResponseBody
	public List<GZApplyInfoEntity> getFR() {
		return gZApplyInfoManageImpl.getNeedTan();
	}

	// 审批操作
	@RequestMapping(value = "/gz/approve")
	@ResponseBody
	public int approveInfo(String approveMan, String applyId) {
		
		//要根据applyId
		List<GZApplyInfoEntity> gzs=gZApplyInfoManageImpl.getOne(applyId);
		AndroidPushEntity pushEntity=new AndroidPushEntity();
		pushEntity.setTriker("审批完毕");
		pushEntity.setTitle("公章管理");
		pushEntity.setText("您的借章申请已审批通过");
		pushEntity.setAfter_open("com.hfga.hfgaoa.borrowstamp.main.StampHomePage");
		
	
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("审批完毕");
		iOSEntity.setSound("您的借章申请已审批通过");
		//Android推送
		pushEntity.setAlias(gzs.get(0).getApplyUserName());
		System.out.println(gzs.get(0).getApplyUserName());
		androidPushUtil.setPushEntity(pushEntity);
		androidPushUtil.sendCustomizedcast();
		iOSEntity.setAlias(gzs.get(0).getApplyUserName());
		iOSPushUtil.setiOSPushEntity(iOSEntity);
		iOSPushUtil.sendCustomizedcast();
		
		return gZApplyInfoManageImpl.updateApprove("通过", approveMan, applyId);
	}

	// 获取审批信息---根据审批人
	@RequestMapping(value = "/gz/getApproveInfo")
	@ResponseBody
	public List<GZApplyInfoEntity> getApproveInfo(String approveMan) {
		System.out.println(approveMan);
		System.out.println(gZApplyInfoManageImpl.getByApprove(approveMan).size());
		return gZApplyInfoManageImpl.getByApprove(approveMan);
	}

	// 确认操作 谭总和
	@RequestMapping(value = "/gz/confirm")
	@ResponseBody
	public int confirm(String approveMan, String applyId) {
		System.out.println(applyId);
		return gZApplyInfoManageImpl.updateSure("已确认", approveMan, applyId);
	}

	// 小殷红完成操作
	@RequestMapping(value = "/gz/complete")
	@ResponseBody
	public int confirm(String applyId) {
		System.out.println(applyId);
		return gZApplyInfoManageImpl.updateStatus("已完成", applyId);
	}

	// 更改公章申请
	@RequestMapping(value = "/gz/modifyOne")
	@ResponseBody
	public int modifyOne(GZApplyInfoDTO gzApplyInfoDTO) {
		return gZApplyInfoManageImpl.modifyOne(gzApplyInfoDTO);
	}

	// 否决操作
	@RequestMapping(value = "/gz/denyApply")
	@ResponseBody
	public int denyApply(String applyId) {
		System.out.println(applyId);
		return gZApplyInfoManageImpl.updateStatus("被否决", applyId);
	}

	// 确定的时候被否决
	@RequestMapping(value = "/gz/denyApplySure")
	@ResponseBody
	public int denyApplySure(String applyId) {
		System.out.println(applyId); 
		return gZApplyInfoManageImpl.updateStatus("被驳回", applyId);
	}

	//
	@RequestMapping(value = "/gz/searchInfo")
	@ResponseBody
	public List<GZApplyInfoEntity> searchInfo() {
		return null;
	}
	
	// 获取个人申请信息
	@RequestMapping(value = "/gz/getPersonal")
	@ResponseBody
	public List<GZApplyInfoEntity> getPersonal(String applyUserName) {
		System.out.println(applyUserName);
		System.out.println(gZApplyInfoManageImpl.getPersonal(applyUserName).size());
		return gZApplyInfoManageImpl.getPersonal(applyUserName);
	}

	@RequestMapping(value = "/gz/getNeedSure")
	@ResponseBody
	public List<GZApplyInfoEntity> getNeedSure(String duty) {
		if (duty.equals("1")) {
			return gZApplyInfoManageImpl.getNeedChuan();
		} else {
			return gZApplyInfoManageImpl.getNeedTan();
		}
	}

	@RequestMapping(value = "/gz/getSearchInfo")
	@ResponseBody
	public List<GZApplyInfoEntity> getSearchInfo(GZSearchInfoDTO gzSearchInfoDTO) {
		return gZApplyInfoManageImpl.getSearchInfo(gzSearchInfoDTO);
	}
	
	/*********************************一条可爱的分割线*****************************/
	//Web-显示公章相关信息
	@RequestMapping("/GZManage/display")
	@ResponseBody
	public Object carDisplay(HttpServletRequest request){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<GZApplyInfoEntity> list = new ArrayList<GZApplyInfoEntity>();
		list = gZApplyInfoManageImpl.GZManageDisplay(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = gZApplyInfoManageImpl.getAllCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
		}
	//Web-显示公章相关信息
	@RequestMapping("/GZManage/displaySearch")
	@ResponseBody
	public Object carDisplaySearch(HttpServletRequest request, GZSearchInfoDTO gzSearchInfoDTO){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<GZApplyInfoEntity> list = new ArrayList<GZApplyInfoEntity>();
		list = gZApplyInfoManageImpl.wGetSearchInfoByPage(gzSearchInfoDTO, start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = gZApplyInfoManageImpl.wGetSearchInfoTotal(gzSearchInfoDTO, start, number);
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}
	//Web-按照部门、申请人、公章类型、申请时间段（开始、结束）查询公章信息
	@RequestMapping(value = "/gz/wGetSearchInfo")
	@ResponseBody
	public List<GZApplyInfoEntity> wGetSearchInfo(GZSearchInfoDTO gzSearchInfoDTO) {
		return gZApplyInfoManageImpl.wGetSearchInfo(gzSearchInfoDTO);
	}
	
	//Web导出公章Excel
			@RequestMapping(value = "/GZManage/exportExcel")
			public void exportExcel(HttpServletRequest request, HttpServletResponse response, String number) {
				String[] nlist = number.split(","); // 获得传递过来的number列表
				// 获取导出文件夹
				String path = request.getSession().getServletContext().getRealPath("/");
				// 生成导出的文件名
				Date dt = new Date();
				SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
				String date = matter.format(dt);
				String fileName = "公章使用信息" + date + ".xlsx";
				String filePath = path + "/" + fileName;
				int flag = gZApplyInfoManageImpl.export(nlist, filePath);
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
	
	
	//Web-获取公章类型√
	@RequestMapping(value = "/gz/getType")
	@ResponseBody
	public List<GZKindEntity> getType() {
		return gZApplyInfoManageImpl.getType();
	}
}
