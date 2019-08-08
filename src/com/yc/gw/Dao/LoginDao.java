package com.yc.gw.Dao;


public class LoginDao  {

    /**
     * 用户登录
     * @param username 账号（电话）
     * @param password 密码
     * @return
     */
   /* public boolean doLogin(String username ,String password){
        DBHelper dbHelper=new DBHelper();
        String sql="select usid,tel,pwd from user where tel = ? and pwd = ?;";
        List list=dbHelper.find(sql,username,password);
        //System.out.println(list);
        if (list != null && list.size() > 0) {
            return true;
        }else{
            return false;
        }

    }*/

    public boolean doRegister(String username,String password){
        DBHelper dbHelper =new DBHelper();
        String sql="INSERT INTO user (tel, pwd) VALUES (?, ?);";
        int rs = dbHelper.update(sql,username,password);
        if (rs !=-1) {
            return false;
        }else {
            return true;
        }
    }
}
