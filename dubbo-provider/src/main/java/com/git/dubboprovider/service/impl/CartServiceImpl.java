package com.git.dubboprovider.service.impl;

import com.git.dubboprovider.util.AnnotationUtil;
import com.git.service.CartService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Service(version = "1.0.0")
@Service(group = "1")
public class CartServiceImpl implements CartService {
	private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
	public String findCartByUserId(Long userId) {
		Service annotation = AnnotationUtil.getAnnotation(this, Service.class);
		logger.info("当前调用服务版本号为：{}，分组为：{}",annotation.version(),annotation.group());
		return "购物车没有东西了"+userId;
	}


}
