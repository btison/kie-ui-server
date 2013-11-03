package org.jboss.btison.kie.services.client.serialization.jaxb.impl;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import org.jbpm.kie.services.impl.model.VariableStateDesc;
import org.kie.api.command.Command;
import org.kie.services.client.serialization.jaxb.impl.AbstractJaxbCommandResponse;

@XmlRootElement(name="node-instance-desc")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbVariableStateDescResponse extends AbstractJaxbCommandResponse<VariableStateDesc> {
    
    @XmlElement(name="id")
    @XmlSchemaType(name="string")
    private String variableId;
    
    @XmlElement(name="variable-id")
    @XmlSchemaType(name="string")
    private String variableInstanceIdId;
    
    @XmlElement(name="old-value")
    @XmlSchemaType(name="string")
    private String oldValue;
    
    @XmlElement(name="new-value")
    @XmlSchemaType(name="string")
    private String newValue;
    
    @XmlElement(name="deployment-id")
    @XmlSchemaType(name="string")
    private String deploymentId;
    
    @XmlElement(name = "process-instance-id")
    @XmlSchemaType(name="long")
    private Long processInstanceId;
    
    @XmlElement(name="date-time-stamp")
    @XmlSchemaType(name="dateTime")
    private Date dataTimeStamp;
    
    public JaxbVariableStateDescResponse() {
        
    }
    
    public JaxbVariableStateDescResponse(VariableStateDesc variableStateDesc) {
        initialize(variableStateDesc);
    }
    
    public JaxbVariableStateDescResponse(VariableStateDesc variableStateDesc, int i, Command<?> cmd) {
        super(i, cmd);
        initialize(variableStateDesc);
    }

    private void initialize(VariableStateDesc variableStateDesc) {
        if (variableStateDesc != null) {
            this.variableId = variableStateDesc.getVariableId();
            this.variableInstanceIdId = variableStateDesc.getVariableInstanceId();
            this.oldValue = variableStateDesc.getOldValue();
            this.newValue = variableStateDesc.getNewValue();
            this.deploymentId = variableStateDesc.getDeploymentId();
            this.processInstanceId = variableStateDesc.getProcessInstanceId();
            this.dataTimeStamp = variableStateDesc.getDataTimeStamp();
        }
    }

    @Override
    public VariableStateDesc getResult() {
        return new VariableStateDesc(this.variableId, this.variableInstanceIdId, this.oldValue, this.newValue, this.deploymentId, this.processInstanceId,
                this.dataTimeStamp);
    }

    @Override
    public void setResult(VariableStateDesc result) {
        initialize(result);        
    }

}
