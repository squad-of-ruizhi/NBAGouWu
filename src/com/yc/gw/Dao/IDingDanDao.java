package com.yc.gw.Dao;

import java.util.List;

import com.yc.gw.entity.DingDan;

public interface IDingDanDao {

	List<DingDan> findAll(String usid);

	int deleteord(String orderid);

	int Ddstatus(String orderid);


}
