package cn.com.hfga.manageimpl.entertain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.entertain.EntertainApplyInfoDAO;
import cn.com.hfga.dao.entertain.EntertainRegisterInfoDAO;
import cn.com.hfga.dao.user.UserDAO;
//import cn.com.hfga.dao.user.DepartmentDAO;
import cn.com.hfga.dto.entertain.EntertainApplyInfoDTO;
import cn.com.hfga.dto.entertain.EntertainInfoDTO;
import cn.com.hfga.dto.entertain.EntertainSearchInfoDTO;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;
import cn.com.hfga.entity.entertain.EntertainRegisterInfoEntity;
import cn.com.hfga.entity.user.UserEntity;
import cn.com.hfga.manager.entertain.EntertainApplyInfoManage;

@Service("entertainApplyInfoManageImpl")
public class EntertainApplyInfoManageImpl implements EntertainApplyInfoManage {

	@Autowired
	private EntertainApplyInfoDAO entertainApplyInfoDAO;
	@Autowired
	private EntertainRegisterInfoDAO entertainRegisterInfoDAO;
	@Autowired
	private UserDAO userDAO;

	// 生成审批单号 √
	@Transactional
	@Override
	public String getNum(String num) { // num为数据库表中查询出的最大编号

		String number = ""; // 定义审批单编号变量，初始为空

		// 截取当前日期中的年份后两位
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		String year = str.substring(2, 4);

		// 如果表中没有记录，给出初始编号
		if (num.equals("")||num==null) {
			number = "ZD" + year + "0001";
		} else {
			String n = num.substring(4); // 截取当前表中最大编号的后四位流水号
			String s2 = ""; // 在当前表中最大流水号的基础上+1
			int lg = Integer.parseInt(n); // 将当前表中最大流水号转为整数

			// 对流水号结尾的四位数字进行判断，分情况进行增加
			if (lg > 0 && lg < 9) {
				s2 = "000" + (lg + 1);
			} else if (lg >= 9 && lg < 99) {
				s2 = "00" + (lg + 1);
			} else if (lg >= 99 && lg < 999) {
				s2 = "0" + (lg + 1);
			} else if (lg >= 999 && lg < 9999) {
				s2 = "" + (lg + 1);
			}
			number = "ZD" + year + s2;
		}
		return number;
	}

	// 获取事前审批数据库表中最后一条数据的审批单号 √
	public String getSqlLast() {
		String num = "";
		int ob=entertainApplyInfoDAO.getApplyObject();
		if(ob==0){
			return num;
		}
		else{
		return entertainApplyInfoDAO.getSqlLast();}
	}

