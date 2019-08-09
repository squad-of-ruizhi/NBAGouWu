package com.yc.gw.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.biz.impl.AddDzBizImpl;
import com.yc.gw.biz.IAddDzBiz;
import com.yc.gw.entity.Addres;


@WebServlet("/Addres")
public class Addresservlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    String op=request.getParameter("op");
		    /*System.out.println(op);*/
		    if("addDz".equals(op)){
		    	AddDz(request,response);
		    }else if("findAllDz".equals(op)){
		    	findAllDz(request,response);
		    }else if("ZTai".equals(op)){
		         ZTai(request,response);	
		    }else if("deletea".equals(op)){
		    	 deletea(request,response);
		    }else if("selectrid".equals(op)){
		    	findSingle(request,response);
		    }else if("updateDZ".equals(op)){
		    	  updateDz(request,response);
		    }else if("AddDD".equals(op)){
		    	AddDD(request,response);
		    }
	}


	private void AddDD(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String orderid=request.getParameter("orderid");
		 String odate=request.getParameter("odate");
		 String oamount=request.getParameter("oamounta");
		 String state=request.getParameter("statea");
		 String addr=request.getParameter("address");
		 String usid=request.getParameter("usida");
		 IAddDzBiz addDzBiz=new AddDzBizImpl();
		 int result=addDzBiz.AddDD( orderid, odate, oamount, state, addr,usid);
		 this.send(response, result);
		
	}

	private void updateDz(HttpServletRequest request, HttpServletResponse response) throws IOException {
               String addrid=request.getParameter("addrid");
               String  province=request.getParameter("province");
    		   String city=request.getParameter("city");
    		   String  area=request.getParameter("area");
    		   String  detailed=request.getParameter("detailed");
    		   String aname=request.getParameter("aname");
    		   String atel=request.getParameter("atel");
    		   String  amail=request.getParameter("amail");
               IAddDzBiz  addDzBiz=new AddDzBizImpl();
   		      int result=addDzBiz.updateDz(addrid,province,city,area,detailed,aname,atel,amail);
   		      this.send(response, result);
	}

	private void findSingle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String addrid=request.getParameter("addrid");
		  IAddDzBiz  addDzBiz=new AddDzBizImpl();
		    this.send(response, addDzBiz.findSingle(addrid));
		
	}

	private void deletea(HttpServletRequest request, HttpServletResponse response) throws IOException {
		  String addrid=request.getParameter("addrid");
		  IAddDzBiz  addDzBiz=new AddDzBizImpl();
		    int result=addDzBiz.deletea(addrid);
		    this.send(response, result);
		
	}

	private void ZTai(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String def=request.getParameter("def");
	    String usid=request.getParameter("usid");
	    String addrid=request.getParameter("addrid");
	     String addrida=addrid.substring(1);
	    
	    IAddDzBiz  addDzBiz=new AddDzBizImpl();
	    int result=addDzBiz.update(usid);
	    int result1=addDzBiz.updatea(def,addrida);
	    this.send(response, result);
		
	}

	private void findAllDz(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String usid=request.getParameter("usid");
		 IAddDzBiz  addDzBiz=new AddDzBizImpl();
		  List<Addres> list=addDzBiz.findAllDz(usid);
		  this.send(response, list);
		
	}

	private void AddDz(HttpServletRequest request, HttpServletResponse response) throws IOException {
		   String  province=request.getParameter("province");
		   String city=request.getParameter("city");
		   String  area=request.getParameter("area");
		   String  detailed=request.getParameter("detailed");
		   String  usid=request.getParameter("usid");
		   String aname=request.getParameter("aname");
		   String atel=request.getParameter("atel");
		   String  amail=request.getParameter("amail");
		   String  adefault=request.getParameter("adefault");
            
		    IAddDzBiz  addDzBiz=new AddDzBizImpl();

			int result=addDzBiz.addDz(province,city,area,detailed,usid,aname,atel,amail,adefault);
			this.send(response, result);
	}

}
