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
import entity.Cart;

/**
 * Servlet implementation class CartControl
 */
@WebServlet(name = "AddCartControl", urlPatterns = {"/addCart"})
public class AddCartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String pid = request.getParameter("pid");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int aid = a.getId();
        DAO dao = new DAO();
        Cart c = dao.checkAmountProduct(aid, pid);
        if(c == null) {
        	try {
				dao.insertCartFirst(aid, pid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else {
        	int amount = c.getAmount();
			amount+= 1;
			dao.updateAmountCart(aid, pid, amount);
		}
        //Product listShoes = dao.getAllProductByID(pid);
        
        //request.setAttribute("listShoes", listShoes);    
        request.getRequestDispatcher("home").forward(request, response);
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
