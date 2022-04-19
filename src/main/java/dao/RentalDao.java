package dao;
import java.util.*;
import util.DBUtil;
import java.sql.*;
public class RentalDao {
	/*
	 	사용할 쿼리
	 *  SELECT r.*,CONCAT(c.first_name,' ',c.last_name) cName, s.store_id storeId,i.film_id filmId,f.title
		FROM rental r INNER JOIN customer c
		ON r.customer_id =c.customer_id INNER JOIN staff s
		ON r.staff_id = s.staff_id INNER JOIN inventory i
		ON r.inventory_id = i.inventory_id INNER JOIN film f
		ON i.film_id = f.film_id
		WHERE s.store_id=?  AND c.first_name LIKE ? OR c.last_name LIKE ? 
		AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND  STR_TO_DATE(?,'%Y-%m-%d');
	 */
	public List<Map<String,Object>> selectReantalSearchList(int beginRow, int rowPerPage,int storeId,String customerName, String beginDate,String endDate){
		List<Map<String,Object>> list =new ArrayList<>();
		Connection conn=null;
		PreparedStatement stmt= null;
		ResultSet rs =null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT r.*,CONCAT(c.first_name,' ',c.last_name) customerName, s.store_id storeId,i.film_id filmId,f.title title"
					+ "		FROM rental r INNER JOIN customer c"
					+ "		ON r.customer_id =c.customer_id INNER JOIN staff s"
					+ "		ON r.staff_id = s.staff_id INNER JOIN inventory i"
					+ "		ON r.inventory_id = i.inventory_id INNER JOIN film f"
					+ "		ON i.film_id = f.film_id"
					+ "		WHERE s.store_id=?  AND c.first_name LIKE ? OR c.last_name LIKE ? "
					+ "		AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND  STR_TO_DATE(?,'%Y-%m-%d') LIMIT ?,?";
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1,storeId);
			stmt.setString(2,"%"+customerName+"%");
			stmt.setString(3,"%"+customerName+"%");
			stmt.setString(4, beginDate);
			stmt.setString(5,endDate);
			stmt.setInt(6, beginRow);
			stmt.setInt(7, rowPerPage);
			rs=stmt.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("rental_id", rs.getInt("rental_id"));
				map.put("inventory_id",rs.getString("inventory_id"));
				map.put("rental_date", rs.getString("rental_date"));
				map.put("customer_id", rs.getString("customer_id"));
				map.put("return_date",rs.getString("return_date"));
				map.put("staff_id",rs.getInt("staff_id"));
				map.put("last_update",rs.getString("last_update"));
				map.put("customerName", rs.getString("customerName"));
				map.put("storeId", rs.getInt("storeId"));
				map.put("filmId",rs.getInt("filmId"));
				map.put("title",rs.getString("title"));
				list.add(map);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close(); stmt.close(); conn.close();
			}catch(SQLException e) {
			}
		}
		return list;
	}
	public int totalRowDao(int storeId,String customerName, String startDate,String endDate){
		int totalRow=0;
		Connection conn=null;
		PreparedStatement totalRowStmt=null;
		ResultSet rs=null;
		String totalRowSql="";
		conn= DBUtil.getConnection();
		//totalRow
		try {
		
			//customerName = null 인 경우
			if(customerName.equals("")) {	
				if(startDate.equals("")==true && endDate.equals("")==true) {
					totalRowSql = "SELECT COUNT(*) cnt FROM rental r INNER JOIN customer c"
							+ "		ON r.customer_id =c.customer_id INNER JOIN staff s"
							+ "		ON r.staff_id = s.staff_id INNER JOIN inventory i"
							+ "		ON r.inventory_id = i.inventory_id INNER JOIN film f"
							+ "		ON i.film_id = f.film_id"
							+ "		WHERE s.store_id=? ";
					totalRowStmt = conn.prepareStatement(totalRowSql);
					totalRowStmt.setInt(1,storeId);
					ResultSet totalRowRs = totalRowStmt.executeQuery();
					if(totalRowRs.next()) {
						totalRow = totalRowRs.getInt("cnt");
						System.out.println(totalRow+" <-- totalRow");
					}

				}else if(endDate.equals("")) {
					totalRowSql = "SELECT COUNT(*) cnt FROM rental r INNER JOIN customer c"
							+ "		ON r.customer_id =c.customer_id INNER JOIN staff s"
							+ "		ON r.staff_id = s.staff_id INNER JOIN inventory i"
							+ "		ON r.inventory_id = i.inventory_id INNER JOIN film f"
							+ "		ON i.film_id = f.film_id"
							+ "		WHERE s.store_id=? AND r.rental_date > STR_TO_DATE(?,'%Y-%m-%d')";
					totalRowStmt = conn.prepareStatement(totalRowSql);
					totalRowStmt.setInt(1,storeId);
					totalRowStmt.setString(2, startDate);
					ResultSet totalRowRs = totalRowStmt.executeQuery();
					if(totalRowRs.next()) {
						totalRow = totalRowRs.getInt("cnt");
						System.out.println(totalRow+" <-- totalRow");
					}
				}else if(startDate.equals("")){
					totalRowSql="SELECT COUNT(*) cnt FROM rental r INNER JOIN customer c"
							+ "		ON r.customer_id =c.customer_id INNER JOIN staff s"
							+ "		ON r.staff_id = s.staff_id INNER JOIN inventory i"
							+ "		ON r.inventory_id = i.inventory_id INNER JOIN film f"
							+ "		ON i.film_id = f.film_id"
							+ "		WHERE s.store_id=? AND r.rental_date < STR_TO_DATE(?,'%Y-%m-%d')";
					totalRowStmt = conn.prepareStatement(totalRowSql);
					totalRowStmt.setInt(1,storeId);
					totalRowStmt.setString(2, endDate);
					ResultSet totalRowRs = totalRowStmt.executeQuery();
					if(totalRowRs.next()) {
						totalRow = totalRowRs.getInt("cnt");
						System.out.println(totalRow+" <-- totalRow");
					}
				}else {
					totalRowSql = "SELECT COUNT(*) cnt FROM rental r INNER JOIN customer c"
							+ "		ON r.customer_id =c.customer_id INNER JOIN staff s"
							+ "		ON r.staff_id = s.staff_id INNER JOIN inventory i"
							+ "		ON r.inventory_id = i.inventory_id INNER JOIN film f"
							+ "		ON i.film_id = f.film_id"
							+ "		WHERE s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND  STR_TO_DATE(?,'%Y-%m-%d')";
					totalRowStmt = conn.prepareStatement(totalRowSql);
					totalRowStmt.setInt(1,storeId);
					totalRowStmt.setString(2, startDate);
					totalRowStmt.setString(3, endDate);
					ResultSet totalRowRs = totalRowStmt.executeQuery();
					if(totalRowRs.next()) {
						totalRow = totalRowRs.getInt("cnt");
						System.out.println(totalRow+" <-- totalRow");
					}
				}
			}
				//customerNaem != null인경우
			if(startDate.equals("")==true && endDate.equals("")==true) {
					totalRowSql = "SELECT COUNT(*) cnt FROM rental r INNER JOIN customer c"
							+ "		ON r.customer_id =c.customer_id INNER JOIN staff s"
							+ "		ON r.staff_id = s.staff_id INNER JOIN inventory i"
							+ "		ON r.inventory_id = i.inventory_id INNER JOIN film f"
							+ "		ON i.film_id = f.film_id"
							+ "		WHERE s.store_id=? AND c.first_name LIKE ? OR c.last_name LIKE ?";
					totalRowStmt = conn.prepareStatement(totalRowSql);
					totalRowStmt.setInt(1,storeId);
					totalRowStmt.setString(2, "%"+customerName+"%");
					totalRowStmt.setString(3, "%"+customerName+"%");
					ResultSet totalRowRs = totalRowStmt.executeQuery();
				if(totalRowRs.next()) {
						totalRow = totalRowRs.getInt("cnt");
						System.out.println(totalRow+" <-- totalRow");
				}

			}else if(endDate.equals("")) {
					totalRowSql = "SELECT COUNT(*) cnt FROM rental r INNER JOIN customer c"
							+ "		ON r.customer_id =c.customer_id INNER JOIN staff s"
							+ "		ON r.staff_id = s.staff_id INNER JOIN inventory i"
							+ "		ON r.inventory_id = i.inventory_id INNER JOIN film f"
							+ "		ON i.film_id = f.film_id"
							+ "		WHERE s.store_id=? AND r.rental_date > STR_TO_DATE(?,'%Y-%m-%d') AND c.first_name LIKE ? OR c.last_name LIKE ? ";
					totalRowStmt = conn.prepareStatement(totalRowSql);
					totalRowStmt.setInt(1,storeId);
					totalRowStmt.setString(2, startDate);
					totalRowStmt.setString(3, "%"+customerName+"%");
					totalRowStmt.setString(4, "%"+customerName+"%");
					ResultSet totalRowRs = totalRowStmt.executeQuery();
				if(totalRowRs.next()) {
						totalRow = totalRowRs.getInt("cnt");
						System.out.println(totalRow+" <-- totalRow");
				}
			}else if(startDate.equals("")){
					totalRowSql="SELECT COUNT(*) cnt FROM rental r INNER JOIN customer c"
							+ "		ON r.customer_id =c.customer_id INNER JOIN staff s"
							+ "		ON r.staff_id = s.staff_id INNER JOIN inventory i"
							+ "		ON r.inventory_id = i.inventory_id INNER JOIN film f"
							+ "		ON i.film_id = f.film_id"
							+ "		WHERE s.store_id=? AND r.rental_date < STR_TO_DATE(?,'%Y-%m-%d') AND c.first_name LIKE ? OR c.last_name LIKE ? ";
					totalRowStmt = conn.prepareStatement(totalRowSql);
					totalRowStmt.setInt(1,storeId);
					totalRowStmt.setString(2, endDate);
					totalRowStmt.setString(3, "%"+customerName+"%");
					totalRowStmt.setString(4, "%"+customerName+"%");
					ResultSet totalRowRs = totalRowStmt.executeQuery();
				if(totalRowRs.next()) {
						totalRow = totalRowRs.getInt("cnt");
						System.out.println(totalRow+" <-- totalRow");
				}
			}else {
					totalRowSql = "SELECT COUNT(*) cnt FROM rental r INNER JOIN customer c"
							+ "		ON r.customer_id =c.customer_id INNER JOIN staff s"
							+ "		ON r.staff_id = s.staff_id INNER JOIN inventory i"
							+ "		ON r.inventory_id = i.inventory_id INNER JOIN film f"
							+ "		ON i.film_id = f.film_id"
							+ "		WHERE s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND  STR_TO_DATE(?,'%Y-%m-%d') and  c.first_name LIKE ? OR c.last_name LIKE ? ";
					totalRowStmt = conn.prepareStatement(totalRowSql);
					totalRowStmt.setInt(1,storeId);
					totalRowStmt.setString(2, startDate);
					totalRowStmt.setString(3, endDate);
					totalRowStmt.setString(4, "%"+customerName+"%");
					totalRowStmt.setString(5, "%"+customerName+"%");
					ResultSet totalRowRs = totalRowStmt.executeQuery();
				if(totalRowRs.next()) {
						totalRow = totalRowRs.getInt("cnt");
						System.out.println(totalRow+" <-- totalRow");
				}
				
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return totalRow;
	}
}
