package cn.com.hfga.dto.privatecar;

/**
 * 
 * ˽�����õ�����dto
 * @author xinyc
 * @since 2017-02-28
 *
 */
public class ApproveDTO {

	private String applyid;  //ID
	
	private String approveman;  //������
	
	//���ô�
	private String approvetime;  //����ʱ��

	public String getApplyid() {
		return applyid;
	}

	public void setApplyid(String applyid) {
		this.applyid = applyid;
	}

	public String getApproveman() {
		return approveman;
	}

	public void setApproveman(String approveman) {
		this.approveman = approveman;
	}

	public String getApprovetime() {
		return approvetime;
	}

	public void setApprovetime(String approvetime) {
		this.approvetime = approvetime;
	}
	
	
}
