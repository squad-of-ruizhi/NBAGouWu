package com.yc.dao.impl;

import java.util.List;

import com.yc.gw.Dao.DBHelper;
import com.yc.gw.Dao.ICartDao;
import com.yc.gw.entity.CartInfo;

public class CartDaoImpl implements ICartDao {
	@Override
	public List<CartInfo> findAll(String usid) {
		DBHelper db = new DBHelper();
		String sql="select shopid,c.spId,s.spname,s.spprice,samount,usid,sppic,spcolor,spsize from shoppingcart c,sp s where s.spId=c.spId and  usid=?";
		return  db.finds(CartInfo.class,sql,usid);
	}

	@Override
	public int add(int usid, int spId, int shopid) {
		DBHelper db = new DBHelper();
		String sql = "update shoppingcart set samount=samount+1 where spId=? and usid=? and shopid=?";
		return db.update(sql, spId, usid, shopid);
	}

	@Override
	public int sub(int usid, int spId, int shopid) {
		DBHelper db = new DBHelper();
		String sql = "update shoppingcart set samount=samount-1 where spId=? and usid=? and shopid=?";
		return db.update(sql, spId, usid, shopid);
	}

	@Override
	public int del(int usid, int spId,int shopid) {
		DBHelper db = new DBHelper();
		String sql = "delete from shoppingcart where usid=? and spId=? and shopid=?";
		return db.update(sql, usid, spId,shopid);
	}

	@Override
	public int delAll() {
		DBHelper db = new DBHelper();
		String sql = "delete from shoppingcart";
		return db.update(sql);
	}
	
	@Override
	public int addTemp(int spId, int usid, String tolprice, int samount) {
		DBHelper db = new DBHelper();
		String sql = "insert into tempa(spId, usid, tolprice, samount) values (?,?,?,?)";
		return db.update(sql, spId, usid, tolprice, samount);
	}

	@Override
	public int addcar(String usid, String spId, String samount) {
		DBHelper db=new DBHelper();
		String sql="insert into shoppingcart(usid,samount,spId) values (?,?,?)";
		return db.update(sql,usid,samount,spId);
	}

	@Override
	public List<CartInfo> selSp(String usid) {
		DBHelper db = new DBHelper();
		String sql="select usid,t.spId,t.samount,t.tolprice,s.spname,s.sppic,s.spsize,s.spcolor,s.spcate,s.spprice  from tempa t,sp s where t.spId=s.spId and usid=? and status=0";
		return  db.finds(CartInfo.class,sql,usid);
	}

	@Override
	public int updateo(String usid) {
		DBHelper db = new DBHelper();
		String sql="update  tempa set status='1'  where usid=?";
		return  db.update(sql, usid);
	}

	@Override
	public int addoneSp(String spId, String usid, String tolprice, String samount) {
		DBHelper db=new DBHelper();
		String sql="insert into tempa(spId, usid, tolprice, samount) values (?,?,?,?)";
		return db.update(sql, spId,usid,tolprice,samount);
	}

	@Override
	public List<CartInfo> selhistory(String usid) {
		DBHelper db=new DBHelper();
		String sql="select t.spId,t.samount,t.tolprice,s.spname,s.sppic,s.spcate,t.tempaId  from  tempa t,sp s  where t.spId=s.spId and usid=? and status='1'";
		return db.finds(CartInfo.class, sql, usid);
	}

	@Override
	public int deletehis(String tempaId) {
		DBHelper db=new DBHelper();
		String sql="delete from tempa where tempaId=?";
		return db.update(sql, tempaId);
	}

}
