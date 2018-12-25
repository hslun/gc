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
   * 财务审核--登记 √
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
	  System.out.println("删除"+Id);
	  return approveExpensesManage.deleteApproveExpenses(Id);
  }
  
  /**
   * web端财务审核--web端财务审核--登记后修改
   *
   * @param approveExpense
   * @return
   */
  @RequestMapping("/modifyFinanceReview")
  @ResponseBody
  public int modifyFinanceReview(ApproveExpensesDTO approveExpense) {
	System.out.println("修改数据是"+approveExpense);
    return approveExpensesManage.modifyFinanceReview(approveExpense);
  }

  /**
   * 财务审核--根据TravelExpenseId查看 √
   *
   * @param travelExpenseId
   * @return
   */
  @RequestMapping("/getApproveExpensesByTId")
  @ResponseBody
  public Object getApproveExpensesByTId(String travelExpenseId) {
	System.out.println("数据是"+approveExpensesManage.getApproveExpensesByTId(travelExpenseId));

    return approveExpensesManage.getApproveExpensesByTId(travelExpenseId);
  }

  /**
   * 财务审核--根据id查看 √
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
   * web端财务登记初始化及其相关查询
   *
   * @param approveExpense
   * @return
   */
  @RequestMapping("/searchApproveExpenses")
  @ResponseBody
  public Object searchApproveExpenses(ApplyExpensesDTO approveExpense,String starTime,String searendTime,String voucherNum) {
	System.out.println("时间"+approveExpense.getBeginTime()+"结束时间"+approveExpense.getEndTime());
    return approveExpensesManage.searchApplyExpense(approveExpense,starTime,searendTime,voucherNum);
  }
  
  /**
   * web端财务登记内容查看
   *
   * @param approveExpense
   * @return
   */
  @RequestMapping("/getroveExpenses")
  @ResponseBody
  public Object searchApproveExpenses(ApproveExpensesDTO approveExpense) {
    return approveExpensesManage.searchApproveExpenses(approveExpense);
  }
  
  
  /*导出已登记列表
   * 
   */
  @RequestMapping("/exportApproveExpenses")
  @ResponseBody
  public void exportApplyExpenses(HttpServletRequest request, HttpServletResponse response,
		  ApplyExpensesDTO applyExpensesDTO,String starTime,String searendTime,String voucherNum) {
    // 获取导出文件夹
    String path = request.getSession().getServletContext().getRealPath("/");
    System.out.println("导出的查询数据为"+applyExpensesDTO);
    String fileName = "差旅费已登记列表" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".xlsx";
    String filePath = path + "/" + fileName;
    int flag = approveExpensesManage.exportapproveExpenses(applyExpensesDTO, filePath,starTime,searendTime,voucherNum);
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
  @RequestMapping("/getApproveExpenses")
  @ResponseBody
  public Object getApproveExpenses(String q){
	  return  approveExpensesManage.listApproveExpenses(q);
  }
  
  
  
}
