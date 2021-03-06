package org.jboss.btison.kie.services.task.commands;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.jboss.btison.kie.services.task.impl.TaskServiceEntryPointImpl;
import org.jbpm.services.task.annotations.Internal;
import org.jbpm.services.task.lifecycle.listeners.TaskLifeCycleEventListener;
import org.jbpm.shared.services.api.JbpmServicesPersistenceManager;
import org.kie.api.task.model.Task;
import org.kie.internal.command.Context;
import org.kie.internal.command.World;
import org.kie.internal.task.api.TaskAttachmentService;
import org.kie.internal.task.api.TaskContentService;
import org.kie.internal.task.api.TaskDefService;
import org.kie.internal.task.api.TaskIdentityService;
import org.kie.internal.task.api.TaskQueryService;
import org.kie.internal.task.api.UserGroupCallback;

public class TaskContext implements Context {
    
    @Inject 
    private JbpmServicesPersistenceManager pm;
    @Inject
    private TaskDefService taskDefService;
    @Inject
    private TaskQueryService taskQueryService;
    @Inject
    private TaskIdentityService taskIdentityService;
    @Inject
    private TaskContentService taskContentService;
    @Inject
    private TaskAttachmentService taskAttachmentService;
    @Inject 
    private Event<Task> taskEvents;
    @Inject @Internal
    private TaskLifeCycleEventListener eventListener;
    @Inject 
    private UserGroupCallback userGroupCallback;
    
    private TaskServiceEntryPointImpl taskService;
    
    public TaskContext() {
    }
    
    public TaskContext(TaskServiceEntryPointImpl taskService) {
        this.taskService = taskService;
    }
    
    public TaskServiceEntryPointImpl getTaskService() {
        return taskService;
    }

    public JbpmServicesPersistenceManager getPm() {
        return pm;
    }

    public void setPm(JbpmServicesPersistenceManager pm) {
        this.pm = pm;
    }
    
    public TaskDefService getTaskDefService() {
        return taskDefService;
    }

    public void setTaskDefService(TaskDefService taskDefService) {
        this.taskDefService = taskDefService;
    }

    public TaskQueryService getTaskQueryService() {
        return taskQueryService;
    }

    public TaskContentService getTaskContentService() {
        return taskContentService;
    }

    public void setTaskContentService(TaskContentService taskContentService) {
        this.taskContentService = taskContentService;
    }
    
    public TaskAttachmentService getTaskAttachmentService() {
        return taskAttachmentService;
    }

    public void setTaskAttachmentService(TaskAttachmentService taskAttachmentService) {
        this.taskAttachmentService = taskAttachmentService;
    }
    
    public void setTaskQueryService(TaskQueryService taskQueryService) {
        this.taskQueryService = taskQueryService;
    }

    public TaskIdentityService getTaskIdentityService() {
        return taskIdentityService;
    }

    public void setTaskIdentityService(TaskIdentityService taskIdentityService) {
        this.taskIdentityService = taskIdentityService;
    }

    public Event<Task> getTaskEvents() {
        return taskEvents;
    }

    public void setTaskEvents(Event<Task> taskEvents) {
        this.taskEvents = taskEvents;
    }

    public TaskLifeCycleEventListener getEventListener() {
        return eventListener;
    }

    public UserGroupCallback getUserGroupCallback() {
        return userGroupCallback;
    }

    public void setUserGroupCallback(UserGroupCallback userGroupCallback) {
        this.userGroupCallback = userGroupCallback;
    }
    
    public void setEventListener(TaskLifeCycleEventListener eventListener) {
        this.eventListener = eventListener;
    }    
    
    public World getContextManager() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object get(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void set(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void remove(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
