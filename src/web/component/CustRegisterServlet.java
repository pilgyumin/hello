package web.component;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cust.Cust;
import com.cust.CustBiz;

@WebServlet({ "/CustRegisterServlet", "/cregister" })
public class CustRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CustBiz biz;
	public CustRegisterServlet() {
        super();
        biz = new CustBiz();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
	
		Cust cust = new Cust(id, pwd, name, Integer.parseInt(age));
	
		String next = "";
		try {
			biz.register(cust);
			request.setAttribute("rcust", cust);
			next = "registerok.jsp";
			
		} catch (Exception e) {
			next = "registerfail.jsp";
			e.printStackTrace();
		}
		RequestDispatcher rd = 
		request.getRequestDispatcher(next);
		rd.forward(request, response);
	}

}



