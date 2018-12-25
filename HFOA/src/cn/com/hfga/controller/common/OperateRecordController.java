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
	 * ��ҳ��ѯ
	 * @param request
	 * @return Object
	 */
	@RequestMapping(value = "/operateRecord/display")
	@ResponseBody
	public Object logDisplay(HttpServletRequest request){
		String page = request.getParameter("page");// �ڼ�ҳ
		String rows = request.getParameter("rows");// ÿҳ������
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
		// ÿҳ�Ŀ�ʼ��¼
		int start = (intPage - 1) * number;
		List<OperationRecord> list = new ArrayList<OperationRecord>();
		list = operateRecordManage.logDisplay(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
		int total = operateRecordManage.logAllCount();
		jsonMap.put("total", total);// total����ܼ�¼��
		jsonMap.put("rows", list);// rows�������ÿҳ��¼list
		return jsonMap;
	}
	/**
	 * ɾ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/operateRecord/deleteRecord")
	@ResponseBody
	public int deleteRecord(String id){
		return operateRecordManage.deleteRecord(Integer.parseInt(id));
	}
	/**
	 * ��ҳ��ѯ
	 * @param request
	 * @return Object
	 */
	@RequestMapping(value = "/operateRecord/displayByPage")
	@ResponseBody
	public Object logDisplayByPage(HttpServletRequest request, String realName, String startTime, String endTime){
		String page = request.getParameter("page");// �ڼ�ҳ
		String rows = request.getParameter("rows");// ÿҳ������
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
		// ÿҳ�Ŀ�ʼ��¼
		int start = (intPage - 1) * number;
		List<OperationRecord> list = new ArrayList<OperationRecord>();
		list = operateRecordManage.searchRecordByPage(realName, startTime, endTime, start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
		int total = operateRecordManage.getSearchInfoCount(realName, startTime, endTime);
		jsonMap.put("total", total);// total����ܼ�¼��
		jsonMap.put("rows", list);// rows�������ÿҳ��¼list
		return jsonMap;
	}
	/**
	 * ������ѯ
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
