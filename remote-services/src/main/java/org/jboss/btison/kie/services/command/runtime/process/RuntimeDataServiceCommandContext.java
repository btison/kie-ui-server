package org.jboss.btison.kie.services.command.runtime.process;

import org.jbpm.kie.services.api.RuntimeDataService;
import org.kie.internal.command.Context;
import org.kie.internal.command.World;

public class RuntimeDataServiceCommandContext implements Context {
    
    private RuntimeDataService dataService;
    
    public RuntimeDataServiceCommandContext() {
        
    }
    
    public RuntimeDataServiceCommandContext(RuntimeDataService dataService) {
       this.dataService = dataService;
    }

    public RuntimeDataService getDataService() {
        return dataService;
    }

    public void setDataService(RuntimeDataService dataService) {
        this.dataService = dataService;
    }

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

}
