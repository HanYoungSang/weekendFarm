package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
 * Servlet implementation class MemberModifyServlet
 */
@WebServlet("/member-modify")
public class MemberModifyServlet extends HttpServlet {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
			System.out.println("MemberModifyServlet:doPost()");
			String email = request.getParameter("EMAIL");
			String name = request.getParameter("NAME");
			String password = request.getParameter("PASSWORD");
			System.out.println(email + "," + name + "," + password);
			if(email.equals("") || name.equals("") || password.equals("")) {
				System.out.println("ID,NAME,PASSWORD가 없습니다.");
				response.sendRedirect("main.jsp?BODY_PATH=MemberModify.jsp&JOINRESULT=-1");
			} else {
			
				System.out.println("ID 체크");
				if (updateMember(email, name, password) > 0 ){
					System.out.println("updateMember(email) > 0");
					HttpSession session = request.getSession();
					session.setAttribute("LOGIN_NAME", name);
					response.sendRedirect("main.jsp?BODY_PATH=MemberModify.jsp&JOINRESULT=1");
				} else {
					response.sendRedirect("main.jsp?BODY_PATH=MemberModify.jspp&JOINRESULT=-3");
				}
			}
			
		} catch (Exception e) {
			System.out.println("MemberModifyServlet:doPost() - exception : "+e.getMessage());
		} finally {
			try { disconnectDB(); } catch(Exception e) {};
			
		}
		
		
	}
	
	private int updateMember(String email, String name, String password) {
		// TODO Auto-generated method stub
		int ret = 0;
		String query = String.format("update proj_member set name='%s', password='%s' where email='%s';", name, password, email);
		System.out.println(query);
		 try {
			connectDB();
			ret = stmt.executeUpdate(query);
			System.out.println("MemberModifyServlet:updateMember : " + ret);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		 
		
		return ret;
	}

	private void connectDB() throws ServletException, Exception{
		System.out.println("MemberModifyServlet:connectDB()");
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
		conn = ds.getConnection();
		if(conn == null)
			throw new ServletException("DB 연결 오류");
		stmt = conn.createStatement();
		
	}
	
	private void disconnectDB(){
		System.out.println("MemberModifyServlet:disconnectDB()");
		try{ rs.close();   } catch(Exception e) {};
		try{ stmt.close(); } catch(Exception e) {};
		try{ conn.close(); } catch(Exception e) {};
	}

}
