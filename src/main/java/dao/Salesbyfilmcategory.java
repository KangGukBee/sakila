package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.DBUtil;
import vo.salesbyfilmcategory;

public class Salesbyfilmcategory {
	public List<salesbyfilmcategory> selectSales(int BeginRow,int rowPerPage){
		List<salesbyfilmcategory> list = new ArrayList<salesbyfilmcategory>();
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		try {	
			conn=DBUtil.getConnection();
			String sql="SELECT store,manager,total_sales totalSales FROM sales_by_store LIMIT ?,?";;
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, BeginRow);
			stmt.setInt(2, rowPerPage);
			rs=stmt.executeQuery();
			while(rs.next()) {
				salesbyfilmcategory sales = new salesbyfilmcategory();
				sales.setStore(rs.getString("store"));
				sales.setManager(rs.getString("manager"));
				sales.setTotalSales(rs.getInt("totalSales"));
				list.add(sales);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close(); rs.close();conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
	
		return list;
	}
	public int totalRowDao(){
		int totalRow=0;
		Connection conn=null;
		conn= DBUtil.getConnection();
		//totalRow\
		try {
			String totalRowSql = "SELECT COUNT(*) cnt FROM actor_info";
			PreparedStatement totalRowStmt = conn.prepareStatement(totalRowSql);
			ResultSet totalRowRs = totalRowStmt.executeQuery();
			if(totalRowRs.next()) {
				totalRow = totalRowRs.getInt("cnt");
				System.out.println(totalRow+" <-- totalRow");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return totalRow;
		
	}

	
	
}
