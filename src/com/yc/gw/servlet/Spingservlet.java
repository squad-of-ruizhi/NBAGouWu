package com.yc.gw.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.biz.impl.SpingBizImpl;
import com.yc.gw.biz.ISpingBiz;
import com.yc.gw.entity.Sping;



@WebServlet("/Sping")
public class Spingservlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String op=request.getParameter("op");
		 if("findAll".equals(op)){
			 findAll(request,response);
		 }else if("findcate".equals(op)){
			 finadcate(request,response);

		 }else if("findSingle".equals(op)){
			  findSingle(request,response);
		 }else if("favorite".equals(op)){
			   findfavorite(request,response);
 master
		 }
		 
	}
	
	

	private void findfavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
               String spcateL=request.getParameter("spcateL");
               String spIdL=request.getParameter("spIdL");
               ISpingBiz  spingBiz=new  SpingBizImpl();
          	   List<Sping>  list=spingBiz.findcateL(spcateL,spIdL);
          	   this.send(response, list);
	}


	private void findSingle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		  String spId=request.getParameter("spId");
		  ISpingBiz  spingBiz=new  SpingBizImpl();
		 this.send(response, spingBiz.findSingle(spId));
	}


	



	private void finadcate(HttpServletRequest request, HttpServletResponse response) throws IOException  {
    	String spcate=request.getParameter("spcate");
    	ISpingBiz  spingBiz=new  SpingBizImpl();
   	    List<Sping>  list=spingBiz.findcate(spcate);
     	 this.send(response, list);
		
	}


	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	 ISpingBiz  spingBiz=new  SpingBizImpl();
    	 List<Sping>  list=spingBiz.findAll();
    	 this.send(response, list);
    	
    	/*ISpingDao  SpingDao = new SpingBizImpl();
		List<Sping> list = SpingDao.finds();
		this.send(response, list);*/
	}	
	

}
