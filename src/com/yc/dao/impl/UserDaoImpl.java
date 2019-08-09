package com.yc.dao.impl;


import com.yc.gw.Dao.DBHelper;
import com.yc.gw.Dao.IUserDao;
import com.yc.gw.entity.User;

public class UserDaoImpl implements IUserDao {

	@Override
	public User  findSingle(String tel, String pwd) {
		DBHelper db=new DBHelper();
        String sql="select usid,tel,pwd from user where tel = ? and pwd = ?;";
		return db.find(User.class,sql,tel,pwd);
	
	}

	}
