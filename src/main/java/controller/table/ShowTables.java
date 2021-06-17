package controller.table;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.CookieCrud;
import crud.TableCrud;
import model.Table;
import model.User;

public class ShowTables extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableCrud tableCrud;
	private CookieCrud cookieCrud;
    public ShowTables() {
        super();
        tableCrud = new TableCrud();
        cookieCrud = new CookieCrud();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = cookieCrud.getSessionUser(request);
		if(user == null) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		ArrayList<Table> tables = tableCrud.getAll(user);
		request.setAttribute("tables", tables);
		request.getRequestDispatcher("show_tables.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
