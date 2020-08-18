package receivefile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;

import file.File;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		
		ServerSocket serverSocket = new ServerSocket(7788);
		
		System.out.println("Creando servidor: \n"+
				"\tIP: "+InetAddress.getLocalHost()+'\n'+
				"\tPUERTO: "+serverSocket.getLocalPort()+'\n');
		
		Socket socket = serverSocket.accept();
		
		System.out.println("Se ha realizado una conexion con:\n"+
				"\t"+socket.getRemoteSocketAddress()+'\n'+
				"\tPUERTO: "+socket.getPort()+'\n');
		
		ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
		
		BufferedOutputStream bufferedOutputStream = null;
		
		File file = (File) objectInputStream.readObject();
		
		objectInputStream.close();
		socket.close();
		
		System.out.println("Seleccione el destino del archivo: ");
		
		Thread.sleep(1500);
		
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setMultiSelectionEnabled(false);
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jFileChooser.setSelectedFile(file);
		
		if(jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			
			System.out.println('\t'+jFileChooser.getSelectedFile().getPath()+'\n');
			
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(jFileChooser.getSelectedFile().getPath()));
			bufferedOutputStream.write(file.getBytes());
			
			bufferedOutputStream.close();
		}
		
		System.out.println("Su archivo se ha guardado con exito en:\n"+
							'\n'+jFileChooser.getSelectedFile().getPath());
		
		serverSocket.close();
	}
}
