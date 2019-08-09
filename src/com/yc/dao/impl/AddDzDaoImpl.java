package com.yc.dao.impl;

import java.util.List;

import com.yc.gw.Dao.DBHelper;
import com.yc.gw.Dao.IAddDzDao;
import com.yc.gw.entity.Addres;

public class AddDzDaoImpl implements IAddDzDao {

	@Override
	public int addDz(String province, String city, String area, String detailed,String usid,String aname, String atel,
			String amail,String adefault) {
		   DBHelper db=new DBHelper();
		   String sql=" insert into address(province,city,area,detailed,usid,aname,atel,amail,adefault) values (?,?,?,?,?,?,?,?,?)";
		   return   db.update(sql,province,city,area,detailed,usid,aname,atel,amail,adefault);
	}

	@Override
	public List<Addres> findAllDz(String usid) {
		 DBHelper  db=new DBHelper();
		 String sql="select addrid,province,city,area,detailed,usid,aname,atel,amail,adefault from address where usid=?  ";
		 return  db.finds(Addres.class, sql, usid);
	}

	@Override
	public int update(String usid) {
		    DBHelper db=new DBHelper();
		    String sql="update address set adefault='0' where usid=?";
		    return db.update(sql, usid);
	}

	@Override
	public int updatea(String def, String addrida) {
		DBHelper db=new DBHelper();
	    String sql="update address set adefault=?  where addrid=?";
	    return db.update(sql,def,addrida);
	}

	@Override
	public int deletea(String addrid) {
		DBHelper db=new DBHelper();
	    String sql="delete from address where addrid=?";
	    return  db.update(sql, addrid);
	}

	@Override
	public Addres findSingle(String addrid) {
		DBHelper db=new DBHelper();
	    String sql="select addrid,province,city,area,detailed,usid,aname,atel,amail,adefault  from address where addrid=?";
	    return  db.find(Addres.class,sql,addrid);
	}

	@Override
	public int updateDz(String addrid, String province, String city, String area, String detailed, String aname,
			String atel, String amail) {
		 DBHelper db=new DBHelper();
		 String sql="update address set province=?,city=?,area=?,detailed=?,aname=?,atel=?,amail=? where addrid=?";
	 return db.update(sql,province,city,area,detailed,aname,atel,amail,addrid);
	}

	@Override
	public int addDD(String orderid, String odate, String oamount, String state, String addr, 
			String usid) {
		 DBHelper db=new DBHelper();
		   String sql=" insert into orders(orderid,odate,oamount,state,addr,usid) values (?,?,?,?,?,?)";
		   return   db.update(sql,orderid,odate,oamount,state,addr,usid);
	}

	

}
