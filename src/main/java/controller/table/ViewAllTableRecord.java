package controller.table;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.CookieCrud;
import crud.TableCrud;
import crud.TableRecordCrud;
import model.Table;
import model.TableRecord;
import model.User;

public class ViewAllTableRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableCrud tableCrud;
    private TableRecordCrud tableRecordCrud;
    private CookieCrud cookieCrud;
    public ViewAllTableRecord() {
        super();
        tableCrud = new TableCrud();
        tableRecordCrud = new TableRecordCrud();
        cookieCrud = new CookieCrud();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = cookieCrud.getSessionUser(request);
		if(user == null) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		ArrayList<Table> tables = tableCrud.getAll(user);
		request.setAttribute("tables", tables);
		request.getRequestDispatcher("view_table_record.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tableId = 0;
		if(request.getParameter("tableid") != null) {
			tableId = Integer.parseInt(request.getParameter("tableid"));
		} else if(request.getAttribute("tableid") != null) {
			tableId = (Integer)request.getAttribute("tableid");
		}
		Table table = tableCrud.get(tableId);
		ArrayList<TableRecord> tableRecords = tableRecordCrud.getAll(table);
		request.setAttribute("tableid", tableId);
		request.setAttribute("tableRecords", tableRecords);
		doGet(request, response);
	}

}
