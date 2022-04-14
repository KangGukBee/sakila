package dao;
import java.util.*;

import util.DBUtil;

import java.sql.*;
public class StaffDao {
	public List<Map<String,Object>> selectStaffList(){
		List<Map<String,Object>> list = new ArrayList<>();
		//SQL 변수 생성
		Connection conn =null;
		PreparedStatement stmt=null;
		ResultSet rs =null;
		
		try {
			conn=DBUtil.getConnection(); 
			String sql ="SELECT staff_id staffID,CONCAT(first_name,_utf8mb4' ',last_name) Name,"
					+ "	address,city,country,store_id storeId,email,phone"
					+ "	FROM staff join address ON staff.address_id = address.address_id"
					+ "	join city ON address.city_id =city.city_id"
					+ "	join country ON city.country_id=country.country_id;";
					
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("staffId", rs.getInt("staffId"));
				map.put("Name", rs.getString("Name"));
				map.put("address",rs.getString("address"));
				map.put("city",rs.getString("city"));
				map.put("country",rs.getString("country"));
				map.put("storeId", rs.getInt("storeId"));
				map.put("phone",rs.getString("phone"));
				map.put("email", rs.getString("email"));
				list.add(map);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("예외 발생");
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(SQLException e) {
			}
		}
		
		
		return list;
	}
	public void main(String[] args) {
		StaffDao dao = new StaffDao();
		List<Map<String,Object>> list=dao.selectStaffList();
		for(Map m : list) {
			System.out.println(m.get("staffId"));
			System.out.println(m.get("Name"));
			System.out.println(m.get("address"));
			System.out.println(m.get("city"));
			System.out.println(m.get("country"));
			System.out.println(m.get("storeId"));
			
		}
	}
}
