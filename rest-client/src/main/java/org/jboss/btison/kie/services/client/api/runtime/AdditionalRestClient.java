package org.jboss.btison.kie.services.client.api.runtime;

import java.util.HashMap;
import java.util.Map;

import org.drools.core.command.runtime.process.AbortProcessInstanceCommand;
import org.drools.core.command.runtime.process.SetProcessInstanceVariablesCommand;
import org.drools.core.command.runtime.process.SignalEventCommand;
import org.jboss.btison.kie.services.client.api.AbstractBaseRestClient;
import org.jboss.btison.kie.services.client.serialization.jaxb.JaxbCommandsRequest;
import org.jboss.btison.kie.services.client.serialization.jaxb.JaxbCommandsResponse;
import org.jboss.resteasy.client.ProxyFactory;


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
}
