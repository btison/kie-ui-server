package org.jboss.btison.kie.services.client.api.runtime;

import java.util.HashMap;
import java.util.Map;

import org.drools.core.command.runtime.process.SetProcessInstanceVariablesCommand;
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
    
    public void setProcessVariable(String domainName, long processInstanceId, String variableId, Object value) {  
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(variableId, value);
        SetProcessInstanceVariablesCommand command = new SetProcessInstanceVariablesCommand(processInstanceId, variables);
        JaxbCommandsRequest request = new JaxbCommandsRequest(domainName, command);
        request.setProcessInstanceId(processInstanceId);
        JaxbCommandsResponse response = RuntimeClientHolder.runtimeClient.execute(domainName, request);
    }
}
