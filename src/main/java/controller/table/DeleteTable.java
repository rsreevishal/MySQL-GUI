package controller.table;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.TableCrud;

public class DeleteTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableCrud tableCrud;
    public DeleteTable() {
        super();
        tableCrud = new TableCrud();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tablename = request.getParameter("tablename");
		int tableid = Integer.parseInt(request.getParameter("tableid"));
		tableCrud.delete(tablename, tableid);
		RequestDispatcher rd = request.getRequestDispatcher("/Dashboard");
		rd.forward(request, response);
	}

}
