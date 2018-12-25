package cn.com.hfga.manageimpl.entertain;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.entertain.EntertainApplyInfoDAO;
import cn.com.hfga.dao.entertain.EntertainRegisterInfoDAO;
import cn.com.hfga.dto.entertain.EntertainApplyInfoDTO;
//import cn.com.hfga.dto.entertain.EntertainApplyInfoDTO;
import cn.com.hfga.dto.entertain.EntertainRegisterInfoDTO;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;
import cn.com.hfga.entity.entertain.EntertainRegisterInfoEntity;
import cn.com.hfga.manager.entertain.EntertainRegisterInfoManage;

@Service("entertainRegisterInfoManageImpl")
public class EntertainRegisterInfoManageImpl implements EntertainRegisterInfoManage {

	@Autowired
	private EntertainRegisterInfoDAO entertainRegisterInfoDAO;
	
	@Autowired
	private EntertainApplyInfoDAO entertainApplyInfoDAO;
	
	@Autowired
	private EntertainApplyInfoManageImpl entertainApplyInfoManageImpl;

	@Autowired
	private EntertainInfoManageImpl entertainInfoManageImpl;
	
	// 事后-保存登记
	@Transactional
	@Override
	public int saveEntertainRegisterInfo(EntertainRegisterInfoDTO entertainRegisterInfoDTO) {
		String number = entertainRegisterInfoDTO.getNumber(); // 获取审批单号
		String remainingBudget = entertainApplyInfoDAO.getRemainingBudget(number);  // 获取数据库中的剩余预算值
		String invoiceSum = entertainRegisterInfoDTO.getInvoiceSum();
		//TODO 转换为Ddouble类型，需要进行测试是否正确，包括updateRemainingBudget方法参数
		double newRemaining =  Double.parseDouble(remainingBudget) - Double.parseDouble(invoiceSum);
		entertainApplyInfoDAO.updateRemainingBudget(number,Double.parseDouble(format2(newRemaining)));
		//根据number查询事前信息
		EntertainApplyInfoEntity enter = entertainApplyInfoDAO.applyDetail(number);
		//判断如果有酒水但没填价格告知提交失败
//		if("1".equals(enter.getIfWine())&&"".equals(entertainRegisterInfoDTO.getWineSum())){
//			return 0;
//		}
		//总人数：招待人数+陪同人数
		int pnum = Integer.parseInt(enter.getEntertainNum())+Integer.parseInt(enter.getAccompanyNum());
		//酒水总额
		double wineSum = 0;
		//有酒水
		if(entertainRegisterInfoDTO.getWineSum()!=null&&!"".equals(entertainRegisterInfoDTO.getWineSum())){
			wineSum = Double.valueOf(entertainRegisterInfoDTO.getWineSum());
		}
		//发票总额
		double invSum = Double.valueOf(invoiceSum);
		//总金额：酒水总额+发票总额
		double all = wineSum+invSum;
		String allSum = String.valueOf(all);
		//人均
		double pSum = all/pnum;
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.0");  
		String ps = df.format(pSum);
		entertainRegisterInfoDTO.setWineSum(String.valueOf(wineSum));
		if(entertainRegisterInfoDTO.getEnterSum()==null){
			entertainRegisterInfoDTO.setEnterSum(allSum);
		}
		if(entertainRegisterInfoDTO.getPersonSum()==null){
			entertainRegisterInfoDTO.setPersonSum(ps);
		}
		if(entertainRegisterInfoDTO.getWineSum()==null){
			entertainRegisterInfoDTO.setWineSum("0.0");
		}
		int flag = entertainRegisterInfoDAO.insert(entertainRegisterInfoDTO.getNumber(),
				entertainRegisterInfoDTO.getInvoiceDate(), entertainRegisterInfoDTO.getInvoiceContent(),
				entertainRegisterInfoDTO.getInvoiceSum(), entertainRegisterInfoDTO.getInvoiceNum(),
				entertainRegisterInfoDTO.getInvoiceUnit(), entertainRegisterInfoDTO.getStatus(),
				entertainRegisterInfoDTO.getRegisterMan(), entertainRegisterInfoDTO.getRemark(),
				entertainRegisterInfoDTO.getInvoiceNumber(),entertainRegisterInfoDTO.getWineSum(),
				entertainRegisterInfoDTO.getEnterSum(),entertainRegisterInfoDTO.getPersonSum());
		if (flag==1){
			
			
			//String number = entertainRegisterInfoDTO.getNumber();
			int test = entertainApplyInfoDAO.plus(number);//插入成功后+1
			System.out.println(test);
		}
		return flag;
	}
	
	//将double转换为小数点后两位
	private double formatDouble(double value){
		long longValue = Math.round(value);
		double res = longValue*100/100.0;
		return res;
	}

	// 事后-获取所有的登记信息
	@Transactional
	@Override
	public List<EntertainRegisterInfoEntity> getAllRegister() {
		return entertainRegisterInfoDAO.getAllRegister();
	}

