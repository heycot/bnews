package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectionDBLibrary;
import model.bean.Category;

public class CategoryDao {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private java.sql.PreparedStatement pst;
	
	public ArrayList<Category> getCats(){
		ArrayList<Category> list = new ArrayList<>();
		
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM category";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while( rs.next()) {
				Category cat = new Category(rs.getInt("id_cat"), rs.getString("name"));
				list.add(cat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		return list;
	}

	public Category getCatById(int cid) {
		Category cat = null;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM category WHERE id_cat = ?";
		try {

			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			if( rs.next()) {
				cat = new Category(rs.getInt("id_cat"), rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return cat;
	}

	public int addCat(Category cat) {
		int kq = 0;
		
		conn = ConnectionDBLibrary.getConnection();
		String sql = "INSERT INTO category(name) VALUES(?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cat.getName());
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}

	public int editCat(Category cat) {
		int kq = 0;
		
		conn = ConnectionDBLibrary.getConnection();
		String sql = "UPDATE FROM category SET name = ? WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cat.getName());
			pst.setInt(2,cat.getId());
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}

	public int deleteCat(int cid) {
		int kq = 0;
		
		conn = ConnectionDBLibrary.getConnection();
		String sql = "DELETE FROM category  WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}
	
	
}
