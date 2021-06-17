package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.CookieCrud;
import model.User;


public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CookieCrud cookieCrud;
    public Dashboard() {
        super();
        cookieCrud = new CookieCrud();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = cookieCrud.getSessionUser(request);
		request.setAttribute("user", user);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
