package dao;
import java.util.*;

import util.DBUtil;

import java.sql.*;
public class filmNotInStock {
	public Map<String,Object> callFilmNot(int filmId, int storeId){
		Map<String,Object> map = new HashMap<String,Object>();
		Connection conn=null;
		CallableStatement stmt = null;
		ResultSet rs=null;
		List<Integer> list = null;
		int count=0;
		
		conn=DBUtil.getConnection();
		try {
			String sql="{CALL film_not_in_stock(?,?,?)}";
			stmt=conn.prepareCall(sql);
			stmt.setInt(1, filmId);
			stmt.setInt(2, storeId);
			stmt.registerOutParameter(3, Types.INTEGER);
			rs=stmt.executeQuery();
			list=new ArrayList<Integer>();
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
			count=stmt.getInt(3);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();conn.close();stmt.close();
			}catch(SQLException e) {
			}
		}
		map.put("list", list);
		map.put("count", count);
		return map;
	}
}
