package controller.crudql;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class ImportQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ImportQuery() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Part filePart = request.getPart("queryFile");
	    InputStream fileContent = filePart.getInputStream();
	    String result = "";
	    int reader;
	    while((reader = fileContent.read()) != -1) {
	    	result += (char)reader;
	    }
	    System.out.println("Query: "+ result);
	    request.setAttribute("query", result);
	    request.getRequestDispatcher("/Crudql").forward(request, response);
	}

}
