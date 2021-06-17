package crud;

import model.ExportModel;
import model.User;

public class ExportCrud {
	private FormCrud formCrud;
	private FormReportCrud formReportCrud;
	
	public ExportCrud() {
		formCrud = new FormCrud();
		formReportCrud = new FormReportCrud();
	}
	
	public ExportModel exportApp(User user) {
		ExportModel model = new ExportModel();
		model.setForms(formCrud.getAll(user));
		model.setReports(formReportCrud.getAll(user));
		return model;
	}
}
