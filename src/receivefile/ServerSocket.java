package receivefile;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

import file.File;

public class ServerSocket extends java.net.ServerSocket{

	private Socket socket;
	
	public ServerSocket(int port) throws IOException {
		super(port);
		
		System.out.println("Creando servidor: \n"+
				"\tIP: "+InetAddress.getLocalHost()+'\n'+
				"\tPUERTO: "+getLocalPort()+'\n');
	}
	
	public File receiveFile() throws ClassNotFoundException, IOException {
		
		socket = accept();
		
		System.out.println("Se ha realizado una conexion con:\n"+
				"\t"+socket.getRemoteSocketAddress()+'\n'+
				"\tPUERTO: "+socket.getPort()+'\n');
		
		ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
		
		objectInputStream.close();
		socket.close();
		
		return (File) objectInputStream.readObject();
	}
}
