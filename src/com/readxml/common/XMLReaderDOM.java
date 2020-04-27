package com.readxml.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;


public class XMLReaderDOM {

    public static void main(String[] args) {
    	
    	try{
    		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
    		URL fileUrl =XMLReaderDOM.class.getClass().getResource("/template.xml");
            File file = new File(fileUrl.getFile());    
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String buffer;
            
            String TEMPLATEID_TAG="<TempalteId";
            String LAND_ID_TAG="<CMS_Lang_ID";
            String  SUBJECT_LINE_TAG="<SubjectLine >";
            
           LinkedHashMap<String,String> templateMap = new LinkedHashMap<String,String>();
            String templateIDValue = null;
            String templateLanguageCode = null;
			  while ((buffer = br.readLine()) != null) {
				  if(buffer!=null && buffer.contains(TEMPLATEID_TAG)){
					  String[] templateValues=buffer.split("\"");
					  if(templateValues!=null && templateValues.length>1){						  
						  if(templateValues[1]!=null && !templateValues[1].isEmpty()){
							  templateIDValue=templateValues[1].substring(0, templateValues[1].length());
						  }						  
					  }
					  System.out.println(templateIDValue);					  
				  }
				  else if(buffer!=null && buffer.contains(LAND_ID_TAG)){
					  String[] tagValues=buffer.split("\"");
					  if(tagValues!=null){
						  templateLanguageCode=tagValues[1].substring(0, tagValues[1].length());						  
					  }
				  }
				  
				  else if(buffer!=null && buffer.contains(SUBJECT_LINE_TAG)){
					  String[] tagValues=buffer.split(SUBJECT_LINE_TAG);
					  if(tagValues!=null){
						  templateMap.put(templateIDValue+"_"+templateLanguageCode, tagValues[1].substring(0, tagValues[1].length()-((SUBJECT_LINE_TAG.length())+1)));						  
					  }
				  }
			  }
			  
			  for(Map.Entry<String, String> value: templateMap.entrySet()) {
				  System.out.println(value.getKey() + "......................" + value.getValue());
			  }
			  
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }

    	
    	
    	
    	

/*
    private static Employee getEmployee(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        Employee emp = new Employee();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            emp.setName(getTagValue("name", element));
            emp.setAge(Integer.parseInt(getTagValue("age", element)));
            emp.setGender(getTagValue("gender", element));
            emp.setRole(getTagValue("role", element));
        }

        return emp;
    }


    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }*/

}
