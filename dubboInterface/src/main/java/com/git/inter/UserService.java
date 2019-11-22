package com.git.inter;


import com.git.model.User;

public interface UserService {

    User getUserRoleByUser(User user);

    User getUserById(Integer uid);
}
