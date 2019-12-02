package com.git.inter;

import com.git.model.Role;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public interface RoleService {

    Role checkRole(Role role, UnaryOperator<Role> function);
}
