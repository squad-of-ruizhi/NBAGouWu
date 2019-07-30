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
		 System.out.println(db.finds(Sping.class,sql,spcateL,spIdL));
	     return  db.finds(Sping.class,sql,spcateL,spIdL);
	}


}
