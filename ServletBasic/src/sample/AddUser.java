package sample;
/**
 *When we create an instance of a class using new operator, it does two things:

1. Load the class in to memory, if it is not loaded - which means creating 
in-memory representation of the class from the .class file so that an instance
can be created out of it. This includes initializing static variables 
(resolving of that class).
2. Create an instance of that class and store the reference to the variable.

Class.forName does only the first thing.
It loads the class in to memory and return that reference as an instance of Class. 
If we want to create an instance then, we can call newInstance method of that class. 
which will invoke the default constructor (no argument constructor).
Note that if the default constructor is not accessible, then newInstance method will 
throw an IllegalAccessException. and if the class is an abstract class or interface 
or it does not have a default constructor, then it will throw an InstantiationException. 
If any exception arises during resolving of that class, it will throw an ExceptionInInitializerError.

If the default constructor is not defined, then we have to invoke the defined 
constructor using reflection API.

But the main advantage with Class.forName is, it can accept the class name as a String argument. 
So we can pass the class name dynamically. But if we create an instance of a class using new operator, 
the class name can't be changed dynamically.

Class.forName() in turn will call loadClass method of the caller ClassLoader (ClassLoder of the 
class from where Class.forName is invoked).

By default, the Class.forName() resolve that class. which means, initialize all static variables inside that class.
same can be changed using the overloaded method of Class.forName(String name,boolean initialize,ClassLoader loader)

The main reason for loading jdbc driver using Class.forName() is, the driver can change dynamically.
in the static block all Drivers will create an instance of itself and register that class with 
DriverManager using DriverManager.registerDriver() method. Since the Class.forName(String className) 
by default resolve the class, it will initialize the static initializer.
So when we call Class.forName("com.sun.jdbc.odbc.JdbcOdbcDriver"),
the Driver class will be loaded, instantiated and registers with DriverManager.

So if you are using new Operator you have to do the following things.

Driver drv = new com.sun.jdbc.odbc.JdbcOdbcDriver();
DriverManager.registerDriver(drv);

======================================
Hello Guys,

I m looking for class loading and unloading behavior of MIDP, I just want to know to the following things

1. When Class is get loaded and unloaded ?
2. Where they are loaded in memory I mean in stack or heap?
3. How much Memory a class will take if its class size is 2KB ?
4. Suppose a class create 1000 String literals in String pool and 2000 prmitive type constants when they eligible for garbage collection.
5. What are "dangling objects" ?

Thanks in advance 
======================================
*/

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddUser
 */
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// check xem nút nào được nhấn
	private String commandType = "";
	private int ret;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() {
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
		String cmd = request.getParameter("commandButton");

		if (cmd != null) {
			if (cmd.equals("Save")) {
				commandType = "Save";
				ret = addProcess(request, response);
				if (ret == -1) { // error can not connect to database
					errorProcessing("ConnectDB", request, response);
				} else {
					errorProcessing("ClearError", request, response);
				}
			} else if (cmd.equals("Search")) {
				commandType = "Search";
				ret = searchProcess(request, response);
				if (ret == -1) { // error can not connect to database
					errorProcessing("ConnectDB", request, response);
				} else {
					errorProcessing("ClearError", request, response);
				}
			} else if (cmd.equals("Delete")) {
				commandType = "Delete";
				ret = deleteProcess(request, response);
				if (ret == -1) {
					// error can not connect to database
					errorProcessing("ConnectDB", request, response);
				} else {
					errorProcessing("ClearError", request, response);
				}
			}
		}
	}// end doPost

	// ===================================
	protected int addProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// init lại hoạt động Add user
		if (Global.commandTypeBck != "Save") {
			Global.UserAdding.clear();
			Global.CurrentNo = 0;
		}
		// String username = request.getParameter("txtUsername");
		String strTitle = request.getParameter("txtTitle");
		String strDescription = request.getParameter("txtDescription");
		String strStartDay = request.getParameter("txtStartDay");
		String strendDay = request.getParameter("txtEndDay");

		String errMsg = "";
		// must to copy file mysql-connector-java-5.1.6-bin.jar to lib folder of
		// Tomcat
		String DRIVERNAME = Global.driveName;
		String CONNECT_URL = Global.connectURL;

		Connection conn = null;
		int rowEffect = 0;

		UserInfor currentUser = new UserInfor();
		currentUser.No = ++Global.CurrentNo;
		currentUser.title = strTitle;
		currentUser.description = strDescription;
		currentUser.startDay = strStartDay;
		currentUser.endDay = strendDay;
		userCommonProcess(currentUser);

		// thực hiện query
		try {
			Class.forName(DRIVERNAME);// tra ve mot class kieu Class
			conn = DriverManager.getConnection(CONNECT_URL, Global.sqlUserName, Global.sqlPassword);

			String queryInsert = "INSERT INTO users VALUES ('" + strTitle + "','" + strDescription + "','" + strStartDay
					+ "','" + strendDay + "') ";

			Statement st = conn.createStatement();
			rowEffect = st.executeUpdate(queryInsert);

			if (rowEffect > 0) {
				errMsg = "Insert complete.";
				System.out.println(errMsg);
			} else {
				errMsg = "Insert incomplete!";
				System.out.println(errMsg);
			}

			st.close();
			conn.close();

		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1; // -1 = NG
		}
		// hiển thị lại trang add user
		addForwardSession(request, response);
		// back-up lại nút nhấn hiện tại
		Global.commandTypeBck = commandType;
		return 0; // 0 = OK
	}

	// ===================================
	protected int searchProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String DRIVERNAME = Global.driveName;
		String CONNECT_URL = Global.connectURL;
		Connection conn = null;
		ResultSet rs = null;

		String strTitle = request.getParameter("txtTitle");
		// nếu user ko nhập vào thì ko tìm kiếm
		if (strTitle == "") {
			strTitle = "*";
		}
		;
		// String strPass = request.getParameter("txtDescription");
		String strStartDay = request.getParameter("txtStartDay");
		// nếu user ko nhập vào thì ko tìm kiếm
		if (strStartDay == "") {
			strStartDay = "*";
		}
		;
		// String strBirthday = request.getParameter("txtBirthday");
		// xóa biến global mỗi một lần search
		Global.userSearch.clear();
		// thực hiện query
		try {
			// tra ve mot class kieu Class
			Class.forName(DRIVERNAME);
			conn = DriverManager.getConnection(CONNECT_URL, Global.sqlUserName, Global.sqlPassword);
			String querySelect = "SELECT * FROM  Users WHERE Title =" + "'" + strTitle + "'" + "OR StartDay =" + "'"
					+ strStartDay + "'";
			Statement st = conn.createStatement();
			rs = st.executeQuery(querySelect);
			// thông tin index của DB
			ResultSetMetaData rst = rs.getMetaData();
			// lấy sổ cột của DB
			int columnNum = rst.getColumnCount();
			int i = 0, j = 0;
			while (rs.next()) {
				if (i <= columnNum) {
					// mỗi lần loop là tạo ra 1 result mới, ko để dòng này ngoài
					// while()
					UserInfor result = new UserInfor();
					result.No = ++j;
					result.title = rs.getString(++i);
					result.description = rs.getString(++i);
					result.startDay = rs.getString(++i);
					result.endDay = rs.getString(++i);
					// đưa record đang get từ rs vào list
					Global.userSearch.add(result);
				}
				i = 0;// reset i
			}
			st.close();
			conn.close();
			rs.close();

		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} // -1 = NG
			// hiển thị lại trang add user
		addForwardSession(request, response);
		// back-up lại nút nhấn hiện tại
		Global.commandTypeBck = commandType;
		return 0; // 0 = OK
	}

	// ===================================
	protected int deleteProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int ret = 0;
		if (Global.commandTypeBck == "Search") {
			int len = Global.userSearch.size();
			UserInfor currentUser = new UserInfor();
			System.out.println("delete From Search ");
			for (int i = 1; i <= len; i++) {// lần lượt xóa trong DataBase
				// String chkBox = request.getParameter("Cbox"+i);
				// System.out.println("check box = "+
				// request.getParameter("Cbox"+i));
				if (request.getParameter("Cbox" + i) != null) {
					System.out.println("delete.... " + i);
					currentUser.No = Global.userSearch.get(i - 1).No;
					currentUser.title = Global.userSearch.get(i - 1).title;
					currentUser.description = Global.userSearch.get(i - 1).description;
					currentUser.startDay = Global.userSearch.get(i - 1).startDay;
					currentUser.endDay = Global.userSearch.get(i - 1).endDay;

					ret = deleteUser(currentUser);
					if (ret == -1) {
						return ret;
					} // xu li lien neu co loi connect Database
				}
			}
			// xóa những user đã bị delete trong list Global.userSearch
			for (int i = len; i > 0; i--) {
				if (request.getParameter("Cbox" + i) != null) {
					Global.userSearch.remove(i - 1);
				}
			}
			// size bây giờ đã thay đổi nếu có p/tử bị xóa
			for (int i = 0; i < Global.userSearch.size(); i++) {
				// reset lại số tt của currentUser.No mỗi lần delete
				Global.userSearch.get(i).No = i + 1;
			}
		} // end if
		else if (Global.commandTypeBck == "Save") {
			int len = Global.UserAdding.size();
			UserInfor currentUser = new UserInfor();
			System.out.println("delete From Save");
			// lần lượt xóa trong DataBase
			for (int i = 1; i <= len; i++) {
				// String chkBox = request.getParameter("Cbox"+i);
				// System.out.println("check box = "+
				// request.getParameter("Cbox"+i));
				if (request.getParameter("Cbox" + i) != null) {
					System.out.println("delete.... " + i);
					currentUser.No = Global.UserAdding.get(i - 1).No;
					currentUser.title = Global.UserAdding.get(i - 1).title;
					currentUser.description = Global.UserAdding.get(i - 1).description;
					currentUser.startDay = Global.UserAdding.get(i - 1).startDay;
					currentUser.endDay = Global.UserAdding.get(i - 1).endDay;

					ret = deleteUser(currentUser);
					// xu li lien neu co loi connect Database
					if (ret == -1) {
						return ret;
					}
				}
			}
			// xóa những user đã bị delete trong list Global.userSearch
			for (int i = len; i > 0; i--) {
				if (request.getParameter("Cbox" + i) != null) {
					Global.UserAdding.remove(i - 1);
				}
			}
			// size bây giờ đã thay đổi nếu có p/tử bị xóa
			for (int i = 0; i < Global.UserAdding.size(); i++) {
				// reset lại số tt của currentUser.No mỗi lần delete
				Global.UserAdding.get(i).No = i + 1;
			}
			// reset lại Global.CurrentNo khi có user trong list của add bị xóa
			Global.CurrentNo = Global.UserAdding.size();
		} // end else if
			// hiển thị lại trang add user
		addForwardSession(request, response);
		return ret; // 0 = OK
	}

	// ===================================
	protected int deleteUser(UserInfor currentUser) {

		String DRIVERNAME = Global.driveName;
		String CONNECT_URL = Global.connectURL;
		Connection conn = null;
		int rowEffect = 0;

		// thực hiện query
		try {
			// tra ve mot class kieu Class
			Class.forName(DRIVERNAME);
			conn = DriverManager.getConnection(CONNECT_URL, Global.sqlUserName, Global.sqlPassword);

			String queryDelete = "DELETE FROM Users WHERE Title =" + "'" + currentUser.title + "'" + "AND EndDay ="
					+ "'" + currentUser.endDay + "'" + "AND Description =" + "'" + currentUser.description + "'"
					+ "AND StartDay =" + "'" + currentUser.startDay + "'";

			Statement st = conn.createStatement();
			rowEffect = st.executeUpdate(queryDelete);

			if (rowEffect > 0) {
				System.out.println("Deleted row =" + rowEffect);
			} else {
				System.out.println("Ko co gi de xoa");
			}

			st.close();
			conn.close();

		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;// -1 = NG
		}
		return 0;// 0 = NG
	}

	// ===================================
	protected void userCommonProcess(UserInfor currentUser) {
		if (commandType == "Save") {
			// đưa user hiện tại vào list
			Global.UserAdding.add(currentUser);

		}
	}

	// ===================================
	protected void addForwardSession(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		// hiển thị thông tin user ở jsp
		session.setAttribute("UserLogin", Global.User);
		// ----------------------
		if (commandType == "Save") {
			// tạo một list tạm để chứa Global.UserAdding
			List<UserInfor> tmp = new ArrayList<UserInfor>();
			for (int i = 0; i < Global.UserAdding.size(); i++) {
				// tạo list tmp có chiều dài bằng list Global.UserAdding
				tmp.add(null);
			}
			// nếu ko tạo list tmp tạm, Global.UserAdding bị empty mỗi khi set
			// setAttribute
			Collections.copy(tmp, Global.UserAdding);

			session.setAttribute("UserAdding", tmp);
			session.setAttribute("CommandView", "Add");
			System.out.println("Global.UserAdding.size = " + Global.UserAdding.size());
			System.out.println("Global.CurrentNo = " + Global.CurrentNo);

		}
		// ----------------------
		if (commandType == "Search") {
			// tạo một list tạm để chứa Global.UserAdding
			List<UserInfor> tmp = new ArrayList<UserInfor>();
			for (int i = 0; i < Global.userSearch.size(); i++) {
				// tạo list tmp có chiều dài bằng list Global.UserAdding
				tmp.add(null);
			}
			// nếu ko tạo list tmp tạm, Global.UserAdding bị empty mỗi khi set
			// setAttribute
			Collections.copy(tmp, Global.userSearch);
			session.setAttribute("UserSearch", tmp);
			session.setAttribute("CommandView", "Search");
			System.out.println("Global.userSearch.size = " + Global.userSearch.size());
		}
		// ----------------------
		if (commandType == "Delete") {
			if (Global.commandTypeBck == "Search") {
				// tạo một list tạm để chứa Global.UserAdding
				List<UserInfor> tmp = new ArrayList<UserInfor>();
				for (int i = 0; i < Global.userSearch.size(); i++) {
					// tạo list tmp có chiều dài bằng list Global.UserAdding
					tmp.add(null);
				}
				// nếu ko tạo list tmp tạm, Global.UserAdding bị empty mỗi khi
				// set setAttribute
				Collections.copy(tmp, Global.userSearch);
				session.setAttribute("UserSearch", tmp);
				session.setAttribute("CommandView", "Search");
				System.out.println("Global.userSearchAffterDelete.size = " + Global.userSearch.size());
			} else if (Global.commandTypeBck == "Save") {
				// tạo một list tạm để chứa Global.UserAdding
				List<UserInfor> tmp = new ArrayList<UserInfor>();
				for (int i = 0; i < Global.UserAdding.size(); i++) {
					// tạo list tmp có chiều dài bằng list Global.UserAdding
					tmp.add(null);
				}
				// nếu ko tạo list tmp tạm, Global.UserAdding bị empty mỗi khi
				// set setAttribute
				Collections.copy(tmp, Global.UserAdding);
				session.setAttribute("UserAdding", tmp);
				session.setAttribute("CommandView", "Add");
				System.out.println("Global.UserAddingAffterDelete.size = " + Global.UserAdding.size());
			}
		}
		// -----------------------
		response.sendRedirect("add.jsp");
	}

	protected void errorProcessing(String ErrorType, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		String errMsg = "Error: Can not Connect to DataBase";

		if (ErrorType == "ConnectDB") {
			session.setAttribute("ErrorMessage", errMsg);
			// response.sendRedirect("add.jsp");
			RequestDispatcher reqDis = request.getRequestDispatcher("add.jsp");
			reqDis.forward(request, response);
			session.setAttribute("ErrorMessage", "");
			// int lai cac bien global chua thong tin cac user dang xu li
			Global.UserAdding.clear();
			Global.userSearch.clear();
			Global.CurrentNo = 0;
		} else if (ErrorType == "ClearError") {
			session.setAttribute("ErrorMessage", "");
		}
	}
}
