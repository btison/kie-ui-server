package org.jboss.btison.kie.services.client.serialization.jaxb.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.jbpm.services.task.impl.model.xml.JaxbComment;
import org.kie.api.command.Command;
import org.kie.services.client.serialization.jaxb.impl.AbstractJaxbCommandResponse;

@XmlRootElement(name="task-comment-list")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(JaxbComment.class)
public class JaxbCommentListResponse extends AbstractJaxbCommandResponse<List<JaxbComment>> {
    
    @XmlElements({
        @XmlElement(name="task-comment",type=JaxbComment.class)
    })
    private List<JaxbComment> commentList;

    public JaxbCommentListResponse() {
        this.commentList = new ArrayList<JaxbComment>();
    }
    
    public JaxbCommentListResponse(List<JaxbComment> commentList) {
        this.commentList = commentList;
    }
    
    public JaxbCommentListResponse(List<JaxbComment> commentList, int i, Command<?> cmd) {
        super(i, cmd);
        this.commentList = commentList;
    }

    @Override
    public List<JaxbComment> getResult() {
        return commentList;
    }

    @Override
    public void setResult(List<JaxbComment> result) {
        this.commentList = result;        
    }
}
