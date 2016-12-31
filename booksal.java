import jade. core. Agent;
import jade.core.AID;
import jade.core.behaviours.*;

public class BookBuyerAgent extends Agent {

private String targetBookTitle; // The title of the book to buy

// The list of known seller agents
private AID[] sellerAgents = { new AID(Уseller1Ф, AID.ISLOCALNAME),
new AID(Уseller2Ф, AID.ISLOCALNAME)};

protected void setup() {
// Printout a welcome message
System.out.println(УHello! Buyer-agent У+getAID().getName()+Ф is ready.Ф);

// Get the title of the book to buy as a start-up argument
Object[] args = getArguments();
if (args != null && args.length > 0) {//≈сли есть название книги
targetBookTitle = (String) args[0];
System.out.println(УTrying to buy У+targetBookTitle);
//то добавить TickerBehaviour, которое организует запрос к Ђпродавцуї каждую минуту
addBehaviour(new TickerBehaviour(this, 60000) {

protected void onTick() {
myAgent.addBehaviour(new RequestPerformer());
}
} );
}
}
else {
// Make the agent terminate immediately
System.out.println(УNo book title specifiedУ);
doDelete();
}

protected void takeDown() {
// Printout a dismissal message
System.out.println(УBuyer-agent У+getAID().getName()+Ф terminating.Ф);
}

//////////////////////////////////////////////////////////////////////////////////////////////////////
