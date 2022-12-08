package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.Account;
import entity.LoadCart;

/**
 * Servlet implementation class LoadCartControl
 */
@WebServlet(name = "LoadCartControl", urlPatterns = {"/loadCart"})
public class LoadCartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int aid = a.getId();
        //String aid = request.getParameter("aid");
        
        DAO dao = new DAO();
        
        List<LoadCart> listShoes = dao.loadProductInCart(aid);
//        System.out.println(listShoes.get(0).getAmount());
//        System.out.println(listShoes.get(0).getPrice());
//        
//        System.out.println(listShoes.get(1).getAmount());
//        System.out.println(listShoes.get(1).getPrice());
//        
//        System.out.println(listShoes.get(2).getAmount());
//        System.out.println(listShoes.get(2).getPrice());
        double sum = 0;
        for(int i = 0; i < listShoes.size(); i++) {
        	sum += (double) listShoes.get(i).getAmount() * listShoes.get(i).getPrice();
        	System.out.println(sum);
        }
        double vat = sum*1/100;
        double sumpay = sum + vat;
        
        request.setAttribute("vat", vat);
        request.setAttribute("sum", sum);
        request.setAttribute("sumpay", sumpay);
        request.setAttribute("listShoes", listShoes);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
        
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