	// 保存申请 √
	@Transactional
	@Override
	public int saveEntertainApplyInfo(EntertainApplyInfoDTO entertainApplyInfoDTO,HttpServletRequest request) throws Exception {

		String date = entertainApplyInfoDTO.getApplyTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String newDate = sdf.format(date);
		Date date1 = sdf.parse(date);
		String newDate = sdf.format(date1);
		entertainApplyInfoDTO.setApplyTime(newDate);
		if(entertainApplyInfoDTO.getIfWine()==null||"(null)".equals(entertainApplyInfoDTO.getIfWine())||"null".equals(entertainApplyInfoDTO.getIfWine())||"".equals(entertainApplyInfoDTO.getIfWine())){
			entertainApplyInfoDTO.setIfWine("0");
		}
		if(entertainApplyInfoDTO.getWineType()==null||"(null)".equals(entertainApplyInfoDTO.getWineType())||"null".equals(entertainApplyInfoDTO.getWineType())||"".equals(entertainApplyInfoDTO.getWineType())){
			entertainApplyInfoDTO.setWineType("");
		}
		if(entertainApplyInfoDTO.getWineNum()==null||"(null)".equals(entertainApplyInfoDTO.getWineNum())||"null".equals(entertainApplyInfoDTO.getWineNum())||"".equals(entertainApplyInfoDTO.getWineNum())){
			entertainApplyInfoDTO.setWineNum("0");
		}
		if(entertainApplyInfoDTO.getIfBefore()==null||"(null)".equals(entertainApplyInfoDTO.getIfBefore())||"null".equals(entertainApplyInfoDTO.getIfBefore())||"".equals(entertainApplyInfoDTO.getIfBefore())){
			entertainApplyInfoDTO.setIfBefore("0");
		}
		if(entertainApplyInfoDTO.getBeforeDate()==null||"(null)".equals(entertainApplyInfoDTO.getBeforeDate())||"null".equals(entertainApplyInfoDTO.getBeforeDate())||"".equals(entertainApplyInfoDTO.getBeforeDate())){
			entertainApplyInfoDTO.setBeforeDate("");
		}
		//判断补录日期和申请日期是否相同，如果相同还原
		if("1".equals(entertainApplyInfoDTO.getIfBefore())){
			String beforeDate = entertainApplyInfoDTO.getBeforeDate();
			Date date2 = sdf.parse(beforeDate);
			String newDate2 = sdf.format(date2);
			if(newDate2.equals(newDate)){
				entertainApplyInfoDTO.setIfBefore("0");
				entertainApplyInfoDTO.setBeforeDate("");
			}
			
			//判断是否是公司领导，如果是公司领导则实际日期就是申请日期
			List<UserEntity> searchUserByCode = userDAO.searchUserByCode(entertainApplyInfoDTO.getManager());
			if(searchUserByCode!=null&&!searchUserByCode.isEmpty()){
				UserEntity user = searchUserByCode.get(0);
				//enabled为判断是否需要将实际日期设置为申请日期
				String enabled = user.getEnabled();
				if("1".equals(enabled)){
					entertainApplyInfoDTO.setApplyTime(entertainApplyInfoDTO.getBeforeDate());
					entertainApplyInfoDTO.setIfBefore("0");
					entertainApplyInfoDTO.setBeforeDate("");
				}
			}
		}
		
		int flag = entertainApplyInfoDAO.insert(getNum(getSqlLast()), entertainApplyInfoDTO.getDepartment(),
				entertainApplyInfoDTO.getApplyTime(), entertainApplyInfoDTO.getEntertainObject(),
				entertainApplyInfoDTO.getEntertainReason(), entertainApplyInfoDTO.getEntertainNum(),
				entertainApplyInfoDTO.getAccompanyNum(), entertainApplyInfoDTO.getPerBudget(),
				entertainApplyInfoDTO.getMasterBudget(), entertainApplyInfoDTO.getEntertainCategory(),
				entertainApplyInfoDTO.getManager(), entertainApplyInfoDTO.getApprover(),
				entertainApplyInfoDTO.getStatus(), entertainApplyInfoDTO.getRemark(),
				entertainApplyInfoDTO.getIfWine(),entertainApplyInfoDTO.getWineType(),
				entertainApplyInfoDTO.getWineNum(),entertainApplyInfoDTO.getWineOther(),
				entertainApplyInfoDTO.getIfBefore(),entertainApplyInfoDTO.getBeforeDate());
		return flag;
	}

	// 删除申请√
	@Transactional
	@Override
	public int delete(String id) {
		return entertainApplyInfoDAO.delete(id);
	}

	// 查询申请
	@Transactional
	@Override
	public List<EntertainApplyInfoEntity> getSearchInfo(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		String department = entertainSearchInfoDTO.getDepartment();// 获得部门名称
		entertainSearchInfoDTO.setManager("%" + entertainSearchInfoDTO.getManager() + "%");
		entertainSearchInfoDTO.setEntertainObject("%" + entertainSearchInfoDTO.getEntertainObject()+ "%");
	//	entertainInfoDTO.setNumber();
	//	Register newRegister = Register();
		if (department.equals("全部")) {
			return entertainApplyInfoDAO.getSearchInfo1(entertainSearchInfoDTO.getEntertainObject(),
					entertainSearchInfoDTO.getStartTime(), entertainSearchInfoDTO.getEndTime(),
					entertainSearchInfoDTO.getManager());
		} else {
			return entertainApplyInfoDAO.getSearchInfo0(department, entertainSearchInfoDTO.getEntertainObject(),
					entertainSearchInfoDTO.getStartTime(), entertainSearchInfoDTO.getEndTime(),
					entertainSearchInfoDTO.getManager());
		}
	}

