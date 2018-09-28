package com.phantom.spring.cloud.weather.util;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * @author Administrator
 *
 */
public class XmlBuilder {
	
	public static Object xmlStrToObject(Class<?> clazz, String xmlStr) throws Exception {
		Object xmlObject = null;
		Reader reader = null;
		JAXBContext context = JAXBContext.newInstance(clazz);//JAXBContext��ʹ��JAXB API�����
		
		//���л�(XML�ı�ת��ΪJAVA����)
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		reader = new StringReader(xmlStr);
		xmlObject = unmarshaller.unmarshal(reader);
		
		if(null != reader) {
			reader.close();
		}
		
		return xmlObject;
	}
	
}
