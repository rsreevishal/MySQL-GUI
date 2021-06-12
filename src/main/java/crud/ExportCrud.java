package crud;

import model.ExportModel;

public class ExportCrud {
	private FormCrud formCrud;
	private FormReportCrud formReportCrud;
	
	public ExportCrud() {
		formCrud = new FormCrud();
		formReportCrud = new FormReportCrud();
	}
	
	public ExportModel exportApp() {
		ExportModel model = new ExportModel();
		model.setForms(formCrud.getAll());
		model.setReports(formReportCrud.getAll());
		return model;
	}
}
