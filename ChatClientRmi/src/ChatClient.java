import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.*;

public class ChatClient {
	public static void main(String[] argv) {
		try {
			final Chat stub = (Chat) Naming.lookup("rmi://localhost:2020/ServerChat");

			String nome;
			String msg = "";
			Scanner scanner = new Scanner(System.in);

			System.out.print("Digite seu nome:");

			nome = scanner.nextLine();

			Thread thread = new Thread(new Runnable() {
				int cont = stub.lerMensagem().size();

				@Override
				public void run() {
					try {
						while (true) {
							if (stub.lerMensagem().size() > cont) {
								System.out.println(stub.lerMensagem().get(stub.lerMensagem().size() - 1));
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

				System.out.print(nome + ": ");

				msg = scanner.nextLine();

				stub.enviarMensagem(nome + ": " + msg);
			}

		} catch (Exception e) {
			System.out.println("[ERRO]: " + e);
		}
	}
}