	// 获取所有的申请信息 √
	@Transactional
	@Override
	public List<EntertainApplyInfoEntity> getAll() {
		return entertainApplyInfoDAO.getAll();
	}
	
	// 获取所有的未提交申请信息 √
	@Transactional
	@Override
	public List<EntertainApplyInfoEntity> getUnSubRecord(int start,int number){
		
		List<EntertainApplyInfoEntity> newls = new ArrayList<>();
		List<EntertainApplyInfoEntity> list = entertainApplyInfoDAO.getUnSubRecord(start, number);
		for(EntertainApplyInfoEntity en:list){
			String wine = en.getWineType();
			if(wine!=null&&!"".equals(wine)&&wine.startsWith("[")){
				try {
					JSONArray json = new JSONArray(wine);
					int iSize = json.length();
					if(iSize!=0){
						String newPass = "";
						for(int i=0;i<iSize;i++){
							JSONObject obj = json.getJSONObject(i);
							newPass += obj.get("name1")+""+obj.get("value")+"瓶  ";
						}
						en.setWineType(newPass);
					}else{
						en.setWineType("");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			newls.add(en);
		}
		
		return newls;
	}
	
	// 获取所有的未提交申请信息 √
	@Transactional
	@Override
	public int getUnSubRecordCount(){
		return entertainApplyInfoDAO.getUnSubRecordCount();
	}

	// 获取需要审批的信息（根据不同审批人） √
	@Transactional
	@Override
	public List<EntertainApplyInfoEntity> getByApprove(String approver) {
		return entertainApplyInfoDAO.getByApprove(approver);
	}

	// 审批 √
	@Transactional
	@Override
	public int updateApprove(String Status, String Approver, String Number, String Time) {
		
		return entertainApplyInfoDAO.updateApprove(Status, Approver, Number, Time);
	}

	// 获取个人申请信息 √
	@Transactional
	@Override
	public List<EntertainApplyInfoEntity> getPersonal(String Manager) {
		List<EntertainApplyInfoEntity> list0 = new ArrayList<>();
		List<EntertainApplyInfoEntity> list = entertainApplyInfoDAO.getPersonal(Manager);
		for(EntertainApplyInfoEntity en:list){
			List<EntertainRegisterInfoEntity> list1 = entertainRegisterInfoDAO.get(en.getNumber());
			if(list1!=null&&!list1.isEmpty()){
				en.setWineSum(list1.get(0).getWineSum());
				en.setEnterSum(list1.get(0).getEnterSum());
				en.setPersonSum(list1.get(0).getPersonSum());
			}
			list0.add(en);
		}
		return list0;
	}

	// 更改招待申请 √
	@Transactional
	@Override
	public int modifyOne(EntertainApplyInfoDTO entertainApplyInfoDTO) throws Exception {
		
		String date = entertainApplyInfoDTO.getApplyTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String newDate = sdf.format(date);
		Date date1 = sdf.parse(date);
		String newDate = sdf.format(date1);
		entertainApplyInfoDTO.setApplyTime(newDate);
		if(entertainApplyInfoDTO.getIfWine()==null||"0".equals(entertainApplyInfoDTO.getIfWine())){
			entertainApplyInfoDTO.setIfWine("0");
			entertainApplyInfoDTO.setWineType("[]");
		}
		if(entertainApplyInfoDTO.getIfBefore()==null||"0".equals(entertainApplyInfoDTO.getIfBefore())){
			entertainApplyInfoDTO.setIfBefore("0");
			entertainApplyInfoDTO.setBeforeDate("");
		}
		//判断补录日期和申请日期是否相同，如果相同还原
		if("1".equals(entertainApplyInfoDTO.getIfBefore())){
			String beforeDate = entertainApplyInfoDTO.getBeforeDate();
			Date date2 = sdf.parse(beforeDate);
			String newDate2 = sdf.format(date2);
			if(newDate2.equals(newDate)){
				entertainApplyInfoDTO.setIfBefore("0");
				entertainApplyInfoDTO.setBeforeDate("");
			}
			
			//判断是否是公司领导，如果是公司领导则实际日期就是申请日期
			List<UserEntity> searchUserByCode = userDAO.searchUserByCode(entertainApplyInfoDTO.getManager());
			if(searchUserByCode!=null&&!searchUserByCode.isEmpty()){
				UserEntity user = searchUserByCode.get(0);
				//enabled为判断是否需要将实际日期设置为申请日期
				String enabled = user.getEnabled();
				if("1".equals(enabled)){
					entertainApplyInfoDTO.setApplyTime(entertainApplyInfoDTO.getBeforeDate());
					entertainApplyInfoDTO.setIfBefore("0");
					entertainApplyInfoDTO.setBeforeDate("");
				}
			}
		}
		int flag = entertainApplyInfoDAO.modifyOne(entertainApplyInfoDTO.getID(), entertainApplyInfoDTO.getNumber(),
				entertainApplyInfoDTO.getDepartment(), entertainApplyInfoDTO.getApplyTime(),
				entertainApplyInfoDTO.getEntertainObject(), entertainApplyInfoDTO.getEntertainReason(),
				entertainApplyInfoDTO.getEntertainNum(), entertainApplyInfoDTO.getAccompanyNum(),
				entertainApplyInfoDTO.getPerBudget(), entertainApplyInfoDTO.getMasterBudget(),
				entertainApplyInfoDTO.getEntertainCategory(), entertainApplyInfoDTO.getManager(),
				entertainApplyInfoDTO.getApprover(), entertainApplyInfoDTO.getStatus(),
				entertainApplyInfoDTO.getRemark(),
				entertainApplyInfoDTO.getIfWine(),entertainApplyInfoDTO.getWineType(),
				entertainApplyInfoDTO.getWineNum(),entertainApplyInfoDTO.getWineOther(),
				entertainApplyInfoDTO.getIfBefore(),entertainApplyInfoDTO.getBeforeDate());
		return flag;
	}

	// 事前-更改状态 √
	@Transactional
	@Override
	public int updateStatus(String Status, String ID) {
		return entertainApplyInfoDAO.updateStatus(Status, ID);
	}
	
	// web-根据审批单号查询相关信息
	@Transactional
	@Override
	public EntertainApplyInfoEntity applyDetail(String number) {
		return entertainApplyInfoDAO.applyDetail(number);
	}

	// web-已审核招待明细-查询	
	@Transactional
	@Override
	public List<EntertainApplyInfoEntity> searchApply(int start, int number,
			EntertainSearchInfoDTO entertainSearchInfoDTO) {
		return entertainApplyInfoDAO.searchApply(start, number,entertainSearchInfoDTO.getDepartment(),
				entertainSearchInfoDTO.getManager(),entertainSearchInfoDTO.getStartTime(),
				entertainSearchInfoDTO.getEndTime(),entertainSearchInfoDTO.getEntertainObject());
	}

	// web-已审核招待明细-查询-获得符合查询条件的条目数
	@Transactional
	@Override
	public int getAllCompletedApplyCount(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		return entertainApplyInfoDAO.getAllCompletedApplyCount(entertainSearchInfoDTO);
	}

	// web-已审核招待明细-查询-获取总数
	@Transactional
	@Override
	public int getAllSearchCount(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		String department = entertainSearchInfoDTO.getDepartment();
		String entertainObject = entertainSearchInfoDTO.getEntertainObject();
		String sentertainObject = "%"+entertainObject+"%";
		int count;
		if (department.equals("全部") && entertainObject.equals("全部")){
			count = entertainApplyInfoDAO.getAllSearchCount11(entertainSearchInfoDTO.getManager(),
					entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime(),sentertainObject);
		}
		else if (!department.equals("全部") && entertainObject.equals("全部")){
			count = entertainApplyInfoDAO.getAllSearchCount01(entertainSearchInfoDTO.getDepartment(),entertainSearchInfoDTO.getManager(),
					entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime());
		}
		else if (department.equals("全部") && !entertainObject.equals("全部")){
			count = entertainApplyInfoDAO.getAllSearchCount10(entertainSearchInfoDTO.getManager(),
					entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime(),sentertainObject);
		}
		else {
			count = entertainApplyInfoDAO.getAllSearchCount00(entertainSearchInfoDTO.getDepartment(),entertainSearchInfoDTO.getManager(),
					entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime(),sentertainObject);
		}
		return count;
	}

	// 事前-获取个人通过的事前信息
	public List<EntertainApplyInfoEntity> getPassApply(String manager) {
		List<EntertainApplyInfoEntity> list0 = new ArrayList<>();
		List<EntertainApplyInfoEntity> list = entertainApplyInfoDAO.getPassApply(manager);
		for(EntertainApplyInfoEntity en:list){
			List<EntertainRegisterInfoEntity> list1 = entertainRegisterInfoDAO.get(en.getNumber());
			if(list1!=null&&!list1.isEmpty()){
				en.setWineSum(list1.get(0).getWineSum());
				en.setEnterSum(list1.get(0).getEnterSum());
				en.setPersonSum(list1.get(0).getPersonSum());
			}
			list0.add(en);
		}
		return list0;
	}

	// 事前-获取所有通过的事前信息
	public List<EntertainApplyInfoEntity> getAllPassApply() {
		return entertainApplyInfoDAO.getAllPassApply();
	}

	// web-已审核招待明细-查询-获取列表信息
		@Transactional
		@Override
		public List<EntertainApplyInfoEntity> wGetSearchApproved(EntertainSearchInfoDTO entertainSearchInfoDTO) {
			List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
			String department = entertainSearchInfoDTO.getDepartment();
			String entertainObject = entertainSearchInfoDTO.getEntertainObject();
			if(!entertainObject.equals("全部"))
			{entertainObject = "%"+entertainObject+"%";}
			String m = entertainSearchInfoDTO.getManager();
			String manager = "%"+m+"%";
			if (department.equals("全部") && entertainObject.equals("全部")){
				list = entertainApplyInfoDAO.wGetSearchApproved11(manager,
						entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime());
			}
			else if (!department.equals("全部") && entertainObject.equals("全部")){
				list = entertainApplyInfoDAO.wGetSearchApproved01(department,manager,
						entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime());
			}
			else if (department.equals("全部") && !entertainObject.equals("全部")){
				list = entertainApplyInfoDAO.wGetSearchApproved10(manager,
						entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime(),entertainObject);
			}
			else {
				list = entertainApplyInfoDAO.wGetSearchApproved00(entertainSearchInfoDTO.getDepartment(),manager,
					entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime(),entertainObject);
			}
			return list;
		}
		// web-已审核招待明细-查询-获取列表信息
		@Transactional
		public List<EntertainApplyInfoEntity> wGetSearchApprovedByPage(EntertainSearchInfoDTO entertainSearchInfoDTO,int start,int number) {
			List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
			List<EntertainApplyInfoEntity> list1 = new ArrayList<EntertainApplyInfoEntity>();
			String department = entertainSearchInfoDTO.getDepartment();
			String entertainObject = entertainSearchInfoDTO.getEntertainObject();
			if(!entertainObject.equals("全部"))
			{entertainObject = "%"+entertainObject+"%";}
			String m = entertainSearchInfoDTO.getManager();
			String manager = "%"+m+"%";
			String invoiceNumber1 = entertainSearchInfoDTO.getInvoiceNumber();
			String invoiceSum1 = entertainSearchInfoDTO.getInvoiceSum();
			if (department.equals("全部") && entertainObject.equals("全部")){
				list = entertainApplyInfoDAO.wGetSearchApproved11ByPage(manager,
						entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime(),start,number);
			}
			else if (!department.equals("全部") && entertainObject.equals("全部")){
				list = entertainApplyInfoDAO.wGetSearchApproved01ByPage(department,manager,
						entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime(),start,number);
			}
			else if (department.equals("全部") && !entertainObject.equals("全部")){
				list = entertainApplyInfoDAO.wGetSearchApproved10ByPage(manager,
						entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime(),entertainObject,start,number);
			}
			else {
				list = entertainApplyInfoDAO.wGetSearchApproved00ByPage(entertainSearchInfoDTO.getDepartment(),manager,
					entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime(),entertainObject,start,number);
			}
			if(list.size()>0){
				for(EntertainApplyInfoEntity app:list){
					String invoiceNumber = "";
					double invoiceSum = 0;
					List<EntertainRegisterInfoEntity> reg = entertainRegisterInfoDAO.get(app.getNumber());
//					boolean flag = true;
					//把事后的发票号用逗号拼接加到事前数据中
					for(int i=0;i<reg.size();i++){
						String numStr = reg.get(i).getInvoiceNumber();
						String sum = reg.get(i).getInvoiceSum();
						if(numStr!=null&&!"".equals(numStr)){
							invoiceNumber += numStr;
							if(i!=reg.size()-1){
								invoiceNumber += ",";
							}
						}
						if(sum!=null&&!"".equals(sum)){
							invoiceSum += Double.parseDouble(sum);
						}
					}
					app.setInvoiceNumber(invoiceNumber);
					app.setInvoiceSum(String.valueOf(invoiceSum));
					//根据发票号和发票金额查询
					if("".equals(invoiceNumber1)&&"".equals(invoiceSum1)){
						list1.add(app);
					}else if(!"".equals(invoiceNumber1)&&"".equals(invoiceSum1)){
						if(invoiceNumber1.equals(app.getInvoiceNumber())){
							list1.add(app);
						}
					}else if("".equals(invoiceNumber1)&&!"".equals(invoiceSum1)){
						if(Double.parseDouble(invoiceSum1)==Double.parseDouble(app.getInvoiceSum())){
							list1.add(app);
						}
					}else if(!"".equals(invoiceNumber1)&&!"".equals(invoiceSum1)){
						if(invoiceNumber1.equals(app.getInvoiceNumber())&&Double.parseDouble(invoiceSum1)==Double.parseDouble(app.getInvoiceSum())){
							list1.add(app);
						}
					}
				}
			}
			return list1;
		}
		@Transactional
		public int wGetSearchApprovedCount(EntertainSearchInfoDTO entertainSearchInfoDTO) {
			List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
			String department = entertainSearchInfoDTO.getDepartment();
			String entertainObject = entertainSearchInfoDTO.getEntertainObject();
			if(!entertainObject.equals("全部"))
			{entertainObject = "%"+entertainObject+"%";}
			String m = entertainSearchInfoDTO.getManager();
			String manager = "%"+m+"%";
			if (department.equals("全部") && entertainObject.equals("全部")){
				return entertainApplyInfoDAO.wGetSearchApproved11Count(manager,
						entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime());
			}
			else if (!department.equals("全部") && entertainObject.equals("全部")){
				return entertainApplyInfoDAO.wGetSearchApproved01Count(department,manager,
						entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime());
			}
			else if (department.equals("全部") && !entertainObject.equals("全部")){
				return entertainApplyInfoDAO.wGetSearchApproved10Count(manager,
						entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime(),entertainObject);
			}
			else {
				return entertainApplyInfoDAO.wGetSearchApproved00Count(entertainSearchInfoDTO.getDepartment(),manager,
					entertainSearchInfoDTO.getStartTime(),entertainSearchInfoDTO.getEndTime(),entertainObject);
			}
		}
		@Override
		public List<EntertainApplyInfoEntity> get(String Number) {
			// TODO Auto-generated method stub
			return entertainApplyInfoDAO.get(Number);
		}

		// 事前-审批否决 √
		@Transactional
		@Override
		public int updateStatusDeny(String string, String applyId, String time) {
			return entertainApplyInfoDAO.updateStatus(string, applyId, time);
		}
	}

