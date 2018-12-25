package cn.com.hfga.push.android;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import cn.com.hfga.push.AndroidNotification;
import cn.com.hfga.push.AndroidNotification.AfterOpenAction;
import cn.com.hfga.push.IPushUtil;
import cn.com.hfga.push.PushUtil;

/**
 * Android端推送类
 * @author xyc
 *
 */

@Service("androidPushUtil")
public class AndroidPushUtil extends PushUtil implements IPushUtil{
	
	private AndroidPushEntity pushEntity;
	
	
	public AndroidPushEntity getPushEntity() {
		return pushEntity;
	}


	public void setPushEntity(AndroidPushEntity pushEntity) {
		this.pushEntity = pushEntity;
	}


	public AndroidPushUtil(String key, String secret) {
		super(key, secret);
		// TODO Auto-generated constructor stub
	}
	
	public AndroidPushUtil(){
		
	}
	
	@Override
	public void sendBroadcast(){
		try{
			AndroidBroadcast broadcast = new AndroidBroadcast(appkey,appMasterSecret);
			broadcast.setTicker(pushEntity.getTriker());
			broadcast.setTitle(pushEntity.getTitle());
			broadcast.setText(pushEntity.getText());
			broadcast.goAppAfterOpen();
			broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
			// TODO Set 'production_mode' to 'false' if it's a test device. 
			// For how to register a test device, please see the developer doc.
			broadcast.setProductionMode();
			// Set customized fields
			//broadcast.setExtraField("test", "helloworld");
			client.send(broadcast);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	@Override
	public void sendUnicast(){
		try{
			AndroidUnicast unicast = new AndroidUnicast(appkey,appMasterSecret);
			// TODO Set your device token
			unicast.setDeviceToken(pushEntity.getDevcie_token());
			unicast.setTicker(pushEntity.getTriker());
			unicast.setTitle(pushEntity.getTitle());
			unicast.setText(pushEntity.getText());
			unicast.goAppAfterOpen();
			unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
			// TODO Set 'production_mode' to 'false' if it's a test device. 
			// For how to register a test device, please see the developer doc.
			unicast.setProductionMode();
			// Set customized fields
			//unicast.setExtraField("test", "helloworld");
			client.send(unicast);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void sendGroupcast(){
		try{
			AndroidGroupcast groupcast = new AndroidGroupcast(appkey,appMasterSecret);
			/*  TODO
			 *  Construct the filter condition:
			 *  "where": 
			 *	{
	    	 *		"and": 
	    	 *		[
	      	 *			{"tag":"test"},
	      	 *			{"tag":"Test"}
	    	 *		]
			 *	}
			 */
			JSONObject filterJson = new JSONObject();
			JSONObject whereJson = new JSONObject();
			JSONArray tagArray = new JSONArray();
			JSONObject testTag = new JSONObject();
			JSONObject TestTag = new JSONObject();
			testTag.put("tag", "test");
			TestTag.put("tag", "Test");
			tagArray.put(testTag);
			tagArray.put(TestTag);
			whereJson.put("and", tagArray);
			filterJson.put("where", whereJson);
			System.out.println(filterJson.toString());
			
			groupcast.setFilter(filterJson);
			groupcast.setTicker( "Android groupcast ticker");
			groupcast.setTitle(  "中文的title");
			groupcast.setText(   "Android groupcast text");
			groupcast.goAppAfterOpen();
			groupcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
			// TODO Set 'production_mode' to 'false' if it's a test device. 
			// For how to register a test device, please see the developer doc.
			groupcast.setProductionMode();
			client.send(groupcast);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void sendCustomizedcast(){
		try{
			AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey,appMasterSecret);
			// TODO Set your alias here, and use comma to split them if there are multiple alias.
			// And if you have many alias, you can also upload a file containing these alias, then 
			// use file_id to send customized notification.
			customizedcast.setAlias(pushEntity.getAlias(), "ALIAS_TYPE_HFGA");
			customizedcast.setTicker(pushEntity.getTriker());
			customizedcast.setTitle(pushEntity.getTitle());
			customizedcast.setText(pushEntity.getText());
			customizedcast.setAfterOpenAction(AfterOpenAction.go_activity);
			customizedcast.setActivity(pushEntity.getAfter_open());
			//customizedcast.goAppAfterOpen();
			customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
			// TODO Set 'production_mode' to 'false' if it's a test device. 
			// For how to register a test device, please see the developer doc.
			customizedcast.setProductionMode();
			client.send(customizedcast);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	@Override
	public void sendCustomizedcastFile(){
		try{
			AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey,appMasterSecret);
			// TODO Set your alias here, and use comma to split them if there are multiple alias.
			// And if you have many alias, you can also upload a file containing these alias, then 
			// use file_id to send customized notification.
			String fileId = client.uploadContents(appkey,appMasterSecret,"aa"+"\n"+"bb"+"\n"+"alias");
			customizedcast.setFileId(fileId, "ALIAS_TYPE_HFGA");
			customizedcast.setTicker( pushEntity.getTriker());
			customizedcast.setTitle(pushEntity.getTitle());
			customizedcast.setText(pushEntity.getText());
			//customizedcast.goAppAfterOpen();
			customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
			// TODO Set 'production_mode' to 'false' if it's a test device. 
			// For how to register a test device, please see the developer doc.
			customizedcast.setProductionMode();
			client.send(customizedcast);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void sendFilecast() {
		try{
			AndroidFilecast filecast = new AndroidFilecast(appkey,appMasterSecret);
			// TODO upload your device tokens, and use '\n' to split them if there are multiple tokens 
			String fileId = client.uploadContents(appkey,appMasterSecret,"aa"+"\n"+"bb");
			filecast.setFileId( fileId);
			filecast.setTicker(pushEntity.getTriker());
			filecast.setTitle(pushEntity.getTitle());
			filecast.setText(pushEntity.getText());
			filecast.goAppAfterOpen();
			filecast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
			client.send(filecast);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//测试Spring注解
	public void test(){
		System.out.print("测试成功");
	}

	
}