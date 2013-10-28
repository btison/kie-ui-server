package org.jboss.btison.kie.services.client.serialization.jaxb.impl.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kie.api.task.model.OrganizationalEntity;

public class OrganizationalEntityMapXmlAdapter extends XmlAdapter<JaxbOrganizationalEntityMap, Map<Long, List<OrganizationalEntity>>> {

    @Override
    public Map<Long, List<OrganizationalEntity>> unmarshal(JaxbOrganizationalEntityMap xmlMap) throws Exception {
        Map<Long, List<OrganizationalEntity>> map = new HashMap<Long, List<OrganizationalEntity>>();
        for (JaxbOrganizationalEntityMapEntry xmlEntry : xmlMap.entries) {
            map.put(xmlEntry.key, xmlEntry.value);
        }
        return map;
    }

    @Override
    public JaxbOrganizationalEntityMap marshal(Map<Long, List<OrganizationalEntity>> v) throws Exception {
        JaxbOrganizationalEntityMap xmlMap = new JaxbOrganizationalEntityMap();
        for (Entry<Long, List<OrganizationalEntity>> entry : v.entrySet()) {
            xmlMap.addEntry(entry);
        }
        return xmlMap;
    }
}
