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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hfga.controller.file.XmlDocument;
import cn.com.hfga.dao.car.CarBaseInfoDAO;
import cn.com.hfga.dao.entertain.EntertainDepartmentDAO;
import cn.com.hfga.dto.car.ApproveDTO;
import cn.com.hfga.dto.car.CarApplyInfoDTO;
import cn.com.hfga.dto.car.CarApporveInfoDTO;
import cn.com.hfga.dto.car.CarBackDTO;
import cn.com.hfga.dto.car.CarBaseInfoDTO;
import cn.com.hfga.dto.car.CarCollectInfoDTO;
import cn.com.hfga.dto.car.CarKindDTO;
import cn.com.hfga.dto.car.CarOutDTO;
import cn.com.hfga.dto.car.CarUseDetailDTO;
import cn.com.hfga.dto.privatecar.PrivateCarSearchDTO;
import cn.com.hfga.dto.user.FindLeaderDTO;
import cn.com.hfga.dto.user.UserDTO;
import cn.com.hfga.dto.user.UserModifyDTO;
import cn.com.hfga.entity.car.CarApplyInfoEntity;
import cn.com.hfga.entity.car.CarBaseInfoEntity;
import cn.com.hfga.entity.car.GasCarInfoEntity;
import cn.com.hfga.entity.car.InsuranceCarInfoEntity;
import cn.com.hfga.entity.car.ProtectCarInfoEntity;
import cn.com.hfga.entity.car.PunishCarInfoEntity;
import cn.com.hfga.entity.car.TrafficCarInfoEntity;
import cn.com.hfga.entity.entertain.EntertainDepartmentEntity;
import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;
import cn.com.hfga.entity.user.DepartmentEntity;
import cn.com.hfga.entity.user.UserEntity;
import cn.com.hfga.manageimpl.car.CarApplyInfoManageImpl;
import cn.com.hfga.manager.car.CarApplyInfoManage;
import cn.com.hfga.manager.car.CarBaseInfoManage;
import cn.com.hfga.manager.car.GasCarInfoManage;
import cn.com.hfga.manager.car.InsuranceCarInfoManage;
import cn.com.hfga.manager.car.ProtectCarInfoManage;
import cn.com.hfga.manager.car.PunishCarInfoManage;
import cn.com.hfga.manager.car.TrafficCarInfoManage;
import cn.com.hfga.manager.user.DepartmentManager;
import cn.com.hfga.manager.user.UserManager;
import cn.com.hfga.push.android.AndroidPushEntity;
import cn.com.hfga.push.android.AndroidPushUtil;
import cn.com.hfga.push.ios.IOSPushEntity;
import cn.com.hfga.push.ios.IOSPushUtil;
import cn.com.hfga.util.common.BeanUtils;
import cn.com.hfga.util.common.LogUtil;
import cn.com.hfga.util.common.MD5Util;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @author xinyc
 * @since 2014-11-13 登录功能
 */
@Controller
public class CarBaseInfoController {

	@Autowired
	private CarBaseInfoManage carBaseInfoManage;

	//Web-显示公车相关信息
	@RequestMapping("/CarBaseInfo/display")
	@ResponseBody
	public Object carDisplay(HttpServletRequest request){
		String page = request.getParameter("page");// 第几页
		String rows = request.getParameter("rows");// 每页多少条
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 页数
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 每页个数
		// 每页的开始记录
		int start = (intPage - 1) * number;
		List<CarBaseInfoEntity> list = new ArrayList<CarBaseInfoEntity>();
		list = carBaseInfoManage.carDisplay(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int total = carBaseInfoManage.getAllCount();
		jsonMap.put("total", total);// total存放总记录数
		jsonMap.put("rows", list);// rows键，存放每页记录list
		return jsonMap;
	}
	
	@RequestMapping("/CarBaseInfo/update")
	@ResponseBody
	public Object updateBaseInfo(CarBaseInfoEntity car){
		return carBaseInfoManage.updateBaseInfo(car);
	}
	
	@RequestMapping("/CarBaseInfo/insert")
	@ResponseBody
	public Object insertBaseInfo(CarBaseInfoEntity car){
		return carBaseInfoManage.insertBaseInfo(car);
	}
}
