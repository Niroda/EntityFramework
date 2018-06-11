package se.codepool.ef.utility;

import static se.codepool.ef.enums.ConnectionProperties.*;

import java.io.File;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import se.codepool.ef.enums.ConnectionProperties;

public class ConnectionString {
	
	private String url;
	
	private String username;
	
	private String password;
	
	private String driver;
	
	public ConnectionString(Object config) {
		if(config instanceof Properties) {
			Properties prop = (Properties) config;
			this.prepareConnectionString(prop);
		} else if(config instanceof File) {
			File xml = (File) config;
			this.prepareConnectionString(xml);
		} else {
			throw new IllegalArgumentException("Only Properties and XML File are supported.");
		}	
	}

	private void prepareConnectionString(Properties prop) {
		this.driver = readProperty(prop, DRIVER);
		this.url = readProperty(prop, URL);
		this.username = readProperty(prop, USER);
		this.password = readProperty(prop, PASS);
		String db = readProperty(prop, DB);
		if(!this.url.endsWith("/"))
			this.url += "/";
		this.url += db + "?serverTimezone=UTC&createDatabaseIfNotExists=true";
	}
	
	
	private void prepareConnectionString(File xml) {
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the    
            // XML file
            dom = db.parse(xml);
            Element doc = dom.getDocumentElement();
            
            this.url = readProperty(doc, URL);
            this.password = readProperty(doc, PASS);
            this.driver = readProperty(doc, DRIVER);
            this.username = readProperty(doc, USER);
            String dbName = readProperty(doc, DB);
    		if(!this.url.endsWith("/"))
    			this.url += "/";
    		this.url += dbName + "?serverTimezone=UTC&createDatabaseIfNotExists=true";

        } catch (Exception ex) {
        	throw new RuntimeException(ex);
        }
    }
	
	
	private String readProperty(Element doc, ConnectionProperties tag) {
	    String value = null;
	    NodeList nl;
	    nl = doc.getElementsByTagName(tag.getPropertyKey());
	    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
	        value = nl.item(0).getFirstChild().getNodeValue();
	    }
	    if(value == null) {
			String message = tag.getPropertyValue() + " cann't be null. Make sure you use '"
					+ tag.getPropertyKey() + "' as a key.";
			throw new IllegalArgumentException(message);
		}
	    return value;
	}

	
	private String readProperty(Properties prop, ConnectionProperties key) {
		String value = prop.getProperty(key.getPropertyKey());
		if(value == null) {
			String message = key.getPropertyValue() + " cann't be null. Make sure you use '"
					+ key.getPropertyKey() + "' as a key.";
			throw new IllegalArgumentException(message);
		}
		return value;
	}
	
	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDriver() {
		return driver;
	}
	
}
