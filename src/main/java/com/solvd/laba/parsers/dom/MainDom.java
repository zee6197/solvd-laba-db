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
            // Create a DocumentBuilderFactory
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);

            // Create a DocumentBuilder
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parse the XML file and get the Document
            Document doc = dBuilder.parse(xmlFile);

            // Create a SchemaFactory
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Compile the schema
            Schema schema = factory.newSchema(xsdFile);

            // Create a Validator instance
            Validator validator = schema.newValidator();

            // Validate the XML document
            validator.validate(new DOMSource(doc));
            System.out.println("XML is valid against the provided XSD schema.");
        } catch (SAXException e) {
            // Handle the situation when the XML is not valid against the XSD
            System.err.println("Validation error: " + e.getMessage());
        } catch (ParserConfigurationException e) {
            // Handle errors related to the parser configuration
            System.err.println("Parser configuration error: " + e.getMessage());
        } catch (IOException e) {
            // Handle IO errors
            System.err.println("IO error: " + e.getMessage());
        }
    }
}

