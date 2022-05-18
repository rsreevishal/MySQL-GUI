package model;

import java.util.ArrayList;

public class Table {
	private int id;
	private User user;
	private String name;
	ArrayList<Field> fields;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Field> getFields() {
		return fields;
	}
	public Field getFieldByName(String name) {
		if(name != null) {
			for(Field f : fields) {
				if(f.getName().equals(name)) {
					return f;
				}
			}
		}
		return null;
	}
	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Pair<ArrayList<Field>, ArrayList<Field>> compareTableField(Table table2) {
		ArrayList<Field> has = new ArrayList<Field>();
		ArrayList<Field> hasNot = new ArrayList<Field>();
		for(Field f2 : table2.getFields()) {
			boolean flag = true;
			for(Field f1: fields) {
				if(f2.getName().equals(f1.getName())) {
					has.add(f2);
					flag = false;
					break;
				}
			}
			if(flag) {
				hasNot.add(f2);
			}
		}
		return new Pair<ArrayList<Field>, ArrayList<Field>>(has, hasNot);
	}
}
