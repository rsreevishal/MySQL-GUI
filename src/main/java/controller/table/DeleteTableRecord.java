package controller.table;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.TableRecordCrud;
import model.PrimaryKey;

public class DeleteTableRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableRecordCrud tableRecordCrud;

	public DeleteTableRecord() {
		super();
		tableRecordCrud = new TableRecordCrud();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tablename = request.getParameter("tablename");
		tableRecordCrud.delete(tablename,
				new PrimaryKey(request.getParameter("primarykey"), request.getParameter("primaryid")));
		request.setAttribute("tableid", Integer.parseInt(request.getParameter("tableid")));
		request.getRequestDispatcher("/ViewAllTableRecord").forward(request, response);
	}

}
