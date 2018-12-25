package cn.com.hfga.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
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

import cn.com.hfga.dto.user.UserDTO;
import cn.com.hfga.entity.user.DutyEntity;
import cn.com.hfga.entity.user.MenuEntity;
import cn.com.hfga.entity.user.UserEntity;
import cn.com.hfga.manager.user.DutyManager;
import cn.com.hfga.manager.user.MenuManager;
import cn.com.hfga.manager.user.UserManager;
@Controller
public class SysUserController {
	@Autowired
	private UserManager userManager;
	@Autowired
	private MenuManager menuManager;
	@Autowired
	private DutyManager dutyManager;
	/**
	 * Web-��ȡ�����û�-��ҳ
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sysUser/getUserAll")
	@ResponseBody
	public Object getAll(HttpServletRequest request){
		String page = request.getParameter("page");// �ڼ�ҳ
		String rows = request.getParameter("rows");// ÿҳ������
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
		// ÿҳ�Ŀ�ʼ��¼
		int start = (intPage - 1) * number;
		Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
		List<UserEntity> list = new ArrayList<UserEntity>();
		list = userManager.getByPage(start, number);
		System.out.println("list"+list.get(0).getQICQ());
		int total = userManager.getAllCount();
		jsonMap.put("total", total);// total����ܼ�¼��
		jsonMap.put("rows", list);// rows�������ÿҳ��¼list
		return jsonMap;
	}
	/**
	 * Web-�����û�
	 * @param userDTO
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/sysUser/saveOrUpdateSysUser")
	@ResponseBody
	public int saveUser(UserDTO userDTO) throws ParseException{
		System.out.println("�޸��û���"+userDTO);
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Date parse = null;
		if(null!=userDTO.getAllowStartTime()&&!userDTO.getAllowStartTime().equals("")){
			 parse = smf.parse(userDTO.getAllowStartTime());
		}
		System.out.println("��ְʱ��"+parse);
		System.out.println("����ʱ��"+date);
		
		
		int flag = 0;
		if(userDTO.getId()==-1){
			//����
			flag = userManager.saveUser(userDTO);
		}else{
			//�޸�
			flag = userManager.updateUser(userDTO);
		}
		return flag;
	}
	/**
	 * Web-ɾ���û�
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/sysUser/deleteSysUser")
	@ResponseBody
	public int deleteUser(String Id){
		return userManager.deleteUser(Id);
	}
	/**
	 * Web-��������
	 * @param Id
	 * @return
	 */
	@RequestMapping(value="/sysUser/ResetPasswordById")
	@ResponseBody
	public int ResetPasswordById(String Id){
		return userManager.ResetPasswordById(Id);
	}
	/**
	 * Web-��ѯ�û�
	 * @param userDTO
	 * @return
	 */
	@RequestMapping(value="/sysUser/searchUser")
	@ResponseBody
	public Object searchUser(HttpServletRequest request,UserDTO userDTO){
		String page = request.getParameter("page");// �ڼ�ҳ
		String rows = request.getParameter("rows");// ÿҳ������
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// ҳ��
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// ÿҳ����
		// ÿҳ�Ŀ�ʼ��¼
		int start = (intPage - 1) * number;
		int total = 0;
		Map<String, Object> jsonMap = new HashMap<String, Object>();// ����map
		List<UserEntity> list = new ArrayList<UserEntity>();
		
		if(!"".equals(userDTO.getDepartmentName())&&!"ȫ��".equals(userDTO.getDepartmentName())&&"".equals(userDTO.getCode())){
			//���ݲ��Ų�ѯ
			list = userManager.searchUserByDepartmentName(userDTO,start,number);
			total = userManager.searchUserCountByDepartmentName(userDTO);
		}else if("".equals(userDTO.getDepartmentName())&&!"".equals(userDTO.getCode())){
			//�����û�����ѯ
			list = userManager.searchUserByCode(userDTO); 
			total = list.size();
		}else if("ȫ��".equals(userDTO.getDepartmentName())&&!"".equals(userDTO.getCode())){
			//��ѯȫ��
			list = userManager.searchUserByCode(userDTO);
			total = userManager.searchUserCountByCode(userDTO);
		}else if("ȫ��".equals(userDTO.getDepartmentName())&&"".equals(userDTO.getCode())){
			//��ѯȫ��
			list = userManager.getByPage(start,number);
			total = userManager.getAllCount();
		}else{
			//�����û����Ͳ��Ų�ѯ
			list = userManager.searchUser(userDTO);
			total = list.size();
		}
		
		jsonMap.put("total", total);// total����ܼ�¼��
		jsonMap.put("rows", list);// rows�������ÿҳ��¼list
		
		return jsonMap;
	}
	/**
	 * Web-�û�����
	 * @param request
	 * @param response
	 * @param number
	 * @param depart
	 */
	@RequestMapping(value = "/sysUser/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,String number,String depart){
		System.out.println("depart��"+depart);
		String deptStr = "";
		try {
			//����
			deptStr=URLDecoder.decode(depart,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String[] nlist = number.split(","); // ��ô��ݹ�����number�б�
		for(int i=0;i<nlist.length;i++){
			System.out.println("���"+nlist[i]);
		}
		// ��ȡ�����ļ���
		String path = request.getSession().getServletContext().getRealPath("/");
		// ���ɵ������ļ���
		Date dt = new Date();
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
		String date = matter.format(dt);
		String fileName = "�û��б�" + date + ".xlsx";
		String filePath = path + "/" + fileName;
		int flag = userManager.export(nlist, filePath, deptStr);
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
	/**
	 * Web-��ȡְλ
	 * @return
	 */
	@RequestMapping(value = "/sysUser/getDuty")
	@ResponseBody
	public List<DutyEntity> getDuty(){
		return dutyManager.getDuty();
	}
	/**
	 * Web-��ȡ�˵�
	 * @param deptId
	 * @return
	 */
	@RequestMapping(value = "/sysUser/getMenu")
	@ResponseBody
	public List<MenuEntity> getMenu(String deptId){
		return menuManager.getByDeptId(deptId);
	}
	/**
	 * Web-����˽��Ȩ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/sysUser/updatePrivate")
	@ResponseBody
	public Object updatePrivate(String id){
		return userManager.updatePrivate(id);
	}
}
