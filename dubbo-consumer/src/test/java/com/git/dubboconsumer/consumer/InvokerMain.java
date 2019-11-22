package com.git.dubboconsumer.consumer;

import com.git.dubboconsumer.util.RpcInvokeStrategy;
import com.git.model.ApiResult;
import com.git.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class InvokerMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("resources/applicationContext-consumer.xml");
        ApiResult api = new ApiResult(0, "msg", null);
        Map<String, Object> map = new HashMap<>();
        map.put("className","com.git.soc.inter.impl.UserServiceImpl");
        map.put("methodName","getUserRoleByUser");


        User user = new User();
        user.setId(2);
        user.setAge(25);
        user.setName("张三");
        user.setSex("男");
        map.put("param",user);
        api.setMap(map);
        System.out.println("start consumer");
//调用服务
        api = RpcInvokeStrategy.startInvoker(api);
        System.out.println("服务消费者/客户端："+api.toString());
    }

}
