package com.yc.dao.impl;

import java.util.List;

import com.yc.gw.Dao.DBHelper;
import com.yc.gw.Dao.ISpingDao;
import com.yc.gw.entity.Sping;
public class SpingDaoImpl implements ISpingDao {

	public List<Sping> findAll() {
		DBHelper db=new  DBHelper();
		String sql="select spid,spname,spprice,sppic,spsize,spcolor,spcate from  sp order by spid";
		return  db.finds(Sping.class, sql);
		
	}

	
	 public List<Sping> findcate(String spcate){
		 DBHelper db=new DBHelper();
		 String sql="select spid,spname,spprice,sppic,spsize,spcolor from  sp where spcate= ? ";
	     return  db.finds(Sping.class,sql,spcate);
		 
	 }


	@Override
	public Sping findSingle(String spId) {
		DBHelper db=new DBHelper();
		 String sql="select spId,spname,spprice,sppic,spsize,spcolor,spcate from  sp where spId= ? ";
	     return  db.find(Sping.class,sql,spId);
	     
	}


	@Override
	public List<Sping> findcateL(String spcateL,String spIdL) {
		 DBHelper db=new DBHelper();
		 String sql="select  spId,spname,spprice,sppic,spsize,spcolor from  sp where   spcate= ? and  spid != ?  ";
	     return  db.finds(Sping.class,sql,spcateL,spIdL);
	}


	@Override
	public List<Sping> pricepx() {
		DBHelper db=new DBHelper();
		String sql="select spid,spname,spprice,sppic,spsize,spcolor,spcate from  sp order by spprice";
		return db.finds(Sping.class, sql);
	}


	@Override
	public List<Sping> newpx() {
		DBHelper db=new DBHelper();
		String sql="select spid,spname,spprice,sppic,spsize,spcolor,spcate from  sp order by  spId desc";
		return db.finds(Sping.class, sql);
	}


	@Override
	public List<Sping> findByPage(int pageNo, int pageSize) {
		DBHelper db=new DBHelper();
		String sql="select *from sp limit ?,?";
		return db.finds(Sping.class, sql,(pageNo-1)*pageSize,pageSize);
	}


	@Override
	public int getTotal() {
		DBHelper db=new DBHelper();
		String sql="select count(spId) from sp";
		return  db.getTotal(sql); 
	}
}
