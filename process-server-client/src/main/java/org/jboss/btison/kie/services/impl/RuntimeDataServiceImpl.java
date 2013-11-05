package org.jboss.btison.kie.services.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.jboss.btison.kie.services.client.api.runtime.AdditionalRestClient;
import org.jboss.seam.transaction.Transactional;
import org.jbpm.kie.services.api.RuntimeDataService;
import org.jbpm.kie.services.impl.event.Deploy;
import org.jbpm.kie.services.impl.event.DeploymentEvent;
import org.jbpm.kie.services.impl.event.Undeploy;
import org.jbpm.kie.services.impl.model.NodeInstanceDesc;
import org.jbpm.kie.services.impl.model.ProcessDesc;
import org.jbpm.kie.services.impl.model.ProcessInstanceDesc;
import org.jbpm.kie.services.impl.model.VariableStateDesc;

@ApplicationScoped
@Transactional
public class RuntimeDataServiceImpl implements RuntimeDataService {
    
    private Set<ProcessDesc> availableProcesses = new HashSet<ProcessDesc>();
    
    public void indexOnDeploy(@Observes@Deploy DeploymentEvent event) {
        Collection<ProcessDesc> assets = event.getDeployedUnit().getDeployedAssets();
        availableProcesses.addAll(assets);
    }
    
    public void removeOnUnDeploy(@Observes@Undeploy DeploymentEvent event) {
        Collection<ProcessDesc> outputCollection = new HashSet<ProcessDesc>();
        CollectionUtils.select(availableProcesses, new ByDeploymentIdPredicate(event.getDeploymentId()), outputCollection);
        
        availableProcesses.removeAll(outputCollection);
    }
    
    @Override
    public Collection<ProcessDesc> getProcessesByDeploymentId(String deploymentId) {
        Collection<ProcessDesc> outputCollection = new HashSet<ProcessDesc>();
        CollectionUtils.select(availableProcesses, new ByDeploymentIdPredicate(deploymentId), outputCollection);
        
        return Collections.unmodifiableCollection(outputCollection);
    }
    
    @Override
    public ProcessDesc getProcessesByDeploymentIdProcessId(String deploymentId, String processId) {
        Collection<ProcessDesc> outputCollection = new HashSet<ProcessDesc>();
        CollectionUtils.select(availableProcesses, new ByDeploymentIdProcessIdPredicate(deploymentId, processId), outputCollection);
        
        if (!outputCollection.isEmpty()) {
            return outputCollection.iterator().next();
        }
        return null;
    }
    
    @Override
    public Collection<ProcessDesc> getProcessesByFilter(String filter) {
        Collection<ProcessDesc> outputCollection = new HashSet<ProcessDesc>();
        CollectionUtils.select(availableProcesses, new RegExPredicate("^.*"+filter+".*$"), outputCollection);
        return Collections.unmodifiableCollection(outputCollection);
    }
    
    public ProcessDesc getProcessById(String processId) {
        
        Collection<ProcessDesc> outputCollection = new HashSet<ProcessDesc>();
        CollectionUtils.select(availableProcesses, new ByProcessIdPredicate(processId), outputCollection);
        if (!outputCollection.isEmpty()) {
            return outputCollection.iterator().next();
        }
        return null;   
    }  
    
    @Override
    public Collection<ProcessDesc> getProcesses() {
        return Collections.unmodifiableCollection(availableProcesses);
    }
    
    @Override
    public Collection<ProcessInstanceDesc> getProcessInstances() {
        throw new UnsupportedOperationException("Not supported yet.");
    }    
    
    @Override
    public Collection<ProcessInstanceDesc> getProcessInstances(List<Integer> states, String initiator) {
       AdditionalRestClient restClient = new AdditionalRestClient();
       return restClient.getProcessInstanceDesc(states, "", initiator);
    }    
    
