package org.jboss.btison.kie.services.client.serialization.jaxb.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.kie.api.command.Command;
import org.kie.services.client.serialization.jaxb.impl.AbstractJaxbCommandResponse;

@XmlRootElement(name="node-instance-desc-list")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(JaxbNodeInstanceDescResponse.class)
public class JaxbNodeInstanceDescListResponse extends AbstractJaxbCommandResponse<List<JaxbNodeInstanceDescResponse>> {
    
    @XmlElements({
        @XmlElement(name="node-instance-desc",type=JaxbNodeInstanceDescResponse.class)
    })
    private List<JaxbNodeInstanceDescResponse> nodeInstanceDescList;
    
    public JaxbNodeInstanceDescListResponse() {
        this.nodeInstanceDescList = new ArrayList<JaxbNodeInstanceDescResponse>();
    }
    
    public JaxbNodeInstanceDescListResponse(List<JaxbNodeInstanceDescResponse> nodeInstanceDescList) {
        this.nodeInstanceDescList = nodeInstanceDescList;
    }
    
    public JaxbNodeInstanceDescListResponse(List<JaxbNodeInstanceDescResponse> nodeInstanceDescList, int i, Command<?> cmd) {
        super(i, cmd);
        this.nodeInstanceDescList = nodeInstanceDescList;
    }

    @Override
    public List<JaxbNodeInstanceDescResponse> getResult() {
        return nodeInstanceDescList;
    }

    @Override
    public void setResult(List<JaxbNodeInstanceDescResponse> result) {
        this.nodeInstanceDescList = result;        
    }

}
