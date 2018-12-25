package cn.com.hfga.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.hfga.dao.privatecar.PrivateCarInvoiceDAO;
import cn.com.hfga.dto.gz.GZSearchInfoDTO;
import cn.com.hfga.dto.privatecar.ApproveDTO;
import cn.com.hfga.dto.privatecar.PrivateCarApplyDTO;
import cn.com.hfga.dto.privatecar.PrivateCarInvoiceDTO;
import cn.com.hfga.dto.privatecar.PrivateCarUseDetailDTO;
import cn.com.hfga.dto.privatecar.getApproveDTO;
import cn.com.hfga.entity.car.CarApplyInfoEntity;
import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;
import cn.com.hfga.entity.privatecar.PrivateCarInvoiceEntity;
import cn.com.hfga.manageimpl.privatecar.PrivateCarApplyManageImpl;
import cn.com.hfga.manager.privatecar.PrivateCarInvoiceManage;
import cn.com.hfga.dto.privatecar.PrivateCarSearchDTO;
/**
 * @author xinyc
 * @since 2014-11-13 登录功能
 */
@Controller
//@RequestMapping("/privateCarM")
public class PrivateCarController {

	@Autowired
	private PrivateCarApplyManageImpl privateCarApplyManageImpl;
	
	@Autowired
	private PrivateCarInvoiceManage privateCarInvoiceManage;
	
	@Autowired
	private PrivateCarInvoiceDAO  privateCarInvoiceDAO;
	//保存申请私车公用申请信息√
	@RequestMapping(value = "/PrivateCar/Save")
	@ResponseBody
	public int saveEntity(PrivateCarApplyDTO p){
		return  privateCarApplyManageImpl.Save(p);
	}
	//财务保存待审批数据
	 @RequestMapping({"/PrivateCar/SaveNew"})
     @ResponseBody
	 public int SaveNewEntity(PrivateCarApplyDTO p){
	  return privateCarApplyManageImpl.SaveNew(p);
	}
	//审批通过√
	@RequestMapping(value = "/PrivateCar/Approve")
	@ResponseBody
	public int approveEntity(ApproveDTO adt){
		return  privateCarApplyManageImpl.Approve(adt);
	}
	
	//审批否决√
	@RequestMapping(value = "/PrivateCar/Deny")
	@ResponseBody
	public int denyEntity(ApproveDTO adt){
		return  privateCarApplyManageImpl.Deny(adt);
	}
	
	//领导获取待审批列表√
	@RequestMapping(value = "/PrivateCar/getApprove")
	@ResponseBody
	public List<PrivateCarApplyEntity> getApproveInfo(getApproveDTO gdto){
		return  privateCarApplyManageImpl.getApprove(gdto);
	}
	
	//个人获取已通过且待执行的列表
	@RequestMapping(value = "/PrivateCar/getPersonalReady")
	@ResponseBody
	public List<PrivateCarApplyEntity> getPersonalReady(String applyman){
		return  privateCarApplyManageImpl.getPersonalReady(applyman);
	}
	
	//个人获取已通过列表√
	@RequestMapping(value = "/PrivateCar/getPersonalApprove")
	@ResponseBody
	public List<PrivateCarApplyEntity> getPersonalApprove(String applyman){
		return  privateCarApplyManageImpl.getPersonalApprove(applyman);
	}
	
	//个人获取待审批和被否决的列表√
	@RequestMapping(value = "/PrivateCar/getUnapproved")
	@ResponseBody
	public List<PrivateCarApplyEntity> getUnapproved(String applyman){
		return  privateCarApplyManageImpl.getUnapproved(applyman);
	}
	
	//个人撤销申请√
	@RequestMapping(value = "/PrivateCar/Delete")
	@ResponseBody
	public int deleteApprove(String applyid){
		return  privateCarApplyManageImpl.deleteApprove(applyid);
	}
	
	//个人修改申请√
	@RequestMapping(value = "/PrivateCar/ModifyApply")
	@ResponseBody
	public int ModifyApply(PrivateCarApplyDTO p) {
		return privateCarApplyManageImpl.modifyEntity(p);
	}	
	
