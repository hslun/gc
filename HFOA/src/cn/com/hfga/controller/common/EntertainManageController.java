package cn.com.hfga.controller.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hfga.dto.entertain.EntertainAnnualInfoDTO;
import cn.com.hfga.dto.entertain.EntertainApplyInfoDTO;
import cn.com.hfga.dto.entertain.EntertainRegisterInfoDTO;
import cn.com.hfga.dto.entertain.EntertainSearchInfoDTO;
import cn.com.hfga.dto.entertain.EntertainSumInfoDTO;
import cn.com.hfga.entity.entertain.EntertainDepartmentEntity;
import cn.com.hfga.entity.entertain.EntertainAnnualBudgetEntity;
import cn.com.hfga.entity.entertain.EntertainApplyInfoEntity;
import cn.com.hfga.entity.entertain.EntertainInfoEntity;
import cn.com.hfga.entity.entertain.EntertainObjectTypeEntity;
import cn.com.hfga.entity.entertain.EntertainInvoiceUnitEntity;
import cn.com.hfga.entity.entertain.EntertainRegisterInfoEntity;
import cn.com.hfga.entity.user.UserEntity;
import cn.com.hfga.manageimpl.entertain.EntertainDepartmentManageImpl;
import cn.com.hfga.manageimpl.entertain.EntertainAnnualBudgetManageImpl;
//import cn.com.hfga.entity.user.DepartmentEntity;
import cn.com.hfga.manageimpl.entertain.EntertainApplyInfoManageImpl;
import cn.com.hfga.manageimpl.entertain.EntertainInfoManageImpl;
import cn.com.hfga.manageimpl.entertain.EntertainObjectTypeManageImpl;
import cn.com.hfga.manageimpl.entertain.EntertainInvoiceUnitManageImpl;
import cn.com.hfga.manageimpl.entertain.EntertainRegisterInfoManageImpl;
import cn.com.hfga.push.android.AndroidPushEntity;
import cn.com.hfga.push.android.AndroidPushUtil;
import cn.com.hfga.push.ios.IOSPushEntity;
import cn.com.hfga.push.ios.IOSPushUtil;
import cn.com.hfga.util.CommonUtil;

@Controller
public class EntertainManageController {
	
	@Autowired
	private EntertainApplyInfoManageImpl entertainApplyInfoManageImpl;

	@Autowired
	private EntertainRegisterInfoManageImpl entertainRegisterInfoManageImpl;

	@Autowired
	private EntertainInfoManageImpl entertainInfoManageImpl;

	@Autowired
	private EntertainObjectTypeManageImpl entertainObjectTypeManageImpl;

    @Autowired
	private EntertainInvoiceUnitManageImpl entertainInvoiceUnitManageImpl;
	
	@Autowired
	private EntertainDepartmentManageImpl entertainDepartmentManageImpl;
	
	@Autowired
	private EntertainAnnualBudgetManageImpl entertainAnnualBudgetManageImpl;
	
	@Autowired
	private AndroidPushUtil androidPushUtil;
	
	@Autowired
	private IOSPushUtil iOSPushUtil;
	
	// ���Ա�����ɷ���
	@RequestMapping(value = "/entertain/testnumber")
	@ResponseBody
	public Object testnumber(String num) {
		return entertainApplyInfoManageImpl.getNum(num);
	}

	// ���Ի��List�ķ���
	@RequestMapping(value = "/entertain/testList")
	@ResponseBody
	public Object testList(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		return entertainInfoManageImpl.testList(entertainSearchInfoDTO);
	}

	
	/****************************************** һ���ɰ��ķָ��� *********************************************/

	// ��ѯһ���������ϸ���ȫ����Ϣ
	@RequestMapping(value = "/entertain/getSearchInfo")
	@ResponseBody
	public EntertainInfoEntity getSearchInfo(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		return entertainInfoManageImpl.getSearchInfo1(entertainSearchInfoDTO);
	}

	//�޸Ĵ����ͨ���б�
	@RequestMapping(value = "/entertain/approveX")
	@ResponseBody
	public int approveX(String number) {
		return entertainRegisterInfoManageImpl.updateStatusByN1("�����", number);
	}
	
