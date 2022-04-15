

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pkg.haldiwale.connection.DbCon;
import pkg.haldiwale.dao.UserDao;
import pkg.haldiwale.model.Users;
public class RegisterServlet  extends HttpServlet  

{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request ,HttpServletResponse response ) throws IOException
	{
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	//make user object
	Users userModel = new Users(name, email, password);

	//create a database model
	UserDao regUser;
	try {
		regUser = new UserDao(DbCon.getConnection());
	
	if (regUser.saveUser(userModel)) {
	   response.sendRedirect("index.jsp");
	} else {
	    String errorMessage = "User Available";
	    HttpSession regSession = request.getSession();
	    regSession.setAttribute("RegError", errorMessage);
	    }
	}
	 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
}
}
