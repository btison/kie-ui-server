package org.jboss.btison.kie.services.command.runtime.process;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.jbpm.kie.services.api.RuntimeDataService;
import org.jbpm.kie.services.impl.model.VariableStateDesc;
import org.kie.internal.command.Context;

@XmlAccessorType(XmlAccessType.NONE)
public class GetVariableStateDescCommand extends RuntimeDataServiceCommand<Collection<VariableStateDesc>> {
    
    @XmlElement(name = "id")
    private Long processInstanceId;
    
    @XmlElement(name = "variable")
    private String variableId;
    
    public GetVariableStateDescCommand() {
    }
    
    public GetVariableStateDescCommand(Long processInstanceId, String variableId) {
        this.processInstanceId = processInstanceId;
        this.variableId = variableId;
    }
    
    @Override
    public Collection<VariableStateDesc> execute(Context context) {
        RuntimeDataService dataService = ((RuntimeDataServiceCommandContext) context).getDataService();
        return dataService.getVariableHistory(processInstanceId, variableId);
    }

}