	// ���ݲ��Ż�ȡ�ò���ʣ��Ľ��
	@RequestMapping(value = "/entertain/getLastSum")
	@ResponseBody
	public Object getLastSum(String department) {
		return entertainAnnualBudgetManageImpl.getLastSum(department);
	}
		
	// ���ݲ�ѯ��������б�
	@RequestMapping(value = "/entertain/getList")
	@ResponseBody
	public List<EntertainInfoEntity> getList(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		return entertainInfoManageImpl.getList(entertainSearchInfoDTO);
	}
	
	// ��ȡ��ǰͨ�����º����˵�List
	@RequestMapping(value = "/entertain/getAPassList")
	@ResponseBody
	public List<EntertainInfoEntity> getAPassList() {
		return entertainInfoManageImpl.getAPassList();
	}

	// ��ȡ�д��ͻ����� ��
	@RequestMapping(value = "/entertain/getType")
	@ResponseBody
	public List<EntertainObjectTypeEntity> getType() {
		return entertainObjectTypeManageImpl.getType();
	}

	// �洢�µ��д��ͻ����� ��
	@RequestMapping(value = "/entertain/saveType")
	@ResponseBody
	public int saveType(String objectName) {
		return entertainObjectTypeManageImpl.saveType(objectName);
	}
	
	// ��ȡ��Ʊ���ߵ�λ����
    @RequestMapping(value = "/entertain/getInvoiceUnitType")
	@ResponseBody
	public List<EntertainInvoiceUnitEntity> getInvoiceUnitType() {
		return entertainInvoiceUnitManageImpl.getInvoiceUnitType();
	}
	
    // �洢�µķ�Ʊ���ߵ�λ���� 
	@RequestMapping(value = "/entertain/saveInvoiceUnitType")
	@ResponseBody
	public int saveInvoiceUnitType(String InvoiceUnitName) {
		return entertainInvoiceUnitManageImpl.saveInvoiceUnitType(InvoiceUnitName);
	} 

	// ��ǰ-�������� ��
	@RequestMapping(value = "/entertain/saveApplyInfo")
	@ResponseBody
	public int saveApplyInfo(EntertainApplyInfoDTO entertainApplyInfoDTO,HttpServletRequest request) throws Exception {
		int flag = entertainApplyInfoManageImpl.saveEntertainApplyInfo(entertainApplyInfoDTO,request);
		if(flag==1){
			//��Ҫ��������
			AndroidPushEntity pushEntity=new AndroidPushEntity();
			pushEntity.setTriker("��ǰ����");
			pushEntity.setTitle("ҵ���д�");
			pushEntity.setText("���д�������Ϣ");
			pushEntity.setAfter_open("com.hfga.hfgaoa.businessentertain.activity.BusinessPriorApprove");
			//IOS
			IOSPushEntity iOSEntity=new IOSPushEntity();
			iOSEntity.setAlert("��ǰ����");
			iOSEntity.setSound("���д�������Ϣ");
			//Android����
			//for(int i=0;i<userList.size();i++){
			pushEntity.setAlias(entertainApplyInfoDTO.getApprover());
			androidPushUtil.setPushEntity(pushEntity);
			androidPushUtil.sendCustomizedcast();
				
			iOSEntity.setAlias(entertainApplyInfoDTO.getApprover());
			iOSPushUtil.setiOSPushEntity(iOSEntity);
			iOSPushUtil.sendCustomizedcast();
		}
		return flag;
	}
	
	// ��������
	@RequestMapping(value = "/entertain/testTuisong")
	@ResponseBody
	public void  testTuisong() {
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("��ǰ����");
		iOSEntity.setSound("���д�������Ϣ");
		iOSEntity.setAlias("ФǬ˧");
		iOSPushUtil.setiOSPushEntity(iOSEntity);
		iOSPushUtil.sendCustomizedcast();
	}
	
	

