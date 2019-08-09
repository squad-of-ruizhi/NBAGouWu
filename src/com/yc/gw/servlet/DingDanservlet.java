package com.yc.gw.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.biz.impl.DingDanImpl;
import com.yc.biz.impl.UserBizImpl;
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
		}else if("alterInfoPwd".equals(op)) {
			alterInfoPwd(request, response);
		} else if("alterInfo".equals(op)) {
			alterInfo(request, response);
		} else if("findAll".equals(op)) {
			getAll(request, response);
		}else if("Ddstatus".equals(op)){
			 Ddstatus(request,response);
		 }
	}

	
	private void Ddstatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		   String orderid=request.getParameter("orderid");
		   IDingDanBiz  dingDanBiz=new DingDanImpl();
			int result=dingDanBiz.Ddstatus(orderid);
			this.send(response, result);
		
	}
	
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int usid = Integer.parseInt(request.getParameter("usid"));
		UserBizImpl userBizImpl = new UserBizImpl();
		List<Map<String, String>> list = userBizImpl.findAll(usid);
		this.send(response, list);
	}

	private void alterInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int usid = Integer.parseInt(request.getParameter("usid"));
		String sex = request.getParameter("sex");
		String mail = request.getParameter("mail");
		String birthday = request.getParameter("birthday");
		String team = request.getParameter("team");
		String star = request.getParameter("star");
		String duration = request.getParameter("duration");
		
		UserBizImpl userBizImpl = new UserBizImpl();
		int result = userBizImpl.insertInfo(usid, birthday, sex, duration, team, star, mail);
		
		this.send(response, result);
	}

	private void alterInfoPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int usid = 0;
		if(request.getParameter("usid") != null) {
			usid = Integer.parseInt(request.getParameter("usid"));
		}
		String sex = request.getParameter("sex");
		String mail = request.getParameter("mail");
		String birthday = request.getParameter("birthday");
		String team = request.getParameter("team");
		String star = request.getParameter("star");
		String duration = request.getParameter("duration");
		String origpwd = request.getParameter("origpwd");
		String newpwd = request.getParameter("newpwd");

		UserBizImpl userBizImpl = new UserBizImpl();
		List<Map<String, String>> list = userBizImpl.findAll(usid);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int result = 0;
		int result2 = 0;
		
		System.out.println(list.get(0).get("pwd"));

		if(origpwd.equals(list.get(0).get("pwd"))) {
			// 修改密码
			// 修改个人信息
			result = userBizImpl.alterPwd(usid, newpwd);
			result2 = userBizImpl.insertInfo(usid, birthday, sex, duration, team, star, mail);
			resultMap.put("pwdIsSuccess", result);
			resultMap.put("pwdInfoSuccess", result2);
		}
		this.send(response, resultMap);
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
