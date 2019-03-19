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

	@Resource(name = "webconLogin")
	private SocketMsgHandler socketMsgHandler;

	@Test
	public void contextLoads() {
//		socketMsgHandler.handler("{\"id\":\"playMusicTask\",\"account\":\"admin\",\"taskid\":\"01a6fe40-12f2-4f3c-9fe1-9c402e225c7f\",\"taskname\":\"aaa\",\"volume\":0,\"mp3\":null,\"terminal\":[\"1\",\"6\",\"2\",\"3\",\"4\"]}");
		socketMsgHandler.handler("{\"id\":\"webconLogin\",\"serverip\":\"192.168.1.186\",\"ip\":\"192.168.1.133\",\"name\":\"webconsole\",\"terminalname\":\"wang\",\"mac\":\"10:D0:7A:21:9D:D0\",\"account\":\"admin\", \"passwd\":\"123456\"}");
	}

}
