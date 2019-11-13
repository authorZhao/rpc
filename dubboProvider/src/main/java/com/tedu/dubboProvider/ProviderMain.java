package com.tedu.dubboProvider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderMain {

	public static void main(String[] args) {

		//spring框架
		ClassPathXmlApplicationContext context=new
				ClassPathXmlApplicationContext("applicationContext-provider.xml");
		System.out.println("start provider 1");
		context.start();
		while(true)
		{}
	}

}
