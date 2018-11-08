package com.media.video_meeting;

import com.media.video_meeting.page.PageAop;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
@MapperScan("com.media.video_meeting.dao")
public class VideoMeetingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoMeetingApplication.class, args);
	}


	@Bean
	public PageAop getPageHelper(){
		return new PageAop();
	}
}
