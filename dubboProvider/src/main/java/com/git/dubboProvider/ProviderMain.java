package com.git.dubboProvider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderMain {

	public static void main(String[] args) {

		//spring框架
		ClassPathXmlApplicationContext context=new
				ClassPathXmlApplicationContext("applicationContext-provider.xml");
		System.out.println("服务启动");
		context.start();
		while(true)
		{}
	}

}
