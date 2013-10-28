package org.jboss.btison.kie.services.task.command;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jboss.btison.kie.services.client.serialization.jaxb.impl.adapter.ObjectMapXmlAdapter;
import org.jboss.seam.transaction.Transactional;
import org.jbpm.services.task.commands.TaskCommand;
import org.jbpm.services.task.commands.TaskContext;
import org.kie.internal.command.Context;

@Transactional
public class AddContentCommand extends TaskCommand<Long> {
    
    @XmlElement
    @XmlJavaTypeAdapter(ObjectMapXmlAdapter.class)
    private Map<String, Object> content;
    
    public AddContentCommand() {
        
    }
    
    public AddContentCommand(long taskId, Map<String, Object> content) {
        this.taskId = taskId;
        this.content = content;
    }

    @Override
    public Long execute(Context cntxt) {
        TaskContext context = (TaskContext) cntxt;
        if (context.getTaskService() != null) {
            return context.getTaskService().addContent(taskId, content);
        }
        return null;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

}
