package dao;
import java.util.*;

import util.DBUtil;

import java.awt.Window.Type;
import java.sql.*;
public class FilmInStock {
	public Map<String,Object> filmInStockCall(int filmId,int storeId){
		Map<String,Object> map = new HashMap<>();
		int count=0;
		List<Integer> list =null;
		Connection conn=null;
		CallableStatement stmt =null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			String sql="{CALL film_in_stock(?,?,?)}";
			stmt=conn.prepareCall(sql);
			stmt.setInt(1,filmId);
			stmt.setInt(2,storeId);
			stmt.registerOutParameter(3, Types.INTEGER);
			rs=stmt.executeQuery();
			list = new ArrayList<Integer>();
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
			count=stmt.getInt(3);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("count",count);
		return map;
	}
}
