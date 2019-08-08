package com.yc.gw.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.biz.impl.UserBizImpl;
import com.yc.gw.Dao.LoginDao;
import com.yc.gw.biz.IuserBiz;
import com.yc.gw.entity.User;

@WebServlet("/login")
public class LoginServlet extends BasicServlet  {
	private static final long serialVersionUID = -2746550911954544755L;


	//http://localhost:8080/login.html

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");

		if ("login".equals(op)){
			Login(request,response);
		}else if ("register".equals(op)){
			Register(request,response);
		}
	}

	private void Login(HttpServletRequest request, HttpServletResponse response) throws  IOException {
		String tel = request.getParameter("username");
		String pwd = request.getParameter("password");
		IuserBiz  userBiz=new UserBizImpl();
		User user = userBiz.findSingle(tel,pwd);
		request.getSession().setAttribute("User", user);
		this.send(response, user);
	}

	protected void Register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String tel = request.getParameter("tel");
		String password = request.getParameter("password");
		boolean flag;

		LoginDao loginDao = new LoginDao();
		flag=loginDao.doRegister(tel,password);

		if (flag){
			this.send(response,0);
		}else {
			this.send(response,1);
		}

	}

}
