import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.*;

 
public class ChatClient {
	public static void main (String[] argv) {
	    try {
		    	Scanner s=new Scanner(System.in);
		    	System.out.println("Enter Your name and press Enter:");
		    	String name=s.nextLine().trim();		    		    	
		    	ChatInterface client = new Chat(name);
 
//		    	ChatInterface server = (ChatInterface)Naming.lookup("Chat");
		    	
				Registry registry = LocateRegistry.getRegistry(2020);
				ChatInterface stub = (ChatInterface) registry.lookup("Chat");
				
		    	String msg="["+client.getName()+"] got connected";
		    	stub.send(msg);
		    	System.out.println("[System] Chat Remote Object is ready:");
		    	stub.setClient(client);
 
		    	while(true){
		    		msg=s.nextLine().trim();
		    		msg="["+client.getName()+"] "+msg;		    		
		    		stub.send(msg);
		    	}
 
	    	}catch (Exception e) {
	    		System.out.println("[System] Server failed: " + e);
	    	}
		}
}