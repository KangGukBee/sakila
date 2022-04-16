package dao;
import java.util.*;

import util.DBUtil;
import vo.*;
import java.sql.*;
public class customerInfoDao {
	public List<CustomerInfo> SelectCustomerByListPage(int BeginRow ,int RowPerPage){
		List<CustomerInfo> list = new ArrayList<>();
		PreparedStatement stmt=null;
		ResultSet rs = null;
		Connection conn =null;
		
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT id ID, name, address,'zip code' zipCode, phone, city, country, notes, sid SID from customer_list order by ID limit ?,?";
		
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, BeginRow);
			stmt.setInt(2, RowPerPage);
			rs=stmt.executeQuery();
			while(rs.next()) {
				CustomerInfo customerinfo = new CustomerInfo();
				customerinfo.setID(rs.getInt("ID"));
				customerinfo.setName(rs.getString("name"));
				customerinfo.setAddress(rs.getString("address"));
				customerinfo.setZipCode(rs.getString("zipCode"));
				customerinfo.setPhone(rs.getString("phone"));
				customerinfo.setCity(rs.getString("city"));
				customerinfo.setCountry(rs.getString("country"));
				customerinfo.setNotes(rs.getString("notes"));
				customerinfo.setSID(rs.getInt("SID"));
				list.add(customerinfo);	
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();conn.close();stmt.close();
			}catch(SQLException e){
				
			}
		}
		
		return list;
	}
	public int totalRowDao() {
		int totalRow=0; // �� ������ �� 
		Connection  conn= null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT COUNT(*) cnt FROM customer_list";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			totalRow=rs.getInt("cnt");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();stmt.close();conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return totalRow;
	}
}
