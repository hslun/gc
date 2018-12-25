package cn.com.hfga.push;
/**
 * Õ∆ÀÕ≥ÈœÛ¿‡
 * @author xyc
 * @time 2017-02-09
 */
public abstract class PushUtil{
	public String appkey = "5593b87d67e58e4b840001c1";
	public String appMasterSecret = "crvad4wibuoi974zie3cclcjhp7b6zru";
	public String timestamp = null;
	public PushClient client = new PushClient();
	
	public PushUtil(String key, String secret) {
		try {
			appkey = key;
			appMasterSecret = secret;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public PushUtil(){}
}