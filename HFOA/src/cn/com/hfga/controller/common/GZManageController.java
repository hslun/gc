package cn.com.hfga.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hfga.dto.gz.GZApplyInfoDTO;
import cn.com.hfga.dto.gz.GZSearchInfoDTO;
import cn.com.hfga.entity.gz.GZApplyInfoEntity;
import cn.com.hfga.entity.gz.GZKindEntity;
import cn.com.hfga.entity.user.DepartmentEntity;
import cn.com.hfga.manageimpl.gz.GZApplyInfoManageImpl;
import cn.com.hfga.manageimpl.user.DepartmentImpl;
import cn.com.hfga.push.android.AndroidPushEntity;
import cn.com.hfga.push.android.AndroidPushUtil;
import cn.com.hfga.push.ios.IOSPushEntity;
import cn.com.hfga.push.ios.IOSPushUtil;

@Controller("gZManageController")
public class GZManageController {

	@Autowired
	private DepartmentImpl departmentImpl;

	@Autowired
	private GZApplyInfoManageImpl gZApplyInfoManageImpl;

	// ��������
	@RequestMapping(value = "/gz/getByName")
	@ResponseBody
	public List<DepartmentEntity> getByName(String departmentName) throws UnsupportedEncodingException {

		byte bb[];
		bb = departmentName.getBytes("ISO-8859-1");
		departmentName = new String(bb, "UTF-8");
		System.out.println(departmentName);
		System.out.println(departmentImpl.getByName(departmentName));
		return departmentImpl.getByName(departmentName);
	}

	// ��ȡ���й�����Ϣ
	@RequestMapping(value = "/gz/getAll")
	@ResponseBody
	public List<GZApplyInfoEntity> getAll() {
		return gZApplyInfoManageImpl.getAll();
	}
	//��׿��������Ϣ
	@Autowired
	private AndroidPushUtil androidPushUtil;
	
	
	
//	public AndroidPushUtil getAndroidPushUtil() {
//		return androidPushUtil;
//	}
//
//	public void setAndroidPushUtil(AndroidPushUtil androidPushUtil) {
//		this.androidPushUtil = androidPushUtil;
//	}
	//IOS��������Ϣ
	@Autowired
	private IOSPushUtil iOSPushUtil;
	
	
	// ���湫��������Ϣ
	@RequestMapping(value = "/gz/saveApplyInfo")
	@ResponseBody
	public int saveApplyInfo(GZApplyInfoDTO gzApplyInfoDTO) {
		AndroidPushEntity pushEntity=new AndroidPushEntity();
		pushEntity.setTriker("��������");
		pushEntity.setTitle("���¹���");
		pushEntity.setText("���д�������Ϣ");
		pushEntity.setAfter_open("com.hfga.hfgaoa.borrowstamp.activity.BorrowStampApprove");
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("��������");
		iOSEntity.setSound("���д�������Ϣ");
		//Android����
		//for(int i=0;i<userList.size();i++){
			pushEntity.setAlias(gzApplyInfoDTO.getApproveMan());
			androidPushUtil.setPushEntity(pushEntity);
			androidPushUtil.sendCustomizedcast();
			
			iOSEntity.setAlias(gzApplyInfoDTO.getApproveMan());
			iOSPushUtil.setiOSPushEntity(iOSEntity);
			iOSPushUtil.sendCustomizedcast();
		return gZApplyInfoManageImpl.saveGZApplyInfo(gzApplyInfoDTO);
	}

	// ɾ������������Ϣ
	@RequestMapping(value = "/gz/deleteApplyInfo")
	@ResponseBody
	public int deleteApplyInfo(String applyId) {
		System.out.println(applyId);
		return gZApplyInfoManageImpl.delete(applyId);
	}

	// С���
	@RequestMapping(value = "/gz/getComplete")
	@ResponseBody
	public List<GZApplyInfoEntity> getComplete() {
		return gZApplyInfoManageImpl.getNeedYin();
	}

