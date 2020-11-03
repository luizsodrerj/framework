package framework.xml;

import org.xml.sax.Attributes;

/**
 * Bean que encapsula dados de elemento xml
 * @author  Luiz Alberto
 * @date    01/10/2004
 */
public class XMLElement  {
    private String      namespace;
    private String      localName;
    private String      qualifiedName;
    private String      value = null;
    private Attributes  attributes;

    /**
     * Incializa a classe com os dados de um elemento xml
     * @param namespace     namespace do elemento xml
     * @param localName     nome local do elemento xml
     * @param qualifiedName nome qualificado do elemento xml
     * @param attributes    atributos do elemento xml
     */
    public XMLElement(  String      namespace,
                        String      localName,
                        String      qualifiedName,
                        Attributes  attributes)  {
        this.namespace      = namespace;
        this.localName      = localName;
        this.qualifiedName  = qualifiedName;
        this.attributes     = attributes;
    }

    /**
     * Retorna o namespace do elemento xml
     * @return o namespace do elemento xml
     */
    public String getNamespace()  {
        return namespace;
    }

    /**
     * Retorna o nome local do elemento xml
     * @return o nome local do elemento xml
     */
    public String getLocalName()  {
        return localName;
    }

    /**
     * Retorna o nome qualificado do elemento xml
     * @return o nome qualificado do elemento xml
     */
    public String getQualifiedName()  {
        return qualifiedName;
    }
    
    /**
     * Retorna os atributos do elemento xml
     * @return os atributos do elemento xml
     */
    public Attributes getAttributes() {
        return attributes;
    }
    
    /**
     * Retorna o valor do elemento xml
     * @return o valor do elemento xml
     */
    public String getValue() {
        return value;
    }

    /**
     * Define o valor do elemento xml
     * @param value valor do elemento xml
     */
    public void setValue(String value) {
        this.value = value;
    }
    
}


