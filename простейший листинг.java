Пакет А (Агент А): 
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
System.out.println("Привет! агент "+getAID().getName()+" готов.");
addBehaviour(new CyclicBehaviour(this) // Поведение агента исполняемое в цикле
{

public void action() {
    ACLMessage msg = receive();
    if (msg != null) {
    System.out.println( " – " + myAgent.getLocalName()+ " received: "+ msg.getContent() );
     } //Вывод на экран локального имени агента и полученного сообщения
     block();
//Блокируем поведение, пока в очереди сообщений агента не появится хотя бы одно сообщение
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
     msg.addReceiver(agentID); // id агента которому отправляем сообщение 
      msg.setLanguage("English"); //Язык 
      msg.setContent("Ping"); //Содержимое сообщения 
      send(msg); //отправляем сообщение 
}
}}
Пакет В (Агент В):

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
System.out.println("Привет! агент "+getAID().getName()+" готов.");
addBehaviour(new CyclicBehaviour(this) {

public void action() {
ACLMessage msg = receive();
if (msg != null) {
System.out.println(" – " +
myAgent.getLocalName() +
" received: "
+ msg.getContent() );
//Вывод на экран локального имени агента и полученного сообщения 
ACLMessage reply = msg.createReply();
reply.setPerformative( ACLMessage.INFORM );
reply.setContent( "Pong");
//Содержимое сообщения 
send(reply); //отправляем сообщения 
}
block();
}
});
}}
