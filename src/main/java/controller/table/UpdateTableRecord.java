package controller.table;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.TableRecordCrud;
import model.FieldType;
import model.PrimaryKey;
import model.TableRecord;
import model.TableRecordField;

public class UpdateTableRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableRecordCrud tableRecordCrud;
	
    public UpdateTableRecord() {
        super();
        tableRecordCrud = new TableRecordCrud();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName = request.getParameter("tablename");
		String[] fieldNames = request.getParameterValues("fieldName");
		String[] fieldTypes = request.getParameterValues("fieldType");
		String[] fieldValue = request.getParameterValues("fieldValue");
		ArrayList<TableRecordField> records = new ArrayList<TableRecordField>();
		for(int i=0; i<fieldNames.length; i++) {
			TableRecordField record = new TableRecordField();
			record.setFieldName(fieldNames[i]);
			record.setFieldType(FieldType.valueOf(fieldTypes[i]));
			record.setFieldValue(fieldValue[i]);
			records.add(record);
		}
		TableRecord tableRecord = new TableRecord();
		tableRecord.setTablename(tableName);
		tableRecord.setFields(records);
		String tablename = request.getParameter("tablename");
		tableRecordCrud.update(tableRecord,
				new PrimaryKey(request.getParameter("primarykey"), request.getParameter("primaryid")));
		request.setAttribute("tableid", Integer.parseInt(request.getParameter("tableid")));
		request.getRequestDispatcher("/ViewAllTableRecord").forward(request, response);
	}

}
