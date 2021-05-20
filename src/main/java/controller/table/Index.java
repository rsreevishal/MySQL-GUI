package controller.table;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.TableCrud;
import model.Field;
import model.FieldConstraint;
import model.FieldType;
import model.Table;

public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableCrud tableCrud;
    public Index() {
        super();
        tableCrud = new TableCrud();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("create_table.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] fieldNames = request.getParameterValues("field");
		String[] fieldTypes = request.getParameterValues("type");
		String[] fieldConstraints = request.getParameterValues("constraint");
		Table table = new Table();
		table.setName(request.getParameter("tablename"));
		ArrayList<Field> fields = new ArrayList<Field>();
		for(int i=0; i<fieldNames.length; i++) {
			Field field = new Field();
			field.setName(fieldNames[i]);
			field.setFieldType(FieldType.valueOf(fieldTypes[i]));
			ArrayList<FieldConstraint> constraint = new ArrayList<FieldConstraint>();
			for(String con: fieldConstraints) {
				if(Integer.parseInt(con.substring(0, 1)) == i) {
					constraint.add(FieldConstraint.valueOf(con.substring(1)));
				}
			}
			field.setFieldConstraint(constraint);
			fields.add(field);
		}
		table.setFields(fields);
		table = tableCrud.create(table);
		request.setAttribute("table", table);
		request.setAttribute("tableid", table.getId());
		RequestDispatcher rd = request.getRequestDispatcher("/TableDetails");
		rd.forward(request, response);
	}

}
