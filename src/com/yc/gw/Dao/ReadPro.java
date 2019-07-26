package com.yc.gw.Dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReadPro  extends  Properties {
   private static final long serialVersionUID=3249050916394513607L;
     private  static ReadPro instance=new ReadPro();
     
     
     private  ReadPro(){
    	   InputStream  is=null;
    	   
    	   
    	   try {
    		    
			  is=ReadPro.class.getClassLoader().getResourceAsStream("db.properties");
			   this.load(is);
			   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is!=null){
				 try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	   
     }
     
      public static ReadPro  getinstance(){
    	  return instance;
      }
}
