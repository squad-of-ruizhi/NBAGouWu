package com.yc.gw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter  implements Filter {
        private String encoding="utf-8";
        
        public void doFilter(ServletRequest  request,ServletResponse  response,FilterChain chain)  throws  IOException,ServletException{
        	HttpServletRequest  req=(HttpServletRequest)  request;
        	HttpServletResponse  resp=(HttpServletResponse) response;
        	
        	
        req.setCharacterEncoding(encoding);
        resp.setCharacterEncoding(encoding);
        
        chain.doFilter(req, resp);
        	
        }
        
        
        
        public void init(FilterConfig  config)  throws ServletException{
        	 String  temp=config.getInitParameter("encoding");
        	 if(temp==null){
        		 return;
        		 
        	 }
        	
        	  encoding=temp;
        	  System.out.println(encoding);
        }


		@Override
		public void destroy() {
			// TODO Auto-generated method stub
			
		}
}
