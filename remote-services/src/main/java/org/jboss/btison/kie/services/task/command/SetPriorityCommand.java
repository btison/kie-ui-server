package org.jboss.btison.kie.services.task.command;

import javax.xml.bind.annotation.XmlElement;

import org.jboss.seam.transaction.Transactional;
import org.jbpm.services.task.commands.TaskCommand;
import org.jbpm.services.task.commands.TaskContext;
import org.kie.internal.command.Context;

@Transactional
public class SetPriorityCommand extends TaskCommand<Void> {
    
    private int priority;
    
    public SetPriorityCommand() {       
    }
    
    public SetPriorityCommand(long taskId, int priority) {
        this.taskId = taskId;
        this.priority = priority;
    }

    @Override
    public Void execute(Context cntxt) {
        TaskContext context = (TaskContext) cntxt;
        if (context.getTaskService() != null) {
            context.getTaskService().setPriority(taskId, priority);
            return null;
        }
        return null;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
