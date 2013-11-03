package org.jboss.btison.kie.services.client.serialization.jaxb.impl;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import org.jbpm.kie.services.impl.model.ProcessInstanceDesc;
import org.kie.api.command.Command;
import org.kie.services.client.serialization.jaxb.impl.AbstractJaxbCommandResponse;

@XmlRootElement(name="process-instance-desc")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbProcessInstanceDescResponse extends AbstractJaxbCommandResponse<ProcessInstanceDesc> {
    
    @XmlElement
    private Long id;
    
    @XmlElement(name="process-id")
    @XmlSchemaType(name="string")
    private String processId;
    
    @XmlElement(name="process-name")
    @XmlSchemaType(name="string")
    private String processName;
    
    @XmlElement(name="process-version")
    @XmlSchemaType(name="string")
    private String processVersion;
    
    @XmlElement(name="process-state")
    @XmlSchemaType(name="int")
    private Integer state;
    
    @XmlElement(name="deployment-id")
    @XmlSchemaType(name="string")
    private String deploymentId;    
    
    @XmlElement(name="initiator")
    @XmlSchemaType(name="string")
    private String initiator;
    
    @XmlElement(name="process-start-date")
    @XmlSchemaType(name="dateTime")
    private Date dataTimeStamp;
    
    
    public JaxbProcessInstanceDescResponse() {
        
    }
    
    public JaxbProcessInstanceDescResponse(ProcessInstanceDesc processInstanceDesc) {
        initialize(processInstanceDesc);
    }
    
    public JaxbProcessInstanceDescResponse(ProcessInstanceDesc processInstanceDesc, int i, Command<?> cmd) {
        super(i, cmd);
        initialize(processInstanceDesc);
    }

    private void initialize(ProcessInstanceDesc processInstanceDesc) {
        if (processInstanceDesc != null) {
            this.id = processInstanceDesc.getId();
            this.processId = processInstanceDesc.getProcessId();
            this.processName = processInstanceDesc.getProcessName();
            this.processVersion = processInstanceDesc.getProcessVersion();
            this.state = processInstanceDesc.getState();
            this.deploymentId = processInstanceDesc.getDeploymentId();
            this.dataTimeStamp = processInstanceDesc.getDataTimeStamp();
            this.initiator = processInstanceDesc.getInitiator();
        }
        
    }

    @Override
    public ProcessInstanceDesc getResult() {
        return new ProcessInstanceDesc(this.id, this.processId, this.processName, this.processVersion, this.state, this.deploymentId, this.dataTimeStamp,
                this.initiator);
    }

    @Override
    public void setResult(ProcessInstanceDesc result) {
        initialize(result);        
    }

}
