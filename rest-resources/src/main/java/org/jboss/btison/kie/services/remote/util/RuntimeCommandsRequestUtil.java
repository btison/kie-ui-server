package org.jboss.btison.kie.services.remote.util;

import java.util.List;

import org.jboss.btison.kie.services.client.api.command.AcceptedCommands;
import org.jboss.btison.kie.services.client.serialization.jaxb.JaxbCommandsRequest;
import org.jboss.btison.kie.services.client.serialization.jaxb.JaxbCommandsResponse;
import org.jboss.btison.kie.services.command.runtime.process.RuntimeDataServiceCommand;
import org.jboss.btison.kie.services.remote.cdi.RuntimeProcessRequestBean;
import org.jboss.resteasy.spi.NotAcceptableException;
import org.kie.api.command.Command;
import org.kie.services.client.serialization.jaxb.impl.JaxbExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuntimeCommandsRequestUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(RuntimeCommandsRequestUtil.class);

    public static JaxbCommandsResponse restProcessJaxbCommandsRequest(JaxbCommandsRequest request, RuntimeProcessRequestBean requestBean) {
        // If exceptions are happening here, then there is something REALLY wrong and they should be thrown.
        JaxbCommandsResponse jaxbResponse = new JaxbCommandsResponse(request);
        List<Command<?>> commands = request.getCommands();

        if (commands != null) {
            for (int i = 0; i < commands.size(); ++i) {
                Command<?> cmd = commands.get(i);
                if (!AcceptedCommands.getSet().contains(cmd.getClass())) {
                    throw new NotAcceptableException("The execute REST operation does not accept " + cmd.getClass().getName()
                            + " instances.");
                }
                logger.debug("Processing command " + cmd.getClass().getSimpleName());
                Object cmdResult = null;
                if (cmd instanceof RuntimeDataServiceCommand<?>) {
                    cmdResult = requestBean.doRuntimeDataServiceOperation(cmd);
                } else {
                    cmdResult = requestBean.doKieSessionOperation(cmd, request.getProcessInstanceId());
                }
                if (cmdResult instanceof JaxbExceptionResponse) {
                    Exception e = ((JaxbExceptionResponse) cmdResult).cause;
                    jaxbResponse.addException(e, i, cmd);
                }
                if (cmdResult != null) {
                    try {
                        // addResult could possibly throw an exception, which is why it's here and not above
                        jaxbResponse.addResult(cmdResult, i, cmd);
                    } catch (Exception e) {
                        logger.error("Unable to add result from " + cmd.getClass().getSimpleName() + "/" + i + " because of "
                                + e.getClass().getSimpleName(), e);
                        jaxbResponse.addException(e, i, cmd);
                    }
                }
            }
        }

        if (commands == null || commands.isEmpty()) {
            logger.info("Commands request object with no commands sent!");
        }

        return jaxbResponse;
    }

}
