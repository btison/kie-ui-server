package org.jboss.btison.kie.services.client.serialization.jaxb.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jboss.btison.kie.services.client.serialization.jaxb.impl.adapter.OrganizationalEntityMapXmlAdapter;
import org.kie.api.command.Command;
import org.kie.api.task.model.OrganizationalEntity;
import org.kie.services.client.serialization.jaxb.impl.AbstractJaxbCommandResponse;

@XmlRootElement(name="organization-map")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbOrganizationalEntityMapResponse extends AbstractJaxbCommandResponse<Map<Long, List<OrganizationalEntity>>> {

    @XmlElement
    @XmlJavaTypeAdapter(value=OrganizationalEntityMapXmlAdapter.class)
    private Map<Long, List<OrganizationalEntity>> organizationalEntityMap;
    
    public JaxbOrganizationalEntityMapResponse() {
        organizationalEntityMap = new HashMap<Long, List<OrganizationalEntity>>();
    }
    
    public JaxbOrganizationalEntityMapResponse(Map<Long, List<OrganizationalEntity>> organizationalEntityMap) {
        this.organizationalEntityMap = organizationalEntityMap;
    }
    
    public JaxbOrganizationalEntityMapResponse(Map<Long, List<OrganizationalEntity>> organizationalEntityMap, int i, Command<?> cmd) {
        super(i, cmd);
        this.organizationalEntityMap = organizationalEntityMap;
    }
    
    @Override
    public Map<Long, List<OrganizationalEntity>> getResult() {
        return organizationalEntityMap;
    }

    @Override
    public void setResult(Map<Long, List<OrganizationalEntity>> result) {
        this.organizationalEntityMap = result;
    }

}
