package controller.table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
		ArrayList<String> fieldNames = new ArrayList<String>(Arrays.asList(request.getParameterValues("fieldName")));
		ArrayList<String> fieldTypes = new ArrayList<String>(Arrays.asList(request.getParameterValues("fieldType")));
		ArrayList<String> fieldValue = new ArrayList<String>(Arrays.asList(request.getParameterValues("fieldValue")));
		ArrayList<String> filteredFieldValue = new ArrayList<String>();
		if(fieldNames.size() != fieldValue.size()) {
			int rCount = 0;
			while(rCount < fieldNames.size()) {
				if(request.getParameter("radio"+rCount) != null) {
					fieldValue.add(rCount, request.getParameter("radio"+rCount));
				}
				rCount++;
			}
		}
		int cbCount = 0, i = 0;
		while(i<fieldValue.size()) {
			String value = "";
			int j = i;
			while(j < fieldValue.size() && fieldValue.get(j).startsWith("cb"+cbCount)) {
				value += "," + fieldValue.get(j).replaceFirst(String.format("^%s_", "cb"+cbCount), "");
				j++;
			}
			if(value.length() > 0) {
			    filteredFieldValue.add(value.substring(1));
			    cbCount++;
			    i=j;
			} else {
			    filteredFieldValue.add(fieldValue.get(i));
			    i++;
			}
		}
		ArrayList<TableRecordField> records = new ArrayList<TableRecordField>();
		for(int j=0; j<fieldNames.size(); j++) {
			TableRecordField record = new TableRecordField();
			record.setFieldName(fieldNames.get(j));
			record.setFieldType(FieldType.valueOf(fieldTypes.get(j)));
			record.setFieldValue(filteredFieldValue.get(j));
			records.add(record);
		}
		TableRecord tableRecord = new TableRecord();
		tableRecord.setTablename(tableName);
		tableRecord.setFields(records);
		tableRecordCrud.create(tableRecord);
		request.getRequestDispatcher("/Dashboard").forward(request, response);
	}

}
