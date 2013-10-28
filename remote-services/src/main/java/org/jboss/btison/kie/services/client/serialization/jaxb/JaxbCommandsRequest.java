package org.jboss.btison.kie.services.client.serialization.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import org.drools.core.command.runtime.process.SetProcessInstanceVariablesCommand;
import org.jboss.btison.kie.services.task.command.AddCommentCommand;
import org.jboss.btison.kie.services.task.command.AddContentCommand;
import org.jboss.btison.kie.services.task.command.DeleteCommentCommand;
import org.jboss.btison.kie.services.task.command.GetAllCommentsByTaskIdCommand;
import org.jboss.btison.kie.services.task.command.GetPotentialOwnersForTaskIdCommand;
import org.jboss.btison.kie.services.task.command.SetDescriptionsCommand;
import org.jboss.btison.kie.services.task.command.SetExpirationDateCommand;
import org.jboss.btison.kie.services.task.command.SetPriorityCommand;
import org.jboss.btison.kie.services.task.command.SetTaskNamesCommand;
import org.jbpm.services.task.commands.TaskCommand;
import org.kie.api.command.Command;

@XmlRootElement(name = "command-request")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbCommandsRequest {

    @XmlElement(name = "deployment-id")
    @XmlSchemaType(name = "string")
    private String deploymentId;

    @XmlElement(name = "process-instance-id")
    @XmlSchemaType(name = "long")
    private Long processInstanceId;

    @XmlElement(name = "ver")
    @XmlSchemaType(name = "int")
    private Integer version = 1;

    @XmlElements({
            @XmlElement(name = "task-set-priority", type = SetPriorityCommand.class),
            @XmlElement(name = "task-set-expiration", type = SetExpirationDateCommand.class),
            @XmlElement(name = "task-set-description", type = SetDescriptionsCommand.class), 
            @XmlElement(name = "task-set-names", type = SetTaskNamesCommand.class),
            @XmlElement(name = "task-add-comment", type = AddCommentCommand.class),
            @XmlElement(name = "task-delete-comment", type = DeleteCommentCommand.class),
            @XmlElement(name = "task-get-all-comments", type = GetAllCommentsByTaskIdCommand.class),
            @XmlElement(name = "get-potential-owners", type = GetPotentialOwnersForTaskIdCommand.class),
            @XmlElement(name = "task-add-content", type = AddContentCommand.class),
            
            @XmlElement(name = "set-process-instance-variable", type=SetProcessInstanceVariablesCommand.class)})
    protected List<Command<?>> commands;

    public JaxbCommandsRequest() {
        // Default constructor
    }

    public JaxbCommandsRequest(Command<?> command) {
        this.commands = new ArrayList<Command<?>>();
        this.commands.add(command);
        checkThatCommandsAreTaskCommands(commands);
    }
    
    public JaxbCommandsRequest(List<Command<?>> commands) {
        this.commands = new ArrayList<Command<?>>(); 
        this.commands.addAll(commands);
        checkThatCommandsAreTaskCommands(commands);
    }

    private void checkThatCommandsAreTaskCommands(List<Command<?>> commands) {
        for( Command<?> command : commands ) { 
           if( ! (command instanceof TaskCommand<?>) ) { 
               throw new UnsupportedOperationException( "Only commands for the task service are supported when leaving out the deployment id (" + command.getClass().getSimpleName()  + ")" );
           }
        }
    }
    
    public JaxbCommandsRequest(String deploymentId, Command<?> command) {
        this.deploymentId = deploymentId;
        this.commands = new ArrayList<Command<?>>();
        this.commands.add(command);
    }

    public JaxbCommandsRequest(String deploymentId, List<Command<?>> commands) {
        this.deploymentId = deploymentId;
        this.commands = new ArrayList<Command<?>>();
        this.commands.addAll(commands);
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public Long getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(Long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setCommands(List<Command<?>> commands) {
        this.commands = commands;
    }

    public List<Command<?>> getCommands() {
        return this.commands;
    }
    
    @SuppressWarnings("rawtypes")
    public String toString() {
        String result = "JaxbCommandsRequest " + deploymentId + "\n";
        if (commands != null) {
            for (Command command: commands) {
                result += command + "\n";
            }
        }
        return result;
    }

}
