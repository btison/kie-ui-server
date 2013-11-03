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

@XmlRootElement(name="process-instance-desc-list")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(JaxbProcessInstanceDescResponse.class)
public class JaxbProcessInstanceDescListResponse extends AbstractJaxbCommandResponse<List<JaxbProcessInstanceDescResponse>> {
    
    @XmlElements({
        @XmlElement(name="process-instance-desc",type=JaxbProcessInstanceDescResponse.class)
    })
    private List<JaxbProcessInstanceDescResponse> processInstanceDescList;
    
    public JaxbProcessInstanceDescListResponse() {
        this.processInstanceDescList = new ArrayList<JaxbProcessInstanceDescResponse>();
    }
    
    public JaxbProcessInstanceDescListResponse(List<JaxbProcessInstanceDescResponse> processInstanceDescList) {
        this.processInstanceDescList = processInstanceDescList;
    }
    
    public JaxbProcessInstanceDescListResponse(List<JaxbProcessInstanceDescResponse> processInstanceDescList, int i, Command<?> cmd) {
        super(i, cmd);
        this.processInstanceDescList = processInstanceDescList;
    }

    @Override
    public List<JaxbProcessInstanceDescResponse> getResult() {
        return processInstanceDescList;
    }

    @Override
    public void setResult(List<JaxbProcessInstanceDescResponse> result) {
        this.processInstanceDescList = result;        
    }

}
