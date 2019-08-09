package com.yc.dao.impl;


import java.util.List;
import java.util.Map;

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

	public List<Map<String, String>> findAll(int usid) {
		DBHelper db=new DBHelper();
		String sql="select * from user where usid=?";
		return db.find(sql, usid);
	}

	@Override
	public int alterPwd(int usid, String pwd) {
		DBHelper db=new DBHelper();
		String sql="update user set pwd=? where usid=?";
		return db.update(sql, pwd, usid);
	}

	@Override
	public int insertInfo(int usid, String birthday, String sex, String duration, String team, String star, String mail) {
		DBHelper db=new DBHelper();
		String sql="update user set birthday=?, sex=?, duration=?, team=?, star=?, mail=? where usid=?";
		return db.update(sql, birthday, sex, duration, team, star, mail, usid);
	}
	
	}
