package com.immoc.dao;

import com.immoc.vo.User;

import java.util.List;

public interface UserDao {
    User getUserByUserName(String userName);

    List<String> queryRolesByUserName(String userName);
}
