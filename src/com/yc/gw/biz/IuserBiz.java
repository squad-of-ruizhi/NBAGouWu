package com.yc.gw.biz;

import com.yc.gw.entity.User;

public interface IuserBiz {

	User findSingle(String tel, String pwd);

}
