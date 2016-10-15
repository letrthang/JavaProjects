/*
RequestDispatcher vs response.sendRedirect.
RequestDispatcher : dÃ¹ng Ä‘á»‘i tÆ°á»£ng nÃ y Ä‘á»ƒ chuyá»ƒn request tá»›i má»™t
page khÃ¡c Ä‘á»ƒ xá»­ lÃ½, cÃ³ nghÄ©a lÃ  trang Ä‘Æ°á»£c chuyá»ƒn tá»›i sáº½ nháº­n Ä‘Æ°á»£c 
Ä‘áº§y Ä‘á»§ tham sá»‘.VÃ  Ä‘iá»�u Ä‘áº·c biá»‡t ná»¯a lÃ  Ä‘á»‹a chá»‰ trÃªn address bar
sáº½ khÃ´ng bá»‹ thay Ä‘á»•i.KhÃ¡i niá»‡m nÃ y giá»‘ng nhÆ° lÃ  mÆ°á»£n má»™t trang 
khÃ¡c Ä‘á»ƒ xá»­ lÃ½.
---------------
sendRedirect() : cÃ¡i nÃ y chá»‰ Ä‘Æ¡n giáº£n lÃ  chuyá»ƒn tá»›i URL chá»‰ 
trÆ°á»›c, toÃ n bá»™ cÃ¡c param sáº½ khÃ´ng tá»“n táº¡i vÃ  Ä‘á»‹a chá»‰ trÃªn 
address bar sáº½ thay Ä‘á»•i thÃ nh URL Ä‘Æ°á»£c chuyá»ƒn tá»›i, Ä‘Æ¡n giáº£n 
lÃ  chá»‰ tráº£ vá»� URL cho trÃ¬nh duyá»‡t.
NÃ³i má»™t cÃ¡ch Ä‘Æ¡n giáº£n hÆ¡n RequestDispatcher lÃ m viá»‡c trÃªn server
vÃ  sendRedirect() lÃ m viá»‡c trÃªn browser.
---------------
Send redirect khiÃªÌ�n client yÃªu cÃ¢Ì€u Ä‘ÃªÌ�n mÃ´Ì£t taÌ€i nguyÃªn (URL) 
khaÌ�c kÃªÌ‰ caÌ‰ coÌ� thÃªÌ‰ thuÃ´Ì£c server khaÌ�c. 
Do Ä‘oÌ� session phuÌ£ thuÃ´Ì£c vaÌ€o client.
NÃªÌ�u client laÌ€ Firefox thiÌ€ baÌ£n seÌƒ nhÃ¢Ì£n Ä‘Æ°Æ¡Ì£c cuÌ€ng session. 
Trong khi forward chiÌ‰ chuyÃªÌ‰n quyÃªÌ€n Ä‘iÃªÌ€u khiÃªÌ‰n cho mÃ´Ì£t 
taÌ€i nguyÃªn nÃ´Ì£i taÌ£i, vÃ¢Ìƒn giÆ°Ìƒ nguyÃªn yÃªu cÃ¢Ì€u taÌ€i nguyÃªn Ä‘ang coÌ�.
 NÃªÌ�u baÌ£n Ä‘ÃªÌ‰ yÌ� trÃªn URL thiÌ€ seÌƒ thÃ¢Ì�y Ä‘iÃªÌ€u naÌ€y..
 */

package sample;

import sample.Global;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("Login") != null) { // Clicked on Add button
			System.out.println("Clicked on Login button");
			processLogin(request, response);
		}
		// Clicked on Add button
		if ((request.getParameter("Add") != null) && (request.getParameter("Add").equals("Add"))) {

			System.out.println("Clicked on Add button");
			// init cÃ¡c biáº¿n global lÃºc vÃ o cÃ¡c mÃ n hÃ¬nh liÃªn quan
			initGlobal("Add");
			processAdd(request, response);
			// Clicked on List button
		} else if ((request.getParameter("List") != null) && (request.getParameter("List").equals("List"))) {
			System.out.println("Clicked on List button");
			// init cÃ¡c biáº¿n global lÃºc vÃ o cÃ¡c mÃ n hÃ¬nh liÃªn quan
			// initGlobal("List");
			processList(request, response);
			// Clicked on Create button
		} else if (request.getParameter("Create") != null) {
			System.out.println("Clicked on Create button");
			processCreate(request, response);
		}

	}

	// Login
	/**
	 * CÃ¡c mÃ´ táº£ cho phÆ°Æ¡ng thá»©c
	 * 
	 * @param request
	 *            : mÃ´ táº£ cho parameter
	 * @param response
	 *            : mÃ´ táº£ cho parameter
	 * @return mÃ´ táº£ giÃ¡ trá»‹ tráº£ vá»�
	 */
	private void processLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String str_name = request.getParameter("uname");
		String str_pass = request.getParameter("pname");

		// lÆ°u láº¡i username Ä‘á»ƒ sent cho AddUser
		Global.User = str_name;
		if ((str_name == "") || (str_pass == "")) {
			str_name = str_pass + "ABC";
		}
		if (str_name.equals(str_pass)) {
			RequestDispatcher reqDis = request.getRequestDispatcher("menu.jsp");
			reqDis.forward(request, response);
		} else {
			RequestDispatcher reqDis = request.getRequestDispatcher("index.jsp");
			reqDis.forward(request, response);
		}
	}

	// Forward to Add page
	private void processAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher reqDis = request.getRequestDispatcher("add.jsp");
		reqDis.forward(request, response);
		// PrintWriter out = response.getWriter();
		// out.println("Hello World");
	}

	// Forward to List page
	private void processList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher reqDis = request.getRequestDispatcher("list.jsp");
		reqDis.forward(request, response);
		// action them
	}

	// Create new record in database
	private void processCreate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get data from the jsp
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");
		String fullName = request.getParameter("fullName");
		String birthday = request.getParameter("birthday");
		// Debug
		System.out.println("fullName=" + userName + ";pass=" + pass + ";address=" + fullName + ";birthay:" + birthday);
		// Insert the record into database
		String DRIVERNAME = Global.driveName;
		String CONNECT_URL = Global.connectURL;

		Connection conn = null;
		int rowEffect = 0;
		String errMsg = "";
		String queryInsert = "INSERT INTO Users VALUES ('" + userName + "','" + pass + "','" + fullName + "','"
				+ birthday + "') ";
		try {
			// 1) Load the driver
			Class.forName(DRIVERNAME);

			// 2) Create Connection Object
			conn = DriverManager.getConnection(CONNECT_URL, Global.sqlUserName, Global.sqlPassword);

			// 3.1) Create PreparedStatement
			Statement st = conn.createStatement();

			// 4) Execute the query
			rowEffect = st.executeUpdate(queryInsert);

			// 5) Report the result.
			if (rowEffect > 0) {
				errMsg = "Insert complete.";
			} else {
				errMsg = "Insert incomplete!";
			}

			st.close();
			conn.close();

		} catch (SQLException sql) {
			sql.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Forward to View page
		RequestDispatcher reqDis = request.getRequestDispatcher("menu.jsp");
		reqDis.forward(request, response);
	}

	private void initGlobal(String str) {

		if (str == "Add") {
			Global.CurrentNo = 0;
			Global.UserAdding.clear();
		}

	}
}