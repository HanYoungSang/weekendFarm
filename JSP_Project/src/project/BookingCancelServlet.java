package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Servlet implementation class BookingCancelServlet
 */
@WebServlet("/BookingCancel")
public class BookingCancelServlet extends HttpServlet {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BookingCancelServlet:doGet() - START");
		int seqNo = Integer.parseInt(request.getParameter("SEQ"));
		int bookSeq = Integer.parseInt(request.getParameter("BOOKSEQ"));
		
		HttpSession session = request.getSession();
		String loginID = (String) session.getAttribute("LOGIN_ID");
		System.out.println("loginID:"+ loginID);
		if(seqNo==0 || loginID==null){
			throw new ServletException("로그인 하지 않았거나 SEQ_NO가 없습니다.");
		}
		try {
			connectDB();
			// BOOKING 테이블에 CNCL_YN UPDATE
			GregorianCalendar now = new GregorianCalendar();
			String query = String.format("update proj_book set cncl_yn='Y' where book_seq='%d' and resource_seq='%d';"
					,bookSeq, seqNo );
			System.out.println("query:"+query);
			
			int sqlRet = stmt.executeUpdate(query);
			System.out.println("sqlRet:"+sqlRet);
			if(sqlRet == 0){
				throw new ServletException("DB에 저장되지 않았습니다.");
			}
			
			// RESOURCE 테이블 UPDATE
			String query2 = String.format(
					"update proj_resource "
					+ "set book_yn = 'N', "
					+ "book_seq=null, "
					+ "final_mod_date=null,"
					+ " final_mod_time=null,"
					+ " final_person=null"
					+ " where seq=%d;", seqNo);

			System.out.println("query2:"+query2);
			
			int sqlRet2 = stmt.executeUpdate(query2);
			if(sqlRet2 == 0){
				throw new ServletException("DB에 저장되지 않았습니다.");
			}
			
			
		} catch (SQLException e) {
			System.out.println("BookingCancelServlet:SQLException catch() "+e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("BookingCancelServlet:Exception catch() "+e.getMessage());
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
