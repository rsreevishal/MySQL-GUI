package model;

public class FormInput {
	private String name, condition, args;
	public FormInput() {}
	public FormInput(String name, String condition, String args) {
		super();
		this.name = name;
		this.condition = condition;
		this.args = args;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}
	
}
