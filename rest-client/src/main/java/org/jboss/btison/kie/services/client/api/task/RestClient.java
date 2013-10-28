package org.jboss.btison.kie.services.client.api.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.btison.kie.services.client.api.AbstractBaseRestClient;
import org.jboss.resteasy.client.ProxyFactory;
import org.jbpm.services.task.commands.AddTaskCommand;
import org.jbpm.services.task.commands.ClaimTaskCommand;
import org.jbpm.services.task.commands.CompleteTaskCommand;
import org.jbpm.services.task.commands.DelegateTaskCommand;
import org.jbpm.services.task.commands.ForwardTaskCommand;
import org.jbpm.services.task.commands.GetTaskAssignedAsPotentialOwnerCommand;
import org.jbpm.services.task.commands.GetTasksOwnedCommand;
import org.jbpm.services.task.commands.ReleaseTaskCommand;
import org.jbpm.services.task.commands.StartTaskCommand;
import org.jbpm.services.task.commands.TaskCommand;
import org.jbpm.services.task.impl.model.AttachmentImpl;
import org.jbpm.services.task.impl.model.CommentImpl;
import org.jbpm.services.task.impl.model.PeopleAssignmentsImpl;
import org.jbpm.services.task.impl.model.TaskDataImpl;
import org.jbpm.services.task.impl.model.TaskImpl;
import org.jbpm.services.task.impl.model.xml.JaxbAttachment;
import org.jbpm.services.task.impl.model.xml.JaxbComment;
import org.jbpm.services.task.impl.model.xml.JaxbTask;
import org.kie.api.task.model.Attachment;
import org.kie.api.task.model.Comment;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.task.api.model.ContentData;
import org.kie.internal.task.api.model.InternalAttachment;
import org.kie.internal.task.api.model.InternalComment;
import org.kie.internal.task.api.model.InternalPeopleAssignments;
import org.kie.internal.task.api.model.InternalTask;
import org.kie.internal.task.api.model.InternalTaskData;
import org.kie.services.client.serialization.jaxb.JaxbCommandsRequest;
import org.kie.services.client.serialization.jaxb.JaxbCommandsResponse;
import org.kie.services.client.serialization.jaxb.impl.JaxbPrimitiveResponse;
import org.kie.services.client.serialization.jaxb.impl.JaxbTaskSummaryListResponse;

public class RestClient extends AbstractBaseRestClient {
    
    public static class TaskClientHolder {
        public static TaskClient taskClient = initTaskClient();
    }
    
    private static TaskClient initTaskClient() {
        TaskClient taskClient = ProxyFactory.create(TaskClient.class, getTargetUrl(), getExecutor());
        return taskClient;
    }
    
