package com.git.dubboconsumer.consumer;

import com.git.inter.UserService;
import com.git.model.User;
import com.git.service.CartService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-consumer.xml");

		// 从spring容器中取对象
		CartService cartService = (CartService) context.getBean("cartService");
		UserService userService =  context.getBean(UserService.class);
		//CartSesriveImpl cartSesriveImpl = (CartSesriveImpl) context.getBean("cartSesriveImpl");
		//CartSesriveImpl
		System.out.println("start consumer");



		while (true) {
			try {
//调用服务
				//System.out.printf("", new CartSesriveImpl().findCartByUserId(8l));

				String result=cartService.findCartByUserId(8L);
				System.out.println("服务消费者/客户端："+result);

				System.out.println("===========开始异步调用===============");
				User userById = userService.getUserById(8);
				System.out.println(userById);
				System.out.println("===========异步调用结束===============");


				Thread.currentThread().sleep(20000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
