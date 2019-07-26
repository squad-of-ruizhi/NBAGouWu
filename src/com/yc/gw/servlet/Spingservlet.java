package com.yc.gw.servlet;

import java.io.IOException;
import java.util.List;

import com.yc.biz.impl.SpingBizImpl;
import com.yc.gw.biz.ISpingBiz;
import com.yc.gw.entity.Sping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Sping")
public class Spingservlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String op=request.getParameter("op");
		 System.out.println(op);
		 
		 if("findAll".equals(op)){
			 findAll(request,response);
		 }
		 
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
