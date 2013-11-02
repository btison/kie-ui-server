package org.jboss.btison.kie.services.remote.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.drools.core.command.impl.GenericCommand;
import org.jboss.seam.transaction.Transactional;
import org.jbpm.kie.services.api.RuntimeDataService;
import org.jbpm.kie.services.impl.model.ProcessInstanceDesc;
import org.kie.api.command.Command;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.Context;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.kie.internal.runtime.manager.context.ProcessInstanceIdContext;
import org.kie.services.client.serialization.jaxb.impl.JaxbExceptionResponse;
import org.kie.services.remote.cdi.RuntimeManagerManager;
import org.kie.services.remote.exception.DomainNotFoundBadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Transactional
public class RuntimeProcessRequestBean {
    
    private static final Logger logger = LoggerFactory.getLogger(RuntimeProcessRequestBean.class);
    
    @Inject
    private RuntimeDataService dataService;
    
    @Inject
    private RuntimeManagerManager runtimeMgrMgr;
    
    public Object doKieSessionOperation(Command<?> cmd, Long processInstanceId) {
        Object result = null;
        RuntimeCommandContext context = new RuntimeCommandContext();
        ProcessInstanceDesc piDesc;
        try {
            piDesc = dataService.getProcessInstanceById(processInstanceId);
            KieSession kieSession = getRuntimeEngine(piDesc.getDeploymentId(), processInstanceId).getKieSession();
            context.setKieSession(kieSession);
            result =  ((GenericCommand<?>) cmd).execute(context);
        } catch (Exception e) {
            JaxbExceptionResponse exceptResp = new JaxbExceptionResponse(e, cmd);
            logger.warn( "Unable to execute " + exceptResp.getCommandName() + " because of " + e.getClass().getSimpleName() + ": " + e.getMessage());
            logger.debug("Stack trace: \n", e);
            result = exceptResp;
        }
        return result;
    }
    
    protected RuntimeEngine getRuntimeEngine(String domainName, Long processInstanceId) {
        RuntimeManager runtimeManager = runtimeMgrMgr.getRuntimeManager(domainName);
        Context<?> runtimeContext;
        if (processInstanceId != null) {
            runtimeContext = new ProcessInstanceIdContext(processInstanceId);
        } else {
            runtimeContext = EmptyContext.get();
        }
        if( runtimeManager == null ) { 
            throw new DomainNotFoundBadRequestException("No runtime manager could be found for domain '" + domainName + "'.");
        }
        return runtimeManager.getRuntimeEngine(runtimeContext);
    }


}
