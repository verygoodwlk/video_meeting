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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.URISyntaxException;

@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableScheduling
@EnableAsync
@MapperScan("com.media.video_meeting.dao")
public class VideoMeetingApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(VideoMeetingApplication.class, args);
	}

	@Value("${golang.serverpath}")
	private String url;

	@Value("${server.port}")
	private int sslPort;
	@Value("${server.httpport}")
	private int httpPort;

	@Value("${server.ssl.enabled}")
	private boolean isSSL;

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

	//http 转发到 https
//	@Bean
//	public Connector connector(){
//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setScheme("http");
//		connector.setPort(httpPort);
//		connector.setSecure(false);
//		connector.setRedirectPort(sslPort);
//		return connector;
//	}

//	@Bean
//	public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector){
//		if(isSSL) {
//			TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//				@Override
//				protected void postProcessContext(Context context) {
//					SecurityConstraint securityConstraint = new SecurityConstraint();
//					securityConstraint.setUserConstraint("CONFIDENTIAL");
//					SecurityCollection collection = new SecurityCollection();
//					collection.addPattern("/*");
//					securityConstraint.addCollection(collection);
//					context.addConstraint(securityConstraint);
//				}
//			};
//			tomcat.addAdditionalTomcatConnectors(connector);
//			return tomcat;
//		}
//		return null;
//	}
}
