package com.git.dubboprovider.service.impl;

import com.git.dubboprovider.util.AnnotationUtil;
import com.git.service.CartService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Service(version = "2.0.0")
@Service(group = "2")
public class DanCartServiceImpl implements CartService {

	private static final Logger logger = LoggerFactory.getLogger(DanCartServiceImpl.class);
	public String findCartByUserId(Long userId) {
		Service annotation = AnnotationUtil.getAnnotation(this, Service.class);
		logger.info("当前调用服务版本号为：{}，分组为：{}",annotation.version(),annotation.group());
		return "危化品，不允许随便购买"+userId;
	}


}
