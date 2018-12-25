package cn.com.hfga.controller.common;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
/**
 * 推送消息类
 * @author xyc
 *
 */
public class JpushMethod {
	
	//推送目标和信息
	public static PushPayload buildPushObject_all_alias_alert2(String username,String Info) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(username))
                .setNotification(Notification.alert(Info))
                .build();
    }
	
	//推送操作
		public static void tuiSong(String username,String info) throws APIConnectionException, APIRequestException
		{
//			JPushClient jPushClient=new JPushClient("edf33a48bcffdd579c976285", "73e5cf377c826fdef9273a8b",86400);
//			System.out.print(username);
//			System.out.print(info);
//			PushPayload payload=buildPushObject_all_alias_alert2(username,info);
//			PushResult msgResult = jPushClient.sendPush(payload);
//			if (null != msgResult) {
//				System.out.print("发送成功");
//			} else {
//			    System.out.println("无法获取数据");
//			}
		}
  
}
