package model;

import java.util.ArrayList;

import expression.FormExpr;
import expression.FormReportExpr;

public class ExportModel {
	private ArrayList<FormExpr> forms;
	private ArrayList<FormReportExpr> reports;
	public ArrayList<FormExpr> getForms() {
		return forms;
	}
	public void setForms(ArrayList<FormExpr> forms) {
		this.forms = forms;
	}
	public ArrayList<FormReportExpr> getReports() {
		return reports;
	}
	public void setReports(ArrayList<FormReportExpr> reports) {
		this.reports = reports;
	}	
}
