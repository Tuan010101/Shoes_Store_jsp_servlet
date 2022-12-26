package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.Account;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet(name = "LoginControl", urlPatterns = {"/login"})
public class LoginControl extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        int boolLogin = 1;
        
        DAO dao = new DAO();     
        Account a = dao.login(username, password);
        if(a==null) {
        	boolLogin = 0;
        	request.setAttribute("boolLogin", boolLogin);
        	request.getRequestDispatcher("Login.jsp").forward(request, response);
        }else {
        	HttpSession session = request.getSession();
        	session.setAttribute("acc", a);
        	//sau 2 tieng tu dong logout
        	session.setMaxInactiveInterval(1200);
        	
        	//bool = 1;
        	//request.getRequestDispatcher("home") sẽ mang dữ liệu về trang home
        	//request.getRequestDispatcher("home").forward(request, response);
        	//response.sendRedirect("home") chỉ chuyển về trang home và không có dữ liệu gì mang về cả
        	response.sendRedirect("home");
		}
        
        //request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
