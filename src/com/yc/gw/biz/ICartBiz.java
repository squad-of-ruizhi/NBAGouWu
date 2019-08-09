package com.yc.gw.biz;

import java.util.List;

import com.yc.gw.entity.CartInfo;

public interface ICartBiz {
	public List<CartInfo> findAll(String usid);
	
	public int add(int spId, int usid, int shopid);
	
	public int sub(int spId, int usid, int shopid);
	
	public int del(int spId, int usid, int shopid);
	
	public int delAll();
	
	public int addTemp(int spId, int usid, String tolprice, int samount);

	public int addcar(String usid, String spId, String samount);

	public List<CartInfo> selSp(String usid);

	public int updateo(String usid);

	public int addoneSp(String spId, String usid, String tolprice, String samount);

	public List<CartInfo> selhistory(String usid);

	public int deletehis(String tempaId);


}
