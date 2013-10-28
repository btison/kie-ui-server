package org.jboss.btison.kie.services.client.api;

import java.util.UUID;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.jboss.btison.kie.services.client.api.task.QueryParamInterceptor;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public abstract class AbstractBaseRestClient {
    
    private static final String KIE_EXEC_SERVER_HOSTNAME = "kie.exec.server.rest.hostname";
    private static final String KIE_EXEC_SERVER_PORT = "kie.exec.server.rest.port";
    private static final String KIE_EXEC_SERVER_SCHEME = "kie.exec.server.rest.scheme";
    private static final String KIE_EXEC_SERVER_USERNAME = "kie.exec.server.rest.username";
    private static final String KIE_EXEC_SERVER_PASSWORD = "kie.exec.server.rest.password";
    private static final String KIE_EXEC_SERVER_BASE_URL = "kie.exec.server.rest.base.url";
    
    public static HttpHost host;
    
    public static String targetUrl;
    
    static {
        ResteasyProviderFactory factory = ResteasyProviderFactory.getInstance();
        factory.getClientExecutionInterceptorRegistry().register(QueryParamInterceptor.class);
        RegisterBuiltin.register(factory);
    }
    
    protected static HttpHost getHost() {
        if (host == null) {
            host = new HttpHost(System.getProperty(KIE_EXEC_SERVER_HOSTNAME), Integer.parseInt(System.getProperty(KIE_EXEC_SERVER_PORT,"-1")),System.getProperty(KIE_EXEC_SERVER_SCHEME,HttpHost.DEFAULT_SCHEME_NAME));
        }   
        return host;
    }
    
    protected static ClientExecutor getExecutor() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), new UsernamePasswordCredentials(System.getProperty(KIE_EXEC_SERVER_USERNAME), System.getProperty(KIE_EXEC_SERVER_PASSWORD)));
        
        BasicScheme basicAuth = new BasicScheme();
        BasicHttpContext httpContext = new BasicHttpContext();
        httpContext.setAttribute(ClientContext.CREDS_PROVIDER, credentialsProvider);
        String contextId = UUID.randomUUID().toString();
        httpContext.setAttribute(contextId, basicAuth);
        
        ClientConnectionManager cm = new PoolingClientConnectionManager();
        DefaultHttpClient httpClient = new DefaultHttpClient(cm);
        ApacheHttpClient4Executor executor = new ApacheHttpClient4Executor(httpClient, httpContext);
        return executor;
    }
    
    protected static String getTargetUrl() {
        if (targetUrl == null) {
            StringBuffer sb = new StringBuffer();
            sb.append(getHost().getSchemeName()).append("://").append(getHost().getHostName());
            if (getHost().getPort() > 0) {
                sb.append(":").append(getHost().getPort());
            }
            sb.append(System.getProperty(KIE_EXEC_SERVER_BASE_URL));
            targetUrl =  sb.toString();
        }
        return targetUrl;
    }
    
    
    
    

}
