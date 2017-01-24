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
 * Servlet implementation class ResourceViewServlet
 */
@WebServlet("/resource-view")
public class ResourceViewServlet extends HttpServlet {

	final int MAX_RESOURCE = 20;
	
	ResourceItem[] resrcItem = new ResourceItem[MAX_RESOURCE];	//
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ResourceViewServlet:doPost() - START");
		try {
			int cnt = 0;
			connectDB();
			String query = "Select * from proj_resource where year=2017;";
			
			rs = stmt.executeQuery(query);
			System.out.println(query);
			while(rs.next()) {
//				System.out.println("cnt:" + cnt);
				resrcItem[cnt] = new ResourceItem();
				resrcItem[cnt].setSeq(rs.getInt("seq"));
				resrcItem[cnt].setYear(rs.getInt("year"));
				resrcItem[cnt].setRow(rs.getInt("row"));
				resrcItem[cnt].setColumn(rs.getInt("column"));
//				String book_yn = rs.getString("book_yn");
//				if(book_yn.equals("N"))
//					resrcItem[cnt].setBook_yn("미예약");
//				else
//					resrcItem[cnt].setBook_yn("예약완료");
				resrcItem[cnt].setBook_yn(rs.getString("book_yn"));
				
				resrcItem[cnt].setBook_seq(rs.getInt("book_seq"));
				resrcItem[cnt].setFinal_mod_date(rs.getDate("final_mod_date"));
				resrcItem[cnt].setFinal_mod_time(rs.getTime("final_mod_time"));
				resrcItem[cnt].setFinal_person(rs.getString("final_person"));
				cnt++;
			}
			if(cnt > 0 ){
//				System.out.println("보여줘");
				System.out.println(resrcItem[0].toString());
			}

			System.out.println("FIN:ResourceViewServlet-doGet");
			request.setAttribute("RESOURCE", resrcItem);;
			RequestDispatcher rd = request.getRequestDispatcher("main.jsp?BODY_PATH=ResourceView.jsp");
			rd.forward(request, response);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			disconnectDB();
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

