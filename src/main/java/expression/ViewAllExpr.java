package expression;

import java.util.ArrayList;

import model.TableRecord;
import model.TableRecordField;

public class ViewAllExpr extends Expression {
	public IdToken idToken;
	private ArrayList<TableRecord> records;
	
	public ArrayList<TableRecord> getRecords() {
		return records;
	}

	public void setRecords(ArrayList<TableRecord> records) {
		this.records = records;
	}

	public ViewAllExpr(IdToken idToken) {
		this.idToken = idToken;
	}

	@Override
	public String toHTML() {
		if (records.size() > 0) {
			String heading = "", values = "";
			for (TableRecordField trf : records.get(0).getFields()) {
				heading += String.format("<th scope='cols'>%s</th>", trf.getFieldName());
			}
			for (TableRecord record : records) {
				String row = "";
				for (TableRecordField trf : record.getFields()) {
					row += String.format("<td>%s</td>", trf.getFieldValue());
				}
				values += String.format("<tr>%s</tr>", row);
			}
			String tableStr = String.format("<table class='table'><tr>%s</tr>%s</table>", heading, values);
			return tableStr;
		}
		return String.format("<p style='color: red;'>No records in %s<p>", idToken.toHTML());
	}

	@Override
	public String toFTL() {
		return "";
	}
}
