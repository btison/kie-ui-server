package org.jboss.btison.kie.services.client.serialization.jaxb.impl.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlElement;

import org.kie.api.task.model.OrganizationalEntity;

public class JaxbOrganizationalEntityMap {
    
    @XmlElement(name="entry")
    public List<JaxbOrganizationalEntityMapEntry> entries = new ArrayList<JaxbOrganizationalEntityMapEntry>();
    
    public void addEntry(Entry<Long, List<OrganizationalEntity>> entry) {
        this.entries.add(new JaxbOrganizationalEntityMapEntry(entry));
    }

}
