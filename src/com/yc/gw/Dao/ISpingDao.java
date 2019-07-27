package com.yc.gw.Dao;

import java.util.List;

import com.yc.gw.entity.Sping;


public interface ISpingDao {
	 public List<Sping> findAll();

	

	public List<Sping> findcate(String spcate);
}
