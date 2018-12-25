package cn.com.hfga.controller.common;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.hfga.dto.privatecar.PrivateCarInvoiceDTO;
import cn.com.hfga.entity.privatecar.PrivateCarApplyEntity;
import cn.com.hfga.entity.privatecar.PrivateCarInvoiceEntity;
import cn.com.hfga.manager.privatecar.PrivateCarApplyManage;
import cn.com.hfga.manager.privatecar.PrivateCarInvoiceManage;
/**
 * 
 * @author ysy
 *
 */
@Controller
public class PrivateCarInvoiceController {

	@Autowired
	private PrivateCarInvoiceManage privateCarInvoiceManage;
	
	@Autowired
	private PrivateCarApplyManage privateCarApplyManage;
	
	
	/**
	 * 淇濆瓨
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/savePrivateCarInvoice")
	@ResponseBody
	public Object savePrivateCarInvoice(PrivateCarInvoiceDTO dto){
		return privateCarInvoiceManage.Save(dto);
	}
	
	/**
	 * 璐㈠姟鏌ヨ鎵�鏈夆�滃緟鎶ラ攢鈥濆嚟鍗�
	 * @return
	 */
	@RequestMapping(value="/selectAllUnPass")
	@ResponseBody
	public Object selectAllUnPass(){
		return privateCarInvoiceManage.selectAllUnPass();
	}
	
	/**
	 * 鍗曚釜鍑崟鏍规嵁applyid杩斿洖澶氫釜绉佽溅瀵硅薄
	 * @param applyid
	 * @return
	 */
	@RequestMapping(value="/selectByApplyIds")
	@ResponseBody
	public Object selectByApplyId(String applyid){
		System.out.println( privateCarInvoiceManage.selectChildByApplyIdFinish1(applyid));
		return privateCarInvoiceManage.selectChildByApplyIdFinish1(applyid);
	}
	
	/**
	 * 鍗曚釜鍑崟鏍规嵁applyid杩斿洖澶氫釜绉佽溅瀵硅薄宸叉姤閿�瀹屾垚
	 * @param applyid
	 * @return
	 */
	@RequestMapping(value="/selectByApplyIdsFinish")
	@ResponseBody
	public Object selectChildByApplyIdFinish(String applyid){
		return privateCarInvoiceManage.selectChildByApplyIdFinish(applyid);
	}
	
	/**
	 * 璐㈠姟鏌ヨ鎵�鏈夆�滃凡鎶ラ攢鈥濆嚟鍗�
	 * @return
	 */
	@RequestMapping(value="/selectAllPassed")
	@ResponseBody
	public Object selectAllPassed(){
		return privateCarInvoiceManage.selectAllPassed();
	}
	
	/**
	 * 璐㈠姟濉啓鍑瘉鍙凤紝鏃ユ湡
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/updateInvoice")
	@ResponseBody
	public int updateInvoice(PrivateCarInvoiceDTO dto){
		return privateCarInvoiceManage.updateInvoice(dto);
	}
	
	/**
	 * 鏌ヨ鎵�鏈夊凡瀹屾垚鍑崟
	 * @return
	 */
	@RequestMapping(value="/selectPassed")
	@ResponseBody
	public Object selectPassed(){
		return privateCarInvoiceManage.selectPassed();
	}
	
	/**
	 * web鑾峰彇鎵�鏈夊凡瀹屾垚姹囨��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/PrivateCar/invoiceDisplay")
	@ResponseBody
	public Object invoiceDisplay(HttpServletRequest request){
		String page = request.getParameter("page");// 绗嚑椤�
		String rows = request.getParameter("rows");// 姣忛〉澶氬皯鏉�
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 椤垫暟
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 姣忛〉涓暟
		// 姣忛〉鐨勫紑濮嬭褰�
		int start = (intPage - 1) * number;
		List<PrivateCarInvoiceEntity> list = new ArrayList<PrivateCarInvoiceEntity>();
		list = privateCarInvoiceManage.invoiceDisplay(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 瀹氫箟map
		int total = privateCarInvoiceManage.getAllCount();
		jsonMap.put("total", total);// total瀛樻斁鎬昏褰曟暟
		jsonMap.put("rows", list);// rows閿紝瀛樻斁姣忛〉璁板綍list
		return jsonMap;
	}
	
	/**
	 * web鑾峰彇鎵�鏈夋湭鐧昏姹囨��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/PrivateCar/unSignInvoiceDisplay")
	@ResponseBody
	public Object unSignInvoiceDisplay(HttpServletRequest request){
		String page = request.getParameter("page");// 绗嚑椤�
		String rows = request.getParameter("rows");// 姣忛〉澶氬皯鏉�
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 椤垫暟
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 姣忛〉涓暟
		// 姣忛〉鐨勫紑濮嬭褰�
		int start = (intPage - 1) * number;
		List<PrivateCarInvoiceEntity> list = new ArrayList<PrivateCarInvoiceEntity>();
		list = privateCarInvoiceManage.unSignInvoiceDisplay(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 瀹氫箟map
		int total = privateCarInvoiceManage.getAllUnSignInvoiceCount();
		jsonMap.put("total", total);// total瀛樻斁鎬昏褰曟暟
		jsonMap.put("rows", list);// rows閿紝瀛樻斁姣忛〉璁板綍list
		return jsonMap;
	}
	
	/**
	 * web鑾峰彇鎵�鏈夋湭鎶ラ攢姹囨��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/PrivateCar/uninvoiceDisplay")
	@ResponseBody
	public Object uninvoiceDisplay(HttpServletRequest request){
		String page = request.getParameter("page");// 绗嚑椤�
		String rows = request.getParameter("rows");// 姣忛〉澶氬皯鏉�
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);// 椤垫暟
		int number = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);// 姣忛〉涓暟
		// 姣忛〉鐨勫紑濮嬭褰�
		int start = (intPage - 1) * number;
		List<PrivateCarInvoiceEntity> list = new ArrayList<PrivateCarInvoiceEntity>();
		list = privateCarInvoiceManage.uninvoiceDisplay(start, number);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 瀹氫箟map
		int total = privateCarInvoiceManage.ungetAllCount();
		jsonMap.put("total", total);// total瀛樻斁鎬昏褰曟暟
		jsonMap.put("rows", list);// rows閿紝瀛樻斁姣忛〉璁板綍list
		return jsonMap;
	}
	
	/**
	 * 鏍规嵁id鑾峰彇涓�鏉¤褰�
	 * @param applyid
	 * @return
	 */
	@RequestMapping(value="/PrivateCarInvoice/getOneRegister")
	@ResponseBody
	public Object getOneById(String applyid){
		return privateCarInvoiceManage.selectByApplyId(applyid);
	}
	
