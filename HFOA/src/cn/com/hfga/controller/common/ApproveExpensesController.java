package cn.com.hfga.controller.common;

import cn.com.hfga.dao.travelExpenses.ApproveExpensesDAO;
import cn.com.hfga.dto.travelexpenses.ApplyExpensesDTO;
import cn.com.hfga.dto.travelexpenses.ApproveExpensesDTO;
import cn.com.hfga.dto.travelexpenses.TravelAddressDTO;
import cn.com.hfga.manager.travelexpenses.ApplyExpensesManage;
import cn.com.hfga.manager.travelexpenses.ApproveExpensesManage;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/approveExpenses")
@ResponseBody
public class ApproveExpensesController {
  @Autowired
  private ApplyExpensesManage applyExpensesManage;
  @Autowired
  private ApproveExpensesManage approveExpensesManage;

  /**
   * �������--�Ǽ� ��
   *
   * @param approveExpense
   * @return
   */
  @RequestMapping("/insertFinanceReview")
  @ResponseBody
  public int insertFinanceReview(ApproveExpensesDTO approveExpense) {
    int result = approveExpensesManage.insertFinanceReview(approveExpense);
    if (result > 0)
      return applyExpensesManage.financeReviewSuccess(approveExpense.getTravelExpenseId());
    else
      return 0;
  }

  @RequestMapping("/updateFinanceReview")
  @ResponseBody
  public int updateFinanceReview(ApproveExpensesDTO approveExpense) {
    int result = approveExpensesManage.modifyFinanceReview(approveExpense);
    return result;
  }
  
  @RequestMapping("/deleteapproveExpenses")
  @ResponseBody
  public int deleteapproveExpenses(String Id){
	  System.out.println("ɾ��"+Id);
	  return approveExpensesManage.deleteApproveExpenses(Id);
  }
  
  /**
   * web�˲������--web�˲������--�ǼǺ��޸�
   *
   * @param approveExpense
   * @return
   */
  @RequestMapping("/modifyFinanceReview")
  @ResponseBody
  public int modifyFinanceReview(ApproveExpensesDTO approveExpense) {
	System.out.println("�޸�������"+approveExpense);
    return approveExpensesManage.modifyFinanceReview(approveExpense);
  }

  /**
   * �������--����TravelExpenseId�鿴 ��
   *
   * @param travelExpenseId
   * @return
   */
  @RequestMapping("/getApproveExpensesByTId")
  @ResponseBody
  public Object getApproveExpensesByTId(String travelExpenseId) {
	System.out.println("������"+approveExpensesManage.getApproveExpensesByTId(travelExpenseId));

    return approveExpensesManage.getApproveExpensesByTId(travelExpenseId);
  }

  /**
   * �������--����id�鿴 ��
   *
   * @param id
   * @return
   */
  @RequestMapping("/getApproveExpensesById")
  @ResponseBody
  public Object getApproveExpensesById(String id) {
    return approveExpensesManage.getApproveExpensesById(id);
  }

  /**
   * web�˲���Ǽǳ�ʼ��������ز�ѯ
   *
   * @param approveExpense
   * @return
   */
  @RequestMapping("/searchApproveExpenses")
  @ResponseBody
  public Object searchApproveExpenses(ApplyExpensesDTO approveExpense,String starTime,String searendTime,String voucherNum) {
	System.out.println("ʱ��"+approveExpense.getBeginTime()+"����ʱ��"+approveExpense.getEndTime());
    return approveExpensesManage.searchApplyExpense(approveExpense,starTime,searendTime,voucherNum);
  }
  
  /**
   * web�˲���Ǽ����ݲ鿴
   *
   * @param approveExpense
   * @return
   */
  @RequestMapping("/getroveExpenses")
  @ResponseBody
  public Object searchApproveExpenses(ApproveExpensesDTO approveExpense) {
    return approveExpensesManage.searchApproveExpenses(approveExpense);
  }
  
  
  /*�����ѵǼ��б�
   * 
   */
  @RequestMapping("/exportApproveExpenses")
  @ResponseBody
  public void exportApplyExpenses(HttpServletRequest request, HttpServletResponse response,
		  ApplyExpensesDTO applyExpensesDTO,String starTime,String searendTime,String voucherNum) {
    // ��ȡ�����ļ���
    String path = request.getSession().getServletContext().getRealPath("/");
    System.out.println("�����Ĳ�ѯ����Ϊ"+applyExpensesDTO);
    String fileName = "���÷��ѵǼ��б�" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".xlsx";
    String filePath = path + "/" + fileName;
    int flag = approveExpensesManage.exportapproveExpenses(applyExpensesDTO, filePath,starTime,searendTime,voucherNum);
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
  @RequestMapping("/getApproveExpenses")
  @ResponseBody
  public Object getApproveExpenses(String q){
	  return  approveExpensesManage.listApproveExpenses(q);
  }
  
  
  
}
