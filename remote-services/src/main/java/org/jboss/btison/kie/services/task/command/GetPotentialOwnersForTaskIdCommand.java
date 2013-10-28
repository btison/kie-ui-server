package org.jboss.btison.kie.services.task.command;

import java.util.List;
import java.util.Map;

import org.jbpm.services.task.commands.TaskCommand;
import org.jbpm.services.task.commands.TaskContext;
import org.kie.api.task.model.OrganizationalEntity;
import org.kie.internal.command.Context;

public class GetPotentialOwnersForTaskIdCommand extends TaskCommand<Map<Long, List<OrganizationalEntity>>> {
    
    private List<Long> taskIds;
    
    public GetPotentialOwnersForTaskIdCommand() {
        
    }
    
    public GetPotentialOwnersForTaskIdCommand(List<Long> taskIds) {
        this.taskIds = taskIds;
    }

    @Override
    public Map<Long, List<OrganizationalEntity>> execute(Context cntxt) {
        TaskContext context = (TaskContext) cntxt;
        if (context.getTaskService() != null) {
            return context.getTaskService().getPotentialOwnersForTaskIds(taskIds);
        }
        return null;
    }

    public void setTaskIds(List<Long> taskIds) {
        this.taskIds = taskIds;
    }

    public List<Long> getTaskIds() {
        return taskIds;
    }   

}
