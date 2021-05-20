package controller.table;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.TableCrud;
import crud.TableRecordCrud;
import model.FieldType;
import model.Table;
import model.TableRecord;
import model.TableRecordField;

public class InsertTableRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableCrud tableCrud;
    private TableRecordCrud tableRecordCrud;
    public InsertTableRecord() {
        super();
        tableCrud = new TableCrud();
        tableRecordCrud = new TableRecordCrud();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Table> tables = tableCrud.getAll();
		request.setAttribute("tables", tables);
		request.getRequestDispatcher("insert_table_record.jsp").forward(request, response);
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
		tableRecordCrud.create(tableRecord);
		request.getRequestDispatcher("/Dashboard").forward(request, response);
	}

}
