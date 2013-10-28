package org.jboss.btison.kie.services.client.api.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.btison.kie.services.client.api.AbstractBaseRestClient;
import org.jboss.btison.kie.services.client.serialization.jaxb.JaxbCommandsRequest;
import org.jboss.btison.kie.services.client.serialization.jaxb.JaxbCommandsResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbCommentListResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbOrganizationalEntityMapResponse;
import org.jboss.btison.kie.services.task.command.AddCommentCommand;
import org.jboss.btison.kie.services.task.command.AddContentCommand;
import org.jboss.btison.kie.services.task.command.DeleteCommentCommand;
import org.jboss.btison.kie.services.task.command.GetAllCommentsByTaskIdCommand;
import org.jboss.btison.kie.services.task.command.GetPotentialOwnersForTaskIdCommand;
import org.jboss.btison.kie.services.task.command.SetDescriptionsCommand;
import org.jboss.btison.kie.services.task.command.SetExpirationDateCommand;
import org.jboss.btison.kie.services.task.command.SetPriorityCommand;
import org.jboss.btison.kie.services.task.command.SetTaskNamesCommand;
import org.jboss.resteasy.client.ProxyFactory;
import org.jbpm.services.task.commands.TaskCommand;
import org.jbpm.services.task.impl.model.CommentImpl;
import org.jbpm.services.task.impl.model.xml.JaxbComment;
import org.kie.api.task.model.Comment;
import org.kie.api.task.model.I18NText;
import org.kie.api.task.model.OrganizationalEntity;
import org.kie.services.client.serialization.jaxb.impl.JaxbCommandResponse;
import org.kie.services.client.serialization.jaxb.impl.JaxbPrimitiveResponse;

public class AdditionalRestClient extends AbstractBaseRestClient {
    
    public static class TaskClientHolder {
        public static AdditionalTaskClient taskClient = initTaskClient();
    }
    
    private static AdditionalTaskClient initTaskClient() {
        AdditionalTaskClient taskClient = ProxyFactory.create(AdditionalTaskClient.class, getTargetUrl(), getExecutor());
        return taskClient;
    }
    
    public void setPriority(long taskId, int priority) {
        TaskCommand<Void> command = new SetPriorityCommand(taskId, priority);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request); 
    }
    
    public void setExpirationDate(long taskId, Date date) {
        TaskCommand<Void> command = new SetExpirationDateCommand(taskId, date);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request); 
    }
    
    public void setDescriptions(long taskId, List<I18NText> descriptions) {
        TaskCommand<Void> command = new SetDescriptionsCommand(taskId, descriptions);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);         
    }
    
    public void setTaskNames(long taskId, List<I18NText> taskName) {
        TaskCommand<Void> command = new SetTaskNamesCommand(taskId, taskName);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
    }
    
    public long addComment(long taskId, Comment comment) {
        TaskCommand<Long> command = new AddCommentCommand(taskId, comment);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
        JaxbCommandResponse<?> resp = response.getResponses().get(0);
        if (resp instanceof JaxbPrimitiveResponse) {
            Object o = ((JaxbPrimitiveResponse)resp).getResult();
            return (Long)o;
        }
        return 0;
    }
    
    public void deleteComment(long taskId, long commentId) {
        TaskCommand<Void> command = new DeleteCommentCommand(taskId, commentId);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
    }
    
    public List<Comment> getAllCommentsByTaskId(long taskId) {
        TaskCommand<List<Comment>> command = new GetAllCommentsByTaskIdCommand(taskId);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
        JaxbCommandResponse<?> resp = response.getResponses().get(0);
        if (resp != null && resp instanceof JaxbCommentListResponse) {
            JaxbCommentListResponse commentResp = (JaxbCommentListResponse) resp;
            List<Comment> commentList = new ArrayList<Comment>();
            for (JaxbComment jaxbComment : commentResp.getResult()) {
                CommentImpl comment = new CommentImpl();
                comment.setAddedAt(jaxbComment.getAddedAt());
                comment.setId(jaxbComment.getId());
                comment.setText(jaxbComment.getText());
                comment.setAddedBy(jaxbComment.getAddedBy());
                commentList.add(comment);
            }
            return commentList;            
        }
        return new ArrayList<Comment>();
    }
    
    public Map<Long, List<OrganizationalEntity>> getPotentialOwnersForTaskIds(List<Long> taskIds) {
        TaskCommand<Map<Long, List<OrganizationalEntity>>> command = new GetPotentialOwnersForTaskIdCommand(taskIds);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
        JaxbCommandResponse<?> resp = response.getResponses().get(0);
        if (resp != null && resp instanceof JaxbOrganizationalEntityMapResponse) {
            JaxbOrganizationalEntityMapResponse orgEntityMap = (JaxbOrganizationalEntityMapResponse) resp;
            return orgEntityMap.getResult();
        }
        return new HashMap<Long, List<OrganizationalEntity>>();
    }
    
    public long addContent(long taskId, Map<String, Object> content) {
        TaskCommand<Long> command = new AddContentCommand(taskId, content);
        JaxbCommandsRequest request = new JaxbCommandsRequest(null, command);
        JaxbCommandsResponse response = TaskClientHolder.taskClient.execute(request);
        JaxbCommandResponse<?> resp = response.getResponses().get(0);
        if (resp instanceof JaxbPrimitiveResponse) {
            Object o = ((JaxbPrimitiveResponse)resp).getResult();
            return (Long)o;
        }
        return 0;
    }
}