	// 事后-获取所有待审核信息
	@Transactional
	@Override
	public List<EntertainRegisterInfoEntity> getAllReady() {
		return entertainRegisterInfoDAO.getAllReady();
	}

	// 事后-根据ID改变事后表状态
	@Transactional
	@Override
	public int updateStatus(String Status, String applyId) {
		int flag = entertainRegisterInfoDAO.updateStatus(Status, applyId);
		String num = entertainRegisterInfoDAO.getNumber(applyId);
		entertainApplyInfoDAO.updateStatusByN(num);//恢复事前表状态为通过
		return flag;
	}

	// 事后-取消审核-根据Number改变事后表状态
	@Transactional
	@Override
	public int updateStatusByN(String Status, String number) {
		int flag = entertainRegisterInfoDAO.updateStatusByN(Status, number);//将事后表状态改为已完成
		entertainApplyInfoDAO.updateStatusByN(number);//恢复事前表状态为通过
		return flag;
	}
	// 审核-根据Number改变事后表状态
	@Transactional
	@Override
	public int updateStatusByN1(String Status, String number) {
		Date dt = new Date();
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
		String date = matter.format(dt);
		int flag = entertainRegisterInfoDAO.updateStatusByN(Status, number);//将事后表状态改为已完成
		entertainApplyInfoDAO.updateStatusByN2(number,date);//恢复事前表状态为已完成
		return flag;
	}
		
	// 事后-修改登记信息 √
	@Transactional
	@Override
	public int modifyRegister(EntertainRegisterInfoDTO entertainRegisterInfoDTO) {
		String Number = entertainRegisterInfoDTO.getNumber(); // 获得审批单号
		String per = entertainRegisterInfoDTO.getPerInvoiceSum(); //获得修改前的发票金额
		String now = entertainRegisterInfoDTO.getInvoiceSum(); // 获得修改后的发票金额
		Double difference = Double.parseDouble(per) - Double.parseDouble(now);  // 发票金额修改前后的差值
		String remainingBudget = entertainApplyInfoDAO.getRemainingBudget(Number);  // 获取数据库中的剩余预算值
		Double newRemainingBudget = Double.parseDouble(remainingBudget)+difference;
		entertainApplyInfoDAO.updateRemainingBudget(Number,Double.parseDouble(format2(newRemainingBudget)));
		//根据number查询事前信息
		EntertainApplyInfoEntity enter = entertainApplyInfoDAO.applyDetail(Number);
		//总人数：招待人数+陪同人数
		int pnum = Integer.parseInt(enter.getEntertainNum())+Integer.parseInt(enter.getAccompanyNum());
		//酒水总额
		double wineSum = 0;
		//有酒水
		if(entertainRegisterInfoDTO.getWineSum()!=null&&!"".equals(entertainRegisterInfoDTO.getWineSum())){
			wineSum = Double.valueOf(entertainRegisterInfoDTO.getWineSum());
		}
		//发票总额
		double invSum = Double.valueOf(now);
		//总金额：酒水总额+发票总额
		double all = wineSum+invSum;
		String allSum = String.valueOf(all);
		//人均
		double pSum = all/pnum;
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.0");  
		String ps = df.format(pSum);
		if(entertainRegisterInfoDTO.getEnterSum()==null){
			entertainRegisterInfoDTO.setEnterSum(allSum);
		}
		if(entertainRegisterInfoDTO.getPersonSum()==null){
			entertainRegisterInfoDTO.setPersonSum(ps);
		}
		if(entertainRegisterInfoDTO.getWineSum()==null){
			entertainRegisterInfoDTO.setWineSum("0.0");
		}
		int flag = entertainRegisterInfoDAO.modifyRegister(entertainRegisterInfoDTO.getID(),
				entertainRegisterInfoDTO.getNumber(), entertainRegisterInfoDTO.getInvoiceDate(),
				entertainRegisterInfoDTO.getInvoiceContent(), entertainRegisterInfoDTO.getInvoiceSum(),
				entertainRegisterInfoDTO.getInvoiceNum(),entertainRegisterInfoDTO.getInvoiceUnit(),
				entertainRegisterInfoDTO.getStatus(), entertainRegisterInfoDTO.getRegisterMan(),
				entertainRegisterInfoDTO.getRemark(),entertainRegisterInfoDTO.getInvoiceNumber(),
				entertainRegisterInfoDTO.getWineSum(),entertainRegisterInfoDTO.getEnterSum(),
				entertainRegisterInfoDTO.getPersonSum());

		return flag;
	}
	
	public  String format2(double value) {

		 DecimalFormat df = new DecimalFormat("0.00");
		 df.setRoundingMode(RoundingMode.HALF_UP);
		 return df.format(value);
	}

