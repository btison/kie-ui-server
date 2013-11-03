package org.jboss.btison.kie.services.client.serialization.jaxb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbCommentListResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbNodeInstanceDescListResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbNodeInstanceDescResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbOrganizationalEntityMapResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbProcessInstanceDescListResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbProcessInstanceDescResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbVariableStateDescListResponse;
import org.jboss.btison.kie.services.client.serialization.jaxb.impl.JaxbVariableStateDescResponse;
import org.jboss.btison.kie.services.command.runtime.process.GetNodeInstanceDescCommand;
import org.jboss.btison.kie.services.command.runtime.process.GetProcessInstanceDescCommand;
import org.jboss.btison.kie.services.command.runtime.process.GetVariableStateDescCommand;
import org.jboss.btison.kie.services.task.command.GetAllCommentsByTaskIdCommand;
import org.jboss.btison.kie.services.task.command.GetPotentialOwnersForTaskIdCommand;
import org.jbpm.kie.services.impl.model.NodeInstanceDesc;
import org.jbpm.kie.services.impl.model.ProcessInstanceDesc;
import org.jbpm.kie.services.impl.model.VariableStateDesc;
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
    
    @Test
    public void testProcessInstanceDescResponse() throws Exception {
        ProcessInstanceDesc p = new ProcessInstanceDesc(1,"id","name","version",1,"deploymentId",new Date(),"initiator");

        JaxbProcessInstanceDescResponse response = new JaxbProcessInstanceDescResponse(p, 1, new GetProcessInstanceDescCommand());

        testRoundtrip(response);
    }
    
    @Test
    public void testProcessInstanceDescListResponse() throws Exception {
        ProcessInstanceDesc p1 = new ProcessInstanceDesc(1,"id1","name1","version1",1,"deploymentId1",new Date(),"initiator1");
        JaxbProcessInstanceDescResponse r1 = new JaxbProcessInstanceDescResponse(p1);
        ProcessInstanceDesc p2 = new ProcessInstanceDesc(2,"id2","name2","version2",2,"deploymentId2",new Date(),"initiator2");
        JaxbProcessInstanceDescResponse r2 = new JaxbProcessInstanceDescResponse(p2);
        List<JaxbProcessInstanceDescResponse> list = new ArrayList<JaxbProcessInstanceDescResponse>();
        list.add(r1);
        list.add(r2);
        
        JaxbProcessInstanceDescListResponse response = new JaxbProcessInstanceDescListResponse(list, 1, new GetProcessInstanceDescCommand());

        testRoundtrip(response);
    }
    
    @Test
    public void testNodeInstanceDescResponse() throws Exception {
        NodeInstanceDesc n = new NodeInstanceDesc("1","nodeid","name","nodetype","deploymentId",1,new Date(),"connection",1);

        JaxbNodeInstanceDescResponse response = new JaxbNodeInstanceDescResponse(n, 1, new GetNodeInstanceDescCommand());

        testRoundtrip(response);
    }
    
    @Test
    public void testNodeInstanceDescListResponse() throws Exception {
        NodeInstanceDesc n1 = new NodeInstanceDesc("1","nodeid","name","nodetype","deploymentId",1,new Date(),"connection",1);
        JaxbNodeInstanceDescResponse r1 = new JaxbNodeInstanceDescResponse(n1);        
        NodeInstanceDesc n2 = new NodeInstanceDesc("2","nodeid2","name2","nodetype2","deploymentId2",2,new Date(),"connection2",1);
        JaxbNodeInstanceDescResponse r2 = new JaxbNodeInstanceDescResponse(n2);
        List<JaxbNodeInstanceDescResponse> list = new ArrayList<JaxbNodeInstanceDescResponse>();
        list.add(r1);
        list.add(r2);
        
        JaxbNodeInstanceDescListResponse response = new JaxbNodeInstanceDescListResponse(list, 1, new GetNodeInstanceDescCommand());

        testRoundtrip(response);
    }
    
    @Test
    public void testVariableStateDescResponse() throws Exception {
        VariableStateDesc v = new VariableStateDesc("id","varInstId","old","new","deploymentId",1,new Date());
        
        JaxbVariableStateDescResponse response = new JaxbVariableStateDescResponse(v, 1, new GetVariableStateDescCommand());

        testRoundtrip(response);
    }
    
    @Test
    public void testVariableStateDescListResponse() throws Exception {
        VariableStateDesc v1 = new VariableStateDesc("id","varInstId","old","new","deploymentId",1,new Date());
        JaxbVariableStateDescResponse r1 = new JaxbVariableStateDescResponse(v1);        
        VariableStateDesc v2 = new VariableStateDesc("id2","varInstId2","old2","new2","deploymentId2",2,new Date());
        JaxbVariableStateDescResponse r2 = new JaxbVariableStateDescResponse(v2);  
        List<JaxbVariableStateDescResponse> list = new ArrayList<JaxbVariableStateDescResponse>();
        list.add(r1);
        list.add(r2);
        
        JaxbVariableStateDescListResponse response = new JaxbVariableStateDescListResponse(list, 1, new GetVariableStateDescCommand());

        testRoundtrip(response);
    }

}
