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

@WebServlet("/bbs-list")
public class BBSListServlet extends HttpServlet{

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String strUpperSeqNo = request.getParameter("LAST_SEQ_NO");
		System.out.println("BBSListServlet.strUpperSeqNo:"+strUpperSeqNo);
		int upperSeqNo;
		if(strUpperSeqNo == null){
			upperSeqNo = Integer.MAX_VALUE;
		} else {
			upperSeqNo = Integer.parseInt(strUpperSeqNo);
		}
		BBSList list = null;
		try {
			list = readDB(upperSeqNo);
		} catch (Exception e) {
			System.out.println("ERROR:readDB("+upperSeqNo+")");
			e.printStackTrace();
		} finally {
			disconnectDB();
		}
//		System.out.println(list);
		System.out.println("FIN:BBSListServlet-doGet");
		request.setAttribute("BBS_LIST", list);;
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp?BODY_PATH=BBSListView.jsp");
		rd.forward(request, response);
		
	}
	
	private BBSList readDB(int upperSeqNo) {
		
		BBSList list = new BBSList();
		try {
			connectDB();
			int totalCount = 0;
			String chkQuery = "Select count(*) as cnt from proj_bbs;";
			System.out.println(chkQuery);
			rs = stmt.executeQuery(chkQuery);
			if(rs.next())
				totalCount =  rs.getInt(1);
			
			int firstPageCheck = 0;
			String command = "Select * from proj_bbs where seqno < " + upperSeqNo + " order by seqno desc;";
			System.out.println(command);
			rs = stmt.executeQuery(command);
	//		System.out.println(rs.next());
			for(int cnt=0;cnt<5;cnt++){
				if(!rs.next()) break;
//				System.out.println(rs.getInt("seqno"));
//				System.out.println(rs.getString("title"));
//				System.out.println(rs.getString("writer"));
				
				list.setSeqNo(cnt, rs.getInt("seqno"));
				list.setTitle(cnt, rs.getString("title"));
				list.setWriter(cnt, rs.getString("writer"));
				list.setDate(cnt, rs.getDate("wdate"));
				list.setTime(cnt, rs.getTime("wtime"));
				list.setCount(cnt, rs.getInt("count"));
				if(cnt == 0)
					firstPageCheck = rs.getInt("seqno");
			}
			
			// 다음 페이지 플래그
			if(!rs.next()){
				list.setLastPage(true);
			}
//			System.out.println(list.isFirstPage());
			System.out.println("seqno : "+firstPageCheck);
			// 이전 페이지 플레그
			if(totalCount == firstPageCheck){
//				System.out.println("firstPageCheck");
				list.setFirstPage(true);
			}
//			System.out.println(list.isFirstPage());
			
		} catch (Exception e ) {
			System.out.println("BBSListServlet-catch");			
		}
		
		return list;
	}

	private void connectDB() throws ServletException, Exception{
		System.out.println("BBSListServlet:connectDB()");
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
		conn = ds.getConnection();
		if(conn == null){
			throw new ServletException("DB 연결 오류");
		}
		stmt = conn.createStatement();
		
	}
	private void disconnectDB(){
		System.out.println("BBSListServlet:disconnectDB()");
		try{ rs.close();   } catch(Exception e) {};
		try{ stmt.close(); } catch(Exception e) {};
		try{ conn.close(); } catch(Exception e) {};
	}

}
