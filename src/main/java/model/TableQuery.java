package model;

public class TableQuery {
	private int id, table_id;
	private String name, query;
	private TableQueryType type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public TableQueryType getType() {
		return type;
	}
	public void setType(TableQueryType type) {
		this.type = type;
	}
}
