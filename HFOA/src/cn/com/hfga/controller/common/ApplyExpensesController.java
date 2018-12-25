package cn.com.hfga.controller.common;

import cn.com.hfga.dto.travelexpenses.ApplyExpensesDTO;
import cn.com.hfga.dto.travelexpenses.TravelAddressDTO;
import cn.com.hfga.manager.travelexpenses.ApplyExpensesManage;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/applyExpenses")
@ResponseBody
public class ApplyExpensesController {
  @Autowired
  private ApplyExpensesManage applyExpensesManage;

  /**
   * 移动端用户插入数据 √
   *
   * @param applyExpense
   * @return
   */
  @RequestMapping("/insertTravelExpenses")
  @ResponseBody
  public int insertTravelExpenses(ApplyExpensesDTO applyExpense) {
	System.out.println(applyExpense.getTripMode());
	System.out.println("移动端插入数据");
	System.out.println("applyExpens是"+applyExpense);
    applyExpense.setTripDetails_list(transformJson(applyExpense.getTripDetails()));
    applyExpense.setTripDetails(JSONArray.fromObject(applyExpense.getTripDetails_list()).toString());
    return applyExpensesManage.insertTravelExpenses(applyExpense);
  }

  /**
   * 移动端用户修改数据  加入重新审核，后台判断某些数据内容是否修改
   *
   * @param applyExpense
   * @return
   */
  @RequestMapping("/modifyApplyExpenses")
  @ResponseBody
  public int modifyTravelExpenses(ApplyExpensesDTO applyExpense,String RoleId) {
	System.out.println("查询id是"+applyExpense); 
	 System.out.println("用户id是"+RoleId);
    applyExpense.setTripDetails_list(transformJson(applyExpense.getTripDetails()));
    applyExpense.setTripDetails(JSONArray.fromObject(applyExpense.getTripDetails_list()).toString());
    return applyExpensesManage.modifyTravelExpenses(applyExpense, RoleId);
  }

  /**
   * 移动端部门审核通过 √
   *
   * @param id
   * @return
   */
  @RequestMapping("/departmentSuccess")
  @ResponseBody
  public int departmentSuccess(String id) {
    return applyExpensesManage.departmentSuccess(id);
  }

  /**
   * 移动端部门审核不通过 √
   *
   * @param id
   * @return
   */
  @RequestMapping("/departmentFail")
  @ResponseBody
  public int departmentFail(String id) {
    return applyExpensesManage.departmentFail(id);
  }

  /**
   * 移动端申请执行 √
   *
   * @param id
   * @return
   */
  @RequestMapping("/applyImplement")
  @ResponseBody
  public int ApplyImplement(String id) {
    return applyExpensesManage.applyImplement(id);
  }

  /**
   * 移动端根据id获取数据 √
   *
   * @param id
   * @return
   */
  @RequestMapping("/getApplyExpenseById")
  @ResponseBody
  public Object getApplyExpenseById(String id) {
    Map<String, Object> jsonMap = new HashMap<String, Object>();
    jsonMap.put("data", applyExpensesManage.getApplyExpenseById(id));
    return jsonMap;
  }

  /**
   * 移动端获取某个人待审批的数据 √
   *
   * @param applyMan
   * @return
   */
  @RequestMapping("/getWaitApplyExpenseByName")
  @ResponseBody
  public Object getWaitApplyExpenseByName(String applyMan) {
    Map<String, Object> jsonMap = new HashMap<String, Object>();
    jsonMap.put("data", applyExpensesManage.getWaitApplyExpenseByName(applyMan));
    return jsonMap;
  }

  /**
   * 移动端获取经理待审批的数据
   *
   * @param approveMan
   * @return
   */
  @RequestMapping("/getWaitApplyExpenseByManager")
  @ResponseBody
  public Object getWaitApplyExpenseByManager(String approveMan) {
    Map<String, Object> jsonMap = new HashMap<String, Object>();
    jsonMap.put("data", applyExpensesManage.getWaitApplyExpenseByManager(approveMan));
    return jsonMap;
  }

  /**
   * 移动端获取某个人部门审批通过后待执行的数据 √
   *
   * @param applyMan
   * @return
   */
  @RequestMapping("/getPassApplyExpenseByName")
  @ResponseBody
  public Object getPassApplyExpenseByName(String applyMan) {
    Map<String, Object> jsonMap = new HashMap<String, Object>();
    jsonMap.put("data", applyExpensesManage.getPassApplyExpenseByName(applyMan));
    return jsonMap;
  }

  /**
   * 移动端获取某个人部门审批不通过待修改的数据 √
   *
   * @param applyMan
   * @return
   */
  @RequestMapping("/getNoPassApplyExpenseByName")
  @ResponseBody
  public Object getNoPassApplyExpenseByName(String applyMan) {
    Map<String, Object> jsonMap = new HashMap<String, Object>();
    jsonMap.put("data", applyExpensesManage.getNoPassApplyExpenseByName(applyMan));
    return jsonMap;
  }

