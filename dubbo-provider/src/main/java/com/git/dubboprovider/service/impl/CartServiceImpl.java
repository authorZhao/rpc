package com.git.dubboprovider.service.impl;

import com.git.service.CartService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class CartServiceImpl implements CartService {

	public String findCartByUserId(Long userId) {
		return "提供者1 返回"+userId;
	}


}
