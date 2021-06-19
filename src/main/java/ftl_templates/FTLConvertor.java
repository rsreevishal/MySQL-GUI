package ftl_templates;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import core.Config;
import expression.Expression;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

public class FTLConvertor {
	public static String convert(Expression expression, HashMap<String, Object> data) {
		try {
			Configuration cfg = new Configuration(new Version("2.3.31"));
			cfg.setClassForTemplateLoading(FTLConvertor.class, "/");
			cfg.setDefaultEncoding("UTF-8");
			String className = expression.getClass().getSimpleName();
			Document doc = getDocument("expression_template.xsd", "expression_template.xml");
			if(doc != null) {
				String fileName = doc.getElementById(className).getTextContent();
				Template template = cfg.getTemplate("ftl_templates/" + fileName);
				StringWriter out = new StringWriter();
				template.process(data, out);
				String queries = out.getBuffer().toString();
				if(queries.length() > 0) {
					return queries;
				}
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private static Document getDocument(String schema, String file) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		docFactory.setNamespaceAware(true);
		File schemaFile = new File(Config.BASE_URL + "ftl_templates/" + schema);
		try {
			docFactory.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(schemaFile));
			DocumentBuilder builder = docFactory.newDocumentBuilder();
			Document doc = builder.parse(Config.BASE_URL + "ftl_templates/" + file);
			return doc;
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
