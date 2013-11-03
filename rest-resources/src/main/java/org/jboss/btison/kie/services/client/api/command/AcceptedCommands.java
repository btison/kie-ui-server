package org.jboss.btison.kie.services.client.api.command;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.drools.core.command.runtime.process.AbortProcessInstanceCommand;
import org.drools.core.command.runtime.process.SetProcessInstanceVariablesCommand;
import org.drools.core.command.runtime.process.SignalEventCommand;
import org.jboss.btison.kie.services.command.runtime.process.GetNodeInstanceDescCommand;
import org.jboss.btison.kie.services.command.runtime.process.GetProcessInstanceDescCommand;
import org.jboss.btison.kie.services.command.runtime.process.GetVariableStateDescCommand;
import org.jboss.btison.kie.services.task.command.AddCommentCommand;
import org.jboss.btison.kie.services.task.command.AddContentCommand;
import org.jboss.btison.kie.services.task.command.DeleteCommentCommand;
import org.jboss.btison.kie.services.task.command.GetAllCommentsByTaskIdCommand;
import org.jboss.btison.kie.services.task.command.GetPotentialOwnersForTaskIdCommand;
import org.jboss.btison.kie.services.task.command.SetDescriptionsCommand;
import org.jboss.btison.kie.services.task.command.SetExpirationDateCommand;
import org.jboss.btison.kie.services.task.command.SetPriorityCommand;
import org.jboss.btison.kie.services.task.command.SetTaskNamesCommand;

@SuppressWarnings("rawtypes")
public class AcceptedCommands {

    private static Set<Class> acceptedCommands = new HashSet<Class>();
    static {
        
        acceptedCommands.add(AbortProcessInstanceCommand.class);
        acceptedCommands.add(SetProcessInstanceVariablesCommand.class);
        acceptedCommands.add(SignalEventCommand.class);
        
        acceptedCommands.add(SetPriorityCommand.class);
        acceptedCommands.add(SetExpirationDateCommand.class);
        acceptedCommands.add(SetDescriptionsCommand.class);
        acceptedCommands.add(SetTaskNamesCommand.class);
        acceptedCommands.add(AddCommentCommand.class);
        acceptedCommands.add(DeleteCommentCommand.class);
        acceptedCommands.add(GetAllCommentsByTaskIdCommand.class);
        acceptedCommands.add(GetPotentialOwnersForTaskIdCommand.class);
        acceptedCommands.add(AddContentCommand.class);
        
        acceptedCommands.add(SetProcessInstanceVariablesCommand.class);
        
        acceptedCommands.add(GetProcessInstanceDescCommand.class);
        
        acceptedCommands.add(GetNodeInstanceDescCommand.class);
        
        acceptedCommands.add(GetVariableStateDescCommand.class);

        acceptedCommands = Collections.unmodifiableSet(acceptedCommands);
    }

    public static Set<Class> getSet() {
        return acceptedCommands;
    }
}
