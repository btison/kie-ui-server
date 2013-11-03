package org.jboss.btison.kie.services.client.serialization.jaxb;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbCommentListResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbNodeInstanceDescListResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbNodeInstanceDescResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbOrganizationalEntityMapResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbProcessInstanceDescListResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbProcessInstanceDescResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbVariableStateDescListResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbVariableStateDescResponse;

public class JaxbSerializationProvider {

    private static Class<?> [] jaxbClasses = { 
        JaxbCommandsRequest.class, 
        JaxbCommandsResponse.class,
        JaxbCommentListResponse.class,
        JaxbOrganizationalEntityMapResponse.class,
        JaxbProcessInstanceDescResponse.class,
        JaxbProcessInstanceDescListResponse.class,
        JaxbNodeInstanceDescListResponse.class,
        JaxbNodeInstanceDescResponse.class,
        JaxbVariableStateDescResponse.class,
        JaxbVariableStateDescListResponse.class
    };
    
    public static String convertJaxbObjectToString(Object object) throws JAXBException {
        Marshaller marshaller = JAXBContext.newInstance(jaxbClasses).createMarshaller();
        StringWriter stringWriter = new StringWriter();
        
        marshaller.marshal(object, stringWriter);
        String output = stringWriter.toString();
        
        return output;
    }
    
    public static Object convertStringToJaxbObject(String xmlStr) throws JAXBException {
        Unmarshaller unmarshaller = JAXBContext.newInstance(jaxbClasses).createUnmarshaller();
        ByteArrayInputStream xmlStrInputStream = new ByteArrayInputStream(xmlStr.getBytes());
        
        Object jaxbObj = unmarshaller.unmarshal(xmlStrInputStream);
        
        return jaxbObj;
    }

}
