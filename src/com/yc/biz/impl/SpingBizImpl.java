package com.yc.biz.impl;

import java.util.List;
import java.util.Map;

import com.yc.dao.impl.SpingDaoImpl;
import com.yc.gw.Dao.ISpingDao;
import com.yc.gw.biz.ISpingBiz;
import com.yc.gw.entity.Sping;

public class SpingBizImpl implements ISpingBiz {

	@Override
	public List<Sping> findAll() {
		ISpingDao dao=new  SpingDaoImpl();
			return dao.findAll();
	}
	
	@Override
	 public List<Sping> findcate(String spcate){
		 ISpingDao dao=new SpingDaoImpl();
		 return dao.findcate(spcate);
	 }

	@Override
	public Sping findSingle(String spId) {
		 ISpingDao dao=new SpingDaoImpl();
		 return dao.findSingle(spId);
	 }

	@Override
	public List<Sping> findcateL(String spcateL,String spIdL) {
		ISpingDao dao=new SpingDaoImpl();
		 return dao.findcateL(spcateL,spIdL);
	}

	@Override
	public List<Sping> pricepx() {
		ISpingDao dao=new SpingDaoImpl();
		return dao.pricepx();
	}

	@Override
	public List<Sping> newpx() {
		ISpingDao dao=new SpingDaoImpl();
		return dao.newpx();
	}

	@Override
	public List<Sping> findByPage(int pageNo, int pageSize) {
		ISpingDao dao=new SpingDaoImpl();
		return dao.findByPage(pageNo,pageSize);
	}

	@Override
	public int getTotal() {
		ISpingDao dao=new SpingDaoImpl();
		return dao.getTotal(); 
	}
	

}
