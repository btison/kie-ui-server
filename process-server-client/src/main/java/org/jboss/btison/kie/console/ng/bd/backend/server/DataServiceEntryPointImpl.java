package org.jboss.btison.kie.console.ng.bd.backend.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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
import org.jbpm.kie.services.impl.model.ProcessInstanceDesc;

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
        return ProcessInstanceHelper.adaptCollection(dataService.getProcessInstances());
    }

    @Override
    public Collection<ProcessInstanceSummary> getProcessInstancesByDeploymentId(String deploymentId, List<Integer> states) {
        return ProcessInstanceHelper.adaptCollection(dataService.getProcessInstancesByDeploymentId(deploymentId, states));
    }

    @Override
    public Collection<ProcessSummary> getProcessesByFilter(String filter) {
        return ProcessHelper.adaptCollection(dataService.getProcessesByFilter(filter));
    }

    @Override
    public ProcessInstanceSummary getProcessInstanceById(long processInstanceId) {
        return ProcessInstanceHelper.adapt(dataService.getProcessInstanceById(processInstanceId));
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
        return ProcessInstanceHelper.adaptCollection(dataService.getProcessInstancesByProcessDefinition(processDefId));
    }

    @Override
    public Collection<NodeInstanceSummary> getProcessInstanceHistory(long processInstanceId) {
        return NodeInstanceHelper.adaptCollection(dataService.getProcessInstanceHistory(null, processInstanceId));
    }

    @Override
    public Collection<NodeInstanceSummary> getProcessInstanceHistory(long processInstanceId, boolean completed) {
        return NodeInstanceHelper.adaptCollection(dataService.getProcessInstanceHistory(null,
                processInstanceId, completed));
    }

    @Override
    public Collection<NodeInstanceSummary> getProcessInstanceFullHistory(long processInstanceId) {
        return NodeInstanceHelper.adaptCollection(dataService.getProcessInstanceFullHistory(null, processInstanceId));
    }

    @Override
    public Collection<NodeInstanceSummary> getProcessInstanceActiveNodes(long processInstanceId) {
        return NodeInstanceHelper.adaptCollection(dataService.getProcessInstanceActiveNodes(null, processInstanceId));
    }

    @Override
    public Collection<ProcessInstanceSummary> getProcessInstances(List<Integer> states, String filterText, String initiator) {
        Collection<ProcessInstanceDesc> result = null;
        if (!filterText.equals("")) {
            // search by process name
            result = dataService.getProcessInstancesByProcessName(states, filterText, initiator);
        } else {
            result = dataService.getProcessInstances(states, initiator);
        }

        return ProcessInstanceHelper.adaptCollection(result);
    }

    @Override
    public Collection<NodeInstanceSummary> getProcessInstanceCompletedNodes(long processInstanceId) {
        return NodeInstanceHelper.adaptCollection(dataService.getProcessInstanceCompletedNodes(null, processInstanceId));
    }

    @Override
    public Collection<VariableSummary> getVariableHistory(long processInstanceId, String variableId) {
        return VariableHelper.adaptCollection(dataService.getVariableHistory(processInstanceId, variableId));
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
