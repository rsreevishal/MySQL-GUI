package expression;

import model.TableRecord;
import model.TableRecordField;

public class ViewExpr extends Expression {
	public IdToken idToken;
	public UidToken uidToken;
	private TableRecord record;
	
	public ViewExpr(IdToken idToken, UidToken uidToken) {
		this.idToken = idToken;
		this.uidToken = uidToken;
	}

	@Override
	public String toHTML() {
		String tableStr = String.format("<p>No such %s in %s</p>", uidToken.toHTML(), idToken.toHTML());
		String heading = "", values = "";
		if(record != null) {
			for (TableRecordField trf : record.getFields()) {
				heading += String.format("<th scope='cols'>%s</th>", trf.getFieldName());
			}
			String row = "";
			for (TableRecordField trf : record.getFields()) {
				row += String.format("<td>%s</td>", trf.getFieldValue());
			}
			values += String.format("<tr>%s</tr>", row);

			tableStr = String.format("<table class='table'><tr>%s</tr>%s</table>", heading, values);
		}
		return tableStr;
	}

	@Override
	public String toFTL() {
		return "";

	}

	public TableRecord getRecord() {
		return record;
	}

	public void setRecord(TableRecord record) {
		this.record = record;
	}
}
