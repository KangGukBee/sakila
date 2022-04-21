package dao;
import java.util.*;
import util.DBUtil;
import vo.*;
import java.sql.*;
public class nicerButSlowerFilmList {
	public static List<nicerbutslowerfilmList> SelectByNicer(int BeginRow, int rowPerPaper){
		List<nicerbutslowerfilmList> list =new ArrayList<>();
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		conn=DBUtil.getConnection();
		
		try {
			String sql="SELECT FID,title,description,category,price,length,rating,actors FROM nicer_but_slower_film_list ORDER BY FID LIMIT ? , ? ";
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, BeginRow);
			stmt.setInt(2, rowPerPaper);
			rs=stmt.executeQuery();
			while(rs.next()) {
				nicerbutslowerfilmList slower = new nicerbutslowerfilmList();
				slower.setFid(rs.getInt("FID"));
				slower.setTitle(rs.getString("title"));
				slower.setDescription(rs.getString("description"));
				slower.setCategory(rs.getString("category"));
				slower.setPrice(rs.getDouble("price"));
				slower.setLength(rs.getInt("length"));
				slower.setRating(rs.getString("rating"));
				slower.setActors(rs.getString("actors"));
				list.add(slower);
			}			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();stmt.close();conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	
	public int totalRow() {
		int totalRow=0;
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		conn=DBUtil.getConnection();
		
		try {
			String sql="SELECT COUNT(*) cnt FROM nicer_but_slower_film_list";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			if(rs.next()) {
				totalRow=rs.getInt("cnt");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();stmt.close();conn.close();
			}catch(SQLException e) {
				
			}
		}
		return totalRow;
	}
}
