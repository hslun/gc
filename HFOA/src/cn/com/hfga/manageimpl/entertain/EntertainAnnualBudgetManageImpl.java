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
	// web-�д����Ԥ��-��ʾ
/*	@Transactional
	@Override
	public List<EntertainAnnualBudgetEntity> wGetAnnualBudget() {
		return entertainAnnualBudgetDAO.wGetAnnualBudget();
	}*/

    @Transactional
	@Override
	public List<EntertainAnnualInfoDTO> wGetAnnualBudget() {
    	//��ȡ��ǰ���
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String year = sdf.format(date);
        
    	List<EntertainAnnualInfoDTO> newlist= new ArrayList<EntertainAnnualInfoDTO>();
    	List<EntertainAnnualBudgetEntity> list= new ArrayList<EntertainAnnualBudgetEntity>();
    	
    	list = entertainAnnualBudgetDAO.wGetAnnualBudget(year);// ��õ���ȫ������
    	for(int i=0;i<list.size();i++){
    	EntertainAnnualInfoDTO aNewList = new EntertainAnnualInfoDTO(); // ���ձ�Ĵ洢����
        EntertainAnnualBudgetEntity aList = new EntertainAnnualBudgetEntity(); // �м��Ĵ洢����
    	String time=list.get(i).getTime(); //��õ�i���ı��ƴ���
    	aList=list.get(i); //��õ��������еĵ�i��
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
		return newlist; //�������ձ�
	}

    // web-��ʾԤ��ִ������б�
    @Transactional
	@Override
	public List<EntertainAnnualBudgetEntity> wGetNowAnnual() {
		//��ȡ��ǰ���
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String year = sdf.format(date);
		return entertainAnnualBudgetDAO.wGetAnnualBudget(year);
	}
 // web-��ʾԤ��ִ������б�
    @Transactional
	@Override
	public List<EntertainAnnualBudgetEntity> wGetNowAnnual1(String year) {
		return entertainAnnualBudgetDAO.wGetAnnualBudget(year);
	}

	// web-�����޸ĵ����Ԥ��ֵ
	@Transactional
	@Override
	public int wSetAdjust(String param) {
		String[] list = param.split(",");
		Date dt=new Date();
	    SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
	    String df = matter.format(dt);
	    
	    // ����޸ĵ����
	    String[] para = list[11].split(":"); 
	    String year = para[1];
	    
		for(int i=0;i<list.length-1;i++){
			if(!list[i].equals("")){
				String[] content=list[i].split(":");
				String time = entertainAnnualBudgetDAO.getTimeById(content[0],year);// ����id���Time��ֵ
				//����time��ֵ�жϸ���λ��
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

	// web-���ѡ����ݵ�Ԥ��ִ������б�
	public List<EntertainAnnualBudgetEntity> wGetSearchImplementation(String year) {
		return entertainAnnualBudgetDAO.wGetAnnualBudget(year);
	}
	
	// ���ݲ��Ż�ȡ�ò���ʣ��Ľ��
	@Transactional
	@Override
	public Object getLastSum(String department){
		//Ԥ��budget
		//ʣ��Ԥ��lastBudget
		//������usedBudget
		//ʹ����percent
		
		// ��ȡ��ǰ���
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String year = sdf.format(date);
        // �ò��ű������ʹ�õĽ�������Ϣ
        String syear = year+"%";
        List<Object[]> list = entertainApplyInfoDAO.getOneDepartmentEntertainSum(department,syear);
        Double mm=0.0d;//��ʹ�õĽ����
        for(int i=0;i<list.size();i++){
        	Object[] objects=(Object[])list.get(i);
			String sum = objects[3].toString();
			mm=mm+Double.parseDouble(sum);
        }
        
        
        if(department.equals("����")){
        	department="�ƻ�����";
        }
        else if(department.equals("������")){
        	department="������ȫ��";
        }
        else if(department.equals("�г���")){
        	department="�г�������";
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
        double budget =  Double.parseDouble(df.format(lastNum))*10000; // ��Ԥ��תΪdouble����
        double lastBudget = budget-usedBudget; // ʣ��Ԥ�㱣����λ��Ч����
        //int budget = //Ԥ��budget
        
        // ����ٷֱ�
        // ����һ����ֵ��ʽ������  
        NumberFormat numberFormat = NumberFormat.getInstance();  
        // ���þ�ȷ��С�����4λ  
        numberFormat.setMaximumFractionDigits(4);  
        String percent = numberFormat.format(usedBudget / budget * 100);  
  
        Map<String,String> jsonMap = new HashMap<String,String>();// ����map
        jsonMap.put("budget", Double.toString(budget));
        jsonMap.put("usedBudget", Double.toString(usedBudget));
        jsonMap.put("lastBudget", Double.toString(lastBudget));
        jsonMap.put("percent", percent+"%");
		return jsonMap;
	}
	
}
