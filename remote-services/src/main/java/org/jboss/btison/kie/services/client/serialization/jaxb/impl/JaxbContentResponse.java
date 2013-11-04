package org.jboss.btison.kie.services.client.serialization.jaxb.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jbpm.services.task.impl.model.xml.JaxbContent;
import org.kie.api.command.Command;
import org.kie.services.client.serialization.jaxb.impl.AbstractJaxbCommandResponse;

@XmlRootElement(name="task-content")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbContentResponse extends AbstractJaxbCommandResponse<JaxbContent> {
    
    @XmlElement(name = "content", type = JaxbContent.class)
    private JaxbContent content;
    
    public JaxbContentResponse() {
        
    }
    
    public JaxbContentResponse(JaxbContent content) {
        this.content = content;
    }
    
    public JaxbContentResponse(JaxbContent content, int i, Command<?> cmd) {
        super(i, cmd);
        this.content = content;
    }

    @Override
    public JaxbContent getResult() {
        return content;
    }

    @Override
    public void setResult(JaxbContent result) {
        this.content = content;
    }

}
