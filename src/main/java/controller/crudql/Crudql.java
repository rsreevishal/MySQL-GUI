package controller.crudql;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import antlr.CrudqlProcessor;
public class Crudql extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Crudql() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("crudql.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		System.out.println("Query: " + query);
		String queryResult = CrudqlProcessor.process(query);
		request.setAttribute("queryResult", queryResult);
		doGet(request, response);
	}

}
