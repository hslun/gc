package cn.com.hfga.controller.common;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hfga.dto.manhour.ManHourDTO;
import cn.com.hfga.dto.manhour.ManhourSearchDTO;
import cn.com.hfga.dto.user.UserDTO;
import cn.com.hfga.entity.car.CarApplyInfoEntity;
import cn.com.hfga.entity.car.CarBaseInfoEntity;
import cn.com.hfga.entity.car.InsuranceCarInfoEntity;
import cn.com.hfga.entity.car.ProtectCarInfoEntity;
import cn.com.hfga.entity.car.PunishCarInfoEntity;
import cn.com.hfga.entity.car.TrafficCarInfoEntity;
import cn.com.hfga.entity.manhour.ManHourEntity;
import cn.com.hfga.entity.user.UserEntity;
import cn.com.hfga.manager.car.CarApplyInfoManage;
import cn.com.hfga.manager.car.CarBaseInfoManage;
import cn.com.hfga.manager.car.InsuranceCarInfoManage;
import cn.com.hfga.manager.car.ProtectCarInfoManage;
import cn.com.hfga.manager.car.PunishCarInfoManage;
import cn.com.hfga.manager.car.TrafficCarInfoManage;
import cn.com.hfga.manager.manhour.ManHourManage;
import cn.com.hfga.manager.user.UserManager;
import cn.com.hfga.util.common.MD5Util;

/**
 * web端controller
 * @author xyc
 *
 */
@Controller
public class WebController {

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private ManHourManage manHourManage;
	
	@Autowired
	private CarBaseInfoManage carBaseInfoManage;
	
	@Autowired 
	private CarApplyInfoManage carApplyInfoManage;
	
	
	@Autowired 
	private PunishCarInfoManage punishCarInfoManage;
	
	@Autowired 
	private TrafficCarInfoManage trafficCarInfoManage;
	
	@Autowired 
	private InsuranceCarInfoManage insuranceCarInfoManage;
	
	@Autowired
	private ProtectCarInfoManage protectCarInfoManage;
	
	
	
	//web登陆
		@RequestMapping(value="/web/Login")
		@ResponseBody
		//登陆是否
		public List<UserEntity>  doLogin( UserDTO UserDTO,HttpSession session,HttpServletResponse response)
		{
			String pwd=UserDTO.getPassword();
			UserDTO.setPassword(MD5Util.toMD5(UserDTO.getPassword()));
			
			List<UserEntity> list= userManager.findOne(UserDTO);
			if(list.size()==0)
			{
				return null;
			}
			else
			{
				//锟斤拷录锟缴癸拷  锟斤拷锟斤拷录状态锟斤拷锟芥到cookie
				/*Cookie username=new Cookie("username", UserDTO.getUsername());
				username.setMaxAge(3650);
				Cookie password=new Cookie("password",pwd);
				password.setMaxAge(3650);
				response.addCookie(username);
				response.addCookie(password);*/
				for (UserEntity userEntity:list)
				{
					session.setAttribute("realname",userEntity.getRealName() );
					session.setAttribute("departmentname", userEntity.getDepartmentName());
					session.setAttribute("departmentid", userEntity.getDepartmentId());
					session.setAttribute("username",userEntity.getUserName() );
					session.setAttribute("password",userEntity.getUserPassword());
					session.setAttribute("roleid", userEntity.getRoleId());	
					break;
				}
				return list;
			}
		}
		//日志填报
		@RequestMapping(value="/web/Fill")
		@ResponseBody
		public int doFill(ManHourDTO manHourDTO,HttpSession session)
		{
			List<ManHourEntity> list=manHourManage.isExist(manHourDTO.getDay());
		    if(session.getAttribute("departmentid")==null)
			{
				return 3;
			}
			else 
			{
				System.out.println(session.getAttribute("departmentid").toString());
				manHourDTO.setDepartmentId(session.getAttribute("departmentid").toString());
				manHourDTO.setDepartmentName(session.getAttribute("departmentname").toString());
				manHourDTO.setRealName(session.getAttribute("realname").toString());
				return manHourManage.insetOne(manHourDTO);
			}
				
		}
		//日志查询
		@RequestMapping(value="/web/SearchInfo")
		@ResponseBody
		public List<ManHourEntity> doSearch(ManhourSearchDTO manhourSearchDTO)
		{
			List<ManHourEntity> list=new ArrayList<ManHourEntity>();
			if(manhourSearchDTO.getName()!="")
			{
				if(manhourSearchDTO.getKind().equalsIgnoreCase("全部")&&manhourSearchDTO.getDepartment().equalsIgnoreCase("全部"))
				{
					list= manHourManage.get111(manhourSearchDTO);
				}
				else if(!manhourSearchDTO.getKind().equalsIgnoreCase("全部")&&manhourSearchDTO.getDepartment().equalsIgnoreCase("全部"))
				{
					list= manHourManage.get110(manhourSearchDTO);
				}
				else if(!manhourSearchDTO.getKind().equalsIgnoreCase("全部")&&!manhourSearchDTO.getDepartment().equalsIgnoreCase("全部"))
				{
					list= manHourManage.get100(manhourSearchDTO);		
				}
				else 
				{
					list= manHourManage.get101(manhourSearchDTO);
				}
				return list;
			}
			else if(manhourSearchDTO.getName()=="")
			{
				if(manhourSearchDTO.getKind().equalsIgnoreCase("全部")&&manhourSearchDTO.getDepartment().equalsIgnoreCase("全部"))
				{
					list= manHourManage.get011(manhourSearchDTO);
				}
				else if(!manhourSearchDTO.getKind().equalsIgnoreCase("全部")&&manhourSearchDTO.getDepartment().equalsIgnoreCase("全部"))
				{
					list= manHourManage.get010(manhourSearchDTO);
				}
				else if(!manhourSearchDTO.getKind().equalsIgnoreCase("全部")&&!manhourSearchDTO.getDepartment().equalsIgnoreCase("全部"))
				{
					list= manHourManage.get000(manhourSearchDTO);		
				}
				else 
				{
					list= manHourManage.get001(manhourSearchDTO);
				}
				return list;
			}
			else
			{
				return null;
			}
		}
		@RequestMapping(value="/web/GetDetail")
		@ResponseBody
		public  List<ManHourEntity> doGetDetail(String id)
		{
			System.out.print( manHourManage.getOne(id).toString());
			return manHourManage.getOne(id);
		}
		
