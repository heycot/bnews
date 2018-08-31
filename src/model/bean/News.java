package model.bean;

public class News {
	private int id;
	private String name;
	private String preview;
	private String detail;
	private int id_cat;
	private String picture;
	private String cname;
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public News(int id, String name, String preview, String detail, int id_cat, String picture, String cname, int status) {
		super();
		this.id = id;
		this.name = name;
		this.preview = preview;
		this.detail = detail;
		this.id_cat = id_cat;
		this.picture = picture;
		this.cname = cname;
		this.status = status;
	}
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
