package org.jboss.btison.kie.services.cdi.extension;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CDIExtension implements Extension {
    
    private static final String VETO_CLASSES="ui.server.veto.classes";
    
    private static Logger log = LoggerFactory.getLogger(CDIExtension.class);
    
    private List<String> vetoClasses;
    
    public CDIExtension() {
        String vetoClassesString = System.getProperty(VETO_CLASSES);
        
        if(vetoClassesString != null) {
            vetoClasses = new ArrayList<String>();
            String[] vetoClassesArray = vetoClassesString.split("\\s");
            for(String vetoClass : vetoClassesArray){
                vetoClasses.add(vetoClass);
            }
        }        
    }
    
    @SuppressWarnings("rawtypes")
    public void processAnnotatedType(@Observes ProcessAnnotatedType pat, BeanManager bm) {
        String name = pat.getAnnotatedType().getJavaClass().getName();
               
        // NOTE: This does not prevent the class from being instantiated
        if(vetoClasses != null && vetoClasses.contains(name)){
            pat.veto();
            log.info("processAnnotatedType() just vetoed : "+ name);
        }
    }
    
    

}
