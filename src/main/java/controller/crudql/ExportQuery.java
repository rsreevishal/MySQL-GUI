package controller.crudql;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.CookieCrud;


public class ExportQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CookieCrud cookieCrud;
	public ExportQuery() {
        super();
        cookieCrud = new CookieCrud();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(cookieCrud.getSessionUser(request) == null) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		request.getRequestDispatcher("crudql.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");  
		PrintWriter out = response.getWriter();  
		String filename = "export.txt";
		response.setContentType("APPLICATION/OCTET-STREAM");   
		response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
		String queryOutput = request.getParameter("export_query");
		if(queryOutput != null) {
			out.write(queryOutput);
		}
		out.close();
	}

}
