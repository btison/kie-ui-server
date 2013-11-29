package org.jboss.btison.kie.services.remote.cdi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.drools.core.command.impl.CommandBasedStatefulKnowledgeSession;
import org.drools.core.command.impl.GenericCommand;
import org.drools.persistence.SingleSessionCommandService;
import org.jboss.btison.kie.services.command.runtime.process.RuntimeDataServiceCommandContext;
import org.jboss.resteasy.spi.InternalServerErrorException;
import org.jbpm.kie.services.api.RuntimeDataService;
import org.jbpm.kie.services.impl.model.ProcessInstanceDesc;
import org.kie.api.command.Command;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.services.client.serialization.jaxb.impl.JaxbExceptionResponse;
import org.kie.services.remote.cdi.RuntimeManagerManager;
import org.kie.services.remote.cdi.TransactionalExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class RuntimeProcessRequestBean {
    
    private static final Logger logger = LoggerFactory.getLogger(RuntimeProcessRequestBean.class);
    
    @Inject
    private RuntimeDataService dataService;
    
    @Inject
    private RuntimeManagerManager runtimeMgrMgr;
    
    @Inject
    private TransactionalExecutor executor;
    
    public Object doKieSessionOperation(Command<?> cmd, Long processInstanceId, String errorMsg) {
        Object result = null;
        ProcessInstanceDesc piDesc;
        try {
            piDesc = dataService.getProcessInstanceById(processInstanceId);
            RuntimeEngine runtimeEngine = runtimeMgrMgr.getRuntimeEngine(piDesc.getDeploymentId(), processInstanceId);
            KieSession kieSession = runtimeEngine.getKieSession();
            SingleSessionCommandService sscs 
                = (SingleSessionCommandService) ((CommandBasedStatefulKnowledgeSession) kieSession).getCommandService();
            synchronized (sscs) { 
                result = executor.execute(kieSession, cmd);
            }
        } catch (Exception e) {
            if( e instanceof RuntimeException ) { 
                throw (RuntimeException) e;
            } else {
                throw new InternalServerErrorException(errorMsg, e);
            }
        }
        return result;
    }
    
    public Object doRuntimeDataServiceOperation(Command<?> cmd) {
        Object result = null;
        RuntimeDataServiceCommandContext context = new RuntimeDataServiceCommandContext(dataService);
        try {
            result =  ((GenericCommand<?>) cmd).execute(context); 
        } catch (Exception e) {
            JaxbExceptionResponse exceptResp = new JaxbExceptionResponse(e, cmd);
            logger.warn( "Unable to execute " + exceptResp.getCommandName() + " because of " + e.getClass().getSimpleName() + ": " + e.getMessage());
            logger.debug("Stack trace: \n", e);
            result = exceptResp;
        }
        return result;
    }
    
}
