package com.craneos.sgv.integration.parser.builders;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class BaseIntegrationBuilder {

    protected static final String XMLNS_KEY = "xmlns";
    protected static final String SCHEMA_URL = "http://www.w3.org/2001/XMLSchema-instance";
    protected static final String SCHEMA_URL_INTEGRATION = "http://www.springframework.org/schema/integration";
    protected static final String SCHEMA_URL_BEANS = "http://www.springframework.org/schema/beans";
    protected static final String SCHEMA_URL_P = "http://www.springframework.org/schema/p";
    protected static final String SCHEMA_URL_UTIL = "http://www.springframework.org/schema/util";
    //
    protected static final String AOP_CONFIG = "aop:config";
    protected static final String AOP_ASPECTJ_AUTOPROXY = "aop:aspectj-autoproxy";
    protected static final String CONTEXT_MBEAN = "context:mbean-export";
    protected static final String CONTEXT_COMPONENT_SCAN = "context:component-scan";
    protected static final String CONTEXT_ANNOTATION_CONFIG = "context:annotation-config";
    protected static final String CONTEXT_PROPERTY_PLACEHOLDER = "context:property-placeholder";
    protected static final String RABBIT_CONNECTION_FACTORY = "rabbit:connection-factory";
    // ATTRIBUTES
    protected static final String ATT_ID = "id";
    protected static final String ATT_PARENT_ID = "parent";
    protected static final String ATT_CLASS = "class";
    protected static final String ATT_PROFILE = "profile";
    protected static final String ATT_RESOURCE = "resource";
    protected static final String ATT_INPUT_CHANNEL = "input-channel";
    protected static final String ATT_OUTPUT_CHANNEL = "output-channel";
    protected static final String ATT_DISCARD_CHANNEL = "discard-channel";
    protected static final String ATT_MAPPING = "int:mapping";
    protected static final String ATT_CHANNEL = "channel";
    //
    protected String PREFIX_DEFAULT = "";
    protected String prefixIntegration = PREFIX_DEFAULT;
    protected String prefixUtil = PREFIX_DEFAULT;


    private BiPredicate<String, String> IS_DEFAULT_PREFIX = (prefix, defaultPrefix) -> prefix.equals(defaultPrefix);

    private BiFunction<String, String, String> COMPOUND_PREFIX = (prefix, tag) -> (prefix.equals(PREFIX_DEFAULT)?prefix:prefix + ":")+tag;;
    /**
     String compoundValue = (prefixIntegration.equals(PREFIX_DEFAULT)?prefixIntegration:prefixIntegration + ":")+value;
     */

    public BaseIntegrationBuilder(){
    }

    protected boolean nodeContains(Node node, String tag){
        //IS_DEFAULT_PREFIX.test(prefixIntegration, PREFIX_DEFAULT)?
        String compoundValueTest = COMPOUND_PREFIX.apply(prefixIntegration, PREFIX_DEFAULT);
        String compoundValue = (prefixIntegration.equals(PREFIX_DEFAULT)?prefixIntegration:prefixIntegration + ":")+tag;
        if (node.getNodeName().contains(compoundValue)){
            return true;
        }
        return false;
    }

    public void init(Document document){
        prefixIntegration = "";
        prefixUtil = "";
        //
        Element mainElement = document.getDocumentElement();
        NamedNodeMap namedNodeMap = mainElement.getAttributes();
        for(int i=0; i<namedNodeMap.getLength();i++){
            Node node = namedNodeMap.item(i);
            String[] names = node.getNodeName().split(":");
            if (names.length==2){
                if (node.getNodeValue().equals(SCHEMA_URL_INTEGRATION)){
                    prefixIntegration = names[1];
                    //System.out.println("-----> prefix for integration: "+prefixIntegration);
                } else if (node.getNodeValue().equals(SCHEMA_URL_UTIL)){
                    prefixUtil = names[1];
                    //System.out.println("-----> prefix for util: "+prefixUtil);
                }
            }
        }
    }

    protected boolean ignore(Node node){
        boolean ignore = false;
        if (node.getNodeName().contains("#comment")){
            ignore = true;
        } else if (node.getNodeName().contains("#text")) {
            ignore = true;
        }
        return ignore;
    }

}
