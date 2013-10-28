package org.jboss.btison.kie.services.task.command;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.jboss.seam.transaction.Transactional;
import org.jbpm.services.task.commands.TaskCommand;
import org.jbpm.services.task.commands.TaskContext;
import org.jbpm.services.task.impl.model.xml.JaxbComment;
import org.kie.api.task.model.Comment;
import org.kie.internal.command.Context;

public class GetAllCommentsByTaskIdCommand extends TaskCommand<List<Comment>> {
    
    public GetAllCommentsByTaskIdCommand() {
    }
    
    public GetAllCommentsByTaskIdCommand(long taskId) {
        this.taskId = taskId;
    }

    @Override
    public List<Comment> execute(Context cntxt) {
        TaskContext context = (TaskContext) cntxt;
        if (context.getTaskService() != null) {
            return context.getTaskService().getAllCommentsByTaskId(taskId);
        }
        return null;
    }  

}
