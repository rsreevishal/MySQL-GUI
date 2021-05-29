package controller.crudql;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import antlr.CrudqlProcessor;
import crud.TableCrud;
public class Crudql extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableCrud tableCrud;
    public Crudql() {
        super();
    	tableCrud = new TableCrud();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String form = tableCrud.getLastForm();
		request.setAttribute("form", form);
		request.getRequestDispatcher("crudql.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		String tab = request.getParameter("tab");
		request.setAttribute("tab1", tab.equals("tab1") ? "active" : "");
		request.setAttribute("tab2", tab.equals("tab2") ? "active" : "");
		request.setAttribute("tab3", tab.equals("tab3") ? "active" : "");
		System.out.println("Query: " + query);
		String queryResult = CrudqlProcessor.process(query);
		request.setAttribute("queryResult", queryResult);
		doGet(request, response);
	}

}
