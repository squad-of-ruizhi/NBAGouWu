package com.yc.biz.impl;

import java.util.List;

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

}