	// 事后-获取个人申请信息 √
	@Transactional
	@Override
	public List<EntertainRegisterInfoEntity> getPersonalRegister(String registerman) {
		List<EntertainRegisterInfoEntity> list1 = new ArrayList<>();
		List<EntertainRegisterInfoEntity> list = entertainRegisterInfoDAO.getPersonalRegister(registerman);
		for(EntertainRegisterInfoEntity en : list){
			if(en.getWineSum()!=null&&!"".equals(en.getWineSum())){
				en.setIfWine("1");
			}else{
				en.setIfWine("0");
			}
			EntertainApplyInfoEntity enty = entertainApplyInfoDAO.applyDetail(en.getNumber());
			if(enty!=null){
				en.setEntertainNum(enty.getEntertainNum());
				en.setAccompanyNum(enty.getAccompanyNum());
			}
			list1.add(en);
		}
		return list1;
	}
	
	// web-根据审批单号查询相关信息
	@Transactional
	@Override
	public List<EntertainRegisterInfoEntity> registerDetail(String number) {
		return entertainRegisterInfoDAO.registerDetail(number);
	}
	
	// 事后-录入凭证号 √
	@Transactional
	@Override
	public int insertVoucherNum(String iD, String paidTime, String voucherNum) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			paidTime = sdf.format(paidTime);
			
			//entertainRegisterInfoDAO.updateStatus("通过", iD);
			int flag = entertainRegisterInfoDAO.insertVoucherNum(iD,paidTime,voucherNum);
			//根据ID得到事后表的Number，根据Number查找对应的事前表比对RegisterNum，都完成将事前表状态修改为已完成
			if(flag==1){String number = entertainRegisterInfoDAO.getNumber(iD);//根据ID获得事后表的审批单号（Number）
			List<EntertainRegisterInfoEntity> register = entertainInfoManageImpl.getSearchRegisterInfo(number); //取出对应的已完成的事后表单
			int registerNum = entertainApplyInfoDAO.getRegisterNum(number);//取出审批单号对应的事前表的RegisterNum值
			if(register.size()==registerNum){ 
			    String id = entertainApplyInfoDAO.getID(number);//获得需要修改状态事前表的ID
				entertainApplyInfoManageImpl.updateStatus("已完成", id);}
			}
		    return flag;
	}

	// web-首页-未审核招待明细-分页显示-获取列表
	@Transactional
	@Override
	public List<EntertainRegisterInfoEntity> wGetAllUnapprovedRegister(int start, int number) {
		return entertainRegisterInfoDAO.wGetAllUnapprovedRegister(start,number);
	}

	// web-首页-未审核招待明细-分页显示-获得总条数
	@Transactional
	@Override
	public int getAllUnapprovedRegisterCount() {
		return entertainRegisterInfoDAO.getAllUnapprovedRegisterCount();
	}

	// 事后-删除登记信息---
	@Transactional
	@Override
	public int deleteRegister(String iD , String invoiceSum) {
			String number = entertainRegisterInfoDAO.getNumber(iD);
			int flag = entertainRegisterInfoDAO.deleteRegister(iD);
			if(flag==1){ 
			String remaining = entertainApplyInfoDAO.getRemainingBudget(number);
			Double newremaining = Double.parseDouble(remaining)+Double.parseDouble(invoiceSum);
			entertainApplyInfoDAO.updateRemainingBudget(number, Double.parseDouble(format2(newremaining)));
			// 事前表的剩余预算恢复
			entertainApplyInfoDAO.minus(number);//关联的事前表RegisterNum-1
			}
		return flag;
	}

	// 事后-财务审核未通过 √
		// 修改status
	@Transactional
	@Override
	public int updateStatusUnapproved(String number) {
		int i=0;
		if(number.startsWith("ZD")){
			//安卓传的是number
			i=entertainRegisterInfoDAO.updateStatus("未通过", number);
		}else{
			//ios传的是id
			//反正我也是服了
			i=entertainRegisterInfoDAO.updateStatusID("未通过", number);
		}
//		int j= entertainApplyInfoDAO.updateStatus2("未通过", number);
		return i/*&j*/;
	}

	// 事后-录入凭证号、报销时间，审核通过-根据Number（权限→刘静）
	@Transactional
	@Override
	public int insertAllVoucherNum(String number, String paidTime, String voucherNum) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		paidTime = sdf.format(paidTime);
			entertainRegisterInfoDAO.updateStatusByN("通过", number);
			int flag = entertainRegisterInfoDAO.insertVoucherNumByN(number,paidTime,voucherNum);
			if(flag!=0){
				entertainApplyInfoDAO.setPaidTime(number,paidTime);// 将报销时间写入事前数据库
			List<EntertainRegisterInfoEntity> register = entertainInfoManageImpl.getSearchRegisterInfo(number); //取出对应的已完成的事后表单
			int registerNum = entertainApplyInfoDAO.getRegisterNum(number);//取出审批单号对应的事前表的RegisterNum值
			if(register.size()==registerNum){ 
			    String id = entertainApplyInfoDAO.getID(number);//获得需要修改状态事前表的ID
				entertainApplyInfoManageImpl.updateStatus("已完成", id);}
			}
		    return flag;
	}

	@Override
	public List<EntertainRegisterInfoEntity> get(String number) {
		// TODO Auto-generated method stub
		return entertainRegisterInfoDAO.get(number);
	}

}
