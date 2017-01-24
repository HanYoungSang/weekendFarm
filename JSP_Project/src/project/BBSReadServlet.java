package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class BBSReadServlet
 */
@WebServlet("/BBS-READ")
public class BBSReadServlet extends HttpServlet {
	Connection conn = null;
	Statement stmt = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int retCode = 1;
		String seqNo = null;
		String listSeqNo = null;
		try {
			seqNo = request.getParameter("SEQ_NO");
			listSeqNo = request.getParameter("LIST_SEQ");
			System.out.println("BBSReadServlet:doGet-seqNo:" + seqNo +",listSeqNo:"+listSeqNo);
			String checkquery = String.format("update proj_bbs set count = count + 1 where seqno = '%s';", seqNo);
			System.out.println(checkquery);
			
			if (seqNo.equals("")) {
				retCode = -1;
				System.out.println("BBSReadServlet:doGet() - seqNo.equals");
				throw new Exception ("SEQ Param 없음");
			}
			if(retCode == 1) {
				connectDB();
				stmt.executeUpdate(checkquery);
			}

			
		} catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			disconnectDB();
		}
		request.setAttribute("SEQ_NO", seqNo);
		request.setAttribute("LIST_SEQ", listSeqNo);
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp?BODY_PATH=BBSViewDetail.jsp");
		rd.forward(request, response);
		
//		response.sendRedirect("BBSViewDetail.jsp?SEQ_NO="+seqNo+"&LIST_SEQ="+listSeqNo);
		
	}
	private void connectDB() throws ServletException, Exception{
		System.out.println("BBSReadServlet:connectDB()");
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
		conn = ds.getConnection();
		if(conn == null){
			throw new ServletException("DB 연결 오류");
		}
		stmt = conn.createStatement();
		
	}
	private void disconnectDB(){
		System.out.println("BBSReadServlet:disconnectDB()");
		try{ stmt.close(); } catch(Exception e) {};
		try{ conn.close(); } catch(Exception e) {};
	}
}
