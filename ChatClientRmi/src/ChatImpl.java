import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class ChatImpl extends UnicastRemoteObject implements Chat {

	private static final long serialVersionUID = 1L;
	private String name;
	private Chat client = null;
	private ArrayList<String> mensagens;

	public ChatImpl(String n) throws RemoteException {
		super();
		this.name = n;
		this.mensagens = new ArrayList<String>();
	}

	public String getName() throws RemoteException {
		return this.name;
	}

	@Override
	public ArrayList<String> getMensagens() throws RemoteException {
		return mensagens;
	}

	@Override
	public void enviarMensagem(String mensagem) throws RemoteException {
		mensagens.add(mensagem);
	}
}