	//已执行√
	@RequestMapping(value = "/PrivateCar/performed")
	@ResponseBody
	public int performed(String applyid){
		return privateCarApplyManageImpl.setPerformed(applyid);
	}
	
	//未执行√
	@RequestMapping(value = "/PrivateCar/unPerformed")
	@ResponseBody
	public int unPerformed(String applyid){
		return privateCarApplyManageImpl.setUnperformed(applyid);
	}
	
	//根据部门、姓名、开始时间、结束时间查询√
	@RequestMapping(value = "/PrivateCar/searchInfo")
	@ResponseBody
	public List<PrivateCarApplyEntity> searchInfo(PrivateCarSearchDTO privateCarSearchDTO){
		List<PrivateCarApplyEntity> p= privateCarApplyManageImpl.getSearchInfo(privateCarSearchDTO);
		return p;
	}
	
	//获取待确认列表
	@RequestMapping(value = "/PrivateCar/getSureInfo")
	@ResponseBody
	public List<PrivateCarApplyEntity> getSureInfo(getApproveDTO gdto){
		List<PrivateCarApplyEntity> p= privateCarApplyManageImpl.getSure(gdto);
		return p;
	}
	
	//确认
	@RequestMapping(value = "/PrivateCar/Sure")
	@ResponseBody
	public int sureEntity(String applyId){
		return  privateCarApplyManageImpl.Sure(applyId);
	}
	
	// 使用明细
	@RequestMapping(value = "/PrivateCar/PrivateCarInfoDetail")
	@ResponseBody
	public List<PrivateCarApplyEntity> doGetDetailInfo(PrivateCarUseDetailDTO privateCarUseDetailDTO) {
		return privateCarApplyManageImpl.getUseDetailInfo(privateCarUseDetailDTO);
	}

	//汇总分析
	@RequestMapping(value = "/PrivateCar/PrivateCarInfoCollect")
	@ResponseBody
	public List<PrivateCarApplyEntity> doGetCollect(PrivateCarUseDetailDTO privateCarUseDetailDTO) {
		return privateCarApplyManageImpl.getUseDetailInfo(privateCarUseDetailDTO);
	}
	
	//个人借用信息
	@RequestMapping(value = "/PrivateCar/PersonalInfo")
	@ResponseBody
	public List<PrivateCarApplyEntity> getPersonalInfo(String applyman) {
		return privateCarApplyManageImpl.getPersonal(applyman);
	}
	
	//个人借用删除
	@RequestMapping(value = "/PrivateCar/DelApply")
	@ResponseBody
	public int delApply(String applyid) {
		return privateCarApplyManageImpl.delPersonal(applyid);
	}
	
