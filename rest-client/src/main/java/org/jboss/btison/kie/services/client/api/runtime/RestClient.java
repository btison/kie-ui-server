package org.jboss.btison.kie.services.client.api.runtime;

import java.util.Map;

import org.drools.core.command.runtime.process.AbortProcessInstanceCommand;
import org.drools.core.command.runtime.process.SignalEventCommand;
import org.drools.core.command.runtime.process.StartProcessCommand;
import org.jboss.btison.kie.services.client.api.AbstractBaseRestClient;
import org.jboss.resteasy.client.ProxyFactory;
import org.kie.services.client.serialization.jaxb.JaxbCommandsRequest;
import org.kie.services.client.serialization.jaxb.JaxbCommandsResponse;
import org.kie.services.client.serialization.jaxb.impl.JaxbProcessInstanceResponse;

public class RestClient extends AbstractBaseRestClient {
    
    public static class RuntimeClientHolder {
        public static RuntimeClient runtimeClient = initRuntimeClient();
    }
    
    private static RuntimeClient initRuntimeClient() {
        RuntimeClient taskClient = ProxyFactory.create(RuntimeClient.class, getTargetUrl(), getExecutor());
        return taskClient;
    }
    
    public long startProcess(String domainName, String processId, Map<String, String> params) {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Map<String,Object> objectMap = (Map)params;
        StartProcessCommand command = new StartProcessCommand(processId, objectMap);
        JaxbCommandsRequest request = new JaxbCommandsRequest(domainName, command);
        JaxbCommandsResponse response = RuntimeClientHolder.runtimeClient.execute(domainName, request);
        if (!response.getResponses().isEmpty() && response.getResponses().get(0) instanceof JaxbProcessInstanceResponse) {
            JaxbProcessInstanceResponse resp = (JaxbProcessInstanceResponse) response.getResponses().get(0);
            return resp.getId();
        }        
        return 0;
    }
    
    public void abortProcessInstance(String domainName, long processInstanceId) {
        AbortProcessInstanceCommand command = new AbortProcessInstanceCommand();
        command.setProcessInstanceId(processInstanceId);
        JaxbCommandsRequest request = new JaxbCommandsRequest(domainName, command);
        request.setProcessInstanceId(processInstanceId);
        JaxbCommandsResponse response = RuntimeClientHolder.runtimeClient.execute(domainName, request);        
    }
    
    public void signalProcessInstance(String domainName, long processInstanceId, String signalName, Object event) {
        SignalEventCommand command = new SignalEventCommand(processInstanceId, signalName, event);
        JaxbCommandsRequest request = new JaxbCommandsRequest(domainName, command);
        request.setProcessInstanceId(processInstanceId);
        JaxbCommandsResponse response = RuntimeClientHolder.runtimeClient.execute(domainName, request);
    }

}
