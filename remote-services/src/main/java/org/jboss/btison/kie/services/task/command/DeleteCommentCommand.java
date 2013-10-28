package org.jboss.btison.kie.services.task.command;

import javax.xml.bind.annotation.XmlElement;

import org.jboss.seam.transaction.Transactional;
import org.jbpm.services.task.commands.TaskCommand;
import org.jbpm.services.task.commands.TaskContext;
import org.jbpm.services.task.impl.model.CommentImpl;
import org.kie.internal.command.Context;

@Transactional
public class DeleteCommentCommand extends TaskCommand<Void> {
    
    private long commentId;

    public DeleteCommentCommand() {

    }
    
    public DeleteCommentCommand(long taskId, long commentId) {
        this.taskId = taskId;
        this.commentId = commentId;
    }

    @Override
    public Void execute(Context cntxt) {
        TaskContext context = (TaskContext) cntxt;
        if (context.getTaskService() != null) {
            context.getTaskService().deleteComment(taskId, commentId);;
            return null;
        }
        return null;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getCommentId() {
        return commentId;
    }
    
}
