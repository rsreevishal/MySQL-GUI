package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.StartUp;
import crud.CookieCrud;
import crud.UserCrud;
import model.User;

public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserCrud userCrud;
	private CookieCrud cookieCrud;
    public Auth() {
        super();
        userCrud = new UserCrud();
        cookieCrud = new CookieCrud();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StartUp.call();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userCrud.authenticateUser(username, password);
		if(user != null) {
			cookieCrud.setSessionUser(user, response);
			request.getRequestDispatcher("/Dashboard").forward(request, response);
		} else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
