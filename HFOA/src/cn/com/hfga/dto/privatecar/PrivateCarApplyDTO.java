package cn.com.hfga.dto.privatecar;


/**
 * @author xinyc
 * @since 2017-02-28
 * 私家车借车请求DTO
 *  * 
 * */

public class  PrivateCarApplyDTO{
	
	
	//不用传
	private String applyid;
	
	private String department;
	
	private String applyman;

	private String approveman;
	
	private String usercartime;
	
	private String reason;

	private String beginaddress;
	
	private String passaddress;

	private String destination;
	
	//不用传
	private String status;
	
	private Double  singlelength;
	
	private Double surelength;

	private Double countlength;
	
	//不用传
	private String applytime;
	
	//不用传
	private String approvetime;
	
	private String ifperform;  //是否执行
	
	private String waymodel;
	
	private String waydetail;
	
	private String doublelength;
	
	private String endlength;
	
	private String ifpass;
	
	private String beforedate;
	
	private String ifbefore;

	//新加
	private String ifsub;
	
	private String approveman2;//报销审核人
	
	private String sum;//金额
	
	private String paidtime;//登记时间
	
	private String danhao;//凭单号
	
	private String subtime;//提交时间
	
	public String getSubtime() {
		return subtime;
	}

	public void setSubtime(String subtime) {
		this.subtime = subtime;
	}

	public String getDanhao() {
		return danhao;
	}

	public void setDanhao(String danhao) {
		this.danhao = danhao;
	}

	public String getPaidtime() {
		return paidtime;
	}

	public void setPaidtime(String paidtime) {
		this.paidtime = paidtime;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getApproveman2() {
		return approveman2;
	}

	public void setApproveman2(String approveman2) {
		this.approveman2 = approveman2;
	}

	public String getIfsub() {
		return ifsub;
	}

	public void setIfsub(String ifsub) {
		this.ifsub = ifsub;
	}

	public String getApprovetime() {
		return approvetime;
	}

	public void setApprovetime(String approvetime) {
		this.approvetime = approvetime;
	}


	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getApplyman() {
		return applyman;
	}

	public void setApplyman(String applyman) {
		this.applyman = applyman;
	}

	public String getApproveman() {
		return approveman;
	}

	public void setApproveman(String approveman) {
		this.approveman = approveman;
	}

	public String getUsercartime() {
		return usercartime;
	}

	public void setUsercartime(String usercartime) {
		this.usercartime = usercartime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBeginaddress() {
		return beginaddress;
	}

	public void setBeginaddress(String beginaddress) {
		this.beginaddress = beginaddress;
	}

	public String getPassaddress() {
		return passaddress;
	}

	public void setPassaddress(String passaddress) {
		this.passaddress = passaddress;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getSinglelength() {
		return singlelength;
	}

	public void setSinglelength(Double singlelength) {
		this.singlelength = singlelength;
	}

	public Double getSurelength() {
		return surelength;
	}

	public void setSurelength(Double surelength) {
		this.surelength = surelength;
	}

	public Double getCountlength() {
		return countlength;
	}

	public void setCountlength(Double countlength) {
		this.countlength = countlength;
	}

	public String getApplyid() {
		return applyid;
	}

	public void setApplyid(String applyid) {
		this.applyid = applyid;
	}

	public String getApplytime() {
		return applytime;
	}

	public void setApplytime(String applytime) {
		this.applytime = applytime;
	}

	public String getIfperform() {
		return ifperform;
	}

	public void setIfperform(String ifperform) {
		this.ifperform = ifperform;
	}

	

	public String getWaymodel() {
		return waymodel;
	}

	public void setWaymodel(String waymodel) {
		this.waymodel = waymodel;
	}

	public String getWaydetail() {
		return waydetail;
	}

	public void setWaydetail(String waydetail) {
		this.waydetail = waydetail;
	}

	public String getDoublelength() {
		return doublelength;
	}

	public void setDoublelength(String doublelength) {
		this.doublelength = doublelength;
	}

	public String getEndlength() {
		return endlength;
	}

	public void setEndlength(String endlength) {
		this.endlength = endlength;
	}

	public String getIfpass() {
		return ifpass;
	}

	public void setIfpass(String ifpass) {
		this.ifpass = ifpass;
	}

	public String getBeforedate() {
		return beforedate;
	}

	public void setBeforedate(String beforedate) {
		this.beforedate = beforedate;
	}

	public String getIfbefore() {
		return ifbefore;
	}

	public void setIfbefore(String ifbefore) {
		this.ifbefore = ifbefore;
	}



	
	
}
