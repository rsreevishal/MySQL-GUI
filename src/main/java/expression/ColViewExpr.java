package expression;

import model.TableRecord;
import model.TableRecordField;

public class ColViewExpr extends Expression {
	public IdToken idToken;
	public IdToken colToken;
	public UidToken uidToken;
	private TableRecord record;
	
	public ColViewExpr(IdToken idToken, IdToken colToken, UidToken uidToken) {
		this.idToken = idToken;
		this.colToken = colToken;
		this.uidToken = uidToken;
	}
	@Override
	public String toHTML() {
		if(record != null) {
			String heading = "", values = "";
			for (TableRecordField trf : record.getFields()) {
				heading += String.format("<th scope='cols'>%s</th>", trf.getFieldName());
			}
			String row = "";
			for (TableRecordField trf : record.getFields()) {
				row += String.format("<td>%s</td>", trf.getFieldValue());
			}
			values += String.format("<tr>%s</tr>", row);

			String tableStr = String.format("<table class='table'><tr>%s</tr>%s</table>", heading, values);
			return tableStr;
		}
		return String.format("No %s in table %s", uidToken.toHTML(), idToken.toHTML());
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
