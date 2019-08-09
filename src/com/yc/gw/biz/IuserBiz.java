package com.yc.gw.biz;

import java.util.List;
import java.util.Map;

import com.yc.gw.entity.User;

public interface IuserBiz {

	User findSingle(String tel, String pwd);

    public List<Map<String, String>> findAll(int usid);
	
	public int alterPwd(int usid,String pwd);
	
	public int insertInfo(int usid, String birthday,String sex,String duration, String team, String star, String mail);
	
}
