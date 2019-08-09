package com.yc.biz.impl;

import java.util.List;

import com.yc.dao.impl.CartDaoImpl;
import com.yc.gw.Dao.ICartDao;
import com.yc.gw.biz.ICartBiz;
import com.yc.gw.entity.CartInfo;

public class CartBizImpl implements ICartBiz {
	private CartDaoImpl ci;
	@Override
	public List<CartInfo> findAll(String usid) {
		ci = new CartDaoImpl();
		return ci.findAll(usid);
	}
	@Override
	public int add(int spId, int usid, int shopid) {
		ci = new CartDaoImpl();
		return ci.add(usid, spId, shopid);
	}
	
	@Override
	public int sub(int spId, int usid, int shopid) {
		ci = new CartDaoImpl();
		return ci.sub(usid, spId, shopid);
	}
	
	public int del(int spId, int usid, int shopid) {
		ci = new CartDaoImpl();
		return ci.del(usid, spId, shopid);
	}
	@Override
	public int delAll() {
		ci = new CartDaoImpl();
		return ci.delAll();
	}
	
	@Override
	public int addTemp(int spId, int usid, String tolprice, int samount) {
		ci = new CartDaoImpl();
		return ci.addTemp(spId, usid, tolprice, samount);
	}
	@Override
	public int addcar(String usid, String spId, String samount) {
		ICartDao dao=new CartDaoImpl();
		return dao.addcar(usid,spId,samount);
	}
	@Override
	public List<CartInfo> selSp(String usid) {
		ICartDao dao=new CartDaoImpl();
		return dao.selSp(usid);
	}
	
	@Override
	public int updateo(String usid) {
		ICartDao dao=new CartDaoImpl();
		return dao.updateo(usid);
	}
	@Override
	public int addoneSp(String spId, String usid, String tolprice, String samount) {
		ICartDao dao=new CartDaoImpl();
		return dao.addoneSp(spId,usid,tolprice,samount);
	}
}
