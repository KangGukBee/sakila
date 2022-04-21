package dao;
import java.sql.*;
import java.util.*;
import util.DBUtil;
public class rewardsReportDao{
	public Map<String,Object> reward(int minMonthlyPurchases,double minDollarAmountPurchased){
		List<Map<String,Object>> list = new ArrayList<>();
		Connection conn=null;
		CallableStatement stmt=null; //�봽濡쒖떆�� �떎�뻾
		Integer count=0;
		ResultSet rs=null;
		Map<String,Object> map=new HashMap<>();
		try {
			conn= DBUtil.getConnection();
			String sql="{call rewards_report(?, ?, ?)}";
			stmt=conn.prepareCall(sql);
			stmt.setInt(1,minMonthlyPurchases);
			stmt.setDouble(2,minDollarAmountPurchased);
			stmt.registerOutParameter(3, Types.INTEGER);
			rs=stmt.executeQuery();
			while(rs.next()) {
			  Map<String,Object> m = new HashMap<>();
				m.put("customer_id",rs.getInt("customer_id"));
				m.put("store_id",rs.getInt("store_id"));
				m.put("first_name",rs.getString("first_name"));
				m.put("last_name",rs.getString("last_name"));
				m.put("email",rs.getString("email"));
				m.put("address_id",rs.getInt("address_id"));
				m.put("active",rs.getInt("active"));
				m.put("create_date",rs.getString("create_date"));
				m.put("last_update",rs.getString("last_update"));
				list.add(m);
			}
			count=stmt.getInt(3); //

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				rs.close(); stmt.close(); conn.close();
			}catch(SQLException e) {
				
			}
		}
		map.put("count", count);
		map.put("list", list);
		
		return map;
	}
	
}
