package org.jboss.btison.kie.services.task.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.btison.kie.services.client.api.task.AdditionalRestClient;
import org.jboss.btison.kie.services.client.api.task.RestClient;
import org.jboss.seam.transaction.Transactional;
import org.kie.api.command.Command;
import org.kie.api.task.model.I18NText;
import org.kie.api.task.model.OrganizationalEntity;
import org.kie.api.task.model.Task;
import org.kie.internal.task.api.TaskInstanceService;
import org.kie.internal.task.api.model.ContentData;
import org.kie.internal.task.api.model.FaultData;
import org.kie.internal.task.api.model.SubTasksStrategy;

@Transactional
@ApplicationScoped
public class TaskInstanceServiceImpl implements TaskInstanceService  {

    @Override
    public <T> T execute(Command<T> command) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long addTask(Task task, Map<String, Object> params) {
        RestClient client = new RestClient();
        return client.addTask(task, params);
    }

    @Override
    public long addTask(Task task, ContentData data) {
        RestClient client = new RestClient();
        return client.addTask(task, data);
    }

    @Override
    public void activate(long taskId, String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void claim(long taskId, String userId) {
        RestClient restClient = new RestClient();
        restClient.claimTask(taskId, userId); 
    }

    @Override
    public void claim(long taskId, String userId, List<String> groupIds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void claimNextAvailable(String userId, String language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void claimNextAvailable(String userId, List<String> groupIds, String language) {
        throw new UnsupportedOperationException("Not supported yet.");        
    }

    @Override
    public void complete(long taskId, String userId, Map<String, Object> data) {
        RestClient restClient = new RestClient();
        restClient.completeTask(taskId, userId, data);
    }

    @Override
    public void delegate(long taskId, String userId, String targetUserId) {
        RestClient restClient = new RestClient();
        restClient.delegateTask(taskId, userId, targetUserId);        
    }

    @Override
    public void exit(long taskId, String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void fail(long taskId, String userId, Map<String, Object> faultData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void forward(long taskId, String userId, String targetEntityId) {
        RestClient restClient = new RestClient();
        restClient.forwardTask(taskId, userId, targetEntityId);
    }

    @Override
    public void release(long taskId, String userId) {
        RestClient restClient = new RestClient();
        restClient.releaseTask(taskId, userId);
    }

    @Override
    public void remove(long taskId, String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void resume(long taskId, String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void skip(long taskId, String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void start(long taskId, String userId) {
        RestClient restClient = new RestClient();
        restClient.startTask(taskId, userId);
    }

    @Override
    public void stop(long taskId, String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void suspend(long taskId, String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void nominate(long taskId, String userId, List<OrganizationalEntity> potentialOwners) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFault(long taskId, String userId, FaultData fault) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setOutput(long taskId, String userId, Object outputContentData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteFault(long taskId, String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteOutput(long taskId, String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPriority(long taskId, int priority) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        restClient.setPriority(taskId, priority);
    }

    @Override
    public void setTaskNames(long taskId, List<I18NText> taskNames) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        restClient.setTaskNames(taskId, taskNames);
    }

    @Override
    public void setExpirationDate(long taskId, Date date) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        restClient.setExpirationDate(taskId, date);
    }

    @Override
    public void setDescriptions(long taskId, List<I18NText> descriptions) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        restClient.setDescriptions(taskId, descriptions);
    }

    @Override
    public void setSkipable(long taskId, boolean skipable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSubTaskStrategy(long taskId, SubTasksStrategy strategy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getPriority(long taskId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Date getExpirationDate(long taskId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<I18NText> getDescriptions(long taskId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isSkipable(long taskId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SubTasksStrategy getSubTaskStrategy(long taskId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
