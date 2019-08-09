package com.yc.gw.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.biz.impl.CartBizImpl;
import com.yc.gw.biz.ICartBiz;
import com.yc.gw.entity.CartInfo;



@WebServlet("/cart")
public class CartServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String op=request.getParameter("op");
		 if("findAll".equals(op)){
			 findAll(request,response);
		 } else if("add".equals(op)){
			 add(request,response);
		 } else if("sub".equals(op)) {
			 sub(request,response);
		 } else if("delete".equals(op)) {
			 delete(request,response);
		 } else if("deleteall".equals(op)) {
			 deleteAll(request, response);
		 } else if("save".equals(op)) {
			 session = request.getSession();
			 session.setAttribute("usid", request.getParameter("usid"));
			 session.setAttribute("spId", request.getParameter("spId"));
			 session.setAttribute("delState", request.getParameter("delState"));
			 session.setAttribute("shopid", request.getParameter("shopid"));
		 } else if("search".equals(op)){
			 search(request,response);
		 }else if("orderInfo".equals(op)) {
			 int spId = Integer.parseInt(request.getParameter("spId"));
			 int usid = Integer.parseInt(request.getParameter("usid"));
			 String tolprice = request.getParameter("tolprice");
			 int samount = Integer.parseInt(request.getParameter("samount"));
			 addTempValues(spId, usid, tolprice, samount);
		 }else if("addcar".equals(op)){
			 addcar(request,response);
		 }else if("selSp".equals(op)){
			 selSp(request,response);
		 }else if("updateo".equals(op)){
			 updateo(request,response);
		 }else if("addoneSp".equals(op)){
			 addoneSp(request,response);
		 }else if("history".equals(op)){
			 selhistory(request,response);
		 }else if("deletehis".equals(op)){
			 deletehis(request,response);
		 }

	}


	private void deletehis(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tempaId=request.getParameter("tempaId");
		ICartBiz  cartBiz=new CartBizImpl();
		int result=cartBiz.deletehis(tempaId);
		this.send(response, result);
		
	}

	private void selhistory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String usid=request.getParameter("usid");
		ICartBiz  cartBiz=new CartBizImpl();
		List<CartInfo>  list=cartBiz.selhistory(usid);
		this.send(response, list);
		
	}

	private void addoneSp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String spId=request.getParameter("spId");
		 String usid=request.getParameter("usid");
		 String tolprice=request.getParameter("tolprice");
		 String samount=request.getParameter("samount");
		 ICartBiz cartBiz=new CartBizImpl();
		 int result=cartBiz.addoneSp(spId,usid,tolprice,samount);
		 this.send(response, result);
		
	}

	private void updateo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String usid=request.getParameter("usid");
		ICartBiz cartBiz=new CartBizImpl();
		int result=cartBiz.updateo(usid);
		this.send(response, result);
		
	}

	private void selSp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String usid=request.getParameter("usid");
		 ICartBiz cartBiz=new CartBizImpl();
		 List<CartInfo> list=cartBiz.selSp(usid);
		 this.send(response, list);
	}

	private void addcar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String usid=request.getParameter("usid");
		 String spId=request.getParameter("spId");
		 String samount=request.getParameter("samount");
		  ICartBiz cartBiz=new CartBizImpl();
		  int result=cartBiz.addcar(usid,spId,samount);
		  this.send(response, result);
		
	}

	/*private void finadcate(HttpServletRequest request, HttpServletResponse response) throws IOException  {
    	String spcate=request.getParameter("spcate");
    	ISpingBiz  spingBiz=new  SpingBizImpl();
   	    List<Sping>  list=spingBiz.findcate(spcate);
     	 this.send(response, list);
		
	}*/
	private void addTempValues(int spId, int usid, String tolprice, int samount) {
		CartBizImpl cartBizImpl = new CartBizImpl();
		int result = cartBizImpl.addTemp(spId, usid, tolprice, samount);
	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String usid = (String) session.getAttribute("usid");
		String spId = (String) session.getAttribute("spId");
		String delState = (String) session.getAttribute("delState");
		String shopid = (String) session.getAttribute("shopid");
		Map<String, String> map = new HashMap<String, String>();
		map.put("usid", usid);
		map.put("spId", spId);
		map.put("delState", delState);
		map.put("shopid", shopid);
		this.send(response, map);
	}


	private void deleteAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CartBizImpl cartBizImpl = new CartBizImpl();
		int result = cartBizImpl.delAll();
		this.send(response, result);
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int usid = Integer.parseInt(request.getParameter("usid"));
		int spId = Integer.parseInt(request.getParameter("spId"));
		int shopid = Integer.parseInt(request.getParameter("shopid"));
		
		CartBizImpl cartBizImpl = new CartBizImpl();
		int result = cartBizImpl.del(spId, usid, shopid);
		this.send(response, result);
	}


	private void sub(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int usid = Integer.parseInt(request.getParameter("usid"));
		int spId = Integer.parseInt(request.getParameter("spId"));
		int shopid = Integer.parseInt(request.getParameter("shopid"));
		
		CartBizImpl cartBizImpl = new CartBizImpl();
		int result = cartBizImpl.sub(spId, usid, shopid);
		this.send(response, result);
	}


	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int usid = Integer.parseInt(request.getParameter("usid"));
		int spId = Integer.parseInt(request.getParameter("spId"));
		int shopid = Integer.parseInt(request.getParameter("shopid"));
		
		CartBizImpl cartBizImpl = new CartBizImpl();
		int result = cartBizImpl.add(spId, usid, shopid);
		this.send(response, result);
	}


	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	 //int usid = Integer.parseInt(request.getParameter("usid"));
    	 String usid = request.getParameter("usid");
    	 CartBizImpl cartBizImpl = new CartBizImpl();
    	 List<CartInfo> list = cartBizImpl.findAll(usid);
    	 
    	 this.send(response, list);
	}	
}
