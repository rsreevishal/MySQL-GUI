package controller.table;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.CookieCrud;
import crud.TableCrud;
import model.Table;


public class TableDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableCrud tableCrud;
	private CookieCrud cookieCrud;
    public TableDetails() {
        super();
        tableCrud = new TableCrud();
        cookieCrud = new CookieCrud();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(cookieCrud.getSessionUser(request) == null) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		request.getRequestDispatcher("table_details.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = 0;
		if(request.getParameter("tableid") != null) {
			id = Integer.parseInt(request.getParameter("tableid"));
		} else if(request.getAttribute("tableid") != null) {
			id = (Integer)request.getAttribute("tableid");
		}
		Table table = tableCrud.get(id);
		request.setAttribute("table", table);
		doGet(request, response);
	}

}
