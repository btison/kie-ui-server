package org.jboss.btison.kie.services.task.command;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

import org.jboss.seam.transaction.Transactional;
import org.jbpm.services.task.commands.TaskCommand;
import org.jbpm.services.task.commands.TaskContext;
import org.kie.internal.command.Context;

@Transactional
public class SetExpirationDateCommand extends TaskCommand<Void> {
    
    @XmlElement(name = "expiration")
    @XmlSchemaType(name = "dateTime")
    private Date date;
    
    public SetExpirationDateCommand() {
    }
    
    public SetExpirationDateCommand(long taskId, Date date) {
        this.taskId = taskId;
        this.date = date;
    }    

    @Override
    public Void execute(Context cntxt) {
        TaskContext context = (TaskContext) cntxt;
        if (context.getTaskService() != null) {
            context.getTaskService().setExpirationDate(taskId, date);
            return null;
        }
        return null;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
