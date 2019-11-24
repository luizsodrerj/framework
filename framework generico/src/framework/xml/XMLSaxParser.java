package framework.xml;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.xerces.parsers.SAXParser;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Classe utilitária para análise de xml
 * @author  Luiz Alberto
 * @date    01/10/2004
 */
public class XMLSaxParser extends DefaultHandler {
    private Collection  	collection  = new ArrayList();
    private StringBuffer    elementText = null;
    private XMLElement  	xmlElement  = null;

    /**
     * Faz a análise de um documento xml e 
     * retorna uma coleção de seus elementos
     * @param   fileName path do documento xml
     * @return  uma coleção de elementos xml
     * @throws  org.xml.sax.SAXException em caso de erro de SAX
     * @throws  java.io.IOException      em caso de erro de io
     */
    public Collection parse(String fileName) throws SAXException, IOException {
        XMLReader   xmlReader  = new SAXParser();
        FileReader  fileReader = new FileReader(fileName);
        
        xmlReader.setContentHandler(this); 
        xmlReader.parse(new InputSource(fileReader));
        
        return collection;
    }
    
    /**
     * Faz a análise de um documento xml e 
     * retorna uma coleção de seus elementos
     * @param   xmlInput stream de entrada do documento xml
     * @return  uma coleção de elementos xml 
     * @throws  org.xml.sax.SAXException em caso de erro de SAX
     * @throws  java.io.IOException      em caso de erro de io
     */
    public Collection parse(InputStream xmlInput) throws SAXException, IOException {
        XMLReader   xmlReader  = new SAXParser();
         
        xmlReader.setContentHandler(this);
        xmlReader.parse(new InputSource(xmlInput));
        
        return collection;
    }
    
    /**
     * Analisa o inicio do elemento xml
     * @param uri        namespace do elemento xml
     * @param localName  nome local do elemento xml
     * @param qName      nome qualificado do elemento xml
     * @param attributes atributos do elemento xml
     */
    public void startElement(String     uri,
                             String     localName,
                             String     qName,
                             Attributes attributes) {
        xmlElement = new XMLElement(uri, localName, qName, attributes);
        elementText = new StringBuffer("");
        collection.add(xmlElement);
    }
    
    /**
     * Trata o texto do elemento xml 
     * @param chars  caracteres do elemento xml 
     * @param start  offset
     * @param length contagem dos caracteres
     */
    public void characters(char[] chars, int start, int length) {
        if (xmlElement != null && elementText != null) {
            String value = new String(chars, start, length);
            elementText.append(value);
        }
    }
    
    /**
     * Analisa o final do elemento xml
     * @param uri        namespace do elemento xml
     * @param localName  nome local do elemento xml
     * @param qName      nome qualificado do elemento xml
     */
    public void endElement(String uri, String localName, String qName) {
        if (xmlElement != null && elementText != null) {
            xmlElement.setValue(elementText.toString().trim());
        }
        xmlElement = null;
    }
}











