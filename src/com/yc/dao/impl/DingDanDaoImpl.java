package com.yc.dao.impl;

import java.util.List;

import com.yc.gw.Dao.DBHelper;
import com.yc.gw.Dao.IDingDanDao;
import com.yc.gw.entity.DingDan;

public class DingDanDaoImpl implements IDingDanDao {

	@Override
	public List<DingDan> findAll(String usid) {
		 DBHelper db=new DBHelper();
		 String sql="select orderid,odate,oamount,state,addr,usid from orders where usid=?";
		return db.finds(DingDan.class, sql, usid);
	}

	@Override
	public int deleteord(String orderid) {
		DBHelper db=new DBHelper();
		 String sql="delete from orders where orderid=?";
		return   db.update(sql, orderid);
	}

}
