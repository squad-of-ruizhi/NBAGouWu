package com.yc.gw.biz;

import java.util.List;
import java.util.Map;

import com.yc.gw.entity.Sping;


public interface ISpingBiz {
    
	  public List<Sping> findAll();
	
	 

	public List<Sping> findcate(String spcate);



	
 public List<Sping> findcateL(String spcateL,String spIdL);


	Sping findSingle(String spId);





 
}