  /**
   * 移动端获取某个人待财务审批的数据 √
   *
   * @param applyMan
   * @return
   */
  @RequestMapping("/getReviewFail")
  @ResponseBody
  public Object getReviewFail(String applyMan) {
    Map<String, Object> jsonMap = new HashMap<String, Object>();
    jsonMap.put("data", applyExpensesManage.getReviewFail(applyMan));
    return jsonMap;
  }

  /**
   * 移动端获取某个人审批登记的数据 √
   *
   * @param applyMan
   * @return
   */
  @RequestMapping("/getReviewSuccessByName")
  @ResponseBody
  public Object getReviewSuccessByName(String applyMan) {
    Map<String, Object> jsonMap = new HashMap<String, Object>();
    jsonMap.put("data", applyExpensesManage.getReviewSuccessByName(applyMan));
    return jsonMap;
  }

  /**
   * 移动端获取某个人申请所有数据 √
   *
   * @param applyMan
   * @return
   */
  @RequestMapping("/getAllState")
  @ResponseBody
  public Object getAllState(String applyMan) {
	
    Map<String, Object> jsonMap = new HashMap<String, Object>();
    jsonMap.put("data", applyExpensesManage.getAllState(applyMan));
    return jsonMap;
  }

  /**
   * 移动端根据id撤销申请 √
   *
   * @param id
   * @return
   */
  @RequestMapping("/deleteApply")
  @ResponseBody
  public Object deleteApply(String id) {
    return applyExpensesManage.deleteApply(id);
  }

  /**
   * web端财务审核--驳回 √
   *
   * @param id
   * @return
   */
  @RequestMapping("/financeReviewFail")
  @ResponseBody
  public int financeReviewFail(String id) {
    return applyExpensesManage.financeReviewFail(id);
  }

  /**
   * web端待登记列表、已登记列表初始化及其相关查询 √
   *
   * @param state
   * @param applyExpense
   * @param request
   * @return
   */
  @RequestMapping("/searchTravelExpenses")
  @ResponseBody
  public Object searchTravelExpenses(
      Integer state, ApplyExpensesDTO applyExpense, HttpServletRequest request) {
    String page = request.getParameter("page");// 第几页
    String rows = request.getParameter("rows");// 每页多少条
    int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
    int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
    // 每页的开始记录
    int start = (intPage - 1) * number;
    List<ApplyExpensesDTO> list_all = new ArrayList<ApplyExpensesDTO>();
    list_all = applyExpensesManage.searchTravelExpenses(state, applyExpense);
    Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
    int total = list_all.size();
    List<ApplyExpensesDTO> list = new ArrayList<>();
    if (start + number <= total)
      list.addAll(list_all.subList(start, start + number));
    else
      list.addAll(list_all.subList(start, total));
    jsonMap.put("total", total);// total存放总记录数
    jsonMap.put("rows", list);// rows键，存放每页记录list
    return jsonMap;
  }

  private List<TravelAddressDTO> transformJson(String tripDetails) {
    List<TravelAddressDTO> travelAddressDTOS = new ArrayList<TravelAddressDTO>();
    System.out.println("tripDetail是"+tripDetails);
    String[] arr1 = tripDetails.split("\\*");
    for (int i = 0; i < arr1.length; i++) {
      String[] arr2 = arr1[i].split(",");
      TravelAddressDTO travelAddressDTO = new TravelAddressDTO();
      travelAddressDTO.setBeginAddress(arr2[0]);
      travelAddressDTO.setEndAddress(arr2[1]);
      travelAddressDTO.setVehicle(arr2[2]);
      travelAddressDTOS.add(travelAddressDTO);
    }
    return travelAddressDTOS;
  }
  @RequestMapping("listApplyExpenses")
  @ResponseBody
  public Object listApplyExpenses( ApplyExpensesDTO applyExpense, HttpServletRequest request){
	  return applyExpensesManage.listTravelExpense(applyExpense);
  }
  
  

  /**
   * 导出excel--差旅费待登记列表
   *
   * @param request
   * @param response
   * @param applyExpense
   */
  @RequestMapping("/exportApplyExpenses")
  @ResponseBody
  public void exportApplyExpenses(HttpServletRequest request, HttpServletResponse response,
                                  ApplyExpensesDTO applyExpense) {
    // 获取导出文件夹
    String path = request.getSession().getServletContext().getRealPath("/");
    String fileName = "差旅费待登记列表" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".xlsx";
    String filePath = path + "/" + fileName;
    int flag = applyExpensesManage.exportApplyExpenses(applyExpense, filePath);
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
