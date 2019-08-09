package com.yc.gw.Dao;

import java.util.List;

import com.yc.gw.entity.Sping;


public interface ISpingDao {
	 public List<Sping> findAll();

	

	public List<Sping> findcate(String spcate);



	public Sping findSingle(String spId);



	public List<Sping> findcateL(String spcateL,String spIdL);



	public List<Sping> pricepx();



	public List<Sping> newpx();



	public List<Sping> findByPage(int pageNo, int pageSize);



	public int getTotal();
}
