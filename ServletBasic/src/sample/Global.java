package sample;

import java.util.ArrayList;
import java.util.List;

public class Global {

	public static  String User = "";
	public static  String commandTypeBck =""; //back up lại tên (commandType) của nút nhấn trước đó
	public static  int CurrentNo = 0;//số tt của user hiện tại, dùng cho chức năng add user
	public static  List<UserInfor> UserAdding = new ArrayList<UserInfor>();//những user đang add
	public static  List<UserInfor> userSearch = new ArrayList<UserInfor>();//những user đang search
	//kết nối SQL database
	public static String  driveName = "com.mysql.jdbc.Driver";	
	public static String  connectURL = "jdbc:mysql://localhost:3306/examuser";
	//public static String  driveName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//public static String  connectURL = "jdbc:sqlserver://localhost:1433;databaseName=examuser";
	public static String  sqlUserName = "root";
	public static String  sqlPassword = "thangle";
}
