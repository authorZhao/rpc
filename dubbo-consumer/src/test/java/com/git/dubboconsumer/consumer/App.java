package com.git.dubboconsumer.consumer;

import com.git.service.CartService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("resources/applicationContext-consumer.xml");

		// 从spring容器中取对象
		CartService cartService = (CartService) context.getBean("cartService");
		//CartSesriveImpl cartSesriveImpl = (CartSesriveImpl) context.getBean("cartSesriveImpl");
		//CartSesriveImpl
		System.out.println("start consumer");
		while (true) {
			try {
//调用服务
				//System.out.printf("", new CartSesriveImpl().findCartByUserId(8l));


				String result=cartService.findCartByUserId(8L);
				System.out.println("服务消费者/客户端："+result);
				Thread.currentThread().sleep(5000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
