package cn.com.hfga.controller.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.com.hfga.entity.log.OperationRecord;
import cn.com.hfga.manager.log.OperateRecordManage;

@Controller
public class OperateRecordController {
	@Autowired
	private OperateRecordManage operateRecordManage;
	/**
	 * 分页查询
	 * @param request
	 * @return Object
	 */
	@RequestMapping(value = "/operateRecord/display")
	@ResponseBody
	public Object logDisplay(HttpServletRequest request){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<OperationRecord> list = new ArrayList<OperationRecord>();
		list = operateRecordManage.logDisplay(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = operateRecordManage.logAllCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/operateRecord/deleteRecord")
	@ResponseBody
	public int deleteRecord(String id){
		return operateRecordManage.deleteRecord(Integer.parseInt(id));
	}
	/**
	 * 分页查询
	 * @param request
	 * @return Object
	 */
	@RequestMapping(value = "/operateRecord/displayByPage")
	@ResponseBody
	public Object logDisplayByPage(HttpServletRequest request, String realName, String startTime, String endTime){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<OperationRecord> list = new ArrayList<OperationRecord>();
		list = operateRecordManage.searchRecordByPage(realName, startTime, endTime, start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = operateRecordManage.getSearchInfoCount(realName, startTime, endTime);
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}
	/**
	 * 条件查询
	 * @param realName
	 * @param startTime
	 * @param endTime
	 * @return List<OperationRecord>
	 */
	@RequestMapping(value="/operateRecord/wGetSearchRecord")
	@ResponseBody
	public List<OperationRecord> searchRecord(String realName, String startTime, String endTime){
		return operateRecordManage.searchRecord(realName, startTime, endTime);
	}
}
