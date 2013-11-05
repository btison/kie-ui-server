package org.jboss.btison.kie.services.task.impl;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.btison.kie.services.client.api.task.AdditionalRestClient;
import org.jboss.seam.transaction.Transactional;
import org.kie.api.task.model.Comment;
import org.kie.internal.task.api.TaskCommentService;

@Transactional
@ApplicationScoped
public class TaskCommentServiceImpl implements TaskCommentService {

    @Override
    public long addComment(long taskId, Comment comment) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        return restClient.addComment(taskId, comment);
    }

    @Override
    public void deleteComment(long taskId, long commentId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        restClient.deleteComment(taskId, commentId);
    }

    @Override
    public List<Comment> getAllCommentsByTaskId(long taskId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        return restClient.getAllCommentsByTaskId(taskId);
    }

    @Override
    public Comment getCommentById(long commentId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
