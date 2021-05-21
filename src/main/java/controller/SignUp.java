package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.UserCrud;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserCrud userCrud;
    public SignUp() {
        super();
        userCrud = new UserCrud();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("sign_up.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		userCrud.create(username, password);
		request.getRequestDispatcher("/DashBoard").forward(request, response);
	}

}
