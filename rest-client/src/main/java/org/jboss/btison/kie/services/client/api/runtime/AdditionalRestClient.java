package org.jboss.btison.kie.services.client.api.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.core.command.runtime.process.AbortProcessInstanceCommand;
import org.drools.core.command.runtime.process.SetProcessInstanceVariablesCommand;
import org.drools.core.command.runtime.process.SignalEventCommand;
import org.jboss.btison.kie.services.client.api.AbstractBaseRestClient;
import org.jboss.btison.kie.services.client.serialization.jaxb.JaxbCommandsRequest;
import org.jboss.btison.kie.services.client.serialization.jaxb.JaxbCommandsResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbNodeInstanceDescListResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbNodeInstanceDescResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbProcessInstanceDescListResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbProcessInstanceDescResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbVariableStateDescListResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbVariableStateDescResponse;
import org.jboss.btison.kie.services.command.runtime.process.GetNodeInstanceDescCommand;
import org.jboss.btison.kie.services.command.runtime.process.GetProcessInstanceDescCommand;
import org.jboss.btison.kie.services.command.runtime.process.GetVariableStateDescCommand;
import org.jboss.resteasy.client.ProxyFactory;
import org.jbpm.kie.services.impl.model.NodeInstanceDesc;
import org.jbpm.kie.services.impl.model.ProcessInstanceDesc;
import org.jbpm.kie.services.impl.model.VariableStateDesc;
import org.kie.services.client.serialization.jaxb.impl.JaxbCommandResponse;


public class AdditionalRestClient extends AbstractBaseRestClient {
    
    public static class RuntimeClientHolder {
        public static AdditionalRuntimeClient runtimeClient = initRuntimeClient();
    }
    
    private static AdditionalRuntimeClient initRuntimeClient() {
        AdditionalRuntimeClient runtimeClient = ProxyFactory.create(AdditionalRuntimeClient.class, getTargetUrl(), getExecutor());
        return runtimeClient;
    }
    
    public void abortProcessInstance(long processInstanceId) {
        AbortProcessInstanceCommand command = new AbortProcessInstanceCommand();
        command.setProcessInstanceId(processInstanceId);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        request.setProcessInstanceId(processInstanceId);
        JaxbCommandsResponse response = RuntimeClientHolder.runtimeClient.execute(request);        
    }
    
    public void signalProcessInstance(long processInstanceId, String signalName, Object event) {
        SignalEventCommand command = new SignalEventCommand(processInstanceId, signalName, event);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        request.setProcessInstanceId(processInstanceId);
        JaxbCommandsResponse response = RuntimeClientHolder.runtimeClient.execute(request);
    }
    
    public void setProcessVariable(long processInstanceId, String variableId, Object value) {  
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(variableId, value);
        SetProcessInstanceVariablesCommand command = new SetProcessInstanceVariablesCommand(processInstanceId, variables);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        request.setProcessInstanceId(processInstanceId);
        JaxbCommandsResponse response = RuntimeClientHolder.runtimeClient.execute(request);
    }
    
    public Collection<ProcessInstanceDesc> getProcessInstanceDesc(List<Integer> states, String filterText, String initiator) {
        return getProcessInstanceDesc(states, filterText, initiator, null);
    }
    
    public Collection<ProcessInstanceDesc> getProcessInstanceDesc(String deploymentId, List<Integer> states) {
        return getProcessInstanceDesc(states, null, null, deploymentId);
    }

    public Collection<ProcessInstanceDesc> getProcessInstanceDesc(List<Integer> states, String filterText, String initiator, String deploymentId) {
        GetProcessInstanceDescCommand command = new GetProcessInstanceDescCommand(states, filterText, initiator, deploymentId);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = RuntimeClientHolder.runtimeClient.execute(request);
        if (response.getResponses() != null && !response.getResponses().isEmpty()) {
            JaxbCommandResponse<?> resp = response.getResponses().get(0);
            if (resp instanceof JaxbProcessInstanceDescListResponse) {
                List<ProcessInstanceDesc> result = new ArrayList<ProcessInstanceDesc>();
                for (JaxbProcessInstanceDescResponse descResponse : ((JaxbProcessInstanceDescListResponse)resp).getResult()) {
                    result.add(descResponse.getResult());
                }
                return result;
            }
        }
        return new ArrayList<ProcessInstanceDesc>();
    }

    public ProcessInstanceDesc getProcessInstanceDescById(long processInstanceId) {
        GetProcessInstanceDescCommand command = new GetProcessInstanceDescCommand(processInstanceId);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = RuntimeClientHolder.runtimeClient.execute(request);
        if (response.getResponses() != null && !response.getResponses().isEmpty()) {
            JaxbCommandResponse<?> resp = response.getResponses().get(0);
            if (resp instanceof JaxbProcessInstanceDescListResponse) {
                List<JaxbProcessInstanceDescResponse> descResponse = ((JaxbProcessInstanceDescListResponse)resp).getResult();
                if (descResponse != null && !descResponse.isEmpty()) {
                    return descResponse.get(0).getResult();
                }
            }
        }
        return null;       
    }

    public Collection<NodeInstanceDesc> getProcessInstanceHistoryLog(long processInstanceId, boolean completed, boolean active) {
        GetNodeInstanceDescCommand command = new GetNodeInstanceDescCommand(processInstanceId, completed, active);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = RuntimeClientHolder.runtimeClient.execute(request);
        if (response.getResponses() != null && !response.getResponses().isEmpty()) {
            JaxbCommandResponse<?> resp = response.getResponses().get(0);
            if (resp instanceof JaxbNodeInstanceDescListResponse) {
                List<NodeInstanceDesc> result = new ArrayList<NodeInstanceDesc>();
                for (JaxbNodeInstanceDescResponse descResponse : ((JaxbNodeInstanceDescListResponse)resp).getResult()) {
                    result.add(descResponse.getResult());
                }
                return result;
            }
        }        
        return new ArrayList<NodeInstanceDesc>();
    }

    public Collection<VariableStateDesc> getVariableStateDesc(long processInstanceId, String variableId) {
        GetVariableStateDescCommand command = new GetVariableStateDescCommand(processInstanceId, variableId);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = RuntimeClientHolder.runtimeClient.execute(request);
        if (response.getResponses() != null && !response.getResponses().isEmpty()) {
            JaxbCommandResponse<?> resp = response.getResponses().get(0);
            if (resp instanceof JaxbVariableStateDescListResponse) {
                List<VariableStateDesc> result = new ArrayList<VariableStateDesc>();
                for (JaxbVariableStateDescResponse descResponse : ((JaxbVariableStateDescListResponse)resp).getResult()) {
                    result.add(descResponse.getResult());
                }
                return result;
            }
        }
        return new ArrayList<VariableStateDesc>();
    }
}
