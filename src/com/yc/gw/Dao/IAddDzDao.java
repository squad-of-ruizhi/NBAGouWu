package com.yc.gw.Dao;

import java.util.List;

import com.yc.gw.entity.Addres;

public interface IAddDzDao {

	int addDz(String province, String city, String area, String detailed,String usid, String aname, String atel, String amail,String adefault);

	List<Addres> findAllDz(String usid);

	int update(String usid);
	
	int updatea(String def,String addrida);

	int deletea(String addrid);


	Addres findSingle(String addrid);

	int updateDz(String addrid, String province, String city, String area, String detailed, String aname, String atel,
			String amail);

	int addDD(String orderid, String odate, String oamount, String state, String addr,String usid);

             
}
