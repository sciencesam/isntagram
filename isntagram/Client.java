import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
	static int ether = 0;

	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(0);
		System.out.println("Started up and listening at " + InetAddress.getLocalHost() + " " + listener.getLocalPort() + "...");
		try {

			while (true) {
				Socket socket = listener.accept();
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					String inputFromClient = br.readLine();
					int receivedEther = Integer.parseInt(inputFromClient);
					if (ether + receivedEther >= 0) {
						ether += receivedEther;
						
						if (receivedEther > 0) {
							out.println("ether received!");
							System.out.println("received: " + receivedEther + "; total: " + ether);
						} else if (receivedEther < 0) {
							out.println("ether withdrawed");
							System.out.println("withdrew: " + receivedEther + "; total: " + ether);
						}
					} else {
						out.println("Failed to withdraw: Balance too low!");
						System.out.println("Failed to withdraw: Balance too low!");
					}
				} finally {
					socket.close();
				}
			}
		} finally {
			listener.close();
		}
	}
}
