package expression;

import java.util.ArrayList;
import java.util.HashMap;

import ftl_templates.FTLConvertor;
import model.TableRecord;
import model.TableRecordField;

public class FormReportExpr extends Expression {
	public IdToken name, table;
	public ArrayList<ConditionExpr> conditions;
	private ArrayList<TableRecord> records;
	public FormReportExpr() {}
	public FormReportExpr(IdToken name, IdToken table, ArrayList<ConditionExpr> conditions) {
		this.name = name;
		this.table = table;
		this.conditions = conditions;
	}
	public void addCondition(ConditionExpr condition) {
		conditions.add(condition);
	}

	@Override
	public String toHTML() {
		String result = String.format("<p style='color: red;'>No records in %s<p>", table.toHTML());
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
			result = tableStr;
		}
		return result;
	}

	@Override
	public String toFTL() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", name.toFTL());
		data.put("table", table.toFTL());
		ArrayList<String> conditionsStr = new ArrayList<String>();
		for(ConditionExpr expr: conditions) {
			conditionsStr.add(expr.toFTL());
		}
		data.put("conditions", conditionsStr);
		return FTLConvertor.convert(data, "CREATE VIEW ${name} FOR ${table} [ <#list conditions as condition>${condition} </#list> ]");
	}

	public ArrayList<TableRecord> getRecords() {
		return records;
	}

	public void setRecords(ArrayList<TableRecord> records) {
		this.records = records;
	}
}
