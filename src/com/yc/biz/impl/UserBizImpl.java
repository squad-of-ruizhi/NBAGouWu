package com.yc.biz.impl;

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

}
