package org.jboss.btison.kie.services.command.runtime.process;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.jbpm.kie.services.api.RuntimeDataService;
import org.jbpm.kie.services.impl.model.NodeInstanceDesc;
import org.jbpm.kie.services.impl.model.ProcessInstanceDesc;
import org.kie.internal.command.Context;

@XmlAccessorType(XmlAccessType.NONE)
public class GetNodeInstanceDescCommand extends RuntimeDataServiceCommand<Collection<NodeInstanceDesc>> {
    
    @XmlElement(name = "id")
    private Long processInstanceId;
    
    @XmlElement(name = "completed")
    private Boolean completed = new Boolean(false);
    
    @XmlElement(name = "active")
    private Boolean active = new Boolean(false);

    public GetNodeInstanceDescCommand() {
    }
    
    public GetNodeInstanceDescCommand(Long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
    
    public GetNodeInstanceDescCommand(Long processInstanceId, boolean completed, boolean active) {
        this.processInstanceId = processInstanceId;
        this.completed = completed;
        this.active = active;
    }
    
    @Override
    public Collection<NodeInstanceDesc> execute(Context context) {
        RuntimeDataService dataService = ((RuntimeDataServiceCommandContext) context).getDataService(); 
        ProcessInstanceDesc desc = dataService.getProcessInstanceById(processInstanceId);
        if (desc != null) {
            if (completed) {
                return dataService.getProcessInstanceCompletedNodes(desc.getDeploymentId(), processInstanceId);
            } else if (active) {
                return dataService.getProcessInstanceActiveNodes(desc.getDeploymentId(), processInstanceId);
            } else {
                return dataService.getProcessInstanceHistory(desc.getDeploymentId(), processInstanceId);
            }
        }
        return new ArrayList<NodeInstanceDesc>();
    }

}
