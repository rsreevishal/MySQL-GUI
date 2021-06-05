package controller.crudql;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import antlr.CrudqlProcessor;
import crud.TableCrud;
import model.TableQuery;
import model.TableQueryType;
public class Crudql extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableCrud tableCrud;
    public Crudql() {
        super();
    	tableCrud = new TableCrud();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<TableQuery> formQueries = tableCrud.getAllFormQuery();
		String form = tableCrud.getLastQueries(2, TableQueryType.FORM);
		String report = tableCrud.getLastQueries(1, TableQueryType.VIEW);
		System.out.println("Form query: " + form);
		System.out.println("Report query: " + report);
		String queryOutput = "";
		if(form.length() > 0 && report.length() > 0) {
			queryOutput = CrudqlProcessor.process(form + "\n" + report, false);	
		}
		if(form.length() == 0) {
			queryOutput += ("\n" + "<p style='color:red;'>No form found create one to view here..</p>");
		}
		if(report.length() == 0) {
			queryOutput += ("\n" + "<p style='color:red;'>No report found create one to view here..</p>");
		}
		request.setAttribute("queryOutput", queryOutput);
		request.setAttribute("formQueries", formQueries);
		request.getRequestDispatcher("crudql.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		if(query == null) {
			query = (String) request.getAttribute("query");
		}
		String tab = request.getParameter("tab");
		request.setAttribute("tab1", tab.equals("tab1") ? "active" : "");
		request.setAttribute("tab2", tab.equals("tab2") ? "active" : "");
		request.setAttribute("tab3", tab.equals("tab3") ? "active" : "");
		System.out.println("Query: " + query);
		String queryResult = CrudqlProcessor.process(query, true);
		request.setAttribute("queryResult", queryResult);
		doGet(request, response);
	}

}
