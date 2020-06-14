package com.sjtu.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = {"com.sjtu.trade.*"})
@EnableScheduling
@ComponentScan("com.sjtu.trade.*")
@EnableAsync
public class TraderApplication {
	public static void main(String[] args) {

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(TraderApplication.class, args);
	}

}
