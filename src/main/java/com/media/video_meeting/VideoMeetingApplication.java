package com.media.video_meeting;

import com.media.video_meeting.log.LogAop;
import com.media.video_meeting.page.PageAop;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
@MapperScan("com.media.video_meeting.dao")
public class VideoMeetingApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(VideoMeetingApplication.class, args);
	}


	@Bean
	public PageAop getPageHelper(){
		return new PageAop();
	}

	@Bean
	public LogAop getLogAop(){
		return new LogAop();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(VideoMeetingApplication.class);
	}
}
