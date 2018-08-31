package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectionDBLibrary;
import model.bean.Category;
import model.bean.News;

public class NewsDao {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;

	public ArrayList<News> getNewByIdCat(int cid, int start, int end) {
		ArrayList<News> listNews = new ArrayList<>();

		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM news WHERE id_cat = ? AND status = 1 LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.setInt(2, start);
			pst.setInt(3, end);
			rs = pst.executeQuery();
			while (rs.next()) {
				News news = new News(rs.getInt("id_news"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getInt("id_cat"), rs.getString("picture"), null, rs.getInt("status"));
				listNews.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}

		return listNews;
	}
	
	public ArrayList<News> getAllNewByIdCat(int cid) {
		ArrayList<News> listNews = new ArrayList<>();

		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM news WHERE id_cat = ? AND status = 1";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				News news = new News(rs.getInt("id_news"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getInt("id_cat"), rs.getString("picture"), null, rs.getInt("status"));
				listNews.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}

		return listNews;
	}

	public News getNewByIdNews(int nid) {
		News news = null;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM news WHERE id_news = ? AND status = 1";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, nid);
			rs = pst.executeQuery();
			if (rs.next()) {
				news = new News(rs.getInt("id_news"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getInt("id_cat"), rs.getString("picture"), null, rs.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return news;
	}

	public ArrayList<News> getNewsSameIdCat(int nid) {
		ArrayList<News> list = new ArrayList<>();

		conn = ConnectionDBLibrary.getConnection();
		NewsDao nd = new NewsDao();
		News news = nd.getNewByIdNews(nid);
		String sql = "SELECT * FROM news WHERE id_cat = ? AND status = 1";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, news.getId_cat());
			rs = pst.executeQuery();
			while (rs.next()) {
				News news1 = new News(rs.getInt("id_news"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getInt("id_cat"), rs.getString("picture"), null, rs.getInt("status"));
				if ((news1.getId() != nid) && (list.size() < 2)) {
					list.add(news1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public News getNewByIdNewDefault() {
		News news = null;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM news GROUP BY id_news DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				news = new News(rs.getInt("id_news"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getInt("id_cat"), rs.getString("picture"), null, rs.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		return news;
	}

	public ArrayList<News> getNews() {
		ArrayList<News> list = new ArrayList<>();
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT *, news.name as nname, category.name as cname FROM news INNER JOIN category ON news.id_cat = category.id_cat WHERE status = 1 ORDER BY id_news DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News news = new News(rs.getInt("id_news"), rs.getString("nname"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getInt("id_cat"), rs.getString("picture"),
						rs.getString("cname"), rs.getInt("status"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		return list;
	}
	
	public ArrayList<News> getNewsLimit(int start, int count) {
		ArrayList<News> list = new ArrayList<>();
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT *, news.name as nname, category.name as cname FROM news INNER JOIN category ON news.id_cat = category.id_cat WHERE status = 1 ORDER BY id_news DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, start);
			pst.setInt(2, count);
			rs = pst.executeQuery();
			while (rs.next()) {
				News news = new News(rs.getInt("id_news"), rs.getString("nname"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getInt("id_cat"), rs.getString("picture"),
						rs.getString("cname"), rs.getInt("status"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return list;
	}

	public int addNews(News news) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "INSERT INTO news(name, preview_text, detail_text, id_cat, picture, status) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, news.getName());
			pst.setString(2, news.getPreview());
			pst.setString(3, news.getDetail());
			pst.setInt(4, news.getId_cat());
			pst.setString(5, news.getPicture());
			pst.setInt(6, 1);
			kq = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}

	public int deleteNews(News news) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "UPDATE news SET status = 0 WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, news.getId());
			kq = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}

	public int editNews(News news) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "UPDATE news SET name = ?, preview_text = ?, detail_text = ?, id_cat = ?, picture = ? WHERE id_news = ? AND status = 1";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, news.getName());
			pst.setString(2, news.getPreview());
			pst.setString(3, news.getDetail());
			pst.setInt(4, news.getId_cat());
			pst.setString(5, news.getPicture());
			pst.setInt(6, news.getId());
			kq = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}

	public ArrayList<News> getNewsSearch(String[] arrsearch) {
		ArrayList<News> list = new ArrayList<>();

		conn = ConnectionDBLibrary.getConnection();

		try {
			for (int i = 0; i < arrsearch.length; i++) {
				String sql = "SELECT *, news.name as nname, category.name as cname FROM news LEFT JOIN category ON news.id_cat = category.id_cat WHERE status = 1 AND news.name LIKE '% " + arrsearch[i] + " %'" ;
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					News news = new News(rs.getInt("id_news"), rs.getString("nname"), rs.getString("preview_text"),
							rs.getString("detail_text"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("cname"), rs.getInt("status"));
					
					if(checkNews(list, news) == false) {
						list.add(news);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}

		return list;
	}
	
	public static boolean checkNews(ArrayList<News> list, News news) {
		for (News news1 : list) {
			if(news1.getId() == news.getId()) return true;
		}
		return false;
	}

	

}
