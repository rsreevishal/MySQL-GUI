package ftl_templates;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class FTLConvertor {
	public static String convert(Map<String, Object> data, String template_path) {
		Configuration cfg = new Configuration(new Version("2.3.31"));
		cfg.setClassForTemplateLoading(FTLConvertor.class, "/");
		cfg.setDefaultEncoding("UTF-8");
		try {
			Template template = cfg.getTemplate(template_path);
			StringWriter out = new StringWriter();
			template.process(data, out);
			String queries = out.getBuffer().toString();
			System.out.println(queries);
			if(queries.length() > 0) {
				return queries;
			}
			out.flush();
		} catch(TemplateException e) {
			e.printStackTrace();
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
