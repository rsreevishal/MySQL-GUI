package model;

import java.util.ArrayList;

public class Report {
	private String name, table;
	private ArrayList<FormInput> inputs;
	public Report() {
		inputs = new ArrayList<FormInput>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public ArrayList<FormInput> getInputs() {
		return inputs;
	}
	public void setInputs(ArrayList<FormInput> inputs) {
		this.inputs = inputs;
	}
	public void addInput(FormInput input) {
		this.inputs.add(input);
	}
	
}