	// ��ͬ��
	@RequestMapping(value = "/gz/getHT")
	@ResponseBody
	public List<GZApplyInfoEntity> getHT() {
		return gZApplyInfoManageImpl.getNeedChuan();
	}

	// ������
	@RequestMapping(value = "/gz/getFR")
	@ResponseBody
	public List<GZApplyInfoEntity> getFR() {
		return gZApplyInfoManageImpl.getNeedTan();
	}

	// ��������
	@RequestMapping(value = "/gz/approve")
	@ResponseBody
	public int approveInfo(String approveMan, String applyId) {
		
		//Ҫ����applyId
		List<GZApplyInfoEntity> gzs=gZApplyInfoManageImpl.getOne(applyId);
		AndroidPushEntity pushEntity=new AndroidPushEntity();
		pushEntity.setTriker("�������");
		pushEntity.setTitle("���¹���");
		pushEntity.setText("���Ľ�������������ͨ��");
		pushEntity.setAfter_open("com.hfga.hfgaoa.borrowstamp.main.StampHomePage");
		
	
		//IOS
		IOSPushEntity iOSEntity=new IOSPushEntity();
		iOSEntity.setAlert("�������");
		iOSEntity.setSound("���Ľ�������������ͨ��");
		//Android����
		pushEntity.setAlias(gzs.get(0).getApplyUserName());
		System.out.println(gzs.get(0).getApplyUserName());
		androidPushUtil.setPushEntity(pushEntity);
		androidPushUtil.sendCustomizedcast();
		iOSEntity.setAlias(gzs.get(0).getApplyUserName());
		iOSPushUtil.setiOSPushEntity(iOSEntity);
		iOSPushUtil.sendCustomizedcast();
		
		return gZApplyInfoManageImpl.updateApprove("ͨ��", approveMan, applyId);
	}

	// ��ȡ������Ϣ---����������
	@RequestMapping(value = "/gz/getApproveInfo")
	@ResponseBody
	public List<GZApplyInfoEntity> getApproveInfo(String approveMan) {
		System.out.println(approveMan);
		System.out.println(gZApplyInfoManageImpl.getByApprove(approveMan).size());
		return gZApplyInfoManageImpl.getByApprove(approveMan);
	}

	// ȷ�ϲ��� ̷�ܺ�
	@RequestMapping(value = "/gz/confirm")
	@ResponseBody
	public int confirm(String approveMan, String applyId) {
		System.out.println(applyId);
		return gZApplyInfoManageImpl.updateSure("��ȷ��", approveMan, applyId);
	}

	// С�����ɲ���
	@RequestMapping(value = "/gz/complete")
	@ResponseBody
	public int confirm(String applyId) {
		System.out.println(applyId);
		return gZApplyInfoManageImpl.updateStatus("�����", applyId);
	}

	// ���Ĺ�������
	@RequestMapping(value = "/gz/modifyOne")
	@ResponseBody
	public int modifyOne(GZApplyInfoDTO gzApplyInfoDTO) {
		return gZApplyInfoManageImpl.modifyOne(gzApplyInfoDTO);
	}

	// �������
	@RequestMapping(value = "/gz/denyApply")
	@ResponseBody
	public int denyApply(String applyId) {
		System.out.println(applyId);
		return gZApplyInfoManageImpl.updateStatus("�����", applyId);
	}

	// ȷ����ʱ�򱻷��
	@RequestMapping(value = "/gz/denyApplySure")
	@ResponseBody
	public int denyApplySure(String applyId) {
		System.out.println(applyId); 
		return gZApplyInfoManageImpl.updateStatus("������", applyId);
	}

	//
	@RequestMapping(value = "/gz/searchInfo")
	@ResponseBody
	public List<GZApplyInfoEntity> searchInfo() {
		return null;
	}
	
	// ��ȡ����������Ϣ
	@RequestMapping(value = "/gz/getPersonal")
	@ResponseBody
	public List<GZApplyInfoEntity> getPersonal(String applyUserName) {
		System.out.println(applyUserName);
		System.out.println(gZApplyInfoManageImpl.getPersonal(applyUserName).size());
		return gZApplyInfoManageImpl.getPersonal(applyUserName);
	}

