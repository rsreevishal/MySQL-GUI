package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.CookieCrud;

public class FormBuilder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CookieCrud cookieCrud;
    public FormBuilder() {
        super();
        cookieCrud = new CookieCrud();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(cookieCrud.getSessionUser(request) == null) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		request.getRequestDispatcher("form_builder.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
