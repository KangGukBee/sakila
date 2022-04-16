package dao;
import java.util.*;
import vo.*;
import util.*;
import java.sql.*;
public class ActorInfoDao {
	public List<ActorInfo> selectActorInfoListByPage(int beginRow,int rowPerPage){
		List<ActorInfo> list =new ArrayList<>();
		Connection conn=null;
     	conn =DBUtil.getConnection();
     	String sql="SELECT actor_id actorId,CONCAT(first_name, _utf8mb4' ',last_name) Name,film_info filmInfo FROM actor_info ORDER BY actor_id LIMIT ? , ? ";
     	PreparedStatement stmt =null;
     	ResultSet rs=null;
     	try {
     		stmt=conn.prepareStatement(sql);
     		stmt.setInt(1,beginRow);
     		stmt.setInt(2,rowPerPage);
     		rs=stmt.executeQuery();
     		while(rs.next()) {
     			ActorInfo actorinfo =new  ActorInfo();
     			actorinfo.setActorId(rs.getInt("actorId"));
     			actorinfo.setName(rs.getString("Name"));
     			actorinfo.setFilmInfo(rs.getString("filmInfo"));
     			list.add(actorinfo);
     			
     		}
     	}catch(SQLException e) {
     		e.printStackTrace();
     	}finally {
     		try {
     			rs.close(); stmt.close(); conn.close();
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
