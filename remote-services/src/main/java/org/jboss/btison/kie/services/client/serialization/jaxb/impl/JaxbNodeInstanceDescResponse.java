package org.jboss.btison.kie.services.client.serialization.jaxb.impl;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import org.jbpm.kie.services.impl.model.NodeInstanceDesc;
import org.kie.api.command.Command;
import org.kie.services.client.serialization.jaxb.impl.AbstractJaxbCommandResponse;

@XmlRootElement(name="node-instance-desc")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbNodeInstanceDescResponse extends AbstractJaxbCommandResponse<NodeInstanceDesc> {
    
    @XmlElement
    @XmlSchemaType(name="string")
    private String id;
    
    @XmlElement(name="node-id")
    @XmlSchemaType(name="string")
    private String nodeId;
    
    @XmlElement(name="node-name")
    @XmlSchemaType(name="string")
    private String name;
    
    @XmlElement(name="deployment-id")
    @XmlSchemaType(name="string")
    private String deploymentId;
    
    @XmlElement(name = "process-instance-id")
    @XmlSchemaType(name="long")
    private Long processInstanceId;
    
    @XmlElement(name="node-type")
    @XmlSchemaType(name="string")
    private String nodeType;
    
    @XmlElement(name="connection")
    @XmlSchemaType(name="string")    
    private String connection;    
    
    @XmlElement(name="type")
    @XmlSchemaType(name="int")
    private Integer type;
    
    @XmlElement(name="date-time-stamp")
    @XmlSchemaType(name="dateTime")
    private Date dataTimeStamp;
    
    public JaxbNodeInstanceDescResponse() {
        
    }
    
    public JaxbNodeInstanceDescResponse(NodeInstanceDesc nodeInstanceDesc) {
        initialize(nodeInstanceDesc);
    }
    
    public JaxbNodeInstanceDescResponse(NodeInstanceDesc nodeInstanceDesc, int i, Command<?> cmd) {
        super(i, cmd);
        initialize(nodeInstanceDesc);
    }

    private void initialize(NodeInstanceDesc nodeInstanceDesc) {
        if (nodeInstanceDesc != null) {
            this.connection = nodeInstanceDesc.getConnection();
            this.dataTimeStamp = nodeInstanceDesc.getDataTimeStamp();
            this.deploymentId = nodeInstanceDesc.getDeploymentId();
            this.id = new Long(nodeInstanceDesc.getId()).toString();
            this.name = nodeInstanceDesc.getName();
            this.nodeId = nodeInstanceDesc.getNodeId();
            this.nodeType = nodeInstanceDesc.getNodeType();
            this.processInstanceId = nodeInstanceDesc.getProcessInstanceId();
            if (nodeInstanceDesc.isCompleted()) {
                this.type = 1;
            } else {
                this.type = 0;
            }
        }
        
    }

    @Override
    public NodeInstanceDesc getResult() {
        return new NodeInstanceDesc(this.id, this.nodeId, this.name, this.nodeType, this.deploymentId, this.processInstanceId, this.dataTimeStamp,
                this.connection, this.type);
    }

    @Override
    public void setResult(NodeInstanceDesc result) {
        initialize(result);        
    }

}
