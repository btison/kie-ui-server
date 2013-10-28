package org.jboss.btison.kie.services.task.command;

import javax.xml.bind.annotation.XmlElement;

import org.jboss.seam.transaction.Transactional;
import org.jbpm.services.task.commands.TaskCommand;
import org.jbpm.services.task.commands.TaskContext;
import org.jbpm.services.task.impl.model.CommentImpl;
import org.jbpm.services.task.impl.model.UserImpl;
import org.jbpm.services.task.impl.model.xml.JaxbComment;
import org.kie.api.task.model.Comment;
import org.kie.internal.command.Context;

@Transactional
public class AddCommentCommand extends TaskCommand<Long> {
    
    @XmlElement(name="comment")
    private JaxbComment jaxbComment;
    
    private Comment comment;

    public AddCommentCommand() {
    }
    
    public AddCommentCommand(long taskId, Comment comment) {
        this.taskId = taskId;
        setComment(comment);
    }

    @Override
    public Long execute(Context cntxt) {
        TaskContext context = (TaskContext) cntxt;
        if (context.getTaskService() != null) {
            if (comment == null) {
                comment = jaxbComment;
            }
            if (comment instanceof JaxbComment) {
                CommentImpl commentImpl = new CommentImpl();
                commentImpl.setAddedAt(jaxbComment.getAddedAt());
                commentImpl.setId(jaxbComment.getId());
                commentImpl.setText(jaxbComment.getText());
                commentImpl.setAddedBy(jaxbComment.getAddedBy());
                return context.getTaskService().addComment(taskId, commentImpl);
            } else {
                return context.getTaskService().addComment(taskId, comment);
            }
        }
        return null;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
        if (comment instanceof JaxbComment) {
            this.jaxbComment = (JaxbComment) comment;
        } else {
            this.jaxbComment = new JaxbComment(comment);
        }
    }

}
