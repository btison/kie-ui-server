package org.jboss.btison.kie.services.task.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.jboss.btison.kie.services.client.api.task.AdditionalRestClient;
import org.jboss.btison.kie.services.client.api.task.RestClient;
import org.jboss.seam.transaction.Transactional;
import org.kie.api.task.model.OrganizationalEntity;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.task.api.TaskQueryService;

@Named
@Transactional
@ApplicationScoped
public class TaskQueryServiceImpl implements TaskQueryService {

    @Override
    public List<TaskSummary> getTasksAssignedAsBusinessAdministrator(String userId, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedAsExcludedOwner(String userId, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedAsPotentialOwner(String userId, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedAsPotentialOwnerByExpirationDate(String userId, List<String> groupIds, List<Status> status, Date expirationDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedAsPotentialOwnerByExpirationDateOptional(String userId, List<String> groupIds, List<Status> status,
            Date expirationDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedAsPotentialOwnerByExpirationDate(String userId, List<Status> status, Date expirationDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedAsPotentialOwnerByExpirationDateOptional(String userId, List<Status> status, Date expirationDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedAsPotentialOwner(String userId, List<String> groupIds, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedAsPotentialOwner(String userId, List<String> groupIds, String language, int firstResult, int maxResults) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedAsPotentialOwnerByStatus(String userId, List<Status> status, String language) {
        RestClient restClient = new RestClient();
        return restClient.getTasksAssignedAsPotentialOwnerByStatus(userId, status, language);
    }

    @Override
    public List<TaskSummary> getTasksAssignedAsPotentialOwnerByStatusByGroup(String userId, List<String> groupIds, List<Status> status, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedAsRecipient(String userId, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedAsTaskInitiator(String userId, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedAsTaskStakeholder(String userId, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedByGroup(String groupId, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedByGroups(List<String> groupsId, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedByGroupsByExpirationDate(List<String> groupIds, String language, Date expirationDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksAssignedByGroupsByExpirationDateOptional(List<String> groupIds, String language, Date expirationDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksOwned(String userId, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksOwnedByStatus(String userId, List<Status> status, String language) {
        RestClient restClient = new RestClient();
        return restClient.getTasksOwnedByStatus(userId, status, language);
    }

    @Override
    public List<TaskSummary> getTasksOwnedByExpirationDate(String userId, List<Status> status, Date expirationDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksOwnedByExpirationDateOptional(String userId, List<Status> status, Date expirationDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksOwnedByExpirationDateBeforeSpecifiedDate(String userId, List<Status> status, Date date) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getSubTasksAssignedAsPotentialOwner(long parentId, String userId, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getSubTasksByParent(long parentId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksByStatusByProcessInstanceId(long processInstanceId, List<Status> status, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskSummary> getTasksByStatusByProcessInstanceIdByTaskName(long processInstanceId, List<Status> status, String taskName, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getPendingSubTasksByParent(long parentId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Task getTaskByWorkItemId(long workItemId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Task getTaskInstanceById(long taskId) {
        RestClient restClient = new RestClient();
        return restClient.getTaskById(taskId);
    }

    @Override
    public List<Long> getTasksByProcessInstanceId(long processInstanceId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<Long, List<OrganizationalEntity>> getPotentialOwnersForTaskIds(List<Long> taskIds) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        return restClient.getPotentialOwnersForTaskIds(taskIds);
    }

}
