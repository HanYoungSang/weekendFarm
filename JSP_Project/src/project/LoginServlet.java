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


@WebServlet("/member-login")
public class LoginServlet extends HttpServlet {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	final int LOGIN_OK = 1;
	final int NOT_FOUND_ID = -10;
	final int PASSWORD_WRONG = -20;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("LoginServlet:doPost() - start");
		String email         = request.getParameter("EMAIL");
		String password   = request.getParameter("PASSWORD");
		String currentURL = request.getParameter("CURRENT_URL");
		int retcode = 0;
		
		if( email==null || password == null ) {
			System.out.println("ID나 PASSWORD 가 없음");
		} else {
			System.out.println(email +","+password+","+currentURL);
			try {
				retcode = checkLoginInfo(request, response, email,password);
				if(retcode == 1 ) {
					System.out.println("로그인 성공");
				} else {
					System.out.println("로그인 실패");
				}
				
			} catch (Exception e) {
				System.out.println("LoginServlet:doPost() - exception : "+e.getMessage());
			}
		}
		
//		System.out.println(currentURL.indexOf("LOGINRESULT", 0));
//		System.out.println(currentURL.substring(0,currentURL.indexOf("LOGINRESULT", 0)));
		
		if(currentURL.contains("?"))
			if(currentURL.contains("&LOGINRESULT"))
				response.sendRedirect(currentURL.substring(0,currentURL.indexOf("LOGINRESULT", 0))+"LOGINRESULT="+retcode);
			else
				response.sendRedirect(currentURL+"&LOGINRESULT="+retcode);
		else
			response.sendRedirect(currentURL+"?LOGINRESULT="+retcode);
			
	}

	private int checkLoginInfo(HttpServletRequest request, HttpServletResponse response, String email, String password) throws ServletException, Exception {
		// TODO Auto-generated method stub
		System.out.println("LoginServlet:checkLoginInfo()");
		try {
			connectDB();
			String query = "select password, name from proj_member where email='"+email+"';";
			System.out.println(query);
			rs = stmt.executeQuery(query);
			if(!rs.next()){
				System.out.println("NOT_FOUND_ID");
				return NOT_FOUND_ID;
			} else {
				String dbpassword = rs.getString("password");
				String name = rs.getString("name");
				if(dbpassword.equals(password)){
					System.out.println("LOGIN_OK");
					saveLoginInfo(email, password);
					HttpSession session = request.getSession();
					session.setAttribute("LOGIN_ID", email);
					session.setAttribute("LOGIN_NAME", name);
					return LOGIN_OK;
				} else {
					System.out.println("PASSWORD_WRONG");
					return PASSWORD_WRONG;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		} finally {
			try { disconnectDB(); } catch (Exception e) { }
			
		}
		return NOT_FOUND_ID;
	}


	private void saveLoginInfo(String email, String password) throws SQLException, Exception {
		// 로그인한 시간 저장
		GregorianCalendar now = new GregorianCalendar();
		String query = String.format("update proj_member set login_date='%TF', login_time='%TT' where email='%s';",now, now, email);
		System.out.println(query);
		int sqlresult = stmt.executeUpdate(query);
		if (sqlresult > 0)
			System.out.println("로그인 시간 저장 성공");
		
	}

	private void connectDB() throws ServletException, Exception{
		System.out.println("LoginServlet:connectDB()");
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
		conn = ds.getConnection();
		if(conn == null)
			throw new ServletException("DB 연결 오류");
		stmt = conn.createStatement();
		
	}
	private void disconnectDB(){
		System.out.println("LoginServlet:disconnectDB()");
		try{ rs.close();   } catch(Exception e) {};
		try{ stmt.close(); } catch(Exception e) {};
		try{ conn.close(); } catch(Exception e) {};
	}
	

}
