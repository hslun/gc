package cn.com.hfga.push.ios;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import cn.com.hfga.push.IPushUtil;
import cn.com.hfga.push.PushUtil;
import cn.com.hfga.push.ios.IOSBroadcast;
import cn.com.hfga.push.ios.IOSCustomizedcast;
import cn.com.hfga.push.ios.IOSFilecast;
import cn.com.hfga.push.ios.IOSGroupcast;
import cn.com.hfga.push.ios.IOSUnicast;

@Service("iOSPushUtil")
public class IOSPushUtil  extends PushUtil implements IPushUtil{
	
	public IOSPushUtil(){
		super();
		appkey = "58a3ffb08f4a9d5e7900104a";
		appMasterSecret = "dpxiwcknljrmohhdep1k2wmltoe488po";
	}
	
	public IOSPushUtil(String key, String secret){
		super(key,secret);
	}
	
	private IOSPushEntity iOSPushEntity;
	
	
	public IOSPushEntity getiOSPushEntity() {
		return iOSPushEntity;
	}

	public void setiOSPushEntity(IOSPushEntity iOSPushEntity) {
		this.iOSPushEntity = iOSPushEntity;
	}

	
	@Override
	public void sendBroadcast(){
		try{
			IOSBroadcast broadcast = new IOSBroadcast(appkey,appMasterSecret);

			broadcast.setAlert(iOSPushEntity.getAlert());
			broadcast.setBadge(0);
			broadcast.setSound(iOSPushEntity.getSound());
			// TODO set 'production_mode' to 'true' if your app is under production mode
			broadcast.setTestMode();
			// Set customized fields
			//broadcast.setCustomizedField("test", "helloworld");
			client.send(broadcast);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void sendUnicast(){
		try{
			IOSUnicast unicast = new IOSUnicast(appkey,appMasterSecret);
			// TODO Set your device token
			unicast.setDeviceToken(iOSPushEntity.getDevice_token());
			unicast.setAlert(iOSPushEntity.getAlert());
			unicast.setBadge( 0);
			unicast.setSound(iOSPushEntity.getSound());
			// TODO set 'production_mode' to 'true' if your app is under production mode
			unicast.setTestMode();
			// Set customized fields
			//unicast.setCustomizedField("test", "helloworld");
			client.send(unicast);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void sendGroupcast(){
		try{
			IOSGroupcast groupcast = new IOSGroupcast(appkey,appMasterSecret);
			/*  TODO
			 *  Construct the filter condition:
			 *  "where": 
			 *	{
	    	 *		"and": 
	    	 *		[
	      	 *			{"tag":"iostest"}
	    	 *		]
			 *	}
			 */
			JSONObject filterJson = new JSONObject();
			JSONObject whereJson = new JSONObject();
			JSONArray tagArray = new JSONArray();
			JSONObject testTag = new JSONObject();
			testTag.put("tag", "iostest");
			tagArray.put(testTag);
			whereJson.put("and", tagArray);
			filterJson.put("where", whereJson);
			System.out.println(filterJson.toString());
			
			// Set filter condition into rootJson
			groupcast.setFilter(filterJson);
			groupcast.setAlert("IOS 组播测试");
			groupcast.setBadge( 0);
			groupcast.setSound( "default");
			// TODO set 'production_mode' to 'true' if your app is under production mode
			groupcast.setTestMode();
			client.send(groupcast);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void sendCustomizedcast(){
		try{
			appkey="58a3ffb08f4a9d5e7900104a";
			appMasterSecret="dpxiwcknljrmohhdep1k2wmltoe488po";
			IOSCustomizedcast customizedcast = new IOSCustomizedcast(appkey,appMasterSecret);
			// TODO Set your alias and alias_type here, and use comma to split them if there are multiple alias.
			// And if you have many alias, you can also upload a file containing these alias, then 
			// use file_id to send customized notification.
			customizedcast.setAlias(iOSPushEntity.getAlias(), "ALIAS_TYPE_HFGA");
			customizedcast.setAlert(iOSPushEntity.getAlert());
			customizedcast.setBadge( 0);
			customizedcast.setSound(iOSPushEntity.getSound());
			// TODO set 'production_mode' to 'true' if your app is under production mode
			customizedcast.setTestMode();
			client.send(customizedcast);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void sendFilecast(){
		try{
			IOSFilecast filecast = new IOSFilecast(appkey,appMasterSecret);
			// TODO upload your device tokens, and use '\n' to split them if there are multiple tokens 
			String fileId = client.uploadContents(appkey,appMasterSecret,"aa"+"\n"+"bb");
			filecast.setFileId( fileId);
			filecast.setAlert(iOSPushEntity.getAlert());
			filecast.setBadge( 0);
			filecast.setSound(iOSPushEntity.getSound());
			// TODO set 'production_mode' to 'true' if your app is under production mode
			filecast.setTestMode();
			client.send(filecast);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	@Override
	public void sendCustomizedcastFile() {
		// TODO Auto-generated method stub
		//IOS中不提此方法
	}
	
	public void test(){
		System.out.print("注入成功");
	}

}
