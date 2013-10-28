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
public class SetTaskNamesCommand extends TaskCommand<Void> {
    
    @XmlElement(name="tasknames")
    @XmlJavaTypeAdapter(value=I18NTextXmlAdapter.class)
    private List<I18NText> taskName;
    
    public SetTaskNamesCommand() {
    }
    
    public SetTaskNamesCommand(long taskId, List<I18NText> taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }     
    
    @Override
    public Void execute(Context cntxt) {
        TaskContext context = (TaskContext) cntxt;
        if (context.getTaskService() != null) {
            context.getTaskService().setTaskNames(taskId, taskName);
            return null;
        }
        return null;
    }

    public void setTaskName(List<I18NText> taskName) {
        this.taskName = taskName;
    }

}
