package com.git.dubboprovider.service.impl;

import com.git.inter.CallbackListener;
import com.git.inter.RoleService;
import com.git.model.Role;
import org.apache.dubbo.config.annotation.Argument;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@Service(methods = @Method(name = "checkRole2",
        arguments = @Argument(index = 1,callback = true)),
        callbacks = 1)
public class RoleServiceImpl implements RoleService {


    @Override
    public Role checkRole(Role role, UnaryOperator<Role> function) {
        if(role==null) throw new RuntimeException("角色不可为空");
        if(role.getName().equals("财务")){
            System.out.println("角色名字应该为为管理员");
            return function.apply(role);
            //role.setName(管理员);
        }
        return role;
    }

    @Override
    public Role checkRole2(Role role, Function<Role, Role> function) {
        System.out.println("function函数处理");
        if(role==null) throw new RuntimeException("角色不可为空");
        if(role.getName().equals("财务")){
            System.out.println("角色名字应该为为管理员");
            return function.apply(role);
            //role.setName(管理员);
        }
        return role;
    }

    @Override
    public Role checkRole3(Role role, CallbackListener callbackListener) {
        System.out.println("callbackListener函数处理");
        if(role==null) throw new RuntimeException("角色不可为空");
        if(role.getName().equals("财务")){
            System.out.println("角色名字应该为为管理员");
            callbackListener.changed(role.getName());
            return role;
            //role.setName(管理员);
        }
        return role;
    }

}
