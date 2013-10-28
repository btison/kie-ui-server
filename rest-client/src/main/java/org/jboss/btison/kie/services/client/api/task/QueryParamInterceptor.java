package org.jboss.btison.kie.services.client.api.task;

import java.lang.reflect.Method;
import java.util.Map;

import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ClientInterceptor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.spi.interception.AcceptedByMethod;
import org.jboss.resteasy.spi.interception.ClientExecutionContext;
import org.jboss.resteasy.spi.interception.ClientExecutionInterceptor;

@Provider
@ClientInterceptor
public class QueryParamInterceptor implements ClientExecutionInterceptor, AcceptedByMethod {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public ClientResponse execute(ClientExecutionContext ctx) throws Exception {
        ClientRequest request = ctx.getRequest();

        if ( request.getBody() != null ) {
            Map<String, String> map = (Map<String, String>) request.getBody();
            for ( Map.Entry<String, String> entry: map.entrySet() ) {
                String[] valueArray = entry.getValue().split(",");
                for (String value : valueArray) {
                    request = request.queryParameter( entry.getKey(), value );
                }
            }
            request.body( null, null, null, null, null );
        } 
        return ctx.proceed();        
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean accept(Class declaring, Method method) {
        boolean accept = method.isAnnotationPresent(VariableQuery.class);
        return accept;
    }

}
