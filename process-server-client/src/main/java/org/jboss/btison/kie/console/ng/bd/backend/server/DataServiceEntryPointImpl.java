package org.jboss.btison.kie.console.ng.bd.backend.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.btison.kie.services.client.api.runtime.AdditionalRestClient;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.seam.transaction.Transactional;
import org.jbpm.console.ng.bd.service.DataServiceEntryPoint;
import org.jbpm.console.ng.ht.backend.server.TaskDefHelper;
import org.jbpm.console.ng.ht.model.TaskDefSummary;
import org.jbpm.console.ng.pr.backend.server.NodeInstanceHelper;
import org.jbpm.console.ng.pr.backend.server.ProcessHelper;
import org.jbpm.console.ng.pr.backend.server.ProcessInstanceHelper;
import org.jbpm.console.ng.pr.backend.server.VariableHelper;
import org.jbpm.console.ng.pr.model.NodeInstanceSummary;
import org.jbpm.console.ng.pr.model.ProcessInstanceSummary;
import org.jbpm.console.ng.pr.model.ProcessSummary;
import org.jbpm.console.ng.pr.model.VariableSummary;
import org.jbpm.kie.services.api.RuntimeDataService;
import org.jbpm.kie.services.api.bpmn2.BPMN2DataService;
import org.jbpm.kie.services.impl.model.NodeInstanceDesc;
import org.jbpm.kie.services.impl.model.ProcessInstanceDesc;
import org.jbpm.kie.services.impl.model.VariableStateDesc;

@Service
@ApplicationScoped
@Transactional
public class DataServiceEntryPointImpl implements DataServiceEntryPoint {

    @Inject
    RuntimeDataService dataService;

    @Inject
    BPMN2DataService bpmn2Service;

    @Override
    public Collection<ProcessInstanceSummary> getProcessInstances() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Collection<ProcessInstanceSummary> getProcessInstancesByDeploymentId(String deploymentId, List<Integer> states) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Collection<ProcessSummary> getProcessesByFilter(String filter) {
        return ProcessHelper.adaptCollection(dataService.getProcessesByFilter(filter));
    }

    @Override
    public ProcessInstanceSummary getProcessInstanceById(long processInstanceId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        ProcessInstanceDesc desc = restClient.getProcessInstanceDescById(processInstanceId);
        return ProcessInstanceHelper.adapt(desc);
    }

    @Override
    public ProcessSummary getProcessById(String processId) {
        return ProcessHelper.adapt(dataService.getProcessById(processId));
    }

    @Override
    public Collection<ProcessSummary> getProcesses() {
        return ProcessHelper.adaptCollection(dataService.getProcesses());
    }

    @Override
    public Collection<ProcessInstanceSummary> getProcessInstancesByProcessDefinition(String processDefId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Collection<NodeInstanceSummary> getProcessInstanceHistory(long processInstanceId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        Collection<NodeInstanceDesc> result = restClient.getProcessInstanceHistoryLog(processInstanceId, false, false);
        return NodeInstanceHelper.adaptCollection(result);
    }

    @Override
    public Collection<NodeInstanceSummary> getProcessInstanceHistory(long processInstanceId, boolean completed) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Collection<NodeInstanceSummary> getProcessInstanceFullHistory(long processInstanceId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Collection<NodeInstanceSummary> getProcessInstanceActiveNodes(long processInstanceId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        Collection<NodeInstanceDesc> result = restClient.getProcessInstanceHistoryLog(processInstanceId, false, true);
        return NodeInstanceHelper.adaptCollection(result);
    }

    @Override
    public Collection<ProcessInstanceSummary> getProcessInstances(List<Integer> states, String filterText, String initiator) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        Collection<ProcessInstanceDesc> result =  restClient.getProcessInstanceDesc(states, filterText, initiator);
        
        return ProcessInstanceHelper.adaptCollection(result);
    }

    @Override
    public Collection<NodeInstanceSummary> getProcessInstanceCompletedNodes(long processInstanceId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        Collection<NodeInstanceDesc> result = restClient.getProcessInstanceHistoryLog(processInstanceId, true, false);
        return NodeInstanceHelper.adaptCollection(result);
    }

    @Override
    public Collection<VariableSummary> getVariableHistory(long processInstanceId, String variableId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        Collection<VariableStateDesc> result = restClient.getVariableStateDesc(processInstanceId, variableId);
        return VariableHelper.adaptCollection(result);
    }

    /*
     * BPMN2
     */

    @Override
    public Collection<String> getReusableSubProcesses(String processId) {
        return bpmn2Service.getReusableSubProcesses(processId);
    }

    @Override
    public List<String> getAssociatedDomainObjects(String processId) {
        return bpmn2Service.getAssociatedDomainObjects(processId);
    }

    @Override
    public Map<String, String> getRequiredInputData(String processId) {
        return bpmn2Service.getProcessData(processId);
    }

    @Override
    public List<String> getAssociatedForms(String processId) {
        return bpmn2Service.getAssociatedForms(processId);
    }

    @Override
    public Collection<TaskDefSummary> getAllTasksDef(String processId) {
        return TaskDefHelper.adaptCollection(bpmn2Service.getAllTasksDef(processId));
    }

    @Override
    public Map<String, String> getAssociatedEntities(String processId) {
        return bpmn2Service.getAssociatedEntities(processId);
    }

    @Override
    public ProcessSummary getProcessDesc(String processId) {
        return ProcessHelper.adapt(bpmn2Service.getProcessDesc(processId));
    }

    @Override
    public Collection<VariableSummary> getVariablesCurrentState(long processInstanceId, String processId) {
        Map<String, String> properties = new HashMap<String, String>(bpmn2Service.getProcessData(processId));
        return VariableHelper.adaptCollection(dataService.getVariablesCurrentState(processInstanceId), properties,
                processInstanceId);
    }

    @Override
    public Map<String, String> getTaskInputMappings(String processId, String taskName) {
        return bpmn2Service.getTaskInputMappings(processId, taskName);
    }

    @Override
    public Map<String, String> getTaskOutputMappings(String processId, String taskName) {
        return bpmn2Service.getTaskOutputMappings(processId, taskName);
    }

}
