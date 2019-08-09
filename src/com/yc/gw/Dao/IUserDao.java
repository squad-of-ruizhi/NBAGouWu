package com.yc.gw.Dao;

import com.yc.gw.entity.User;

public interface IUserDao {

	User findSingle(String tel, String pwd);

}
