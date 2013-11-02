/*
 * Copyright 2012 JBoss by Red Hat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.btison.kie.console.ng.bd.backend.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.btison.kie.services.client.api.runtime.AdditionalRestClient;
import org.jboss.btison.kie.services.client.api.runtime.RestClient;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.seam.transaction.Transactional;
import org.jbpm.console.ng.bd.service.KieSessionEntryPoint;

@Service
@ApplicationScoped
@Transactional
public class KieSessionEntryPointImpl implements KieSessionEntryPoint {
    
    @Override
    public long startProcess(String domainName, String processId) {
        RestClient restClient = new RestClient();
        return restClient.startProcess(domainName, processId, new HashMap<String, String>());
    }

    @Override
    public long startProcess(String domainName, String processId, Map<String, String> params) {
        RestClient restClient = new RestClient();
        return restClient.startProcess(domainName, processId, params);
    }

    @Override
    public void abortProcessInstance(long processInstanceId) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        restClient.abortProcessInstance(processInstanceId);
    }
    
    @Override
    public void abortProcessInstances(List<Long> processInstanceIds) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        for (long processInstanceId : processInstanceIds) {
            restClient.abortProcessInstance(processInstanceId);
        }
    }

    @Override
    public void suspendProcessInstance(long processInstanceId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void signalProcessInstance(long processInstanceId, String signalName, Object event) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        restClient.signalProcessInstance(processInstanceId, signalName, event);
    }
    
    @Override
    public void signalProcessInstances(List<Long> processInstanceIds, String signalName, Object event) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        for (Long processInstanceId : processInstanceIds) {
            restClient.signalProcessInstance(processInstanceId, signalName, event);
        }
    }

    @Override
    public Collection<String> getAvailableSignals(long processInstanceId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void setProcessVariable(long processInstanceId, String variableId, Object value) {
        AdditionalRestClient restClient = new AdditionalRestClient();
        restClient.setProcessVariable(processInstanceId, variableId, value);
    }

}
