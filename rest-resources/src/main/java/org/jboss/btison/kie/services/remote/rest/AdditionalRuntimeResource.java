package org.jboss.btison.kie.services.remote.rest;

import static org.jboss.btison.kie.services.remote.util.RuntimeCommandsRequestUtil.restProcessJaxbCommandsRequest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.btison.kie.services.client.serialization.jaxb.JaxbCommandsRequest;
import org.jboss.btison.kie.services.client.serialization.jaxb.JaxbCommandsResponse;
import org.jboss.btison.kie.services.remote.cdi.RuntimeProcessRequestBean;


@RequestScoped
@Path("additional/runtime/")
public class AdditionalRuntimeResource {
    
    @Inject
    private RuntimeProcessRequestBean runtimeProcessRequestBean;

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/execute")
    public JaxbCommandsResponse execute(JaxbCommandsRequest cmdsRequest) {
        return restProcessJaxbCommandsRequest(cmdsRequest, runtimeProcessRequestBean);
    }

}
