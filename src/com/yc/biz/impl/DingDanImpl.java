package com.yc.biz.impl;

import java.util.List;

import com.yc.dao.impl.DingDanDaoImpl;
import com.yc.gw.Dao.IDingDanDao;
import com.yc.gw.biz.IDingDanBiz;
import com.yc.gw.entity.DingDan;

public class DingDanImpl implements IDingDanBiz {

	@Override
	public List<DingDan> selectId(String usid) {
		IDingDanDao dao=new  DingDanDaoImpl();
		return dao.findAll(usid);
	}

	@Override
	public int deleteord(String orderid) {
		IDingDanDao dao=new  DingDanDaoImpl();
		return dao.deleteord(orderid);
	}
}
