package project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BBSItem {
	private int seqNo;
	private String title;
	private String content;
	private String writer;
	private Date date;
	private Time time;
	private int count;
	
	public BBSItem() {}

	public BBSItem(int seqNo, String title, String content, String writer,
			Date date, Time time, int count) {
		this.seqNo = seqNo;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.date = date;
		this.time = time;
		this.count = count;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public String toString() {
		return "Seq : "+ this.seqNo + ", Title : " + this.title + ", Content : " + this.content + ", writer : " + this.writer +
				", Date : " + this.date + " " + this.time + ",COUNT : " + this.count; 
	}
	
	public void readDB() throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
			conn = ds.getConnection();
			if(conn == null){
				throw new Exception("DB 연결에 실패했습니다.");
			}
//			System.out.println("conn : " + conn.toString());
			stmt = conn.createStatement();
			String command = "select * from proj_bbs where seqno = '"+this.seqNo+"';";
//			System.out.println(command);
			rs = stmt.executeQuery(command);
			while(rs.next()){
//				setSeqNo(rs.getInt("seqno"));
				setTitle(rs.getString("title"));
				setContent(rs.getString("content"));
				setWriter(rs.getString("writer"));
				setDate(rs.getDate("wdate"));
				setTime(rs.getTime("wtime"));
				setCount(rs.getInt("count"));
			}
			this.toString();
		} catch (Exception e ) {
			System.out.println("BBSItem-catch");
		} finally {
			try { rs.close();  } catch(Exception e) {}
			try { stmt.close(); } catch(Exception e) {}
			try { conn.close(); } catch(Exception e) {}
			
		}
	}
	
}