    @Override
    public Collection<ProcessInstanceDesc> getProcessInstancesByDeploymentId(String deploymentId, List<Integer> states) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<ProcessInstanceDesc> getProcessInstancesByProcessDefinition(String processDefId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProcessInstanceDesc getProcessInstanceById(long processId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        return restClient.getProcessInstanceDescById(processId);
    }
    
    @Override
    public Collection<ProcessInstanceDesc> getProcessInstancesByProcessId(List<Integer> states, String processId, String initiator) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        return restClient.getProcessInstanceDesc(states, processId, initiator);
    }
   
    @Override
    public Collection<ProcessInstanceDesc> getProcessInstancesByProcessName(List<Integer> states, String processName, String initiator) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<NodeInstanceDesc> getProcessInstanceHistory(String deploymentId, long processId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        return restClient.getProcessInstanceHistoryLog(processId, false, false);
    }

    @Override
    public Collection<NodeInstanceDesc> getProcessInstanceHistory(String deploymentId, long processId, boolean completed) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<NodeInstanceDesc> getProcessInstanceFullHistory(String deploymentId, long processId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<NodeInstanceDesc> getProcessInstanceActiveNodes(String deploymentId, long processId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
       return restClient.getProcessInstanceHistoryLog(processId, false, true);
    }

    @Override
    public Collection<NodeInstanceDesc> getProcessInstanceCompletedNodes(String deploymentId, long processId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        return restClient.getProcessInstanceHistoryLog(processId, true, false);
    }

    @Override
    public Collection<VariableStateDesc> getVariablesCurrentState(long processInstanceId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        return restClient.getVariableStateDesc(processInstanceId, null);
    }

    @Override
    public Collection<VariableStateDesc> getVariableHistory(long processInstanceId, String variableId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        return restClient.getVariableStateDesc(processInstanceId, variableId);
    }

    private class RegExPredicate implements Predicate {
        private String pattern;
        
        private RegExPredicate(String pattern) {
            this.pattern = pattern;
        }
        
        @Override
        public boolean evaluate(Object object) {
            if (object instanceof ProcessDesc) {
                ProcessDesc pDesc = (ProcessDesc) object;
                
                if (pDesc.getId().matches(pattern) 
                        || pDesc.getName().matches(pattern)) {
                    return true;
                }
            }
            return false;
        }
        
    }
    
    private class ByDeploymentIdPredicate implements Predicate {
        private String deploymentId;
        
        private ByDeploymentIdPredicate(String deploymentId) {
            this.deploymentId = deploymentId;
        }
        
        @Override
        public boolean evaluate(Object object) {
            if (object instanceof ProcessDesc) {
                ProcessDesc pDesc = (ProcessDesc) object;
                
                if (pDesc.getDeploymentId().equals(deploymentId)) {
                    return true;
                }
            }
            return false;
        }
        
    }
    
    private class ByProcessIdPredicate implements Predicate {
        private String processId;
        
        private ByProcessIdPredicate(String processId) {
            this.processId = processId;
        }
        
        @Override
        public boolean evaluate(Object object) {
            if (object instanceof ProcessDesc) {
                ProcessDesc pDesc = (ProcessDesc) object;
                
                if (pDesc.getId().equals(processId)) {
                    return true;
                }
            }
            return false;
        }
        
    }
    
    private class ByDeploymentIdProcessIdPredicate implements Predicate {
        private String processId;
        private String depoymentId;
        
        private ByDeploymentIdProcessIdPredicate(String depoymentId, String processId) {
            this.depoymentId = depoymentId;
            this.processId = processId;
        }
        
        @Override
        public boolean evaluate(Object object) {
            if (object instanceof ProcessDesc) {
                ProcessDesc pDesc = (ProcessDesc) object;
                
                if (pDesc.getId().equals(processId) && pDesc.getDeploymentId().equals(depoymentId)) {
                    return true;
                }
            }
            return false;
        }
        
    }

}