	// ��ǰ-ɾ������ ��
	@RequestMapping(value = "/entertain/deleteApplyInfo")
	@ResponseBody
	public int deleteApplyInfo(String ID) {
		System.out.println(ID);
		return entertainApplyInfoManageImpl.delete(ID);
	}

	// ��ǰ-��ȡ���е�������Ϣ ��
	@RequestMapping(value = "/entertain/getAll")
	@ResponseBody
	public List<EntertainApplyInfoEntity> getAll() {
		return entertainApplyInfoManageImpl.getAll();
	}
	
	// ��ǰ-��ȡ���е�������Ϣ ��
	@RequestMapping(value = "/entertain/getUnSubRecord")
	@ResponseBody
	public Object getUnSubRecord(HttpServletRequest request, HttpServletResponse response) {
		
		String page = request.getParameter("page");// �ڼ�ҳ
		String rows = request.getParameter("rows");// ÿҳ������
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
		// ÿҳ�Ŀ�ʼ��¼
		int start = (intPage - 1) * number;
		List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
		list = entertainApplyInfoManageImpl.getUnSubRecord(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
		int total = entertainApplyInfoManageImpl.getUnSubRecordCount();
		jsonMap.put("total", total);// total����ܼ�¼��
		jsonMap.put("rows", list);// rows�������ÿҳ��¼list
		return jsonMap;
	}

	// ��ǰ-��ȡ����������Ϣ ��
	@RequestMapping(value = "/entertain/getPersonal")
	@ResponseBody
	public List<EntertainApplyInfoEntity> getPersonal(String manager) {
		return entertainApplyInfoManageImpl.getPersonal(manager);
	}

	// ��ǰ-��ȡ����ͨ������ǰ��Ϣ
	@RequestMapping(value = "/entertain/getPassApply")
	@ResponseBody
	public List<EntertainApplyInfoEntity> getPassApply(String manager) {
		return entertainApplyInfoManageImpl.getPassApply(manager);
	}

	// ��ǰ-��ȡ����ͨ������ǰ��Ϣ
	@RequestMapping(value = "/entertain/getAllPassApply")
	@ResponseBody
	public List<EntertainApplyInfoEntity> getAllPassApply() {
		return entertainApplyInfoManageImpl.getAllPassApply();
	}

	// ��ǰ-��ȡ��Ҫ��������Ϣ�����ݲ� ͬ�����ˣ� ��
	@RequestMapping(value = "/entertain/getApproveInfo")
	@ResponseBody
	public List<EntertainApplyInfoEntity> getApproveInfo(String approver) {
		System.out.println(approver);
		System.out.println(entertainApplyInfoManageImpl.getByApprove(approver).size());
		return entertainApplyInfoManageImpl.getByApprove(approver);
	}

	// ��ǰ-�������� ��
	@RequestMapping(value = "/entertain/approve")
	@ResponseBody
	public int approveInfo(String approver, String number) {
		
		//��ȡ��ǰʱ��
		String t = CommonUtil.getInstance().getTime();
		String time = t.substring(0,10);
		
		//����Number��ȡ������Ϣ
		List<EntertainApplyInfoEntity> list=entertainApplyInfoManageImpl.get(number);
		AndroidPushEntity pushEntity=new AndroidPushEntity();
		pushEntity.setTriker("����ͨ��");
		pushEntity.setTitle("ҵ���д�");
		pushEntity.setText("�����д�����������ͨ��");
		pushEntity.setAfter_open("com.hfga.hfgaoa.businessentertain.main.BusinessEntertainHomePage");
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("����ͨ��");
		iOSEntity.setSound("�����д�����������ͨ��");
		
		
		//Android����
		//for(int i=0;i<userList.size();i++){
		pushEntity.setAlias(list.get(0).getManager());
		androidPushUtil.setPushEntity(pushEntity);
		androidPushUtil.sendCustomizedcast();
			
		iOSEntity.setAlias(list.get(0).getManager());
		iOSPushUtil.setiOSPushEntity(iOSEntity);
		iOSPushUtil.sendCustomizedcast();
		return entertainApplyInfoManageImpl.updateApprove("ͨ��", approver, number,time);
	}

	// ��ǰ-������� ��
	@RequestMapping(value = "/entertain/denyApply")
	@ResponseBody
	public int denyApply(String applyId) {
		System.out.println(applyId);
		//��ȡ��ǰʱ��
		String t = CommonUtil.getInstance().getTime();
		String time = t.substring(0,10);
		int i = entertainApplyInfoManageImpl.updateStatusDeny("δͨ��", applyId, time);
		return i;
	}

	// �޸��д����� ��
	@RequestMapping(value = "/entertain/modifyOne")
	@ResponseBody
	public int modifyOne(EntertainApplyInfoDTO entertainApplyInfoDTO) throws Exception{
		return entertainApplyInfoManageImpl.modifyOne(entertainApplyInfoDTO);
	}
 
	// web-�����������Ų�ѯ�����Ϣ
	@RequestMapping(value = "/entertain/applyDetail")
	@ResponseBody
	public Object applyDetail(String number) {
		return entertainApplyInfoManageImpl.applyDetail(number);
	}

	// web-������д���ϸ-��ѯ
	@RequestMapping(value = "/entertain/searchApply")
	@ResponseBody
	public Object searchApply(HttpServletRequest request, EntertainSearchInfoDTO entertainSearchInfoDTO) {
		String page = request.getParameter("page");// �ڼ�ҳ
		String rows = request.getParameter("rows");// ÿҳ������
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
		// ÿҳ�Ŀ�ʼ��¼
		int start = (intPage - 1) * number;
		List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
		list = entertainApplyInfoManageImpl.searchApply(start, number, entertainSearchInfoDTO);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
		int total = entertainApplyInfoManageImpl.getAllCompletedApplyCount(entertainSearchInfoDTO);
		jsonMap.put("total", total);// total����ܼ�¼��
		jsonMap.put("rows", list);// rows�������ÿҳ��¼list
		return jsonMap;
	}

	/****************************************** һ���ɰ��ķָ��� *********************************************/
	// �º�-����Ǽ� ��
	@RequestMapping(value = "/register/saveRegister")
	@ResponseBody
	public int saveRegisterInfo(EntertainRegisterInfoDTO entertainRegisterInfoDTO) {
		/*
		AndroidPushEntity pushEntity=new AndroidPushEntity();
		pushEntity.setTriker("�º�����");
		pushEntity.setTitle("ҵ���д�");
		pushEntity.setText("���д�������Ϣ");
		pushEntity.setAfter_open("com.hfga.hfgaoa.businessentertain.activity.BusinessPriorApprove");
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("�º�����");
		iOSEntity.setSound("���д�������Ϣ");
		//Android����
		//for(int i=0;i<userList.size();i++){
		pushEntity.setAlias("����");
		androidPushUtil.setPushEntity(pushEntity);
		androidPushUtil.sendCustomizedcast();
			
		iOSEntity.setAlias("����");
		iOSPushUtil.setiOSPushEntity(iOSEntity);
		iOSPushUtil.sendCustomizedcast();
		*/
		return entertainRegisterInfoManageImpl.saveEntertainRegisterInfo(entertainRegisterInfoDTO);
	}

	// �º�-��ȡ���еĵǼ���Ϣ ��
	@RequestMapping(value = "/register/getAll")
	@ResponseBody
	public List<EntertainRegisterInfoEntity> getAllRegister() {
		return entertainRegisterInfoManageImpl.getAllRegister();
	}

	// �º�-��ȡ���д���˵Ǽ���Ϣ ��
	@RequestMapping(value = "/register/getReadyApproveInfo")
	@ResponseBody
	public List<EntertainRegisterInfoEntity> getReadyApproveInfo() {
		return entertainRegisterInfoManageImpl.getAllReady();
	}

	// �º�-¼��ƾ֤�ţ����ͨ��-����ID��Ȩ�ޡ������� ��
	@RequestMapping(value = "/register/insertVoucherNum")
	@ResponseBody
	public int insertVoucherNum(String ID, String paidTime, String voucherNum) {
		
		return entertainRegisterInfoManageImpl.insertVoucherNum(ID, paidTime, voucherNum);
	}

	// �º�-¼��ƾ֤�š�����ʱ�䣬���ͨ��-����Number��Ȩ�ޡ�������
	@RequestMapping(value = "/register/insertAllVoucherNum")
	@ResponseBody
	public int insertAllVoucherNum(String number, String paidTime, String voucherNum) {
		List<EntertainRegisterInfoEntity> list=entertainRegisterInfoManageImpl.get(number);
		/*
		AndroidPushEntity pushEntity=new AndroidPushEntity();
		pushEntity.setTriker("�������");
		pushEntity.setTitle("ҵ���д�");
		pushEntity.setText("�����д����������ͨ��");
		pushEntity.setAfter_open("com.hfga.hfgaoa.businessentertain.main.BusinessEntertainHomePage");
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("�������");
		iOSEntity.setSound("�����д����������ͨ��");
		//Android����
		//for(int i=0;i<userList.size();i++){
		pushEntity.setAlias(list.get(0).getRegisterMan());
		androidPushUtil.setPushEntity(pushEntity);
		androidPushUtil.sendCustomizedcast();
			
		iOSEntity.setAlias(list.get(0).getRegisterMan());
		iOSPushUtil.setiOSPushEntity(iOSEntity);
		iOSPushUtil.sendCustomizedcast();
		*/
		return entertainRegisterInfoManageImpl.insertAllVoucherNum(number, paidTime, voucherNum);
	}

	// �º�-�������δͨ��
	@RequestMapping(value = "/register/notThrough")
	@ResponseBody
	public int notThrough(String applyId) {
		return entertainRegisterInfoManageImpl.updateStatusUnapproved(applyId);
	}

	// �º�-ȡ����ˣ��˻����-����ID ��
	@RequestMapping(value = "/register/cancelRegister")
	@ResponseBody
	public int cancelRegister(String applyId) {
		return entertainRegisterInfoManageImpl.updateStatus("�����", applyId);
	}

	// �º�-ȡ����ˣ��˻����-����Number ��
	@RequestMapping(value = "/register/cancelRegisterByN")
	@ResponseBody
	public int cancelRegisterByN(String number) {
		return entertainRegisterInfoManageImpl.updateStatusByN("�����", number);
	}

	// �º�-���˳��صǼ���Ϣ�������ݿ�ɾ����
	@RequestMapping(value = "/register/deleteRegister")
	@ResponseBody
	public int deleteRegister(String ID, String invoiceSum) {
		return entertainRegisterInfoManageImpl.deleteRegister(ID,invoiceSum);
	}

	// �º�-�޸ĵǼ���Ϣ ��
	@RequestMapping(value = "/register/modifyRegiser")
	@ResponseBody
	public int modifyRegister(EntertainRegisterInfoDTO entertainRegisterInfoDTO) {
		return entertainRegisterInfoManageImpl.modifyRegister(entertainRegisterInfoDTO);
	}

	// �º�-��ȡ����������Ϣ ��
	@RequestMapping(value = "/register/getPersonalRegister")
	@ResponseBody
	public List<EntertainRegisterInfoEntity> getPersonalRegister(String registerman) {
		return entertainRegisterInfoManageImpl.getPersonalRegister(registerman);
	}

	// web-�����������Ų�ѯ�����Ϣ
	@RequestMapping(value = "/entertain/registerDetail")
	@ResponseBody
	public Object registerDetail(String number) {
		return entertainRegisterInfoManageImpl.registerDetail(number);
	}

	@RequestMapping(value = "/entertain/getOneRegister")
	@ResponseBody
	public Object getOneRegister(String number){
		return entertainRegisterInfoManageImpl.get(number);
	}
	/****************************************** һ���ɰ��ķָ��� *********************************************/
	// Web-��ҳ-������д���ϸ-��ʾ
	@RequestMapping(value = "/entertain/wGetAllApproved")
	@ResponseBody
	public Object wGetAllApproved(HttpServletRequest request) {
		String page = request.getParameter("page");// �ڼ�ҳ
		String rows = request.getParameter("rows");// ÿҳ������
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
		// ÿҳ�Ŀ�ʼ��¼
		int start = (intPage - 1) * number;
		List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
		list = entertainInfoManageImpl.wGetAllApproved(start, number);
		System.out.println("��ѯ��������"+list);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
		int total = entertainInfoManageImpl.getAllCompletedCount();
		jsonMap.put("total", total);// total����ܼ�¼��
		jsonMap.put("rows", list);// rows�������ÿҳ��¼list
		return jsonMap;
		// return list;
	}
	// Web-��ҳ-������д���ϸ-��ʾ
	@RequestMapping(value = "/entertain/wGetSearchApprovedByPage")
	@ResponseBody
	public Object wGetAllApprovedByPage(HttpServletRequest request,EntertainSearchInfoDTO entertainSearchInfoDTO) {
		String page = request.getParameter("page");// �ڼ�ҳ
		String rows = request.getParameter("rows");// ÿҳ������
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
		// ÿҳ�Ŀ�ʼ��¼
		int start = (intPage - 1) * number;
		List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
		list = entertainApplyInfoManageImpl.wGetSearchApprovedByPage(entertainSearchInfoDTO,start, number);
		System.out.println("��ѯ������"+list);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
		int total = entertainApplyInfoManageImpl.wGetSearchApprovedCount(entertainSearchInfoDTO);
		/*int total = list.size();*/
		jsonMap.put("total", total);// total����ܼ�¼��
		jsonMap.put("rows", list);// rows�������ÿҳ��¼list
		return jsonMap;
		// return list;
	}
	
	

	// Web-��ҳ-������º���Ϣ-��ʾ
	@RequestMapping(value = "/entertain/wRGetAllApproved")
	@ResponseBody
	public Object wRGetAllApproved(HttpServletRequest request) {
		String page = request.getParameter("page");// �ڼ�ҳ
		String rows = request.getParameter("rows");// ÿҳ������
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
		// ÿҳ�Ŀ�ʼ��¼
		int start = (intPage - 1) * number;
		List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
		list = entertainInfoManageImpl.wRGetAllApproved(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
		int total = entertainInfoManageImpl.getAllRCompletedCount();
		jsonMap.put("total", total);// total����ܼ�¼��
		jsonMap.put("rows", list);// rows�������ÿҳ��¼list
		return jsonMap;
	}
	
	// Web-��ҳ-������º���Ϣ-��ʾ
	@RequestMapping(value = "/entertain/wRGetAllEntertain")
	@ResponseBody
	public Object wRGetAllEntertain(HttpServletRequest request) {
		String page = request.getParameter("page");// �ڼ�ҳ
		String rows = request.getParameter("rows");// ÿҳ������
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
		// ÿҳ�Ŀ�ʼ��¼
		int start = (intPage - 1) * number;
		List<EntertainApplyInfoEntity> list = new ArrayList<EntertainApplyInfoEntity>();
		list = entertainInfoManageImpl.wRGetAllEntertain(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
		int total = entertainInfoManageImpl.getAllEntertainCount();
		jsonMap.put("total", total);// total����ܼ�¼��
		jsonMap.put("rows", list);// rows�������ÿҳ��¼list
		return jsonMap;
	}

	// Web-������д���ϸ-������
	@RequestMapping(value = "/entertain/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, String number) {
		System.out.println("������������"+number);
		String[] nlist = number.split(","); // ��ô��ݹ�����number�б�
		// ��ȡ�����ļ���
		String path = request.getSession().getServletContext().getRealPath("/");
		// ���ɵ������ļ���
		Date dt = new Date();
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
		String date = matter.format(dt);
		String fileName = "ҵ���д���ϸ" + date + ".xlsx";
		String filePath = path + "/" + fileName;
		int flag = entertainInfoManageImpl.export(nlist, filePath);
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
			response.setCharacterEncoding("UTF- 8");
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

	// web-��ҳ-δ����д���ϸ-��ʾ ��
	@RequestMapping(value = "/entertain/wGetAllUnapprovedRegister")
	@ResponseBody
	public Object wGetAllUnapprovedRegister(HttpServletRequest request) {
		String page = request.getParameter("page");// �ڼ�ҳ
		String rows = request.getParameter("rows");// ÿҳ������
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
		// ÿҳ�Ŀ�ʼ��¼
		int start = (intPage - 1) * number;
		List<EntertainRegisterInfoEntity> list = new ArrayList<EntertainRegisterInfoEntity>();
		list = entertainRegisterInfoManageImpl.wGetAllUnapprovedRegister(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
		int total = entertainRegisterInfoManageImpl.getAllUnapprovedRegisterCount();
		jsonMap.put("total", total);// total����ܼ�¼��
		jsonMap.put("rows", list);// rows�������ÿҳ��¼list
		return jsonMap;
	}

	// web-����д������б�
	@RequestMapping("/entertain/getObject")
	@ResponseBody
	public List<EntertainObjectTypeEntity> getAllObject() {
		return entertainObjectTypeManageImpl.getAllObject();
	}

	// web-��ò����б�
	@RequestMapping("/entertain/getDepartment")
	@ResponseBody
	public List<EntertainDepartmentEntity> getAllDepartment() {
		return entertainDepartmentManageImpl.getAllDepartment();
	}
	
	@RequestMapping(value = "/entertain/wGetSearchApproved") 
	@ResponseBody
	public Object wGetSearchApproved(EntertainSearchInfoDTO entertainSearchInfoDTO) {
		return entertainApplyInfoManageImpl.wGetSearchApproved(entertainSearchInfoDTO);
	}

	/****************************************** һ���ɰ��ķָ��� *********************************************/
	
	// web-��ý�ʮ������
	@RequestMapping("/entertain/getYear")
	@ResponseBody
	public Object getYear(){
		return entertainInfoManageImpl.getYear();
	}
	
	// web-�д����Ԥ��-��ʾ
	@RequestMapping("/entertain/wGetAnnualBudget")
	@ResponseBody
	public List<EntertainAnnualInfoDTO> wGetAnnualBudget(){
		return entertainAnnualBudgetManageImpl.wGetAnnualBudget();
		}
	
	// web-��ʾԤ��ִ������б�
	@RequestMapping("/entertain/wGetNowAnnual")
	@ResponseBody
	public List<EntertainAnnualBudgetEntity> wGetNowAnnual(){
		return entertainAnnualBudgetManageImpl.wGetNowAnnual();
		}
	// web-��ʾԤ��ִ������б�
	@RequestMapping("/entertain/wGetNowAnnual1")
	@ResponseBody
	public List<EntertainAnnualBudgetEntity> wGetNowAnnual1(String year){
		return entertainAnnualBudgetManageImpl.wGetNowAnnual1(year);
		}
	
	// web-�����޸ĵ����Ԥ��ֵ
	@RequestMapping("/entertain/wSetAdjust")
	@ResponseBody
	public int wSetAdjust(String param){
		return entertainAnnualBudgetManageImpl.wSetAdjust(param);
		}
	
	// web-��õ�ǰÿ�����ŵķ�����
	@RequestMapping("/entertain/wGetAllUsedNow")
	@ResponseBody
	public Object wGetAllUsed(){
		return entertainInfoManageImpl.wGetAllUsed();
		}
	
	// web-������ݲ�ѯ����Ϣ
		@RequestMapping("/entertain/wGetSearchAnnual")
		@ResponseBody
		public Object wGetSearchAnnual(String year){
			return entertainInfoManageImpl.wGetSearchAnnual(year);
			}
		
	// web-���ѡ����ݵ�Ԥ��ִ������б�
		@RequestMapping("/entertain/wGetSearchImplementation")
		@ResponseBody
		public List<EntertainAnnualBudgetEntity> wGetSearchImplementation(String year){
			return entertainAnnualBudgetManageImpl.wGetSearchImplementation(year);
			}
		
	// web-���ѡ�����ÿ�����ŵķ�����
		@RequestMapping("/entertain/wGetSelectedUsed")
		@ResponseBody
		public Object wGetSelectedUsed(String year){
			return entertainInfoManageImpl.wGetSelectedUsed(year);
		}
	
}