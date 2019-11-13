package com.tedu.dubboProvider;

import com.tedu.service.CartService;

public class CartServiceImpl implements CartService{

	public String findCartByUserId(Long userId) {
		return "提供者1 返回"+userId;
	}
	

}
