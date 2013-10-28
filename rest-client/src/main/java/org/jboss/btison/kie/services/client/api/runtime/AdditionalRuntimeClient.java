package org.jboss.btison.kie.services.client.api.runtime;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.btison.kie.services.client.serialization.jaxb.JaxbCommandsRequest;
import org.jboss.btison.kie.services.client.serialization.jaxb.JaxbCommandsResponse;

@Path("additional/runtime")
public interface AdditionalRuntimeClient {
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{id: [a-zA-Z0-9-:\\.]+}/execute")
    public JaxbCommandsResponse execute(@PathParam("id") String deploymentId, JaxbCommandsRequest cmdsRequest);
    
}
