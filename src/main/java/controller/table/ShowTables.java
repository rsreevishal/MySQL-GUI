package controller.table;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.TableCrud;
import model.Table;

public class ShowTables extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableCrud tableCrud;
    public ShowTables() {
        super();
        tableCrud = new TableCrud();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Table> tables = tableCrud.getAll();
		request.setAttribute("tables", tables);
		request.getRequestDispatcher("show_tables.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
