package cn.com.hfga.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hfga.dto.user.UserDTO;
import cn.com.hfga.entity.user.DutyEntity;
import cn.com.hfga.entity.user.MenuEntity;
import cn.com.hfga.entity.user.UserEntity;
import cn.com.hfga.manager.user.DutyManager;
import cn.com.hfga.manager.user.MenuManager;
import cn.com.hfga.manager.user.UserManager;
@Controller
public class SysUserController {
	@Autowired
	private UserManager userManager;
	@Autowired
	private MenuManager menuManager;
	@Autowired
	private DutyManager dutyManager;
	/**
	 * Web-获取所有用户-分页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sysUser/getUserAll")
	@ResponseBody
	public Object getAll(HttpServletRequest request){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		List<UserEntity> list = new ArrayList<UserEntity>();
		list = userManager.getByPage(start, number);
		System.out.println("list"+list.get(0).getQICQ());
		int total = userManager.getAllCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}
	/**
	 * Web-新增用户
	 * @param userDTO
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/sysUser/saveOrUpdateSysUser")
	@ResponseBody
	public int saveUser(UserDTO userDTO) throws ParseException{
		System.out.println("修改用户是"+userDTO);
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Date parse = null;
		if(null!=userDTO.getAllowStartTime()&&!userDTO.getAllowStartTime().equals("")){
			 parse = smf.parse(userDTO.getAllowStartTime());
		}
		System.out.println("入职时间"+parse);
		System.out.println("现在时间"+date);
		
		
		int flag = 0;
		if(userDTO.getId()==-1){
			//保存
			flag = userManager.saveUser(userDTO);
		}else{
			//修改
			flag = userManager.updateUser(userDTO);
		}
		return flag;
	}
	/**
	 * Web-删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/sysUser/deleteSysUser")
	@ResponseBody
	public int deleteUser(String Id){
		return userManager.deleteUser(Id);
	}
	/**
	 * Web-重置密码
	 * @param Id
	 * @return
	 */
	@RequestMapping(value="/sysUser/ResetPasswordById")
	@ResponseBody
	public int ResetPasswordById(String Id){
		return userManager.ResetPasswordById(Id);
	}
	/**
	 * Web-查询用户
	 * @param userDTO
	 * @return
	 */
	@RequestMapping(value="/sysUser/searchUser")
	@ResponseBody
	public Object searchUser(HttpServletRequest request,UserDTO userDTO){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		int total = 0;
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		List<UserEntity> list = new ArrayList<UserEntity>();
		
		if(!"".equals(userDTO.getDepartmentName())&&!"全部".equals(userDTO.getDepartmentName())&&"".equals(userDTO.getCode())){
			//根据部门查询
			list = userManager.searchUserByDepartmentName(userDTO,start,number);
			total = userManager.searchUserCountByDepartmentName(userDTO);
		}else if("".equals(userDTO.getDepartmentName())&&!"".equals(userDTO.getCode())){
			//根据用户名查询
			list = userManager.searchUserByCode(userDTO); 
			total = list.size();
		}else if("全部".equals(userDTO.getDepartmentName())&&!"".equals(userDTO.getCode())){
			//查询全部
			list = userManager.searchUserByCode(userDTO);
			total = userManager.searchUserCountByCode(userDTO);
		}else if("全部".equals(userDTO.getDepartmentName())&&"".equals(userDTO.getCode())){
			//查询全部
			list = userManager.getByPage(start,number);
			total = userManager.getAllCount();
		}else{
			//根据用户名和部门查询
			list = userManager.searchUser(userDTO);
			total = list.size();
		}
		
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		
		return jsonMap;
	}
	/**
	 * Web-用户导出
	 * @param request
	 * @param response
	 * @param number
	 * @param depart
	 */
	@RequestMapping(value = "/sysUser/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,String number,String depart){
		System.out.println("depart是"+depart);
		String deptStr = "";
		try {
			//解码
			deptStr=URLDecoder.decode(depart,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String[] nlist = number.split(","); // 获得传递过来的number列表
		for(int i=0;i<nlist.length;i++){
			System.out.println("输出"+nlist[i]);
		}
		// 获取导出文件夹
		String path = request.getSession().getServletContext().getRealPath("/");
		// 生成导出的文件名
		Date dt = new Date();
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
		String date = matter.format(dt);
		String fileName = "用户列表" + date + ".xlsx";
		String filePath = path + "/" + fileName;
		int flag = userManager.export(nlist, filePath, deptStr);
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
	/**
	 * Web-获取职位
	 * @return
	 */
	@RequestMapping(value = "/sysUser/getDuty")
	@ResponseBody
	public List<DutyEntity> getDuty(){
		return dutyManager.getDuty();
	}
	/**
	 * Web-获取菜单
	 * @param deptId
	 * @return
	 */
	@RequestMapping(value = "/sysUser/getMenu")
	@ResponseBody
	public List<MenuEntity> getMenu(String deptId){
		return menuManager.getByDeptId(deptId);
	}
	/**
	 * Web-更新私车权限
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/sysUser/updatePrivate")
	@ResponseBody
	public Object updatePrivate(String id){
		return userManager.updatePrivate(id);
	}
}
