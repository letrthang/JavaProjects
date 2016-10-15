package sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ListUser
 */
public class ListUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// String errMsg = "";
		String commandType = request.getParameter("CammandButton");
		String DRIVERNAME = Global.driveName;
		String CONNECT_URL = Global.connectURL;
		Connection conn = null;
		ResultSet rs = null;
		int rowEffect = 0;
		System.out.println("ListUser.java - commandType =" + commandType);

		if (commandType.equals("Display")) {

			List<UserInfor> userlist = new ArrayList<UserInfor>();
			try {
				// no support in java 8
				Class.forName(DRIVERNAME);
				conn = DriverManager.getConnection(CONNECT_URL, Global.sqlUserName, Global.sqlPassword);

				String querySelect = "SELECT * FROM users";

				Statement st = conn.createStatement();
				rs = st.executeQuery(querySelect);
				// thông tin index của DB
				ResultSetMetaData rst = rs.getMetaData();
				// lấy sổ cột của DB
				int columnNum = rst.getColumnCount();
				int i = 0, j = 0;
				while (rs.next()) {
					if (i <= columnNum) {
						// mỗi lần loop là tạo ra 1 result mới, ko để dòng này
						// ngoài while()
						UserInfor result = new UserInfor();
						result.No = ++j;
						result.title = rs.getString(++i);
						result.description = rs.getString(++i);
						result.startDay = rs.getString(++i);
						result.endDay = rs.getString(++i);
						// đưa record dang doc vào list
						userlist.add(result);
					}
					i = 0;// reset i

				}
				// ---------------------For test
				// tạo bộ duyệt cho list
				Iterator<UserInfor> itr = userlist.iterator();
				// còn p/tử nào ko
				while (itr.hasNext()) {
					// tới p/tử tiếp theo
					UserInfor rsl = itr.next();
					System.out.println(" ListUser.java - rsl.No = " + rsl.No);
					System.out.println(" ListUser.java - rsl.Username = " + rsl.title);
				}
				// ---------------------End For test
				// trả về session UserInfor
				reviewList(request, response, userlist);

				st.close();
				conn.close();
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} // end if
		else if (commandType.equals("Delete All")) {
			try {
				// tra ve mot class kieu Class
				Class.forName(DRIVERNAME);
				conn = DriverManager.getConnection(CONNECT_URL, Global.sqlUserName, Global.sqlPassword);

				String queryDelete = "DELETE FROM  Users";

				Statement st = conn.createStatement();
				rowEffect = st.executeUpdate(queryDelete);

				st.close();
				conn.close();
			} catch (ClassNotFoundException cnf) {
				cnf.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<UserInfor> userlist = new ArrayList<UserInfor>();
			// trả về session UserInfor
			reviewList(request, response, userlist);
		} // end else if

	}

	protected void reviewList(HttpServletRequest request, HttpServletResponse response, List<UserInfor> userlist)
			throws IOException {

		HttpSession session = request.getSession();
		// String forward = (String)session.getAttribute("UserInforForward");
		// if(forward == null){
		// System.out.println(" ListUser.java - userlist.Birthday = "+
		// userlist.get(0).Birthday);
		session.setAttribute("UserInfor", userlist);
		session.setAttribute("ListForward", "No");// ko dùng tới
		System.out.println("ListUser.java - userlist.size = " + userlist.size());
		response.sendRedirect("list.jsp");
		// }
	}

}
