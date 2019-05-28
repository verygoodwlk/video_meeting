package com.media.video_meeting;

import com.media.video_meeting.websocket.SocketMsgHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoMeetingApplicationTests {

	@Resource(name = "online")
	private SocketMsgHandler socketMsgHandler;

	@Test
	public void contextLoads() {
//		socketMsgHandler.handler("{\"id\":\"playMusicTask\",\"account\":\"admin\",\"taskid\":\"01a6fe40-12f2-4f3c-9fe1-9c402e225c7f\",\"taskname\":\"aaa\",\"volume\":0,\"mp3\":null,\"terminal\":[\"1\",\"6\",\"2\",\"3\",\"4\"]}");
//		socketMsgHandler.handler("{\"id\":\"webconLogin\",\"serverip\":\"192.168.1.186\",\"ip\":\"192.168.1.133\",\"name\":\"webconsole\",\"terminalname\":\"wang\",\"mac\":\"10:D0:7A:21:9D:D0\",\"account\":\"admin\", \"passwd\":\"123456\"}");

//		socketMsgHandler.handler("{\"id\":\"saveRecord\",\"host\":\"6\",\"users\":[\"6\",\"1\"],\"type\":1,\"starttime\":\"2019-04-23 17:14:13\",\"endtime\":\"2019-04-23 17:14:18\",\"record\":\"6_2019-04-23_17-14-13.webm\"}");

//		socketMsgHandler.handler("{\"id\":\"getRecordlist\", \"terminal\":\"1\"}");

//		socketMsgHandler.handler("{\"id\":\"online\",\"userid\":\"13\",\"terminalname\":\"13\",\"mac\":\"28:ED:E0:73:C1:3A\",\"ip\":\"192.168.1.43\",\"serverip\":\"192.168.1.186\",\"volume\":\"\",\"intercomEnable\":\"\",\"cameraIntercomEnable\":\"\",\"updateStatus\":\"\",\"dhcpStatus\":\"\",\"productsModel\":\"\"}");
//		socketMsgHandler.handler("{\"id\": \"renameid\", \"oldid\":\"5\", \"newid\":\"3\"}");

		socketMsgHandler.handler("{\"id\":\"online\",\"userid\":\"58\",\"terminalname\":\"58\",\"mac\":\"28:ED:E0:73:C1:58\",\"ip\":\"192.168.1.16\",\"serverip\":\"192.168.1.186\",\"volume\":\"\",\"intercomEnable\":\"\",\"cameraIntercomEnable\":\"\",\"updateStatus\":\"\",\"dhcpStatus\":\"\",\"productsModel\":\"\"}");
	}

}