	//Web-瀵煎叆绉佽溅鍏敤鎶ラ攢鍗昬xcel
	@RequestMapping("/PrivateCar/importPrivateCarInvoiceExcel")
	@ResponseBody
	public Object importPrivateCar(@RequestParam(value = "fileImport", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model){
        String path = request.getSession().getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
		// 鑾峰彇鍘熷鏂囦欢鍚嶏紙涓嶅寘鎷墿灞曞悕锛�
		String originalFileName = fileName.substring(0, fileName.lastIndexOf("."));
		// 鑾峰彇鏃堕棿鎴�
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStamp = sdf.format(new Date());
		// 鏂版枃浠跺悕
		String newFileName = originalFileName + timeStamp
				+ fileName.substring(fileName.lastIndexOf("."), fileName.length());
		File targetFile = new File(path + "/excel", newFileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 淇濆瓨
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("fileUrl", request.getContextPath() + "/upload/excel/" + newFileName);
		// 缁濆璺緞
		String absolutePath = targetFile.getAbsolutePath();
		System.out.println(absolutePath);
		String result="";
		//IE8涓嬪墠绔毦浠ュ垽鏂ぇ灏忥紝鏀剧疆鍚庡彴  鏂囦欢澶у皬澶т簬100M杩斿洖2 
		long fileSize=targetFile.length();
		String size=String.valueOf(targetFile.length());
		if(fileSize<(1024*1024*100)){
//	 		璋冪敤灏唀xcel鏁版嵁淇濆瓨鍒版暟鎹簱鐨勬柟娉�
//		      result = String.valueOf(fillblankService.importFillblankExcel(absolutePath, Integer.parseInt(systemId)));
			result = String.valueOf(privateCarInvoiceManage.importPrivateCarInvoiceExcel(absolutePath));
		}else{
			result="2";
		}
		return result;
	}
	//Web-瀵煎嚭excel鏂规硶
	// Web-宸插鏍告嫑寰呮槑缁�-瀵煎嚭琛ㄥ崟
	@RequestMapping(value = "/PrivateCarInvoice/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, String number) {
		String[] nlist = number.split(","); // 鑾峰緱浼犻�掕繃鏉ョ殑number鍒楄〃
		// 鑾峰彇瀵煎嚭鏂囦欢澶�
		String path = request.getSession().getServletContext().getRealPath("/");
		// 鐢熸垚瀵煎嚭鐨勬枃浠跺悕
		Date dt = new Date();
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
		String date = matter.format(dt);
		String fileName = "已登记明细" + date + ".xlsx";
		String filePath = path + "/" + fileName;
		int flag = privateCarInvoiceManage.export(nlist, filePath);
		if (flag != 1) {
			return;
		}
		try {
			// 鏍规嵁涓嶅悓鐨勬祻瑙堝櫒澶勭悊涓嬭浇鏂囦欢鍚嶄贡鐮侀棶棰�
			String userAgent = request.getHeader("User-Agent");
			// 閽堝IE鎴栬�呮槸浠e涓哄唴鏍哥殑娴忚鍣�
			if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} else {
				// 闈濱E娴忚鍣ㄧ殑澶勭悊锛�
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			// 鑾峰彇涓�涓祦
			InputStream in = new FileInputStream(new File(filePath));
			// 璁剧疆涓嬭浇鐨勫搷搴斿ご
			response.setHeader("content-disposition", "attachment;fileName=" + fileName);
			response.setCharacterEncoding("UTF-8");
			// 鑾峰彇response瀛楄妭娴�
			OutputStream out = response.getOutputStream();
			byte[] b = new byte[1024];
			int len = -1;
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			// 鍏抽棴
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//鐢ㄦ埛鏌ョ湅宸查┏鍥炲嚟鍗�
	@RequestMapping(value="/getBackInvoiceList")
	@ResponseBody
	public Object selectBackList(String username){
		return privateCarInvoiceManage.selectBackList(username);
	}
	
	//鍒犻櫎涓�涓嚟鍗�
	@RequestMapping(value="/deleteByApplyid")
	@ResponseBody
	public Object delete(String applyid){
		return privateCarInvoiceManage.deleteByApplyid(applyid);
	}
}