	//获取一条申请信息
	@RequestMapping(value = "/PrivateCar/getApply")
	@ResponseBody
	public PrivateCarApplyEntity  getApply(String applyid) {
		return privateCarApplyManageImpl.getOne(applyid);
	}

/****************************************一条可爱的分界线********************************/	
	//Web-显示私车公用相关信息（原方法）
	@RequestMapping("/PrivateCar/display")
	@ResponseBody
	public Object carDisplay(HttpServletRequest request){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<PrivateCarApplyEntity> list = new ArrayList<PrivateCarApplyEntity>();
		list = privateCarApplyManageImpl.carDisplay(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = privateCarApplyManageImpl.getAllCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}
	//Web-显示私车公用相关信息（新方法/已审核）
	@RequestMapping("/PrivateCar/display1")
	@ResponseBody
	public Object carDisplay1(HttpServletRequest request){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<PrivateCarApplyEntity> list = new ArrayList<PrivateCarApplyEntity>();
		list = privateCarApplyManageImpl.carDisplay1(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = privateCarApplyManageImpl.getAllCount1();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}
	//Web-显示私车公用相关信息（新方法/未审核）
	@RequestMapping("/PrivateCar/undisplay")
	@ResponseBody
	public Object uncarDisplay(HttpServletRequest request){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<PrivateCarApplyEntity> list = new ArrayList<PrivateCarApplyEntity>();
		list = privateCarApplyManageImpl.uncarDisplay(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = privateCarApplyManageImpl.ungetAllCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}
	//web-查询方法/PrivateCar/searchInfo
/*	@RequestMapping(value = "/PrivateCar/webSearchInfo")
	@ResponseBody
	public Object searchApply(HttpServletRequest request, PrivateCarSearchDTO privateCarSearchDTO) {
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<PrivateCarApplyEntity> list = new ArrayList<PrivateCarApplyEntity>();
		list = privateCarApplyManageImpl.searchPrivate(start, number, privateCarSearchDTO);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = privateCarApplyManageImpl.getSearchAllCount(privateCarSearchDTO);
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}*/
	//Web-导出excel方法
	// Web-已审核招待明细-导出表单
	@RequestMapping(value = "/PrivateCar/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, String number) {
		String[] nlist = number.split(","); // 获得传递过来的number列表
		// 获取导出文件夹
		String path = request.getSession().getServletContext().getRealPath("/");
		// 生成导出的文件名
		Date dt = new Date();
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
		String date = matter.format(dt);
		String fileName = "私车公用信息" + date + ".xlsx";
		String filePath = path + "/" + fileName;
		int flag = privateCarApplyManageImpl.export(nlist, filePath);
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
	
	//Web-导入私车公用excel
	@RequestMapping("/PrivateCar/importPrivateCar")
	@ResponseBody
	public Object importPrivateCar(@RequestParam(value = "fileImport", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model){
        String path = request.getSession().getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
		// 获取原始文件名（不包括扩展名）
		String originalFileName = fileName.substring(0, fileName.lastIndexOf("."));
		// 获取时间戳
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStamp = sdf.format(new Date());
		// 新文件名
		String newFileName = originalFileName + timeStamp
				+ fileName.substring(fileName.lastIndexOf("."), fileName.length());
		File targetFile = new File(path + "/excel", newFileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("fileUrl", request.getContextPath() + "/upload/excel/" + newFileName);
		// 绝对路径
		String absolutePath = targetFile.getAbsolutePath();
		System.out.println(absolutePath);
		String result="";
		//IE8下前端难以判断大小，放置后台  文件大小大于100M返回2 
		long fileSize=targetFile.length();
		String size=String.valueOf(targetFile.length());
		if(fileSize<(1024*1024*100)){
// 		调用将excel数据保存到数据库的方法
//	      result = String.valueOf(fillblankService.importFillblankExcel(absolutePath, Integer.parseInt(systemId)));
			result = String.valueOf(privateCarApplyManageImpl.importPrivateCarExcel(absolutePath));
		}else{
			result="2";
		}
		return result;
	}
	//获取某用户下所有未报销私车信息
	@RequestMapping(value="/getAlreadyApplyList")
	@ResponseBody
	public Object getApplyList(String username){
		return privateCarApplyManageImpl.getApplyList(username);
	}
	
	//设置为已通过
	@RequestMapping(value="/updatePrivateCarIfPassed")
	@ResponseBody
	public Object updatePrivateCarIfPassed(String applyid){
		return privateCarApplyManageImpl.updatePrivateCarIfPassed(applyid);
	}
	
	//设置为已驳回（单个驳回）
	@RequestMapping(value="/updatePrivateCarIfUnPass")
	@ResponseBody
	public Object updatePrivateCarIfUnPass(String parentid, String applyid){
		return privateCarApplyManageImpl.updatePrivateCarIfUnPass(parentid,applyid);
	}
	
	//提交报销前更新私车信息
	@RequestMapping(value="/updatePrivateCar")
	@ResponseBody
	public Object update(PrivateCarApplyDTO pto){
		return privateCarApplyManageImpl.update(pto);
	}
	
	//财务修改私车申请信息
	@RequestMapping({"/PrivateCar/updateNew"})
	@ResponseBody
	public Object updateNew(PrivateCarApplyDTO pto){
	    return Integer.valueOf(privateCarApplyManageImpl.updateNew(pto));
	}
	//提交报销前更新私车信息
	@RequestMapping(value="/updatePrivateCarStatusBack")
	@ResponseBody
	public Object updatePrivateCarStatusBack(PrivateCarApplyDTO pto){
		return privateCarApplyManageImpl.updatePrivateCarStatusBack(pto);
	}
	
	//设置为已驳回（单个驳回）
	@RequestMapping(value="/backPrivateCars")
	@ResponseBody
	public Object backPrivateCars(String applyid){
		return privateCarApplyManageImpl.backPrivateCar(applyid);
	}
	
	//设置为已通过（多个通过）
	@RequestMapping(value="/passPrivateCars")
	@ResponseBody
	public Object passPrivateCars(String applyid, String applyids){
		return privateCarApplyManageImpl.passPrivateCar(applyid, applyids);
	}
	
	//显示所有私车申请信息
	@RequestMapping({"/PrivateCar/displayAll"})
	@ResponseBody
	public Object privateCarData(HttpServletRequest request)
	{
	  String page = request.getParameter("page");
	  String rows = request.getParameter("rows");
	  int intPage = Integer.parseInt((page == null) || (page == "0") ? "1" : page);
	  int number = Integer.parseInt((rows == null) || (rows == "0") ? "20" : rows);
	  
	  int start = (intPage - 1) * number;
	  List<PrivateCarApplyEntity> list = new ArrayList();
	  list = this.privateCarApplyManageImpl.allPrivateCarDisplay(start, number);
	  for (PrivateCarApplyEntity car : list)
	  {
		  System.out.println("申请单号"+car.getApplyId());
	    PrivateCarInvoiceEntity carinvoice = this.privateCarInvoiceManage.getByNumber("%" + car.getApplyId() + "%");
	    if (carinvoice != null) {
	      car.setDanhao(carinvoice.getVoucherNum());
	    } else {
	      car.setDanhao("");
	    }
	  }
	  Map<String, Object> jsonMap = new HashMap();
	  int total = this.privateCarApplyManageImpl.getAllDataCount();
	  jsonMap.put("total", Integer.valueOf(total));
	  jsonMap.put("rows", list);
	  return jsonMap;
	}
	
	@RequestMapping({"/PrivateCar/displaySearch"})
	@ResponseBody
	public Object displaySearch(HttpServletRequest request, GZSearchInfoDTO gzSearchInfoDTO) throws ParseException
	{
	  String page = request.getParameter("page");
	  String rows = request.getParameter("rows");
	  int intPage = Integer.parseInt((page == null) || (page == "0") ? "1" : page);
	  int number = Integer.parseInt((rows == null) || (rows == "0") ? "20" : rows);
	  
	  int start = (intPage - 1) * number;
	  List<PrivateCarApplyEntity> list = new ArrayList();
	  String department = gzSearchInfoDTO.getDepartmentName();
	  String applyMan = gzSearchInfoDTO.getUserName();
	  String startTime = gzSearchInfoDTO.getStartTime();
	  String endTime = gzSearchInfoDTO.getEndTime();
	  String status = gzSearchInfoDTO.getStatus();
	  int total = 0;
	  if (("全部".equals(department)) && ("".equals(applyMan)) && ("全部".equals(status)))
	  {
	    list = this.privateCarApplyManageImpl.searchByTime(startTime, endTime, start, number);
	    for (PrivateCarApplyEntity car : list)
	    {
	      PrivateCarInvoiceEntity carinvoice = this.privateCarInvoiceManage.getByNumber("%" + car.getApplyId() + "%");
	      if (carinvoice != null) {
	        car.setDanhao(carinvoice.getVoucherNum());
	      } else {
	        car.setDanhao("");
	      }
	    }
	     total = this.privateCarApplyManageImpl.getCountByTime(startTime, endTime);
	   }
	   if ((!"全部".equals(department)) && ("".equals(applyMan)) && ("全部".equals(status)))
	   {
	     list = this.privateCarApplyManageImpl.searchByDepartment(department, startTime, endTime, start, number);
	     for (PrivateCarApplyEntity car : list)
	     {
	       PrivateCarInvoiceEntity carinvoice = this.privateCarInvoiceManage.getByNumber("%" + car.getApplyId() + "%");
	       if (carinvoice != null) {
	         car.setDanhao(carinvoice.getVoucherNum());
	       } else {
	         car.setDanhao("");
	       }
	     }
	     total = this.privateCarApplyManageImpl.getCountByDepartment(department, startTime, endTime);
	   }
	   if (("全部".equals(department)) && (!"".equals(applyMan)) && ("全部".equals(status)))
	   {
	     list = this.privateCarApplyManageImpl.searchByApplyman("%" + applyMan + "%", startTime, endTime, start, number);
	     for (PrivateCarApplyEntity car : list)
	     {
	       PrivateCarInvoiceEntity carinvoice = this.privateCarInvoiceManage.getByNumber("%" + car.getApplyId() + "%");
	       if (carinvoice != null) {
	         car.setDanhao(carinvoice.getVoucherNum());
	       } else {
	         car.setDanhao("");
	       }
	     }
	     total = this.privateCarApplyManageImpl.getCountByApplyman("%" + applyMan + "%", startTime, endTime);
	   }
	   if (("全部".equals(department)) && ("".equals(applyMan)) && (!"全部".equals(status)))
	   {
	     list = this.privateCarApplyManageImpl.searchByStatus(status, startTime, endTime, start, number);
	     for (PrivateCarApplyEntity car : list)
	     {
	       PrivateCarInvoiceEntity carinvoice = this.privateCarInvoiceManage.getByNumber("%" + car.getApplyId() + "%");
	       if (carinvoice != null) {
	         car.setDanhao(carinvoice.getVoucherNum());
	       } else {
	         car.setDanhao("");
	       }
	     }
	     total = this.privateCarApplyManageImpl.getCountByStatus(status, startTime, endTime);
	   }
	   if ((!"全部".equals(department)) && (!"".equals(applyMan)) && ("全部".equals(status)))
	   {
	     list = this.privateCarApplyManageImpl.searchByDepartApp(department, "%" + applyMan + "%", startTime, endTime, start, number);
	     for (PrivateCarApplyEntity car : list)
	     {
	       PrivateCarInvoiceEntity carinvoice = this.privateCarInvoiceManage.getByNumber("%" + car.getApplyId() + "%");
	       if (carinvoice != null) {
	         car.setDanhao(carinvoice.getVoucherNum());
	       } else {
	         car.setDanhao("");
	       }
	     }
	     total = this.privateCarApplyManageImpl.getCountByDepartApp(department, "%" + applyMan + "%", startTime, endTime);
	   }
	   if ((!"全部".equals(department)) && ("".equals(applyMan)) && (!"全部".equals(status)))
	   {
	     list = this.privateCarApplyManageImpl.searchByDepartStatus(department, status, startTime, endTime, start, number);
	     for (PrivateCarApplyEntity car : list)
	     {
	       PrivateCarInvoiceEntity carinvoice = this.privateCarInvoiceManage.getByNumber("%" + car.getApplyId() + "%");
	       if (carinvoice != null) {
	         car.setDanhao(carinvoice.getVoucherNum());
	       } else {
	         car.setDanhao("");
	       }
	     }
	     total = this.privateCarApplyManageImpl.getCountByDepartStatus(department, status, startTime, endTime);
	   }
	   if (("全部".equals(department)) && (!"".equals(applyMan)) && (!"全部".equals(status)))
	   {
	     list = this.privateCarApplyManageImpl.searchByAppStatus("%" + applyMan + "%", status, startTime, endTime, start, number);
	     for (PrivateCarApplyEntity car : list)
	     {
	       PrivateCarInvoiceEntity carinvoice = this.privateCarInvoiceManage.getByNumber("%" + car.getApplyId() + "%");
	       if (carinvoice != null) {
	         car.setDanhao(carinvoice.getVoucherNum());
	       } else {
	         car.setDanhao("");
	       }
	     }
	     total = this.privateCarApplyManageImpl.getCountByAppStatus("%" + applyMan + "%", status, startTime, endTime);
	   }
	   if ((!"全部".equals(department)) && (!"".equals(applyMan)) && (!"全部".equals(status)))
	   {
	     list = this.privateCarApplyManageImpl.searchByDepartAppStatus(department, "%" + applyMan + "%", status, startTime, endTime, start, number);
	     for (PrivateCarApplyEntity car : list)
	     {
	       PrivateCarInvoiceEntity carinvoice = this.privateCarInvoiceManage.getByNumber("%" + car.getApplyId() + "%");
	       if (carinvoice != null) {
	          car.setDanhao(carinvoice.getVoucherNum());
	        } else {
	          car.setDanhao("");
	        }
	      }
	      total = this.privateCarApplyManageImpl.getCountByDepartAppStatus(department, "%" + applyMan + "%", status, startTime, endTime);
	    }
	    Map<String, Object> jsonMap = new HashMap();
	    jsonMap.put("total", Integer.valueOf(total));
	    jsonMap.put("rows", list);
    return jsonMap;
	  }
	  
	 @RequestMapping({"/PrivateCar/deleteApplyInfo"})
	 @ResponseBody
	 public Object deleteApplyInfo(String ApplyId)
	  {
	    PrivateCarInvoiceEntity carinvoice = privateCarInvoiceManage.getByNumber("%" + ApplyId + "%");
//	   if (carinvoice != null) {
//	      privateCarInvoiceManage.deleteByApplyIds("%" + ApplyId + "%");
//	   }
	    PrivateCarApplyEntity one = privateCarApplyManageImpl.getOne(ApplyId);
	   if(carinvoice!=null){
			  if(carinvoice.getApplyIds().split(",").length==1){
				  privateCarInvoiceManage.deleteByApplyid(carinvoice.getApplyId());
			  }else if(carinvoice.getApplyIds().split(",").length>1){
				  String applyIds="";
				  if(carinvoice.getApplyIds().split(",")[0].equals(ApplyId)){
					  applyIds=carinvoice.getApplyIds().replace(ApplyId+",","");
				  }else{
					  applyIds=carinvoice.getApplyIds().replace(","+ApplyId,"");
				  }
				  carinvoice.setApplyIds(applyIds);
				  carinvoice.setSum(String.valueOf(Double.parseDouble(carinvoice.getSum())-one.getSureLength()));
				  privateCarApplyManageImpl.updateForDelete(carinvoice);
//				  privateCarInvoiceDAO.updateEntity(carinvoice.getApplyId(), carinvoice.getApplyMan(), carinvoice.getApproveMan(), carinvoice.getApplyTime(), carinvoice.getSum(), carinvoice.getSureLength(), carinvoice.getVoucherNum(), carinvoice.getStatus(), carinvoice.getPaidTime(), carinvoice.getApplyIds());
			  }
		}
	   Integer result = Integer.valueOf(privateCarApplyManageImpl.deleteApprove(ApplyId));
	   return result;
	 }
	 //登记私车申请信息
	 @RequestMapping({"/PrivateCar/registApplyInfo"})
	 @ResponseBody
	 public Object registApplyInfo(String ApplyIds,String registtime,String registman,String vouchernum,String sum,String subtime)
	  {
		 Object result = null;
		 //判断存不存在凭单号
		 PrivateCarInvoiceDTO p=new PrivateCarInvoiceDTO();
		 PrivateCarInvoiceEntity carinvoice = privateCarInvoiceManage.getByNumberes("%" + ApplyIds + "%");
		 PrivateCarApplyDTO pto=new PrivateCarApplyDTO();
		 String[] split = ApplyIds.split(",");
		 if(carinvoice==null){
			 PrivateCarApplyEntity getapplyman = privateCarApplyManageImpl.getOne(split[0]);
			 String applyid = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
			 if("".equals(vouchernum)&&"".equals(registtime)&&"".equals(registman)){
				 p.setStatus("待审核");
			 }else{
				 p.setStatus("已完成");
			 }
			 p.setApplyid(applyid);
			 p.setApplyids(ApplyIds);
			 p.setApplyman(getapplyman.getApplyMan());
			 p.setApplytime(subtime);
			 p.setApproveman(registman);
			 p.setPaidtime(registtime);
			 p.setSum(sum);
			 p.setVouchernum(vouchernum);
			 for(int i=0;i<split.length;i++){
				 PrivateCarApplyEntity one = privateCarApplyManageImpl.getOne(split[i]);
				 pto.setApplyid(one.getApplyId());
				 pto.setApplyman(one.getApplyMan());
				 pto.setApplytime(one.getApplyTime());
				 pto.setApproveman(one.getApproveMan());
				 pto.setApprovetime(one.getApproveTime());
				 pto.setBeforedate(one.getBeforeDate());
				 pto.setApproveman2(registman);
				 pto.setBeginaddress(one.getBeginAddress());
				 pto.setCountlength(one.getCountLength());
				 pto.setDanhao(vouchernum);
				 pto.setDepartment(one.getDepartment());
				 pto.setDestination(one.getDestination());
				 pto.setDoublelength(one.getDoubleLength());
				 pto.setEndlength(one.getEndLength());
				 pto.setIfbefore(one.getIfBefore());
				 if("".equals(vouchernum)&&"".equals(registtime)&&"".equals(registman)){
					 pto.setIfpass("未报销");
				 }else{
					 pto.setIfpass("已报销");
				 }
				 pto.setIfperform("已执行");
				 pto.setIfsub("1");
				 pto.setPaidtime(registtime);
				 pto.setSubtime(subtime);
				 pto.setPassaddress(one.getPassAddress());
				 pto.setReason(one.getReason());
				 pto.setSinglelength(one.getSingleLength());
				 pto.setStatus("已通过");
				 pto.setSum(String.valueOf(one.getSureLength()));
				 pto.setSurelength(one.getSureLength());
				 pto.setUsercartime(one.getUserCarTime());
				 pto.setWaydetail(one.getWayDetail());
				 pto.setWaymodel(one.getWayModel());
				 privateCarApplyManageImpl.updatePrivateCar(pto);
			 }
		    result = privateCarInvoiceManage.regist(p);
		 }else{
			 for(int i=0;i<split.length;i++){
				 PrivateCarApplyEntity one = privateCarApplyManageImpl.getOne(split[i]);
				 pto.setApplyid(one.getApplyId());
				 pto.setApplyman(one.getApplyMan());
				 pto.setApplytime(one.getApplyTime());
				 pto.setApproveman(one.getApproveMan());
				 pto.setApprovetime(one.getApproveTime());
				 pto.setBeforedate(one.getBeforeDate());
				 pto.setApproveman2(registman);
				 pto.setBeginaddress(one.getBeginAddress());
				 pto.setCountlength(one.getCountLength());
				 pto.setDanhao(vouchernum);
				 pto.setDepartment(one.getDepartment());
				 pto.setDestination(one.getDestination());
				 pto.setDoublelength(one.getDoubleLength());
				 pto.setEndlength(one.getEndLength());
				 pto.setIfbefore(one.getIfBefore());
				 if("".equals(vouchernum)&&"".equals(registtime)&&"".equals(registman)){
					 pto.setIfpass("未报销");
				 }else{
					 pto.setIfpass("已报销");
				 }
				 pto.setIfperform("已执行");
				 pto.setIfsub("1");
				 pto.setPaidtime(registtime);
				 pto.setSubtime(subtime);
				 pto.setPassaddress(one.getPassAddress());
				 pto.setReason(one.getReason());
				 pto.setSinglelength(one.getSingleLength());
				 pto.setStatus("已通过");
				 pto.setSum(String.valueOf(one.getSureLength()));
				 pto.setSurelength(one.getSureLength());
				 pto.setUsercartime(one.getUserCarTime());
				 pto.setWaydetail(one.getWayDetail());
				 pto.setWaymodel(one.getWayModel());
				 privateCarApplyManageImpl.updatePrivateCar(pto);
			 }
			 if("".equals(vouchernum)&&"".equals(registtime)&&"".equals(registman)){
				 p.setStatus("待审核");
			 }else{
				 p.setStatus("已完成");
			 }
			 p.setApplyid(carinvoice.getApplyId());
			 p.setApproveman(registman);
			 p.setPaidtime(registtime);
			 p.setSum(sum);
			 p.setVouchernum(vouchernum);
			 p.setApplytime(subtime);
			 privateCarInvoiceManage.updateInvoice(p);
		 }
		 
	   return result;
	 }
	 
}
