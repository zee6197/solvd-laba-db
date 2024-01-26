package com.solvd.laba.parsers.dom;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class MainDom {

    public static void main(String[] args) {

        File xmlFile = new File("src/main/resources/parsers/xml/buildingCompany.xml");
        File xsdFile = new File("src/main/resources/parsers/xml/buildingCompany.xsd");

        validateXML(xmlFile, xsdFile);
    }

    private static void validateXML(File xmlFile, File xsdFile) {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(doc));
            System.out.println("XML is valid against the provided XSD schema.");
        } catch (SAXException e) {
            System.err.println("Validation error: " + e.getMessage());
        } catch (ParserConfigurationException e) {
            System.err.println("Parser configuration error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
    }
}