	@RequestMapping(value = "/gz/getNeedSure")
	@ResponseBody
	public List<GZApplyInfoEntity> getNeedSure(String duty) {
		if (duty.equals("1")) {
			return gZApplyInfoManageImpl.getNeedChuan();
		} else {
			return gZApplyInfoManageImpl.getNeedTan();
		}
	}

	@RequestMapping(value = "/gz/getSearchInfo")
	@ResponseBody
	public List<GZApplyInfoEntity> getSearchInfo(GZSearchInfoDTO gzSearchInfoDTO) {
		return gZApplyInfoManageImpl.getSearchInfo(gzSearchInfoDTO);
	}
	
	/*********************************һ���ɰ��ķָ���*****************************/
	//Web-��ʾ���������Ϣ
	@RequestMapping("/GZManage/display")
	@ResponseBody
	public Object carDisplay(HttpServletRequest request){
		String page = request.getParameter("page");// �ڼ�ҳ
		String rows = request.getParameter("rows");// ÿҳ������
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
		// ÿҳ�Ŀ�ʼ��¼
		int start = (intPage - 1) * number;
		List<GZApplyInfoEntity> list = new ArrayList<GZApplyInfoEntity>();
		list = gZApplyInfoManageImpl.GZManageDisplay(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
		int total = gZApplyInfoManageImpl.getAllCount();
		jsonMap.put("total", total);// total����ܼ�¼��
		jsonMap.put("rows", list);// rows�������ÿҳ��¼list
		return jsonMap;
		}
	//Web-��ʾ���������Ϣ
	@RequestMapping("/GZManage/displaySearch")
	@ResponseBody
	public Object carDisplaySearch(HttpServletRequest request, GZSearchInfoDTO gzSearchInfoDTO){
		String page = request.getParameter("page");// �ڼ�ҳ
		String rows = request.getParameter("rows");// ÿҳ������
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
		// ÿҳ�Ŀ�ʼ��¼
		int start = (intPage - 1) * number;
		List<GZApplyInfoEntity> list = new ArrayList<GZApplyInfoEntity>();
		list = gZApplyInfoManageImpl.wGetSearchInfoByPage(gzSearchInfoDTO, start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
		int total = gZApplyInfoManageImpl.wGetSearchInfoTotal(gzSearchInfoDTO, start, number);
		jsonMap.put("total", total);// total����ܼ�¼��
		jsonMap.put("rows", list);// rows�������ÿҳ��¼list
		return jsonMap;
	}
	//Web-���ղ��š������ˡ��������͡�����ʱ��Σ���ʼ����������ѯ������Ϣ
	@RequestMapping(value = "/gz/wGetSearchInfo")
	@ResponseBody
	public List<GZApplyInfoEntity> wGetSearchInfo(GZSearchInfoDTO gzSearchInfoDTO) {
		return gZApplyInfoManageImpl.wGetSearchInfo(gzSearchInfoDTO);
	}
	
	//Web��������Excel
			@RequestMapping(value = "/GZManage/exportExcel")
			public void exportExcel(HttpServletRequest request, HttpServletResponse response, String number) {
				String[] nlist = number.split(","); // ��ô��ݹ�����number�б�
				// ��ȡ�����ļ���
				String path = request.getSession().getServletContext().getRealPath("/");
				// ���ɵ������ļ���
				Date dt = new Date();
				SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
				String date = matter.format(dt);
				String fileName = "����ʹ����Ϣ" + date + ".xlsx";
				String filePath = path + "/" + fileName;
				int flag = gZApplyInfoManageImpl.export(nlist, filePath);
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
	
	
	//Web-��ȡ�������͡�
	@RequestMapping(value = "/gz/getType")
	@ResponseBody
	public List<GZKindEntity> getType() {
		return gZApplyInfoManageImpl.getType();
	}
}
