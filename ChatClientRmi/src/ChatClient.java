import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ChatClient {
	private static Scanner scanner;

	public static void main(String[] argv) {
		try {
			final Chat stub = (Chat) Naming.lookup("rmi://localhost:2020/ServerChat");

			String nome;
			String msg = "";
			scanner = new Scanner(System.in);

			System.out.print("Digite seu nome:");

			nome = scanner.next();
			
			Thread thread = new Thread(new Runnable() {
				int cont = stub.getMensagens().size();

				@Override
				public void run() {
					try {
						while (true) {
							if (stub.getMensagens().size() > cont) {
								String lastMsg = "\t"+stub.getMensagens().get(stub.getMensagens().size() - 1);
								if (lastMsg.endsWith(": ")){
									System.out.println(lastMsg + " Entrou no Grupo.");
								}
									else{
									System.out.println(lastMsg);
								}
								cont++;
							}
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
			while (msg != "exit") {
				
				msg = scanner.nextLine();
				stub.enviarMensagem(nome + ": " + msg);
			}

		}catch(

	Exception e)
	{
		System.out.println("[ERRO]: " + e);
	}
}}