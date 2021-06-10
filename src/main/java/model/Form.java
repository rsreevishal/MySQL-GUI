package model;

import java.util.ArrayList;

public class Form {
	private String name;
	private ArrayList<FormInput> inputs;
	public Form() {
		inputs = new ArrayList<FormInput>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
