package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XSDValidator {
    public static void main(String[] args) {
        // Base path
        String basePath = "/home/merimba/IdeaProjects/XML-Validation-XSD/src/main/java/org/example/";

        // File names
        String xsdFile = "config.xsd";
        String xmlFile = "config.xml";

        // Full file paths
        String xsdPath = Paths.get(basePath, xsdFile).toString();
        String xmlPath = Paths.get(basePath, xmlFile).toString();

        boolean isValid = validateXMLSchema(xsdPath, xmlPath);

        if (isValid) {
            System.out.println(xmlFile + " is valid against " + xsdFile);
        } else {
            System.out.println(xmlFile + " is not valid against " + xsdFile);
        }
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        } catch (SAXException e1) {
            System.out.println("SAX Exception: " + e1.getMessage());
            return false;
        }

        return true;
    }
}