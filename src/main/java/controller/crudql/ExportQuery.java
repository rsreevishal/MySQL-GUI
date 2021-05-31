package controller.crudql;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ExportQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ExportQuery() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("crudql.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");  
		PrintWriter out = response.getWriter();  
		String filename = "export.txt";
		response.setContentType("APPLICATION/OCTET-STREAM");   
		response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
		String[] formQueries = request.getParameterValues("formQueries");
		String[] reportQueries = request.getParameterValues("reportQueries");
		if(formQueries != null) {
			for(String query: formQueries) {
				out.write(query);
				out.write('\n');
			}
		}
		if(reportQueries != null) {
			for(String query: reportQueries) {
				out.write(query);
				out.write('\n');
			}
		}
		out.close();
	}

}
