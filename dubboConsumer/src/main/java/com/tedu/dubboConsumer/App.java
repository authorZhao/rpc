package com.tedu.dubboConsumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tedu.service.CartService;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-consumer.xml");

		// 从spring容器中取对象
		CartService cartService = (CartService) context.getBean("cartService");
		System.out.println("start consumer");
		while (true) {
			try {
//调用服务
				String result=cartService.findCartByUserId(8L);
				System.out.println("服务消费者/客户端："+result);
				Thread.currentThread().sleep(5000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
