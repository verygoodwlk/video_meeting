package com.media.video_meeting;

import com.media.video_meeting.log.LogAop;
import com.media.video_meeting.page.PageAop;
import com.media.video_meeting.websocket.MyWebSocket;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.URISyntaxException;

@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableAsync
@MapperScan("com.media.video_meeting.dao")
public class VideoMeetingApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(VideoMeetingApplication.class, args);
	}

	@Value("${golang.serverpath}")
	private String url;

	@Bean
	public PageAop getPageHelper(){
		return new PageAop();
	}

	@Bean
	public LogAop getLogAop(){
		return new LogAop();
	}


	@Bean
	public MyWebSocket getWebSocket(){
		try {
			return  new MyWebSocket(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(VideoMeetingApplication.class);
	}
}
