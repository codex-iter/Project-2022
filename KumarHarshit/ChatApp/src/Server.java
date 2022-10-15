
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {

	
	private ServerSocket serverSocket;   // this object is responsible for incoming connections or clients for communications.
	
	public Server(ServerSocket serverSocket) {
		this.serverSocket=serverSocket;
	}
	
	public void startServer() { // method to keep the server running.
		
		try {    // try-catch for input-output error handling. 
			while(true && !serverSocket.isClosed()) {
				
				Socket socket=serverSocket.accept(); // a socket object for communication and this is later returned. 
				System.out.println("A new client is connected.");
				ClientHandler clientHandler=new ClientHandler(socket); //this class implements runnable interface.
				
				Thread thread=new Thread(clientHandler); 
				thread.start();
				
			}
		}
		catch(IOException e) {
			
		}
	}
	
	public void closeServerSocket() { // method to close the server.
		try {
			if(serverSocket!=null) {
				serverSocket.close();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws IOException{
		
		ServerSocket serverSocket=new ServerSocket(1234); // port number for connection to this server.
		Server server=new Server(serverSocket);
		server.startServer();
	}
}

