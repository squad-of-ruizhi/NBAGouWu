package com.yc.gw.Dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {
	static {
		try {
			Class.forName(ReadPro.getinstance().getProperty("className"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���ӵķ���
	 * @return
	 */
	private Connection getConnection() {
		Connection con = null;
		try {
//			con = DriverManager.getConnection(ReadPro.getInstance().getProperty("url"),
//					ReadPro.getInstance().getProperty("user"), 
//					ReadPro.getInstance().getProperty("password"));
			con = DriverManager.getConnection(ReadPro.getinstance().getProperty("url"), ReadPro.getinstance());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * �ر���Դ�ķ��� 
	 * @param rs
	 * @param pstmt
	 * @param con
	 */
	private void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��Ԥ����ִ������е�ռλ����ֵ
	 * @param pstmt Ҫ��ֵ��Ԥ�����
	 * @param params ֵ�б�
	 */
	private void setValues(PreparedStatement pstmt, Object ... params) {
		if (pstmt == null || params == null || params.length <= 0) {
			return;
		}
		
		for (int i = 0, len = params.length; i < len; ++ i) {
			try {
				pstmt.setObject(i + 1, params[i]);
			} catch (SQLException e) {
				System.out.println("��" + (i + 1) + " ��ռλ����ֵʧ��...");
				e.printStackTrace();
			}
		}
	}
	
	private  void  setValues(PreparedStatement pstmt,List<Object>params){
		if (pstmt == null || params == null || params.size() <= 0) {
			return;
		}
		
		for (int i = 0, len = params.size(); i < len; ++ i) {
			try {
				pstmt.setObject(i + 1, params.get(i));
			} catch (SQLException e) {
				System.out.println("��" + (i + 1) + " ��ռλ����ֵʧ��...");
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * ִ�и��²���������update, delete,insert
	 * @param sql Ҫִ�еĸ��� ���
	 * @param objects Ҫִ�еĸ������ռλ�� �� ��Ӧ��ֵ
	 * @return �������ִ�к���Ӱ�����������
	 */
	public int update(String sql, Object ... params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			con = this.getConnection(); // ��ȡ����
			pstmt = con.prepareStatement(sql); // Ԥ����ִ������
			this.setValues(pstmt, params);  // ��Ԥ����ִ�������ռλ����ֵ
			result = pstmt.executeUpdate(); // ִ������ȡ���
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(null, pstmt, con);
		}
		
		return result;
	}
	
	/**
	 * ��ѯ����
	 * @param sql Ҫִ�еĲ�ѯ���
	 * @param params Ҫִ�в�ѯ�����ռλ����ֵ
	 * @return ÿһ�����ݴ浽һ��map�ӣ�������Ϊ������Ӧ�е�ֵΪֵ�������м�¼�浽list�����С�
	 */
	public List<Map<String, String>> find(String sql, Object ... params) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setValues(pstmt, params);
			rs = pstmt.executeQuery();
			Map<String, String> map = null;
			
//			String[] cols = {"deptno", "dname", "loc"};  // ??? 
			
			ResultSetMetaData rsmd = rs.getMetaData(); // ��ȡ�������Ԫ������Ϣ
			int columnCount = rsmd.getColumnCount(); // ��ȡ������е�����
			String[] columnNames = new String[columnCount]; // ����һ�����飬��������е�����
			for (int i = 0; i < columnCount; ++ i) {
				columnNames[i] = rsmd.getColumnName(i + 1).toLowerCase();
			}
			
			while(rs.next()) {
				map = new HashMap<String, String>();
				// �е�������д�����޷��������ı� �� -> �ܲ��ܶ�̬�Ļ�ȡ������е�ÿ���е�������
				// ������ȡ�������ǿ��Խ���Щ������ŵ�һ�������У�Ȼ���������ȡֵ����
				for (String col : columnNames) {
					map.put(col, rs.getString(col));
				}
				
				list.add(map); // ���һ�е������ݶ�ȡ����������һ�����ݴ浽list������
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		
		return list;
	}
	
	/**
	 * ��ѯ����
	 * @param sql Ҫִ�еĲ�ѯ���
	 * @param params Ҫִ�в�ѯ�����ռλ����ֵ
	 * @return ÿһ�����ݴ浽һ��map�ӣ�������Ϊ������Ӧ�е�ֵΪֵ�������м�¼�浽list�����С�
	 */
	public Map<String, String> findSingle(String sql, List<Object> params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, String> map = null;

		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setValues(pstmt, params);
			rs = pstmt.executeQuery();
			
//			String[] cols = {"deptno", "dname", "loc"};  // ??? 
			
			ResultSetMetaData rsmd = rs.getMetaData(); // ��ȡ�������Ԫ������Ϣ
			int columnCount = rsmd.getColumnCount(); // ��ȡ������е�����
			String[] columnNames = new String[columnCount]; // ����һ�����飬��������е�����
			for (int i = 0; i < columnCount; ++ i) {
				columnNames[i] = rsmd.getColumnName(i + 1).toLowerCase();
			}
			
			if(rs.next()) {
				map = new HashMap<String, String>();
				// �е�������д�����޷��������ı� �� -> �ܲ��ܶ�̬�Ļ�ȡ������е�ÿ���е�������
				// ������ȡ�������ǿ��Խ���Щ������ŵ�һ�������У�Ȼ���������ȡֵ����
				for (String col : columnNames) {
					map.put(col, rs.getString(col));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		
		return map;
	}
	
	
	/**
	 * ��ѯ����
	 * @param sql Ҫִ�еĲ�ѯ���
	 * @param params Ҫִ�в�ѯ�����ռλ����ֵ
	 * @return ÿһ�����ݴ浽һ��map�ӣ�������Ϊ������Ӧ�е�ֵΪֵ�������м�¼�浽list�����С�
	 */
	public List<Map<String, Object>> findObj(String sql, List<Object> params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setValues(pstmt, params);
			rs = pstmt.executeQuery();
			Map<String, Object> map = null;
			
//			String[] cols = {"deptno", "dname", "loc"};  // ??? 
			
			ResultSetMetaData rsmd = rs.getMetaData(); // ��ȡ�������Ԫ������Ϣ
			int columnCount = rsmd.getColumnCount(); // ��ȡ������е�����
			String[] columnNames = new String[columnCount]; // ����һ�����飬��������е�����
			for (int i = 0; i < columnCount; ++ i) {
				columnNames[i] = rsmd.getColumnName(i + 1).toLowerCase();
			}
			
			String typeName=null;
			Object obj=null;
			Blob  blob=null;
			byte[] bt=null;
			while(rs.next()) {
				map = new HashMap<String, Object>();
				// �е�������д�����޷��������ı� �� -> �ܲ��ܶ�̬�Ļ�ȡ������е�ÿ���е�������
				// ������ȡ�������ǿ��Խ���Щ������ŵ�һ�������У�Ȼ���������ȡֵ����
				
				for (String col : columnNames) {
					  obj=rs.getObject(col) ;
					if(obj!=null&&!"".equals(obj)){
						typeName=obj.getClass().getSimpleName();
						
						if("BLOB".equals(typeName)){
							blob=rs.getBlob(col);
							bt=blob.getBytes(1, (int)blob.length());
							map.put(col, bt);
						}else{
							map.put(col, obj);
						}
					}else{
							map.put(col, obj);
						}
					}
				
		list.add(map); // ���һ�е������ݶ�ȡ����������һ�����ݴ浽list������
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		
		return list;
	}
	
	
	/**
	 * ��ѯ����
	 * @param sql Ҫִ�еĲ�ѯ���
	 * @param params Ҫִ�в�ѯ�����ռλ����ֵ
	 * @return ÿһ�����ݴ浽һ��map�ӣ�������Ϊ������Ӧ�е�ֵΪֵ�������м�¼�浽list�����С�
	 */
	public Map<String, Object> findSingleObj(String sql, List<Object> params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;

		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setValues(pstmt, params);
			rs = pstmt.executeQuery();
			
//			String[] cols = {"deptno", "dname", "loc"};  // ??? 
			
			ResultSetMetaData rsmd = rs.getMetaData(); // ��ȡ�������Ԫ������Ϣ
			int columnCount = rsmd.getColumnCount(); // ��ȡ������е�����
			String[] columnNames = new String[columnCount]; // ����һ�����飬��������е�����
			for (int i = 0; i < columnCount; ++ i) {
				columnNames[i] = rsmd.getColumnName(i + 1).toLowerCase();
			}
			
			
			String typeName=null;
			Object obj=null;
			Blob  blob=null;
			byte[] bt=null;
			
			if(rs.next()) {
				map = new HashMap<String, Object>();
				// �е�������д�����޷��������ı� �� -> �ܲ��ܶ�̬�Ļ�ȡ������е�ÿ���е�������
				// ������ȡ�������ǿ��Խ���Щ������ŵ�һ�������У�Ȼ���������ȡֵ����
				for (String col : columnNames) {
					  obj=rs.getObject(col);
					  if(obj!=null&&!"".equals(obj)){
						  typeName=obj.getClass().getSimpleName();
						  
						  if("BLOB".equals(typeName)){
							  blob=rs.getBlob(col);
							  bt=blob.getBytes(1, (int)blob.length());
							  map.put(col, bt);
						  }else{
							  map.put(col, obj);						  }
					  }else{
						  map.put(col, obj);
					  }
					  
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		
		return map;
	}
	
	/**
	 * ��ѯ����
	 * @param sql Ҫִ�еĲ�ѯ���
	 * @param params Ҫִ�в�ѯ�����ռλ����ֵ
	 * @return ÿһ�����ݴ浽һ��map�ӣ�������Ϊ������Ӧ�е�ֵΪֵ�������м�¼�浽list�����С�
	 */
	public List<Map<String, String>> find(String sql, List<Object>  params) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setValues(pstmt, params);
			rs = pstmt.executeQuery();
			Map<String, String> map = null;
			
//			String[] cols = {"deptno", "dname", "loc"};  // ??? 
			
			ResultSetMetaData rsmd = rs.getMetaData(); // ��ȡ�������Ԫ������Ϣ
			int columnCount = rsmd.getColumnCount(); // ��ȡ������е�����
			String[] columnNames = new String[columnCount]; // ����һ�����飬��������е�����
			for (int i = 0; i < columnCount; ++ i) {
				columnNames[i] = rsmd.getColumnName(i + 1).toLowerCase();
			}
			
			while(rs.next()) {
				map = new HashMap<String, String>();
				// �е�������д�����޷��������ı� �� -> �ܲ��ܶ�̬�Ļ�ȡ������е�ÿ���е�������
				// ������ȡ�������ǿ��Խ���Щ������ŵ�һ�������У�Ȼ���������ȡֵ����
				for (String col : columnNames) {
					map.put(col, rs.getString(col));
				}
				
				list.add(map); // ���һ�е������ݶ�ȡ����������һ�����ݴ浽list������
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		
		return list;
	}
	
	/**
	 * ��ѯ����
	 * @param sql Ҫִ�еĲ�ѯ���
	 * @param params Ҫִ�в�ѯ�����ռλ����ֵ
	 * @return ÿһ�����ݴ浽һ��map�ӣ�������Ϊ������Ӧ�е�ֵΪֵ�������м�¼�浽list�����С�
	 */
	public Map<String, String> findSingle(String sql, Object ... params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, String> map = null;

		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setValues(pstmt, params);
			rs = pstmt.executeQuery();
			
//			String[] cols = {"deptno", "dname", "loc"};  // ??? 
			
			ResultSetMetaData rsmd = rs.getMetaData(); // ��ȡ�������Ԫ������Ϣ
			int columnCount = rsmd.getColumnCount(); // ��ȡ������е�����
			String[] columnNames = new String[columnCount]; // ����һ�����飬��������е�����
			for (int i = 0; i < columnCount; ++ i) {
				columnNames[i] = rsmd.getColumnName(i + 1).toLowerCase();
			}
			
			if(rs.next()) {
				map = new HashMap<String, String>();
				// �е�������д�����޷��������ı� �� -> �ܲ��ܶ�̬�Ļ�ȡ������е�ÿ���е�������
				// ������ȡ�������ǿ��Խ���Щ������ŵ�һ�������У�Ȼ���������ȡֵ����
				for (String col : columnNames) {
					map.put(col, rs.getString(col));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		
		return map;
	}
	
	/**
	 * ��ѯ����
	 * @param sql Ҫִ�еĲ�ѯ���
	 * @param params Ҫִ�в�ѯ�����ռλ����ֵ
	 * @return ÿһ�����ݴ浽һ��map�ӣ�������Ϊ������Ӧ�е�ֵΪֵ�������м�¼�浽list�����С�
	 */
	public List<Map<String, Object>> findObj(String sql, Object ... params) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setValues(pstmt, params);
			rs = pstmt.executeQuery();
			Map<String,Object> map=null;
			
//			String[] cols = {"deptno", "dname", "loc"};  // ??? 
			
			ResultSetMetaData rsmd = rs.getMetaData(); // ��ȡ�������Ԫ������Ϣ
			int columnCount = rsmd.getColumnCount(); // ��ȡ������е�����
			String[] columnNames = new String[columnCount]; // ����һ�����飬��������е�����
			for (int i = 0; i < columnCount; ++ i) {
				columnNames[i] = rsmd.getColumnName(i + 1).toLowerCase();
			}
			
			
			String typeName=null;
			Object obj=null;
			Blob  blob=null;
			byte[] bt=null;
			
			if(rs.next()) {
				map = new HashMap<String, Object>();
				// �е�������д�����޷��������ı� �� -> �ܲ��ܶ�̬�Ļ�ȡ������е�ÿ���е�������
				// ������ȡ�������ǿ��Խ���Щ������ŵ�һ�������У�Ȼ���������ȡֵ����
				for (String col : columnNames) {
					  obj=rs.getObject(col);
					  if(obj!=null&&!"".equals(obj)){
						  typeName=obj.getClass().getSimpleName();
						  
						  if("BLOB".equals(typeName)){
							  blob=rs.getBlob(col);
							  bt=blob.getBytes(1, (int)blob.length());
							  map.put(col, bt);
						  }else{
							  map.put(col, obj);						  }
					  }else{
						  map.put(col, obj);
					  }
					  
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		
		return list;
	}
	
	
	/**
	 * ��ѯ����
	 * @param sql Ҫִ�еĲ�ѯ���
	 * @param params Ҫִ�в�ѯ�����ռλ����ֵ
	 * @return ÿһ�����ݴ浽һ��map�ӣ�������Ϊ������Ӧ�е�ֵΪֵ�������м�¼�浽list�����С�
	 */
	public Map<String,Object> findSingleObj(String sql, Object ... params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;

		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setValues(pstmt, params);
			rs = pstmt.executeQuery();
			
//			String[] cols = {"deptno", "dname", "loc"};  // ??? 
			
			ResultSetMetaData rsmd = rs.getMetaData(); // ��ȡ�������Ԫ������Ϣ
			int columnCount = rsmd.getColumnCount(); // ��ȡ������е�����
			String[] columnNames = new String[columnCount]; // ����һ�����飬��������е�����
			for (int i = 0; i < columnCount; ++ i) {
				columnNames[i] = rsmd.getColumnName(i + 1).toLowerCase();
			}
			
			String typeName=null;
			Object obj=null;
			Blob  blob=null;
			byte[] bt=null;
			
			if(rs.next()) {
				map = new HashMap<String, Object>();
				// �е�������д�����޷��������ı� �� -> �ܲ��ܶ�̬�Ļ�ȡ������е�ÿ���е�������
				// ������ȡ�������ǿ��Խ���Щ������ŵ�һ�������У�Ȼ���������ȡֵ����
				for (String col : columnNames) {
					obj=rs.getObject(col);
					 if(obj!=null&&!"".equals(obj)){
						  typeName=obj.getClass().getSimpleName();
						  
						  if("BLOB".equals(typeName)){
							  blob=rs.getBlob(col);
							  bt=blob.getBytes(1, (int)blob.length());
							  map.put(col, bt);
						  }else{
							  map.put(col, obj);						  }
					  }else{
						  map.put(col, obj);
					  }
					  
				}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		
		return map;
	}
	
	
	/**
	 * ����һ���������ķ���
	 * @param sql
	 * @param params
	 * @return
	 */
	public int getTotal(String sql, Object ... params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setValues(pstmt, params);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		
		return result;
	}
/**
 * ���ڶ���Ĳ�ѯ
 * @param cl ���ݶ�Ӧ������Ϣ
 * @param sql
 * @param params
 * @return
 */
@SuppressWarnings("unchecked")
public <T> List<T> finds(Class<?> c, String sql, Object ... params) {
	List<T> list = new ArrayList<T>();
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	T t = null;
	try {
		con = this.getConnection();
		pstmt = con.prepareStatement(sql);
		this.setValues(pstmt, params);
		rs = pstmt.executeQuery();

		Method[] methods = c.getDeclaredMethods(); // ��ȡָ�������е����з���
		List<Method> setters = new ArrayList<Method>(); // ��ȡ���е�����setter����
		Map<String, String> typeNames = new HashMap<String, String>();
		for(Method method : methods) {
			if (method.getName().startsWith("set")) {
				setters.add(method);

				// ��ȡ���setter�����ĵ�һ������������
				// ��ȡ������Ͷ�Ӧ��������ͣ����õ���SimpleName��˵������Ҫ��ȡ�����İ�·������Ҫ����
				typeNames.put(method.getName(), method.getParameterTypes()[0].getSimpleName()); 
			}
		}

		ResultSetMetaData rsmd = rs.getMetaData(); // ��ȡ�������Ԫ������Ϣ
		int columnCount = rsmd.getColumnCount(); // ��ȡ�������������
		String[] columnNames = new String[columnCount]; // ����һ�����飬��������е�����
		for (int i = 0; i < columnCount; ++ i) {
			columnNames[i] = rsmd.getColumnName(i + 1).toLowerCase();
		}

		String mname = null;
		String tname = null;
		while(rs.next()) { // ÿѭ��һ�ξ���һ�����ݣ���ôҪ����һ�����ݴ浽map��
			// ��������ʵ����һ�������Ǹ�
			t = (T) c.newInstance(); // t = new UserInfo();

			// ѭ����һ�������е������У�ȡ��ÿһ�е�����
			for (String colName : columnNames) { // usid  uname pwd
				mname = "set" + colName; // setuisd setuname setpwd
				// ѭ�����еķ������ҵ���һ�ж�Ӧ��ע����setter����
				for (Method md : setters) {
					if (mname.equalsIgnoreCase(md.getName())) { // setUsid setUname setPwd
						tname = typeNames.get(md.getName());
						if ("int".equals(tname) || "Integer".equals(tname)) {
							md.invoke(t, rs.getInt(colName)); // t.setUsid(1001) t.setUname("yc")
						} else if ("float".equals(tname) || "Float".equals(tname)) {
							md.invoke(t, rs.getFloat(colName)); 
						} else if ("double".equals(tname) || "Double".equals(tname)) {
							md.invoke(t, rs.getDouble(colName)); 
						} else {
							md.invoke(t, rs.getString(colName)); // t.setUsid(1001) t.setUname("yc")
						}
						break;
					}
				}
			}
			list.add(t);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (InstantiationException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
	} finally {
		this.closeAll(rs, pstmt, con);
	}
	return list;
}

	@SuppressWarnings("unchecked")
	public <T> T find(Class<?> c, String sql, Object ... params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		T t = null;
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setValues(pstmt, params);
			rs = pstmt.executeQuery();

			Method[] methods = c.getDeclaredMethods(); // ��ȡָ�������е����з���
			List<Method> setters = new ArrayList<Method>(); // ��ȡ���е�����setter����
			Map<String, String> typeNames = new HashMap<String, String>();
			for(Method method : methods) {
				if (method.getName().startsWith("set")) {
					setters.add(method);

					// ��ȡ���setter�����ĵ�һ������������
					// ��ȡ������Ͷ�Ӧ��������ͣ����õ���SimpleName��˵������Ҫ��ȡ�����İ�·������Ҫ����
					typeNames.put(method.getName(), method.getParameterTypes()[0].getSimpleName()); 
				}
			}

			ResultSetMetaData rsmd = rs.getMetaData(); // ��ȡ�������Ԫ������Ϣ
			int columnCount = rsmd.getColumnCount(); // ��ȡ�������������
			String[] columnNames = new String[columnCount]; // ����һ�����飬��������е�����
			for (int i = 0; i < columnCount; ++ i) {
				columnNames[i] = rsmd.getColumnName(i + 1).toLowerCase();
			}

			String mname = null;
			String tname = null;
			if(rs.next()) { // ÿѭ��һ�ξ���һ�����ݣ���ôҪ����һ�����ݴ浽map��
				// ��������ʵ����һ�������Ǹ�
				t = (T) c.newInstance(); // t = new UserInfo();

				// ѭ����һ�������е������У�ȡ��ÿһ�е�����
				for (String colName : columnNames) { // usid  uname pwd
					mname = "set" + colName; // setuisd setuname setpwd
					// ѭ�����еķ������ҵ���һ�ж�Ӧ��ע����setter����
					for (Method md : setters) {
						if (mname.equalsIgnoreCase(md.getName())) { // setUsid setUname setPwd
							tname = typeNames.get(md.getName());
							if ("int".equals(tname) || "Integer".equals(tname)) {
								md.invoke(t, rs.getInt(colName)); // t.setUsid(1001) t.setUname("yc")
							} else if ("float".equals(tname) || "Float".equals(tname)) {
								md.invoke(t, rs.getFloat(colName)); 
							} else if ("double".equals(tname) || "Double".equals(tname)) {
								md.invoke(t, rs.getDouble(colName)); 
							} else {
								md.invoke(t, rs.getString(colName)); // t.setUsid(1001) t.setUname("yc")
							}
							break;
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, con);
		}
		return t;
	
}

/*public void tets(){
	finds(UserInfo.class, sql, params)
	
	
}*/

/**
 * ���ڶ���Ĳ�ѯ
 * @param cl ���ݶ�Ӧ������Ϣ
 * @param sql
 * @param params
 * @return
 */
@SuppressWarnings("unchecked")
public <T> T find(Class<?> c, String sql, List<Object> params) {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	T t = null;
	try {
		con = this.getConnection();
		pstmt = con.prepareStatement(sql);
		this.setValues(pstmt, params);
		rs = pstmt.executeQuery();

		Method[] methods = c.getDeclaredMethods(); // ��ȡָ�������е����з���
		List<Method> setters = new ArrayList<Method>(); // ��ȡ���е�����setter����
		Map<String, String> typeNames = new HashMap<String, String>();
		for(Method method : methods) {
			if (method.getName().startsWith("set")) {
				setters.add(method);

				// ��ȡ���setter�����ĵ�һ������������
				// ��ȡ������Ͷ�Ӧ��������ͣ����õ���SimpleName��˵������Ҫ��ȡ�����İ�·������Ҫ����
				typeNames.put(method.getName(), method.getParameterTypes()[0].getSimpleName()); 
			}
		}

		ResultSetMetaData rsmd = rs.getMetaData(); // ��ȡ�������Ԫ������Ϣ
		int columnCount = rsmd.getColumnCount(); // ��ȡ�������������
		String[] columnNames = new String[columnCount]; // ����һ�����飬��������е�����
		for (int i = 0; i < columnCount; ++ i) {
			columnNames[i] = rsmd.getColumnName(i + 1).toLowerCase();
		}

		String mname = null;
		String tname = null;
		if(rs.next()) { // ÿѭ��һ�ξ���һ�����ݣ���ôҪ����һ�����ݴ浽map��
			// ��������ʵ����һ�������Ǹ�
			t = (T) c.newInstance(); // t = new UserInfo();

			// ѭ����һ�������е������У�ȡ��ÿһ�е�����
			for (String colName : columnNames) { // usid  uname pwd
				mname = "set" + colName; // setuisd setuname setpwd
				// ѭ�����еķ������ҵ���һ�ж�Ӧ��ע����setter����
				for (Method md : setters) {
					if (mname.equalsIgnoreCase(md.getName())) { // setUsid setUname setPwd
						tname = typeNames.get(md.getName());
						if ("int".equals(tname) || "Integer".equals(tname)) {
							md.invoke(t, rs.getInt(colName)); // t.setUsid(1001) t.setUname("yc")
						} else if ("float".equals(tname) || "Float".equals(tname)) {
							md.invoke(t, rs.getFloat(colName)); 
						} else if ("double".equals(tname) || "Double".equals(tname)) {
							md.invoke(t, rs.getDouble(colName)); 
						} else {
							md.invoke(t, rs.getString(colName)); // t.setUsid(1001) t.setUname("yc")
						}
						break;
					}
				}
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (InstantiationException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
	} finally {
		this.closeAll(rs, pstmt, con);
	}
	return t;
}
}