		@RequestMapping(value="/web/GetRole")
		@ResponseBody
		public  List<Map<String, Object>> doGetRole(HttpSession session)
		{
			List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
			Map<String, Object> m=new HashMap<>();
			m.put("roleid", session.getAttribute("roleid"));
			m.put("departmentid", session.getAttribute("departmentid"));
			m.put("realname",session.getAttribute("realname"));
			list.add(m);
			return list;
		}
		
		@RequestMapping(value="/web/First")
		public String doGetFirst(@CookieValue(value="username",defaultValue="") String username)
		{
			String returnviewString="";
			if(username==null||username=="")
			{
				returnviewString= "redirect:/view/Manhour/Login.html";
			}
			else
			{
				returnviewString="redirect:/view/Manhour/Fill.html";	
			}
			return returnviewString;
		}
		
		@RequestMapping(value="/web/Out")
		public String   doGetOut(HttpSession session)
		{
			session.setAttribute("realname",null);
			session.setAttribute("departmentname", null);
			session.setAttribute("departmentid", null);
			session.setAttribute("username",null );
			session.setAttribute("password",null);
			session.setAttribute("roleid", null);	
			return "1";
		}
		
		@RequestMapping(value="/web/GetOneInfo")
		@ResponseBody
		public List<CarBaseInfoEntity> doGetOneInfo(String carnum) throws UnsupportedEncodingException
		{
			byte bb[];
			bb=carnum.getBytes("ISO-8859-1");
			carnum=new String(bb,"UTF-8");
			System.out.println(carnum);
			return carBaseInfoManage.getOneInfo(carnum);
		}
		//查看某个车的申请和预约信息---web
		@RequestMapping(value="/web/CarStatusDetail")
		@ResponseBody
		public List<CarApplyInfoEntity> doGetStatusDetail(String carnum) throws UnsupportedEncodingException
		{
			//carid="1";
			byte bb[];
			bb=carnum.getBytes("ISO-8859-1");
			carnum=new String(bb,"UTF-8");
			System.out.print(carnum);
			System.out.print(carApplyInfoManage.getAllApplyDetail(carnum));
			return carApplyInfoManage.getAllApplyDetail(carnum);
		}
		//获取车辆的处罚信息--车牌号	
		@RequestMapping(value="/web/GetPunishInfo")
		@ResponseBody
		public List<PunishCarInfoEntity> doGetPunishInfo(String carnum) throws UnsupportedEncodingException
		{
			byte bb[];
			bb=carnum.getBytes("ISO-8859-1");
			carnum=new String(bb,"UTF-8");
			System.out.print(carnum);
			System.out.print(punishCarInfoManage.getAll(carnum));
			return punishCarInfoManage.getAll(carnum);
		}
		
		//获取车辆事故信息--车牌号
		@RequestMapping(value="/web/GetAccidentInfo")
		@ResponseBody
		public List<TrafficCarInfoEntity> doGetAccidentInfo(String carnum) throws UnsupportedEncodingException
		{
//			carnum="京HV3507";
			byte bb[];
			bb=carnum.getBytes("ISO-8859-1");
			carnum=new String(bb,"UTF-8");
			System.out.print(carnum);
			System.out.print(trafficCarInfoManage.getAll(carnum));
			return trafficCarInfoManage.getAll(carnum);
		}
		//获取车辆的保险信息
		@RequestMapping(value="/web/GetInsuranceInfo")
		@ResponseBody
		public List<InsuranceCarInfoEntity> doGetInsuranceInfo(String carnum) throws UnsupportedEncodingException
		{
//			carnum="京HV3507";
			byte bb[];
			bb=carnum.getBytes("ISO-8859-1");
			carnum=new String(bb,"UTF-8");
			System.out.print(insuranceCarInfoManage.getAll(carnum));
			System.out.print("chepaihao"+carnum);
			List<InsuranceCarInfoEntity> list= insuranceCarInfoManage.getAll(carnum);
//			List<InsuranceCarInfoEntity> list= new ArrayList<InsuranceCarInfoEntity>();//insuranceCarInfoManage.getAll(carnum);
			return list;
		}
		
		//获取车辆的保养维修信息
		@RequestMapping(value="/web/GetProtectInfo")
		@ResponseBody
		public List<ProtectCarInfoEntity> doGetProtectCarInfo(String carnum) throws UnsupportedEncodingException
		{
//			carnum="京HV3507";
			byte bb[];
			bb=carnum.getBytes("ISO-8859-1");
			carnum=new String(bb,"UTF-8");
			System.out.println(protectCarInfoManage.getAll(carnum));
			return protectCarInfoManage.getAll(carnum);
			
		}
}
