package org.jboss.btison.kie.services.client.api.task;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jbpm.services.task.impl.model.xml.JaxbTask;
import org.kie.services.client.serialization.jaxb.JaxbCommandsRequest;
import org.kie.services.client.serialization.jaxb.JaxbCommandsResponse;
import org.kie.services.client.serialization.jaxb.impl.JaxbTaskSummaryListResponse;

@Path("/task")
public interface TaskClient {
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/execute")
    public JaxbCommandsResponse execute(JaxbCommandsRequest cmdsRequest);
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{taskId: [0-9-]+}")
    public JaxbTask getTaskInstanceInfo(@PathParam("taskId") long taskId);
    
    @GET
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/query")
    @VariableQuery
    public JaxbTaskSummaryListResponse query(Map<String, String> queryParameterMap);

}
