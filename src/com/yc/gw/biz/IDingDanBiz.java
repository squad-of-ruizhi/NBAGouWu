package com.yc.gw.biz;

import java.util.List;

import com.yc.gw.entity.DingDan;

public interface IDingDanBiz {

	List<DingDan> selectId(String usid);

	int deleteord(String orderid);

	public int Ddstatus(String orderid);
}
