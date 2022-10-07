
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable { // this class implements the runnable interface.

	public static ArrayList<ClientHandler> clientHandlers=new ArrayList<>();
	// this Array-list is used to keep track of all the clients and for convenience while the message is sent from the clients.  
	private Socket socket;
	//passed from the server class to establish the connection between client and server.
	private BufferedReader bufferedReader;
	//read data or messages sent from clients.
	private BufferedWriter bufferedWriter;
	// used to send data or messages from clients.
	private String clientUsername;
	
	public ClientHandler(Socket socket) {
		try {
			this.socket=socket;
			this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			// wrapping byte to char (get o/p stream to o/p writer ) and then wrapping this char stream to buffered Writer. 
			this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.clientUsername=bufferedReader.readLine();
			clientHandlers.add(this);
			// adding client to Array-List
			broadcastMessage("SERVER : "+clientUsername+" has entered the chat! ");
		}
		catch(IOException e) {
			closeEverything(socket, bufferedReader, bufferedWriter);
		}
	}
	
	public void run() { // override method from runnable 
		
		String messageFromClient;
		
		while(socket.isConnected()) {
			try {
				messageFromClient=bufferedReader.readLine();
				//reading the message from client using buffered-Reader
				broadcastMessage(messageFromClient);
			}
			catch(IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
				break;
			}
		}
	}
	
	public void broadcastMessage(String messageToSend) {
		// method to display the messages. 
		for(ClientHandler clientHandler : clientHandlers) {
			try {
				if(!clientHandler.clientUsername.equals(clientUsername)) {
					clientHandler.bufferedWriter.write(messageToSend);
					clientHandler.bufferedWriter.newLine();
					clientHandler.bufferedWriter.flush();
				}
			}
			catch(IOException e) {
				closeEverything(socket,bufferedReader, bufferedWriter);
			}
		}
	}
	
	public void removeClientHandler() {
		clientHandlers.remove(this);
		broadcastMessage("SERVER : "+clientUsername+" has left the chat! ");
	} 
	
	public void closeEverything(Socket socket, BufferedReader bufferdReader, BufferedWriter bufferedWriter) {
		removeClientHandler();
		try {
			if(bufferedReader !=null) {
				bufferedReader.close();
			}
			if(bufferedWriter !=null) {
				bufferedWriter.close();
			}
			if(socket !=null) {
				socket.close();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}