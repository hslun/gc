package com.hfoa.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hfoa.entity.common.BMessagenotice;
import com.hfoa.entity.common.BNotice;
import com.hfoa.entity.department.BDepartmentEntity;
import com.hfoa.service.common.BMessagenoticeService;
import com.hfoa.service.common.BNoticeService;
import com.hfoa.service.department.BDepartmentService;
import com.hfoa.service.role.BRoleService;
import com.hfoa.service.user.USerService;
import com.hfoa.util.TimeUtil;
import com.itextpdf.text.pdf.codec.Base64.InputStream;

/**
 * @author wzx
 * 微信公告
 */
@Controller
@RequestMapping("notice")
public class NoticeController {

	@Autowired
	private BNoticeService bNoticeService;
	@Autowired
	private BMessagenoticeService bMessagenoticeService;
	//微信公告信息
	//Web-显示微信公告相关信息
	@RequestMapping("/getAllNoticeByPage")
	@ResponseBody
	public Object getAllNoticeByPage(HttpServletRequest request){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<BNotice> list = new ArrayList<BNotice>();
		list = bNoticeService.noticeDisplayByPage(start, number);
	
		for (BNotice bNotice : list) {
			bNotice.setForUpload(bNotice.getId());
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = bNoticeService.getAllCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	} 
	//添加公告
	@RequestMapping("/insertNotice")
	@ResponseBody
	public Object insertNotice(BNotice bNotice){
		return bNoticeService.insert(bNotice);
	}
	//修改公告信息
	@RequestMapping("/updateNotice")
	@ResponseBody
	public Object updateNotice(BNotice bNotice){
		return bNoticeService.update(bNotice);
	}
	
	//上传文件
	@RequestMapping(value = "/uploadFile")
	@ResponseBody 
	public Object uploadFile(HttpServletRequest request, MultipartFile upload,int noticeid) {  
		String originalName = upload.getOriginalFilename(); 
		String originalFileName = originalName.substring(0,originalName.lastIndexOf("."));
		String newFileName = originalFileName+
				originalName.substring(originalName.lastIndexOf("."),originalName.length());
		String path = request.getSession().getServletContext().getRealPath("/upload");
		System.out.println("path是"+path);
		
		String newFile="微信公告";
//				new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		path+="/"+newFile+"/";
		File dir=new File(path);
		if(!dir.exists()&&!dir.isDirectory()){//如果文件不存在则创建文件
			dir.mkdirs();
		}
		File targetFile = new File(path,newFileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		//保存
		try {
			upload.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		String newPath= "http://192.168.4.122:9988/HFOANEW/upload/微信公告/"+newFileName;
		String newPath= "https://gongche.hfga.com.cn/HFOANEW/upload/微信公告/"+newFileName;
		BNotice notice=bNoticeService.selectById(noticeid);
//		if(StringUtils.isNotBlank(notice.getImgurl())){
//			File file = new File(notice.getImgurl());
//			if(file.exists()){//删除文件
//				file.delete();
//			}
//		}
		String lastFile;
		String p=System.getProperty("user.dir");
		if(notice.getImgurl()!=null&&!notice.getImgurl().equals("null")&&!"".equals(notice.getImgurl())){
			try {
				String projectPath=URLDecoder.decode(p, "UTF-8").replace("\\", "/");
				lastFile="upload/微信公告/"+notice.getImgurl().split("/")[notice.getImgurl().split("/").length-1];
				path=projectPath+"/src/main/webapp/"+lastFile;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			File file = new File(path);
			if(file.exists()){//删除文件
				file.delete();
			}
		}
		notice.setImgurl(newPath);
		Integer update = bNoticeService.update(notice);
		return update;
	}
	//删除公告信息
	@RequestMapping("/deleteNotice")
	@ResponseBody
	public Object deleteNotice(int id){
		BNotice selectById = bNoticeService.selectById(id);
		Integer result=null;
		if(StringUtils.isNotBlank(selectById.getImgurl())){
			String realPath=System.getProperty("user.dir")+"/src/main/webapp/"+selectById.getImgurl();
			File file = new File(realPath);
			if(file.exists()){//删除文件
				file.delete();
			}
			result=bNoticeService.deleteById(id);
		}
		return result;
	}
	//公告的模糊查询
	@RequestMapping("/searchNotice")
	@ResponseBody
	public Object searchNotice(HttpServletRequest request,String title){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<BNotice> list = new ArrayList<BNotice>();
		list = bNoticeService.noticeVagueByPage(start, number,title);
		for (BNotice notice : list) {
			notice.setForUpload(notice.getId());
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = bNoticeService.getVagueCount(title);
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}
	//修改部门角色中间表
//	@RequestMapping("/updateMiddleDepartment")
//	@ResponseBody
//	public Object updateMiddleDepartment(String roleids,int departmentid){
//		String[] split = roleids.split(",");
//		bRoleService.deleteMiddleDepartment(departmentid);
//		int result=0;
//		for(int i=0;i<split.length;i++){
//			result=bDepartmentService.insertMiddleRole(departmentid,Integer.parseInt(split[i]));
//		}
//		return result;
//	}
	//微信公告前台显示
	@RequestMapping("/showImage")
	@ResponseBody
	public Object showImage(){
		List<BNotice> list = new ArrayList<BNotice>();
		list = bNoticeService.getAvailable();
		return list;
	} 
	//在前台显示公告信息（转换）
	@RequestMapping("/showNoticeInfo")
	@ResponseBody
	public ModelAndView showNoticeInfo(){
		ModelAndView result=new ModelAndView("common/notice");
		return result;
	} 
	//微信消息公告信息
	//Web-显示微信消息公告相关信息
	@RequestMapping("/getAllMessageNoticeByPage")
	@ResponseBody
	public Object getAllMessageNoticeByPage(HttpServletRequest request){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<BMessagenotice> list = new ArrayList<BMessagenotice>();
		list = bMessagenoticeService.messageNoticeDisplayByPage(start, number);
	
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = bMessagenoticeService.getAllCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	} 
	//在前台显示消息公告信息（转换）
	@RequestMapping("/showMessageNoticeInfo")
	@ResponseBody
	public ModelAndView showMessageNoticeInfo(){
		ModelAndView result=new ModelAndView("common/messageNotice");
		return result;
	} 
	//添加消息公告
	@RequestMapping("/insertMessagenotice")
	@ResponseBody
	public Object insertMessagenotice(BMessagenotice messageNotice){
		List<BMessagenotice> messageList=bMessagenoticeService.getAll();
		for (BMessagenotice bMessagenotice : messageList) {
			if(!messageNotice.getMaintitle().equals(bMessagenotice.getMaintitle())){
				bMessagenotice.setMaintitle(messageNotice.getMaintitle());
				bMessagenoticeService.update(bMessagenotice);
			}
		}
		Date now=new Date();
		String time=TimeUtil.fromDateDateToString(now);
		messageNotice.setUsertime(time);
		return bMessagenoticeService.insert(messageNotice);
	}
	//修改消息公告信息
	@RequestMapping("/updateMessagenotice")
	@ResponseBody
	public Object updateMessagenotice(BMessagenotice messageNotice){
		BMessagenotice message=bMessagenoticeService.getById(messageNotice.getId());
		message.setId(messageNotice.getId());
		message.setContent(messageNotice.getContent());
		message.setContenttitle(messageNotice.getContenttitle());
		message.setMaintitle(messageNotice.getMaintitle());
		message.setUsertime(messageNotice.getUsertime());
		message.setUserName(messageNotice.getUserName());
		message.setDepartment(messageNotice.getDepartment());
		message.setImgUrl(messageNotice.getImgUrl());
		List<BMessagenotice> messageList=bMessagenoticeService.getAll();
		for (BMessagenotice bMessagenotice : messageList) {
			if(!messageNotice.getMaintitle().equals(bMessagenotice.getMaintitle())){
				bMessagenotice.setMaintitle(messageNotice.getMaintitle());
				bMessagenoticeService.update(bMessagenotice);
			}
		}
		return bMessagenoticeService.update(message);
	}
	//删除消息公告信息
	@RequestMapping("/deleteMessagenotice")
	@ResponseBody
	public Object deleteMessagenotice(int id){
		return bMessagenoticeService.deleteById(id);
	}
	//上传文件
	@RequestMapping(value = "/upload")
	@ResponseBody 
	public Object upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
    FileUploadException {  
		ServletContext application = request.getSession().getServletContext();
        String savePath = application.getRealPath("/") + "images/";

        // 文件保存目录URL
        String saveUrl = request.getContextPath() + "/images/";
        System.out.println(application.getRealPath("/"));
        System.out.println(savePath);
        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

        // 最大文件大小
        long maxSize = 1000000;

        response.setContentType("text/html; charset=UTF-8");

        if (!ServletFileUpload.isMultipartContent(request)) {
            return getError("请选择文件。");
        }
        // 检查目录
        File uploadDir = new File(savePath);
        if (!uploadDir.isDirectory()) {
            return getError("上传目录不存在。");
        }
        // 检查目录写权限
        if (!uploadDir.canWrite()) {
            return getError("上传目录没有写权限。");
        }

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if (!extMap.containsKey(dirName)) {
            return getError("目录名不正确。");
        }
        
      // 创建文件夹
      savePath += dirName + "/";
      saveUrl += dirName + "/";
      File saveDirFile = new File(savePath);
      if (!saveDirFile.exists()) {
          saveDirFile.mkdirs();
      }
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      String ymd = sdf.format(new Date());
      savePath += ymd + "/";
      saveUrl += ymd + "/";
      File dirFile = new File(savePath);
      if (!dirFile.exists()) {
          dirFile.mkdirs();
      }

      FileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload upload = new ServletFileUpload(factory);
      upload.setHeaderEncoding("UTF-8");


      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      
      Iterator item = multipartRequest.getFileNames();
      while (item.hasNext()) {

          String fileName = (String) item.next();

          MultipartFile file = multipartRequest.getFile(fileName);


          // 检查文件大小

          if (file.getSize() > maxSize) {

              return getError("上传文件大小超过限制。");

          }

          // 检查扩展名

          String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();

          if (!Arrays. asList(extMap.get(dirName).split(",")).contains(fileExt)) {
              return getError("上传文件扩展名是不允许的扩展名。\n只允许"
                      + extMap.get(dirName) + "格式。");

          }
          SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

          String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;

                   
          try {
        	  File uploadedFile = new File(savePath, newFileName);
        	  file.transferTo(uploadedFile);
	  		} catch (Exception e) {
	  			e.printStackTrace();
	  			return null;
	  		}
          Map<String, Object> msg = new HashMap<String, Object>();
          msg.put("error", 0);
          msg.put("url", saveUrl + newFileName);
          return msg;
          }
      return null;
  }
	
	/**
     * 删除文件
     * @param fileAbsSavePath   要删除的图片的绝对保存路径
     */
	@RequestMapping("/deleteFile")
	@ResponseBody
    public void deleteFile(HttpServletRequest request, String fileAbsSavePath) {
		
		if(fileAbsSavePath != null && !fileAbsSavePath.equals("")){
			ServletContext application = request.getSession().getServletContext();
			fileAbsSavePath = application.getRealPath("/") + "images/" + fileAbsSavePath;
		}
		System.out.println(fileAbsSavePath);
		File file=new File(fileAbsSavePath);
        if(file.exists()){
        	
        	 file.delete();
        }
        
    }
	
	private Map<String, Object> getError(String message) {
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("error", 1);
        msg.put("message", message);
        return msg;
    }
	//传给前台公告
	@RequestMapping("/showMessage")
	@ResponseBody
	public Object showMessage(){
		List<BMessagenotice> messageList=bMessagenoticeService.getAll();
		String message=messageList.get(0).getMaintitle();
		return message;
	}
	//获取所有可用公告的数量
	@RequestMapping("/countAvailable")
	@ResponseBody
	public Object countAvailable(){
		int countAvailable=bNoticeService.countAvailable();
		return countAvailable;
	}
	//获取已经添加的消息公告的主标题
	@RequestMapping("/getMainTitle")
	@ResponseBody
	public Object getMainTitle(){
		String title=null;
		List<BMessagenotice> messageList=bMessagenoticeService.getAll();
		if(messageList.size()>0){
			title=messageList.get(0).getMaintitle();
		}
		return title;
	}
	
	//查看公告详情
	@RequestMapping("/showMessageDetail")
	@ResponseBody
	public Object showMessageDetail(String mainTitle){
		List<BMessagenotice> messageList=bMessagenoticeService.getByMainTitle(mainTitle);
		return messageList;
	}
}
