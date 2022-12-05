package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import entity.Account;

/**
 * Servlet implementation class SignUpControl
 */
@WebServlet(name = "SignUpControl", urlPatterns = {"/signup"})
public class SignUpControl extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String repass = request.getParameter("repass");
		int boolSignUp = 1;
		if(!pass.equals(repass)) {
			boolSignUp = 0;
        	request.setAttribute("boolSignUp", boolSignUp);
        	request.getRequestDispatcher("Login.jsp").forward(request, response);
		}else {
			DAO dao = new DAO();
			Account a = dao.checkAccountExist(user);
			if(a == null) {
				//tai khoan chua co => được đk
				dao.signUp(user, pass);
				boolSignUp = 2;
				request.setAttribute("boolSignUp", boolSignUp);				
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}else {
				//tai khoan da co
				boolSignUp = 3;
				request.setAttribute("boolSignUp", boolSignUp);
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		}
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
