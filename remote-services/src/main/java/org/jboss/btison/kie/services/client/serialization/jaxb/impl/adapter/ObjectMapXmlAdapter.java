package org.jboss.btison.kie.services.client.serialization.jaxb.impl.adapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ObjectMapXmlAdapter extends XmlAdapter<JaxbStringMap, Map<String, Object>> {

    @Override
    public Map<String, Object> unmarshal(JaxbStringMap xmlMap) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        for( JaxbStringMapEntry xmlEntry : xmlMap.entries ) { 
            map.put(xmlEntry.key, xmlEntry.value);
        }
        return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public JaxbStringMap marshal(Map<String, Object> map) throws Exception {
        JaxbStringMap xmlMap = new JaxbStringMap();
        for(Entry entry : map.entrySet()) {
           entry.setValue(entry.getValue().toString()); 
           xmlMap.addEntry(entry);
        }
        return xmlMap;
    }
}
