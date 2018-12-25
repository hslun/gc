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
   * �ƶ����û��������� ��
   *
   * @param applyExpense
   * @return
   */
  @RequestMapping("/insertTravelExpenses")
  @ResponseBody
  public int insertTravelExpenses(ApplyExpensesDTO applyExpense) {
	System.out.println(applyExpense.getTripMode());
	System.out.println("�ƶ��˲�������");
	System.out.println("applyExpens��"+applyExpense);
    applyExpense.setTripDetails_list(transformJson(applyExpense.getTripDetails()));
    applyExpense.setTripDetails(JSONArray.fromObject(applyExpense.getTripDetails_list()).toString());
    return applyExpensesManage.insertTravelExpenses(applyExpense);
  }

  /**
   * �ƶ����û��޸�����  ����������ˣ���̨�ж�ĳЩ���������Ƿ��޸�
   *
   * @param applyExpense
   * @return
   */
  @RequestMapping("/modifyApplyExpenses")
  @ResponseBody
  public int modifyTravelExpenses(ApplyExpensesDTO applyExpense,String RoleId) {
	System.out.println("��ѯid��"+applyExpense); 
	 System.out.println("�û�id��"+RoleId);
    applyExpense.setTripDetails_list(transformJson(applyExpense.getTripDetails()));
    applyExpense.setTripDetails(JSONArray.fromObject(applyExpense.getTripDetails_list()).toString());
    return applyExpensesManage.modifyTravelExpenses(applyExpense, RoleId);
  }

  /**
   * �ƶ��˲������ͨ�� ��
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
   * �ƶ��˲�����˲�ͨ�� ��
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
   * �ƶ�������ִ�� ��
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
   * �ƶ��˸���id��ȡ���� ��
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
   * �ƶ��˻�ȡĳ���˴����������� ��
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
   * �ƶ��˻�ȡ���������������
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
   * �ƶ��˻�ȡĳ���˲�������ͨ�����ִ�е����� ��
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
   * �ƶ��˻�ȡĳ���˲���������ͨ�����޸ĵ����� ��
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
   * �ƶ��˻�ȡĳ���˴��������������� ��
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
   * �ƶ��˻�ȡĳ���������Ǽǵ����� ��
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
   * �ƶ��˻�ȡĳ���������������� ��
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
   * �ƶ��˸���id�������� ��
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
   * web�˲������--���� ��
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
   * web�˴��Ǽ��б��ѵǼ��б��ʼ��������ز�ѯ ��
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
    String page = request.getParameter("page");// �ڼ�ҳ
    String rows = request.getParameter("rows");// ÿҳ������
    int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
    int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
    // ÿҳ�Ŀ�ʼ��¼
    int start = (intPage - 1) * number;
    List<ApplyExpensesDTO> list_all = new ArrayList<ApplyExpensesDTO>();
    list_all = applyExpensesManage.searchTravelExpenses(state, applyExpense);
    Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
    int total = list_all.size();
    List<ApplyExpensesDTO> list = new ArrayList<>();
    if (start + number <= total)
      list.addAll(list_all.subList(start, start + number));
    else
      list.addAll(list_all.subList(start, total));
    jsonMap.put("total", total);// total����ܼ�¼��
    jsonMap.put("rows", list);// rows�������ÿҳ��¼list
    return jsonMap;
  }

  private List<TravelAddressDTO> transformJson(String tripDetails) {
    List<TravelAddressDTO> travelAddressDTOS = new ArrayList<TravelAddressDTO>();
    System.out.println("tripDetail��"+tripDetails);
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
   * ����excel--���÷Ѵ��Ǽ��б�
   *
   * @param request
   * @param response
   * @param applyExpense
   */
  @RequestMapping("/exportApplyExpenses")
  @ResponseBody
  public void exportApplyExpenses(HttpServletRequest request, HttpServletResponse response,
                                  ApplyExpensesDTO applyExpense) {
    // ��ȡ�����ļ���
    String path = request.getSession().getServletContext().getRealPath("/");
    String fileName = "���÷Ѵ��Ǽ��б�" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".xlsx";
    String filePath = path + "/" + fileName;
    int flag = applyExpensesManage.exportApplyExpenses(applyExpense, filePath);
    if (flag != 1) {
      return;
    }
    try {
      // ���ݲ�ͬ����������������ļ�����������
      String userAgent = request.getHeader("User-Agent");
      // ���IE��������ieΪ�ں˵������
      if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
        fileName = URLEncoder.encode(fileName, "UTF-8");
      } else {
        // ��IE������Ĵ���
        fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
      }
      // ��ȡһ����
      InputStream in = new FileInputStream(new File(filePath));
      // �������ص���Ӧͷ
      response.setHeader("content-disposition", "attachment;fileName=" + fileName);
      response.setCharacterEncoding("UTF-8");
      // ��ȡresponse�ֽ���
      OutputStream out = response.getOutputStream();
      byte[] b = new byte[1024];
      int len = -1;
      while ((len = in.read(b)) != -1) {
        out.write(b, 0, len);
      }
      // �ر�
      out.close();
      in.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
