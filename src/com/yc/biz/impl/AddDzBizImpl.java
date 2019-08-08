package com.yc.biz.impl;

import java.util.List;

import com.yc.dao.impl.AddDzDaoImpl;
import com.yc.gw.Dao.IAddDzDao;
import com.yc.gw.biz.IAddDzBiz;
import com.yc.gw.entity.Addres;

public class AddDzBizImpl implements IAddDzBiz {

	@Override
	public int addDz(String province, String city, String area, String detailed,String usid, String aname, String atel,
			String amail,String adefault) {
		    IAddDzDao  dao=new AddDzDaoImpl();
		    return dao.addDz(province,city,area,detailed,usid,aname,atel,amail,adefault);
	}

	@Override
	public List<Addres> findAllDz(String usid) {
		  IAddDzDao dao=new  AddDzDaoImpl();
		  return  dao.findAllDz(usid);
	}

	@Override
	public int update(String usid) {
		IAddDzDao dao=new  AddDzDaoImpl();
		 return dao.update(usid);
	}

	@Override
	public int updatea(String def, String addrida) {
		IAddDzDao dao=new  AddDzDaoImpl();
		 return dao.updatea(def,addrida);
	}

	@Override
	public int deletea(String addrid) {
		IAddDzDao dao=new  AddDzDaoImpl();
		 return dao.deletea(addrid);
	}

	
	@Override
	public Addres findSingle(String addrid) {
		IAddDzDao dao=new  AddDzDaoImpl();
		  return  dao.findSingle(addrid);
	}

	@Override
	public int updateDz(String addrid, String province, String city, String area, String detailed, String aname,
			String atel, String amail) {
		IAddDzDao dao=new  AddDzDaoImpl();
		  return  dao.updateDz(addrid,province,city,area,detailed,aname,atel,amail);
	}

	@Override
	public int AddDD(String orderid, String odate, String oamount, String state, String addr,
			String usid) {
		IAddDzDao  dao=new AddDzDaoImpl();
	    return dao.addDD(orderid,odate,oamount,state,addr,usid);
	}

	
}
