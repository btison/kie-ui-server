package org.jboss.btison.kie.services.task.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.btison.kie.services.client.api.task.AdditionalRestClient;
import org.jboss.seam.transaction.Transactional;
import org.kie.api.task.model.Content;
import org.kie.api.task.model.Task;
import org.kie.internal.task.api.ContentMarshallerContext;
import org.kie.internal.task.api.TaskContentService;

@Transactional
@ApplicationScoped
public class TaskContentServiceImpl implements TaskContentService {
    
    private ConcurrentHashMap<String, ContentMarshallerContext> marhsalContexts = new ConcurrentHashMap<String, ContentMarshallerContext>();

    @Override
    public long addContent(long taskId, Content content) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long addContent(long taskId, Map<String, Object> params) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        return restClient.addContent(taskId, params);
    }

    @Override
    public void deleteContent(long taskId, long contentId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Content> getAllContentByTaskId(long taskId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Content getContentById(long contentId) {
        AdditionalRestClient additionalRestClient = new AdditionalRestClient();
        return additionalRestClient.getContentById(contentId);
    }

    @Override
    public void addMarshallerContext(String ownerId, ContentMarshallerContext context) {
        this.marhsalContexts.put(ownerId, context);
    }

    @Override
    public void removeMarshallerContext(String ownerId) {
        this.marhsalContexts.remove(ownerId);
    }   

    public ContentMarshallerContext getMarshallerContext(Task task) {
        if (task.getTaskData().getDeploymentId() != null && this.marhsalContexts.containsKey(task.getTaskData().getDeploymentId())) {
            return this.marhsalContexts.get(task.getTaskData().getDeploymentId());
        }
        
        return new ContentMarshallerContext();
    }
    

}
