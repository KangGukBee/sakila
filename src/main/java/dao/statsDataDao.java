package dao;
import java.util.*;
import java.sql.*;
import util.DBUtil;
public class statsDataDao {
	//고객별 매출현황
	public List<Map<String, Object>> amountByCustomer(int BeginPage,int rowPerPage) {
	      List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      conn = DBUtil.getConnection();
	      String sql = "SELECT p.customer_id customerId,"
	            + "      concat(c.first_name,' ', c.last_name) name,"
	            + "      sum(p.amount) total"
	            + "      FROM payment p INNER JOIN customer c"
	            + "      ON p.customer_id = c.customer_id"
	            + "      GROUP BY p.customer_id"
	            + "      ORDER BY SUM(p.amount) DESC LIMIT ?,?";
	      try {
	         stmt = conn.prepareStatement(sql);
	         stmt.setInt(1, BeginPage);
	         stmt.setInt(2, rowPerPage);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            Map<String, Object> m = new HashMap<>();
	            m.put("customerId",rs.getInt("customerId"));
	            m.put("name",rs.getString("name"));
	            m.put("total",rs.getInt("total"));
	            list.add(m);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            rs.close();
	            stmt.close();
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return list;
	   }
	//임대료 별 영화 갯수
	public List<Map<String,Object>> selectRate(){
		Map<String,Object> map =null;
		List<Map<String,Object>> list=null;
		Connection conn =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		conn=DBUtil.getConnection();
		try {
			String sql="SELECT rental_rate rate,COUNT('film_id')  cnt FROM film GROUP BY rental_rate";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			list=new ArrayList<Map<String,Object>>();
			while(rs.next()) {
				map =new HashMap<>();
				map.put("rate", rs.getDouble("rate"));
				map.put("cnt", rs.getInt("cnt"));
				list.add(map);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//언어 별 영화 갯수
	public List<Map<String,Object>> selectLanguage(){
		List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
		Map<String,Object> map =null;
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		conn=DBUtil.getConnection();
		try {
			String sql="SELECT l.name name,COUNT('f.film_id') cnt  FROM film f inner join language l";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()){
				map=new HashMap<>();
				map.put("name",rs.getString("name"));
				map.put("cnt", rs.getInt(rs.getInt("cnt")));
				list.add(map);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();conn.close();stmt.close();
			}catch(SQLException e) {
			}
		}
		return list;
	}
	public List<Map<String,Object>> selectDayPay(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs =null;
		conn=DBUtil.getConnection();
		try {
			String sql=" SELECT s.store_id id, t.w w, case t.w"
					+ " 								WHEN 0 THEN '월'"
					+ " 								WHEN 1 THEN '화'"
					+ " 								WHEN 2 THEN '수'"
					+ " 								WHEN 3 THEN '목'"
					+ " 								WHEN 4 THEN '금'"
					+ " 								WHEN 5 THEN '토'"
					+ " 								WHEN 6 THEN '일'"
					+ " 							END DAYOFWEEK, t.c"
					+ "FROM (SELECT staff_id, WEEKDAY(payment_date) w, SUM(amount) c"
					+ "		FROM payment"
					+ "		GROUP BY staff_id, WEEKDAY(payment_date)) t"
					+ "		INNER JOIN staff s"
					+ "		ON t.staff_id = s.staff_id"
					+ "ORDER BY s.store_id, t.w ASC";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", rs.getInt("id"));
				map.put("w", rs.getString("w"));
				list.add(map);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public int totalRowDao() {
		int totalRow=0;
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		conn=DBUtil.getConnection();
		try {
			String sql="SELECT COUNT(*) cnt FROM payment p INNER JOIN customer c ON p.customer_id = c.customer_id";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			if(rs.next()) {
				totalRow=rs.getInt("cnt");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();conn.close();stmt.close();
			}catch(SQLException e) {
			}
		}
		return totalRow;
	}
}
