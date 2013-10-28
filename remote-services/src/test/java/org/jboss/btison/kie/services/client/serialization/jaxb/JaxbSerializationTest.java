package org.jboss.btison.kie.services.client.serialization.jaxb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbCommentListResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbOrganizationalEntityMapResponse;
import org.jboss.btison.kie.services.task.command.GetAllCommentsByTaskIdCommand;
import org.jboss.btison.kie.services.task.command.GetPotentialOwnersForTaskIdCommand;
import org.jbpm.services.task.impl.model.CommentImpl;
import org.jbpm.services.task.impl.model.UserImpl;
import org.jbpm.services.task.impl.model.xml.JaxbComment;
import org.junit.Test;
import org.kie.api.task.model.OrganizationalEntity;

public class JaxbSerializationTest {
    
    public Object testRoundtrip(Object in) throws Exception {
        String xmlObject = JaxbSerializationProvider.convertJaxbObjectToString(in);
        return JaxbSerializationProvider.convertStringToJaxbObject(xmlObject);
    }  
    
    @Test
    public void testCommentListResponse() throws Exception {
        CommentImpl comment1 = new CommentImpl();
        comment1.setAddedAt(new Date());
        comment1.setAddedBy(new UserImpl("user1"));
        comment1.setId(1);
        comment1.setText("text1");
        JaxbComment jaxbComment1 = new JaxbComment(comment1);
        CommentImpl comment2 = new CommentImpl();
        comment2.setAddedAt(new Date());
        comment2.setAddedBy(new UserImpl("user2"));
        comment2.setId(2);
        comment2.setText("text2");
        JaxbComment jaxbComment2 = new JaxbComment(comment2);
        List<JaxbComment> jaxbComments = new ArrayList<JaxbComment>();
        jaxbComments.add(jaxbComment1);
        jaxbComments.add(jaxbComment2);
        
        JaxbCommentListResponse response = new JaxbCommentListResponse(jaxbComments,1,new GetAllCommentsByTaskIdCommand());
        
        testRoundtrip(response);
    }
    
    @Test
    public void testOrganizationalEntityMapResponse() throws Exception {
        UserImpl user1 = new UserImpl("user1");
        UserImpl user2 = new UserImpl("user2");
        List<OrganizationalEntity> list1 = new ArrayList<OrganizationalEntity>();
        list1.add(user1);
        list1.add(user2);
        UserImpl user3 = new UserImpl("user3");
        UserImpl user4 = new UserImpl("user4");
        List<OrganizationalEntity> list2 = new ArrayList<OrganizationalEntity>();
        list2.add(user3);
        list2.add(user4);
        Map<Long, List<OrganizationalEntity>> map = new HashMap<Long, List<OrganizationalEntity>>();
        map.put(new Long(1), list1);
        map.put(new Long(2), list2);
        
        JaxbOrganizationalEntityMapResponse response = new JaxbOrganizationalEntityMapResponse(map, 1, new GetPotentialOwnersForTaskIdCommand());
        
        testRoundtrip(response);
    }

}
