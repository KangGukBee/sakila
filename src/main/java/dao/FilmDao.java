package dao;
import java.util.*;
import dao.*;
import util.DBUtil;
import vo.*;
import java.sql.*;
public class FilmDao {
	public List<Film> selectFilmListByPage(int BeginRow,int rowPerPage){
		List<Film> list = new ArrayList<>();
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			
			conn=DBUtil.getConnection();
			String sql="SELECT film.film_id AS FID, film.title AS title, film.description AS description, category.name AS category, film.rental_rate AS price,film.length AS length, film.rating AS rating, GROUP_CONCAT(CONCAT(actor.first_name, _utf8mb4' ', actor.last_name) SEPARATOR ', ') AS actor  FROM category LEFT JOIN film_category ON category.category_id = film_category.category_id LEFT JOIN film ON film_category.film_id = film.film_id  JOIN film_actor ON film.film_id = film_actor.film_id JOIN actor ON film_actor.actor_id = actor.actor_id GROUP BY film.film_id, category.name LIMIT ?,?";
			stmt =conn.prepareStatement(sql);
			stmt.setInt(1, BeginRow);
			stmt.setInt(2, rowPerPage);
			rs=stmt.executeQuery();
			while(rs.next()) {
				Film film = new Film();
				film.setFID(rs.getInt("FID"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setCategory(rs.getString("category"));
				film.setPrice(rs.getInt("price"));
				film.setLength(rs.getInt("length"));
				film.setRating(rs.getString("rating"));
				film.setActors(rs.getString("actor"));
				list.add(film);
			}
		}catch(SQLException e){		
			e.printStackTrace();
		}finally {
			try {
				stmt.close();rs.close();conn.close();
			}catch(SQLException e) {
				
			}
		}
		return list;
	}
	public int totalRowDao() {
		int totalRow=0;
		Connection conn =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT COUNT(*) cnt FROM film_list";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			if(rs.next()) {
				totalRow = rs.getInt("cnt");
				System.out.println(totalRow+" <-- totalRow");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close(); stmt.close();conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return totalRow;
		
	}
	//프로시저
		public Map<String, Object> filmInStockCall(int filmId, int storeId) {
			Map<String, Object> map = new HashMap<String, Object>();
			Connection conn = null;
			// PreparedStatement : 쿼리를 실행
			// CallableStatement : 프로시저를 실행 
			CallableStatement stmt = null;
			ResultSet rs = null;
			// select inventory_id .... 
			List<Integer> list = new ArrayList<>();
			// select count(inventroy_id) ....
			Integer count = 0;
			conn = DBUtil.getConnection();
			try {
				stmt = conn.prepareCall("{call film_in_stock(?, ?, ?)}");
				stmt.setInt(1, filmId);
				stmt.setInt(2, storeId);
				stmt.registerOutParameter(3, Types.INTEGER);
				rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1)); // rs.getInt("inventory_id")
			}
			count = stmt.getInt(3); // 프로시저 3번째 out변수 값
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	public Map<String,Object> filmNotInStockCall(int filmId,int storeId){
		Map<String,Object> map =new HashMap<String,Object>();
		Connection conn=null;
		CallableStatement stmt=null;
		ResultSet rs =null;
		Integer count = 0;
		List<Integer> list =new ArrayList<>();
		
		conn=DBUtil.getConnection();
		try {
			
			stmt=conn.prepareCall("{CALL film_not_in_stock(?, ?, ?)}");
			stmt.setInt(1, filmId);
			stmt.setInt(2, storeId);
			stmt.registerOutParameter(3, Types.INTEGER);
			rs=stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
			count=stmt.getInt(3);
		}catch(SQLException e){
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("count", count);
		return map;
	}

	public static void main(String[] args) {
		FilmDao fd = new FilmDao();
		int filmId = 17;
		int storeId = 1;
		Map<String,Object> map = fd.filmNotInStockCall(filmId, storeId);
		List<Integer> list = (List<Integer>)(map.get("list"));
		int count = (Integer)map.get("count");
		
		System.out.println(filmId + "번 영화는 "+ storeId +"번 가게에 "+count+"개 나감");
		for(int id : list) {
			System.out.println(id);
		}
	}
}