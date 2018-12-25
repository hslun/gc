package cn.com.hfga.manageimpl.entertain;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.entertain.EntertainAnnualBudgetDAO;
import cn.com.hfga.dao.entertain.EntertainApplyInfoDAO;
import cn.com.hfga.dto.entertain.EntertainAnnualInfoDTO;
import cn.com.hfga.entity.entertain.EntertainAnnualBudgetEntity;
import cn.com.hfga.manager.entertain.EntertainAnnualBudgetManage;

@Service("entertainAnnualBudgetManageImpl")
public class EntertainAnnualBudgetManageImpl implements EntertainAnnualBudgetManage{

	@Autowired
	private EntertainAnnualBudgetDAO entertainAnnualBudgetDAO;
	
	//@Autowired
	//private EntertainInfoManageImpl entertainInfoManageImpl;
	
	@Autowired
	private EntertainApplyInfoDAO entertainApplyInfoDAO;
	// web-招待年度预算-显示
/*	@Transactional
	@Override
	public List<EntertainAnnualBudgetEntity> wGetAnnualBudget() {
		return entertainAnnualBudgetDAO.wGetAnnualBudget();
	}*/

    @Transactional
	@Override
	public List<EntertainAnnualInfoDTO> wGetAnnualBudget() {
    	//获取当前年份
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String year = sdf.format(date);
        
    	List<EntertainAnnualInfoDTO> newlist= new ArrayList<EntertainAnnualInfoDTO>();
    	List<EntertainAnnualBudgetEntity> list= new ArrayList<EntertainAnnualBudgetEntity>();
    	
    	list = entertainAnnualBudgetDAO.wGetAnnualBudget(year);// 获得当年全部数据
    	for(int i=0;i<list.size();i++){
    	EntertainAnnualInfoDTO aNewList = new EntertainAnnualInfoDTO(); // 最终表的存储变量
        EntertainAnnualBudgetEntity aList = new EntertainAnnualBudgetEntity(); // 中间表的存储变量
    	String time=list.get(i).getTime(); //获得第i条的编制次数
    	aList=list.get(i); //获得当年数据中的第i条
    	if(time.equals("0")||time.equals("1")){
    		aNewList.setID(i);
    		aNewList.setDepartment(aList.getDepartment());
    		aNewList.setBudgetSum(aList.getBudgetSum0());
    		aNewList.setCopileTime(aList.getCopileTime0());
    		newlist.add(aNewList);
    	}
    	else if(time.equals("2")){
    		aNewList.setID(i);
    		aNewList.setDepartment(aList.getDepartment());
    		Double sum=Double.parseDouble(aList.getBudgetSum0())+Double.parseDouble(aList.getBudgetSum1());
    		aNewList.setBudgetSum(Double.toString(sum));
    		aNewList.setCopileTime(aList.getCopileTime1());
    		newlist.add(aNewList);
    	}
    	else if(time.equals("3")){
    		aNewList.setID(i);
    		aNewList.setDepartment(aList.getDepartment());
    		Double sum=Double.parseDouble(aList.getBudgetSum0())+Double.parseDouble(aList.getBudgetSum1())+Double.parseDouble(aList.getBudgetSum2());
    		aNewList.setBudgetSum(Double.toString(sum));
    		aNewList.setCopileTime(aList.getCopileTime2());
    		newlist.add(aNewList);
    	}
    	else if(time.equals("4")){
    		aNewList.setID(i);
    		aNewList.setDepartment(aList.getDepartment());
    		Double sum=Double.parseDouble(aList.getBudgetSum0())+Double.parseDouble(aList.getBudgetSum1())+Double.parseDouble(aList.getBudgetSum2())+Double.parseDouble(aList.getBudgetSum3());
    		aNewList.setBudgetSum(Double.toString(sum));
    		aNewList.setCopileTime(aList.getCopileTime3());
    		newlist.add(aNewList);
    	}
    	else if(time.equals("5")){
    		aNewList.setID(i);
    		aNewList.setDepartment(aList.getDepartment());
    		Double sum=Double.parseDouble(aList.getBudgetSum0())+Double.parseDouble(aList.getBudgetSum1())+Double.parseDouble(aList.getBudgetSum2())+Double.parseDouble(aList.getBudgetSum3())+Double.parseDouble(aList.getBudgetSum4());
    		aNewList.setBudgetSum(Double.toString(sum));
    		aNewList.setCopileTime(aList.getCopileTime4());
    		newlist.add(aNewList);
    	}
    	else{
    		aNewList.setID(i);
    		aNewList.setDepartment(aList.getDepartment());
    		Double sum=Double.parseDouble(aList.getBudgetSum0())+Double.parseDouble(aList.getBudgetSum1())+Double.parseDouble(aList.getBudgetSum2())+Double.parseDouble(aList.getBudgetSum3())+Double.parseDouble(aList.getBudgetSum4())+Double.parseDouble(aList.getBudgetSum5());
    		aNewList.setBudgetSum(Double.toString(sum));
    		aNewList.setCopileTime(aList.getCopileTime5());
    		newlist.add(aNewList);
    	}
    	}
		return newlist; //返回最终表
	}

    // web-显示预算执行情况列表
    @Transactional
	@Override
	public List<EntertainAnnualBudgetEntity> wGetNowAnnual() {
		//获取当前年份
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String year = sdf.format(date);
		return entertainAnnualBudgetDAO.wGetAnnualBudget(year);
	}
 // web-显示预算执行情况列表
    @Transactional
	@Override
	public List<EntertainAnnualBudgetEntity> wGetNowAnnual1(String year) {
		return entertainAnnualBudgetDAO.wGetAnnualBudget(year);
	}

