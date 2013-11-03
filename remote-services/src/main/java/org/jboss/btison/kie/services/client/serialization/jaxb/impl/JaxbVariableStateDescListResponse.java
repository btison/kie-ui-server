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

@XmlRootElement(name="variable-state-desc-list")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(JaxbVariableStateDescResponse.class)
public class JaxbVariableStateDescListResponse extends AbstractJaxbCommandResponse<List<JaxbVariableStateDescResponse>> {
    
    @XmlElements({
        @XmlElement(name="variable-state-desc",type=JaxbVariableStateDescResponse.class)
    })
    private List<JaxbVariableStateDescResponse> variableStateDescList;
    
    public JaxbVariableStateDescListResponse() {
        this.variableStateDescList = new ArrayList<JaxbVariableStateDescResponse>();
    }
    
    public JaxbVariableStateDescListResponse(List<JaxbVariableStateDescResponse> variableStateDescList) {
        this.variableStateDescList = variableStateDescList;
    }
    
    public JaxbVariableStateDescListResponse(List<JaxbVariableStateDescResponse> variableStateDescList, int i, Command<?> cmd) {
        super(i, cmd);
        this.variableStateDescList = variableStateDescList;
    }

    @Override
    public List<JaxbVariableStateDescResponse> getResult() {
        return variableStateDescList;
    }

    @Override
    public void setResult(List<JaxbVariableStateDescResponse> result) {
        this.variableStateDescList = result;        
    }

}
