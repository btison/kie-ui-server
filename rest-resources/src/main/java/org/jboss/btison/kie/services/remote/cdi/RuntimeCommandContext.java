package org.jboss.btison.kie.services.remote.cdi;

import org.drools.core.command.impl.KnowledgeCommandContext;
import org.kie.api.KieBase;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.api.runtime.rule.EntryPoint;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.command.World;

public class RuntimeCommandContext implements KnowledgeCommandContext {
    
    private KieSession kieSession;

    @Override
    public World getContextManager() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object get(String identifier) {
        return null;
    }

    @Override
    public void set(String identifier, Object value) {
    }

    @Override
    public void remove(String identifier) {
    }

    @Override
    public KnowledgeBuilder getKnowledgeBuilder() {
        return null;
    }

    @Override
    public void setKnowledgeBuilder(KnowledgeBuilder kbuilder) {
    }

    @Override
    public KieBase getKieBase() {
        return null;
    }

    @Override
    public KieSession getKieSession() {
        return kieSession;
    }

    @Override
    public WorkItemManager getWorkItemManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ExecutionResults getExecutionResults() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EntryPoint getWorkingMemoryEntryPoint() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setKieSession(KieSession kieSession) {
        this.kieSession = kieSession;
    }

}
