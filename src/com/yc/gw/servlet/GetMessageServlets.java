package com.yc.gw.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.gw.util.AuthCode;
import com.yc.gw.util.SendSms;
import com.yc.gw.util.configUtil;

/**
 * Servlet implementation class getMessageServlets
 */
@WebServlet("/getMessageServlets")
public class GetMessageServlets extends BasicServlet {


	private static final long serialVersionUID = -5702703617443318582L;

	@Override
    protected void  doPost(HttpServletRequest request,HttpServletResponse response)
         throws  ServletException, IOException{
		String op=request.getParameter("op");
		if("sendCode".equals(op)){
    		  sendCode(request,response);
    	  }
    	
    }

	protected void sendCode(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8"); 
		String phone = req.getParameter("phone");
		configUtil.phoneNumber = phone;
		System.out.println(configUtil.phoneNumber);

		//生成随机验证码
		AuthCode authCode = new AuthCode();
		String str = "{\"code\":\""+authCode.getCode()+"\"}";
		configUtil.templateParam = str;

		//发送短信
		SendSms sendSms = new SendSms();
		String request = sendSms.send(configUtil.phoneNumber,configUtil.templateParam);
		System.out.println(request);

	}
}
