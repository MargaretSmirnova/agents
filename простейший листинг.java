����� � (����� �): 
/*
* To change this template, choose Tools | Templates and open the template in the editor.*/
package A;

import jade.core.Agent;
import jade.core.AID;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.*;
import jade.core.behaviours.*;
import jade.lang.acl.*;

/**
*
* @author TRSteep
*/
public class AMain extends Agent {

public void setup() {
System.out.println("������! ����� "+getAID().getName()+" �����.");
addBehaviour(new CyclicBehaviour(this) // ��������� ������ ����������� � �����
{

public void action() {
    ACLMessage msg = receive();
    if (msg != null) {
    System.out.println( " � " + myAgent.getLocalName()+ " received: "+ msg.getContent() );
     } //����� �� ����� ���������� ����� ������ � ����������� ���������
     block();
//��������� ���������, ���� � ������� ��������� ������ �� �������� ���� �� ���� ���������
   }
   });
   AMSAgentDescription [] agents = null;
      try {
           SearchConstraints c = new SearchConstraints();
            c.setMaxResults(new Long(-1));
            agents = AMSService.search(this, new AMSAgentDescription(), c);
      } catch (Exception e) {
System.out.println( "Problem searching AMS: " + e);

e.printStackTrace();
}

      for(int i=0; i<4; i++) {
     AID agentID = agents[i].getName();
     ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
     msg.addReceiver(agentID); // id ������ �������� ���������� ��������� 
      msg.setLanguage("English"); //���� 
      msg.setContent("Ping"); //���������� ��������� 
      send(msg); //���������� ��������� 
}
}}
����� � (����� �):

/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package B;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;
/**
*
* @author TRSteep
*/
public class BClass extends Agent {

protected void setup() {
System.out.println("������! ����� "+getAID().getName()+" �����.");
addBehaviour(new CyclicBehaviour(this) {

public void action() {
ACLMessage msg = receive();
if (msg != null) {
System.out.println(" � " +
myAgent.getLocalName() +
" received: "
+ msg.getContent() );
//����� �� ����� ���������� ����� ������ � ����������� ��������� 
ACLMessage reply = msg.createReply();
reply.setPerformative( ACLMessage.INFORM );
reply.setContent( "Pong");
//���������� ��������� 
send(reply); //���������� ��������� 
}
block();
}
});
}}
