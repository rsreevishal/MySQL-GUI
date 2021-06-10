package model;

import java.util.ArrayList;

public class ExportModel {
	private ArrayList<Form> forms;
	private ArrayList<Report> reports;
	public ArrayList<Form> getForms() {
		return forms;
	}
	public void setForms(ArrayList<Form> forms) {
		this.forms = forms;
	}
	public ArrayList<Report> getReports() {
		return reports;
	}
	public void setReports(ArrayList<Report> reports) {
		this.reports = reports;
	}	
}
