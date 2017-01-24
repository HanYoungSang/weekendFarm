package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class MemberJoinServlet
 */
@WebServlet("/member-join")
public class MemberJoinServlet extends HttpServlet {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			System.out.println("MemberJoinServlet:doPost()");
			String email = request.getParameter("EMAIL");
			String name = request.getParameter("NAME");
			String password = request.getParameter("PASSWORD");
			System.out.println(email + "," + name + "," + password);
			if(email.equals("") || name.equals("") || password.equals("")) {
				System.out.println("ID,NAME,PASSWORD가 없습니다.");
				response.sendRedirect("main.jsp?BODY_PATH=MemberJoin.jsp&JOINRESULT=-1");
			} else {
			
				System.out.println("ID 체크");
				if (checkMember(email) > 0 ){
					System.out.println("checkMember(email) > 0");
					response.sendRedirect("main.jsp?BODY_PATH=MemberJoin.jsp&JOINRESULT=-2");
				} else {
					joinMember(email,password,name);
					response.sendRedirect("main.jsp?BODY_PATH=MemberJoinOK.jsp");
				}
			}
			
		} catch (Exception e) {
			System.out.println("MemberJoinServlet:doPost() - exception : "+e.getMessage());
		} finally {
			try { disconnectDB(); } catch(Exception e) {};
			
		}
		
		

	}
	
	private int checkMember(String email) {
		
		String checkquery = String.format("select count(*) as cnt from proj_member where email='%s';",email);
		System.out.println(checkquery);
		try {
			connectDB();
			System.out.println("before rs:"+rs);
			rs = stmt.executeQuery(checkquery);
			System.out.println("afer rs:"+rs);
			if(rs.next()){
				System.out.println(rs.getString("rs.next():cnt"));
				return Integer.parseInt(rs.getString("cnt"));
			} else {
				return 0;
			}
			
		} catch (SQLTimeoutException e) {
			System.out.println("checkMember():SQLException"+e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("checkMember():SQLException"+e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("checkMember()Exception:"+e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
	private void joinMember(String email, String password, String name) throws ServletException, Exception {
		// TODO Auto-generated method stub
		
		GregorianCalendar now = new GregorianCalendar();
		String query = String.format("insert into proj_member(email,name,password,join_date) values ('%s','%s','%s','%TF')",email, password, name, now);
		System.out.println(query);
		int sqlResult = stmt.executeUpdate(query);
		if ( sqlResult < 1 ){
			System.out.println("INSERT MEMBER 실패");
		}
		
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

}
