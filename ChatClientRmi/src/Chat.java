import java.rmi.*;
import java.util.ArrayList;

public interface Chat extends Remote {
	public String getName() throws RemoteException;

	public void send(String msg) throws RemoteException;

	public void enviarMensagem(String mensagem) throws RemoteException;

	public ArrayList<String> lerMensagem() throws RemoteException;
}