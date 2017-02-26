import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Sender {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		//System.out.print("Enter server IP and port: ");
		//String[] serverInfo = in.nextLine().split(" ");
		Socket s = new Socket(args[0], Integer.parseInt(args[1]));
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		int numToServer = Integer.parseInt(args[2]);
		out.println(numToServer);
		System.out.println("sent: " + numToServer);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String answer = input.readLine();
		System.out.println(answer);
		s.close();
		in.close();
		System.exit(0);
	}
}
