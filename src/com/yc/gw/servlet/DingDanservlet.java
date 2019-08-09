package com.yc.gw.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.biz.impl.DingDanImpl;
import com.yc.gw.biz.IDingDanBiz;
import com.yc.gw.entity.DingDan;

/**
 * Servlet implementation class DingDanservlet
 */
@WebServlet("/DingDan")
public class DingDanservlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		if("selectId".equals(op)){
			selectId(request,response);
		}else if("deleteord".equals(op)){
			deleteord(request,response);
		}
	}

	private void deleteord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String orderid=request.getParameter("orderid");
		IDingDanBiz  dingDan=new DingDanImpl();
		int result=dingDan.deleteord(orderid);
		 this.send(response, result);
		
	}

	private void selectId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String usid=request.getParameter("usid");
		 IDingDanBiz  dingDan=new DingDanImpl();
		 List<DingDan> list=dingDan.selectId(usid);
		 this.send(response, list);
		
	}

}
