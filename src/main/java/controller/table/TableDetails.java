package controller.table;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.TableCrud;
import model.Table;


public class TableDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableCrud tableCrud;
    public TableDetails() {
        super();
        tableCrud = new TableCrud();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("table_details.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("tableid"));
		Table table = tableCrud.get(id);
		request.setAttribute("table", table);
		doGet(request, response);
	}

}