	// web-插入修改的年度预算值
	@Transactional
	@Override
	public int wSetAdjust(String param) {
		String[] list = param.split(",");
		Date dt=new Date();
	    SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
	    String df = matter.format(dt);
	    
	    // 获得修改的年份
	    String[] para = list[11].split(":"); 
	    String year = para[1];
	    
		for(int i=0;i<list.length-1;i++){
			if(!list[i].equals("")){
				String[] content=list[i].split(":");
				String time = entertainAnnualBudgetDAO.getTimeById(content[0],year);// 根据id获得Time的值
				//根据time的值判断更新位置
				if(time.equals("0")&&content.length==2){
					entertainAnnualBudgetDAO.updateBudgetSum(content[0], content[1], df, year);
				}
				else if(time.equals("1")&&content.length==2){
					entertainAnnualBudgetDAO.updateBudgetSum0(content[0], content[1], df, year);
				}
				else if(time.equals("2")&&content.length==2){
					entertainAnnualBudgetDAO.updateBudgetSum1(content[0], content[1], df, year);
				}
				else if(time.equals("3")&&content.length==2){
					entertainAnnualBudgetDAO.updateBudgetSum2(content[0], content[1], df, year);
				}
				
				else if(time.equals("4")&&content.length==2){
					entertainAnnualBudgetDAO.updateBudgetSum3(content[0], content[1], df, year);
				}	
				else if(time.equals("5")&&content.length==2){
					entertainAnnualBudgetDAO.updateBudgetSum4(content[0], content[1], df, year);
				}
			}
			else{
				continue;
			}
		}
	 return 0;
	}

	// web-获得选中年份的预算执行情况列表
	public List<EntertainAnnualBudgetEntity> wGetSearchImplementation(String year) {
		return entertainAnnualBudgetDAO.wGetAnnualBudget(year);
	}
	
	// 根据部门获取该部门剩余的金额
	@Transactional
	@Override
	public Object getLastSum(String department){
		//预算budget
		//剩余预算lastBudget
		//发生额usedBudget
		//使用率percent
		
		// 获取当前年份
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String year = sdf.format(date);
        // 该部门本年度已使用的金额相关信息
        String syear = year+"%";
        List<Object[]> list = entertainApplyInfoDAO.getOneDepartmentEntertainSum(department,syear);
        Double mm=0.0d;//已使用的金额数
        for(int i=0;i<list.size();i++){
        	Object[] objects=(Object[])list.get(i);
			String sum = objects[3].toString();
			mm=mm+Double.parseDouble(sum);
        }
        
        
        if(department.equals("财务部")){
        	department="计划财务部";
        }
        else if(department.equals("质量部")){
        	department="质量安全部";
        }
        else if(department.equals("市场部")){
        	department="市场开发部";
        }
        Double lastNum=0.0d;
        EntertainAnnualBudgetEntity entity = entertainAnnualBudgetDAO.getEntity(department,year);
        String time=entity.getTime();
        if(time.equals("0")){
        	lastNum=0.0d;
        }
        else if(time.equals("1")){
    		lastNum=Double.parseDouble(entity.getBudgetSum0());
    	}
    	else if(time.equals("2")){
    		lastNum=Double.parseDouble(entity.getBudgetSum0())+Double.parseDouble(entity.getBudgetSum1());
    		
    	}
    	else if(time.equals("3")){
    		lastNum=Double.parseDouble(entity.getBudgetSum0())+Double.parseDouble(entity.getBudgetSum1())+Double.parseDouble(entity.getBudgetSum2());
    	}
    	else if(time.equals("4")){
    		lastNum=Double.parseDouble(entity.getBudgetSum0())+Double.parseDouble(entity.getBudgetSum1())+Double.parseDouble(entity.getBudgetSum2())+Double.parseDouble(entity.getBudgetSum3());
    	}
    	else if(time.equals("5")){ 
    		lastNum=Double.parseDouble(entity.getBudgetSum0())+Double.parseDouble(entity.getBudgetSum1())+Double.parseDouble(entity.getBudgetSum2())+Double.parseDouble(entity.getBudgetSum3())+Double.parseDouble(entity.getBudgetSum4());
    	}
    	else{
    		lastNum=Double.parseDouble(entity.getBudgetSum0())+Double.parseDouble(entity.getBudgetSum1())+Double.parseDouble(entity.getBudgetSum2())+Double.parseDouble(entity.getBudgetSum3())+Double.parseDouble(entity.getBudgetSum4())+Double.parseDouble(entity.getBudgetSum5());
    	}
        DecimalFormat df=new DecimalFormat("#.00");
        double usedBudget = Double.parseDouble(df.format(mm));
        double budget =  Double.parseDouble(df.format(lastNum))*10000; // 把预算转为double类型
        double lastBudget = budget-usedBudget; // 剩余预算保留四位有效数字
        //int budget = //预算budget
        
        // 计算百分比
        // 创建一个数值格式化对象  
        NumberFormat numberFormat = NumberFormat.getInstance();  
        // 设置精确到小数点后4位  
        numberFormat.setMaximumFractionDigits(4);  
        String percent = numberFormat.format(usedBudget / budget * 100);  
  
        Map<String,String> jsonMap = new HashMap<String,String>();// 定义map
        jsonMap.put("budget", Double.toString(budget));
        jsonMap.put("usedBudget", Double.toString(usedBudget));
        jsonMap.put("lastBudget", Double.toString(lastBudget));
        jsonMap.put("percent", percent+"%");
		return jsonMap;
	}
	
}
