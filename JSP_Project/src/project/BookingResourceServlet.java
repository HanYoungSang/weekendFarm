package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class BookingResourceServelt
 */
@WebServlet("/booking-resource")
public class BookingResourceServlet extends HttpServlet {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BookingResourceServlet:doGet() - START");
		int seqNo = Integer.parseInt(request.getParameter("SEQ"));
		HttpSession session = request.getSession();
		String loginID = (String) session.getAttribute("LOGIN_ID");
		System.out.println("loginID:"+ loginID);
		if(seqNo==0 || loginID==null){
			throw new ServletException("로그인 하지 않았거나 SEQ_NO가 없습니다.");
		}
		
		try {
			int bookingSeq = 0;
					
			// BOOKING 테이블 SEQ 받기
			String seqQuery = "select count(book_seq) as cnt from proj_book;";
			System.out.println(seqQuery);
			connectDB();
			rs = stmt.executeQuery(seqQuery);
			if(rs.next())
				bookingSeq = rs.getInt(1)+1;
			else
				bookingSeq = 1;
			
			// BOOKING 테이블 INSERT
			GregorianCalendar now = new GregorianCalendar();
			String query = String.format("insert into proj_book values('%d','%d','%s','%TF','%TT','N');",
					bookingSeq, seqNo, loginID, now, now);
			System.out.println(query);
			
			int sqlRet = stmt.executeUpdate(query);
			if(sqlRet == 0){
				throw new ServletException("DB에 저장되지 않았습니다.");
			}
			
			// RESOURCE 테이블 UPDATE
			String query2 = String.format(
					"update proj_resource "
					+ "set book_yn = 'Y', "
					+ "book_seq=%d, "
					+ "final_mod_date='%TF',"
					+ " final_mod_time='%TT',"
					+ " final_person='%s'"
					+ " where seq=%d;",
					bookingSeq, now, now,loginID, seqNo);

			System.out.println(query2);
			
			int sqlRet2 = stmt.executeUpdate(query2);
			if(sqlRet2 == 0){
				throw new ServletException("DB에 저장되지 않았습니다.");
			}
			
			
		
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			disconnectDB();
		}
		response.sendRedirect("main.jsp?BODY_PATH=resource-view");
	}
	
	private void connectDB() throws ServletException, Exception{
		System.out.println("MemberJoinServlet:connectDB()");
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
		conn = ds.getConnection();
		if(conn == null)
			throw new ServletException("DB 연결 오류");
		stmt = conn.createStatement();
		
	}
	private void disconnectDB(){
		System.out.println("MemberJoinServlet:disconnectDB()");
		try{ rs.close();   } catch(Exception e) {};
		try{ stmt.close(); } catch(Exception e) {};
		try{ conn.close(); } catch(Exception e) {};
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	
}
