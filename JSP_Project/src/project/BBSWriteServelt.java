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
 * Servlet implementation class BBSWriteServelt
 */
@WebServlet("/bbs-post")
public class BBSWriteServelt extends HttpServlet {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		try {
			int seqNo;
			String id = (String)session.getAttribute("LOGIN_ID");
			if(id == null) {
				try {
					response.sendRedirect("main.jsp?BODY_PATH=bbs-list");
					throw new Exception("로그인하지 않은 회원입니다.");
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			GregorianCalendar now = new GregorianCalendar();
			connectDB();
			rs = stmt.executeQuery("select MAX(SEQNO) as MAX from proj_bbs");
			rs.next();
			seqNo = rs.getInt("max") + 1;
			String query = String.format("insert into proj_bbs(seqno,title,content,writer,wdate, wtime)"
					+ "value('%d','%s','%s','%s','%TF','%TT');",
					seqNo, title, content, session.getAttribute("LOGIN_ID"), now, now);
			System.out.println(query);

			int rowNum = stmt.executeUpdate(query);
			if(rowNum < 1 ){
				response.sendRedirect("main.jsp?BODY_PATH=bbs-list");
				throw new Exception("DB에 저장되지 않았습니다.");
				
			} 
			response.sendRedirect("main.jsp?BODY_PATH=bbs-list");
			
		} catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			 disconnectDB();
		}
		System.out.println("FINISH-BBSWriteServelt");
		
	}
	private void connectDB() throws ServletException, Exception{
		System.out.println("BBSWriteServelt:connectDB()");
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
		conn = ds.getConnection();
		if(conn == null){
			throw new ServletException("DB 연결 오류");
		}
		stmt = conn.createStatement();
		
	}
	private void disconnectDB(){
		System.out.println("BBSWriteServelt:disconnectDB()");
		try{ rs.close();   } catch(Exception e) {};
		try{ stmt.close(); } catch(Exception e) {};
		try{ conn.close(); } catch(Exception e) {};
	}
}
