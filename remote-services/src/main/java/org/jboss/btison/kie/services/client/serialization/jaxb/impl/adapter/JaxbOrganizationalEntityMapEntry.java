package org.jboss.btison.kie.services.client.serialization.jaxb.impl.adapter;

import java.util.List;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jbpm.services.task.impl.model.xml.adapter.OrganizationalEntityXmlAdapter;
import org.kie.api.task.model.OrganizationalEntity;

public class JaxbOrganizationalEntityMapEntry {
    
    @XmlAttribute
    public Long key;
    
    @XmlElements({
        @XmlElement(name="organizational-entity")
    })
    @XmlJavaTypeAdapter(value=OrganizationalEntityXmlAdapter.class)
    public List<OrganizationalEntity> value;
    
    public JaxbOrganizationalEntityMapEntry() {
        
    }
    
    public JaxbOrganizationalEntityMapEntry(Entry<Long, List<OrganizationalEntity>> entry) {
        this.key = entry.getKey();
        this.value = entry.getValue();
    }

}
