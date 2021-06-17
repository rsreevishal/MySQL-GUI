package controller.crudql;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import antlr.CrudqlProcessor;
import crud.CookieCrud;
import crud.ExportCrud;
import expression.FormExpr;
import expression.FormReportExpr;
import model.ExportModel;
import model.User;

public class Crudql extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExportCrud exportCrud;
	private CookieCrud cookieCrud;
    public Crudql() {
        super();
    	exportCrud = new ExportCrud();
    	cookieCrud = new CookieCrud();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = cookieCrud.getSessionUser(request);
		if(user == null) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		String queryOutput = "";
		ExportModel model = exportCrud.exportApp(user);
		if(model.getForms().size() > 0 || model.getReports().size() > 0) {
			String queries = "";
			for(FormExpr form: model.getForms()) {
				queries += (form.toFTL() + "\n");
			}
			for(FormReportExpr report: model.getReports()) {
				queries += (report.toFTL() + "\n");
			}
			if(queries.length() > 0) {
				queryOutput = CrudqlProcessor.process(queries, user, false);
			}
			request.setAttribute("formQueries", queries);
		}
		if(model.getForms().size() == 0) {
			queryOutput += ("\n" + "<p style='color:red;'>No form found create one to view here..</p>");
		}
		if(model.getReports().size() == 0) {
			queryOutput += ("\n" + "<p style='color:red;'>No report found create one to view here..</p>");
		}
		request.setAttribute("queryOutput", queryOutput);
		request.getRequestDispatcher("crudql.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = cookieCrud.getSessionUser(request);
		if(user == null) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		String query = request.getParameter("query");
		if(query == null) {
			query = (String) request.getAttribute("query");
		}
		String tab = request.getParameter("tab");
		request.setAttribute("tab1", tab.equals("tab1") ? "active" : "");
		request.setAttribute("tab2", tab.equals("tab2") ? "active" : "");
		request.setAttribute("tab3", tab.equals("tab3") ? "active" : "");
		System.out.println("Query: " + query);
		String queryResult = CrudqlProcessor.process(query, user, true);
		request.setAttribute("queryResult", queryResult);
		doGet(request, response);
	}

}
