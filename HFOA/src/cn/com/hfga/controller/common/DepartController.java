package cn.com.hfga.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hfga.entity.user.DepartmentEntity;
import cn.com.hfga.manager.user.DepartmentManager;
/**
 * 
 * @author ysy
 *
 */
@Controller
public class DepartController {

	@Autowired
	private DepartmentManager deptManage;
	
	//获取全部部门
	@RequestMapping(value="/department/getAllDept")
	@ResponseBody
	public Object getAllDept(HttpServletRequest request){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		List<DepartmentEntity> list = deptManage.getAllDept(start,number);
		int total = deptManage.getAllDeptCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}
	//保存部门
	@RequestMapping(value="/department/saveDept")
	@ResponseBody
	public Object saveDept(DepartmentEntity dept){
		return deptManage.saveDept(dept);
	}
	
	//修改部门
	@RequestMapping(value="/department/updateDept")
	@ResponseBody
	public Object updateDept(DepartmentEntity dept){
		return deptManage.updateDept(dept);
	}
}
