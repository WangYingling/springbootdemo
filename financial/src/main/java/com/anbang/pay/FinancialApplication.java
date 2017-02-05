package com.anbang.pay;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan//启动Servlet自动扫描
public class FinancialApplication {

	public static void main(String[] args) {
//		System.out.print("");
		SpringApplication app=new SpringApplication(FinancialApplication.class);
		app.setBannerMode(Banner.Mode.CONSOLE);
		app.run(args);
//		SpringApplication.run(FinancialApplication.class, args);
	}
}