    public void claimTask(long taskId, String userId) {                       
        TaskCommand<Void> command = new ClaimTaskCommand(taskId, userId);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);        
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
    }
    
    public void startTask(long taskId, String userId) {               
        TaskCommand<Void> command = new StartTaskCommand(taskId, userId);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);        
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
    }
    
    public void releaseTask(long taskId, String userId) {        
        TaskCommand<Void> command = new ReleaseTaskCommand(taskId, userId);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);        
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
    }
    
    public Long addTask(Task task, Map<String, Object> params) {        
        TaskCommand<Long> command = new AddTaskCommand(task, params);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
        
        if (response.getResponses().get(0) instanceof JaxbPrimitiveResponse) {
            return (Long) ((JaxbPrimitiveResponse)response.getResponses().get(0)).getResult();
        }
        return new Long(0);
    }
    
    public Long addTask(Task task, ContentData data) {        
        TaskCommand<Long> command = new AddTaskCommand(task, data);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
        
        if (response.getResponses().get(0) instanceof JaxbPrimitiveResponse) {
            return (Long) ((JaxbPrimitiveResponse)response.getResponses().get(0)).getResult();
        }        
        return new Long(0);
    }
    
    public void forwardTask(long taskId, String userId, String targetEntityId) {
        TaskCommand<Void> command = new ForwardTaskCommand(taskId, userId, targetEntityId);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
    }
    
    public void delegateTask(long taskId, String userId, String targetUserId) {
        TaskCommand<Void> command = new DelegateTaskCommand(taskId, userId, targetUserId);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
    }
    
    public void completeTask(long taskId, String userId, Map<String, Object> data) {
        TaskCommand<Void> command = new CompleteTaskCommand(taskId, userId, data);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
    }
    
    public Task getTaskById(long taskId) {        
        JaxbTask jaxbTask = TaskClientHolder.taskClient.getTaskInstanceInfo(taskId);  
        InternalTask task = new TaskImpl();
        task.setDescriptions(jaxbTask.getDescriptions());
        task.setId(jaxbTask.getId());
        task.setPriority(jaxbTask.getPriority());
        task.setNames(jaxbTask.getNames());
        task.setSubjects(jaxbTask.getSubjects());
        task.setTaskType(jaxbTask.getTaskType());
        
        InternalPeopleAssignments peopleAssignments = new PeopleAssignmentsImpl();
        peopleAssignments.setBusinessAdministrators(jaxbTask.getPeopleAssignments().getBusinessAdministrators());
        peopleAssignments.setPotentialOwners(jaxbTask.getPeopleAssignments().getPotentialOwners());        
        peopleAssignments.setTaskInitiator(jaxbTask.getPeopleAssignments().getTaskInitiator());
        task.setPeopleAssignments(peopleAssignments);
        
        InternalTaskData taskData = new TaskDataImpl();
        task.setTaskData(taskData);
        taskData.setActivationTime(jaxbTask.getTaskData().getActivationTime());
        taskData.setActualOwner(jaxbTask.getTaskData().getActualOwner());
        taskData.setCreatedBy(jaxbTask.getTaskData().getCreatedBy());
        taskData.setCreatedOn(jaxbTask.getTaskData().getCreatedOn());
        taskData.setDeploymentId(jaxbTask.getTaskData().getDeploymentId());
        taskData.setDocumentContentId(jaxbTask.getTaskData().getDocumentContentId());
        taskData.setDocumentType(jaxbTask.getTaskData().getDocumentType());
        taskData.setExpirationTime(jaxbTask.getTaskData().getExpirationTime());
        taskData.setFaultContentId(jaxbTask.getTaskData().getFaultContentId());
        taskData.setFaultName(jaxbTask.getTaskData().getFaultName());
        taskData.setFaultType(jaxbTask.getTaskData().getFaultType());
        taskData.setOutputContentId(jaxbTask.getTaskData().getOutputContentId());
        taskData.setOutputType(jaxbTask.getTaskData().getOutputType());
        taskData.setParentId(jaxbTask.getTaskData().getParentId());
        taskData.setPreviousStatus(jaxbTask.getTaskData().getPreviousStatus());
        taskData.setProcessId(jaxbTask.getTaskData().getProcessId());
        taskData.setProcessInstanceId(jaxbTask.getTaskData().getProcessInstanceId());
        taskData.setProcessSessionId(jaxbTask.getTaskData().getProcessSessionId());
        taskData.setSkipable(jaxbTask.getTaskData().isSkipable());
        taskData.setStatus(jaxbTask.getTaskData().getStatus());
        taskData.setWorkItemId(jaxbTask.getTaskData().getWorkItemId());
        
        List<Attachment> attachments = new ArrayList<Attachment>();
        taskData.setAttachments(attachments);
        for (Attachment att : jaxbTask.getTaskData().getAttachments()) {
            JaxbAttachment jaxbAttachment = (JaxbAttachment) att;
            InternalAttachment attachment = new AttachmentImpl();
            attachment.setAttachedAt(jaxbAttachment.getAttachedAt());
            attachment.setAttachedBy(jaxbAttachment.getAttachedBy());
            attachment.setAttachmentContentId(jaxbAttachment.getAttachmentContentId());
            attachment.setContentType(jaxbAttachment.getContentType());
            attachment.setId(jaxbAttachment.getId());
            attachment.setName(jaxbAttachment.getName());
            attachment.setSize(jaxbAttachment.getSize());
            attachments.add(attachment);
        }
        
        List<Comment> comments = new ArrayList<Comment>();
        taskData.setComments(comments);
        for (Comment cmnt : jaxbTask.getTaskData().getComments()) {
            JaxbComment jaxbComment = (JaxbComment) cmnt;
            InternalComment comment = new CommentImpl();
            comment.setAddedAt(jaxbComment.getAddedAt());
            comment.setAddedBy(jaxbComment.getAddedBy());
            comment.setId(jaxbComment.getId());
            comment.setText(jaxbComment.getText());
        }
                
        return task;
    }
    
    public List<TaskSummary> queryTask(Map<String, String[]> parameters) {        
        //concatenate parameters
        Map<String, String> concatenatedParameters = new HashMap<String, String>();
        for (Map.Entry<String, String[]> entry : parameters.entrySet() ) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < entry.getValue().length; i++) {
                sb.append(entry.getValue()[i]);
                if (i < entry.getValue().length -1) {
                    sb.append(",");
                }
            }
            concatenatedParameters.put(entry.getKey(), sb.toString());
        }
        
        JaxbTaskSummaryListResponse response = TaskClientHolder.taskClient.query(concatenatedParameters);
        return response.getResult();
    }
    
    public List<TaskSummary> getTasksAssignedAsPotentialOwnerByStatus(String userId, List<Status> status, String language) {
        TaskCommand<List<TaskSummary>> command = new GetTaskAssignedAsPotentialOwnerCommand(userId, language, status);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
        if (!response.getResponses().isEmpty()  && response.getResponses().get(0) instanceof JaxbTaskSummaryListResponse) {
            JaxbTaskSummaryListResponse taskSummaryListResponse = (JaxbTaskSummaryListResponse) response.getResponses().get(0);
            return taskSummaryListResponse.getResult();
        }
        return new ArrayList<TaskSummary>();
    }
    
    public List<TaskSummary> getTasksOwnedByStatus(String userId, List<Status> status, String language) {
        TaskCommand<List<TaskSummary>> command = new GetTasksOwnedCommand(userId, language, status);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
        if (!response.getResponses().isEmpty()  && response.getResponses().get(0) instanceof JaxbTaskSummaryListResponse) {
            JaxbTaskSummaryListResponse taskSummaryListResponse = (JaxbTaskSummaryListResponse) response.getResponses().get(0);
            return taskSummaryListResponse.getResult();
        }
        return new ArrayList<TaskSummary>();        
    }

}
