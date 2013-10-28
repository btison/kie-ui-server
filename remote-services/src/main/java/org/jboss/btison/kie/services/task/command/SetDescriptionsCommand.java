package org.jboss.btison.kie.services.task.command;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jboss.seam.transaction.Transactional;
import org.jbpm.services.task.commands.TaskCommand;
import org.jbpm.services.task.commands.TaskContext;
import org.jbpm.services.task.impl.model.xml.adapter.I18NTextXmlAdapter;
import org.kie.api.task.model.I18NText;
import org.kie.internal.command.Context;

@Transactional
public class SetDescriptionsCommand extends TaskCommand<Void> {
    
    @XmlElement(name="descriptions")
    @XmlJavaTypeAdapter(value=I18NTextXmlAdapter.class)
    private List<I18NText> descriptions;
    
    public SetDescriptionsCommand() {
    }
    
    public SetDescriptionsCommand(long taskId, List<I18NText> descriptions) {
        this.taskId = taskId;
        this.descriptions = descriptions;
    }

    @Override
    public Void execute(Context cntxt) {
        TaskContext context = (TaskContext) cntxt;
        if (context.getTaskService() != null) {
            context.getTaskService().setDescriptions(taskId, descriptions);
            return null;
        }
        return null;
    }

    public void setDescriptions(List<I18NText> descriptions) {
        this.descriptions = descriptions;
    }
    
    

}
