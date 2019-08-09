package com.yc.biz.impl;

import java.util.List;
import java.util.Map;

import com.yc.dao.impl.UserDaoImpl;
import com.yc.gw.Dao.IUserDao;
import com.yc.gw.biz.IuserBiz;
import com.yc.gw.entity.User;

public class UserBizImpl implements IuserBiz {

	@Override
	public User findSingle(String tel, String pwd) {
		IUserDao dao=new UserDaoImpl();
		 return dao.findSingle(tel,pwd);
	}

	@Override
	public List<Map<String, String>> findAll(int usid) {
		IUserDao dao=new UserDaoImpl();
		return dao.findAll(usid);
	}

	@Override
	public int alterPwd(int usid, String pwd) {
		IUserDao dao=new UserDaoImpl();
		return dao.alterPwd(usid, pwd);
	}

	@Override
	public int insertInfo(int usid, String birthday, String sex, String duration, String team, String star, String mail) {
		IUserDao dao=new UserDaoImpl();
		return dao.insertInfo(usid, birthday, sex, duration, team, star, mail);
	}

}
