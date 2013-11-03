package org.jboss.btison.kie.services.command.runtime.process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.jbpm.kie.services.api.RuntimeDataService;
import org.jbpm.kie.services.impl.model.ProcessInstanceDesc;
import org.kie.internal.command.Context;

@XmlAccessorType(XmlAccessType.NONE)
public class GetProcessInstanceDescCommand extends RuntimeDataServiceCommand<Collection<ProcessInstanceDesc>> {
    
    @XmlElement(name = "id")
    private Long processInstanceId;

    @XmlElementWrapper(name="states")
    private List<Integer> states;
    
    @XmlElement(name="filter")
    private String filterText;
    
    @XmlElement(name="initiator")
    private String initiator;
    
    public GetProcessInstanceDescCommand() {
    }
    
    public GetProcessInstanceDescCommand(Long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
    
    public GetProcessInstanceDescCommand(List<Integer> states, String filterText, String initiator) {
        this.states = states;
        this.filterText = filterText;
        this.initiator = initiator;
    }

    @Override
    public Collection<ProcessInstanceDesc> execute(Context context) {
        RuntimeDataService dataService = ((RuntimeDataServiceCommandContext) context).getDataService(); 
        if (processInstanceId != null) {
            List<ProcessInstanceDesc> result = new ArrayList<ProcessInstanceDesc>();
            ProcessInstanceDesc desc = dataService.getProcessInstanceById(processInstanceId);
            if (desc != null) {
                result.add(desc);
            }
            return result;
        }
        if (filterText != null && !filterText.equals("")) {
            return dataService.getProcessInstancesByProcessName(states, filterText, initiator);
        } else {
            return dataService.getProcessInstances(states, initiator);
        }
    }

}
