package receivefile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;

import file.File;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		
		ServerSocket serverSocket = new ServerSocket(7788);
		
		BufferedOutputStream bufferedOutputStream = null;
		
		File file = serverSocket.receiveFile();
		
		System.out.println("Seleccione el destino del archivo: ");
		
		Thread.sleep(1500);
		
		JFileChooser jFileChooser = new JFileChooser(file);
		jFileChooser.setMultiSelectionEnabled(false);
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if(jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			
			System.out.println('\t'+jFileChooser.getSelectedFile().getPath()+'\n');
			
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(jFileChooser.getSelectedFile().getPath()));
			bufferedOutputStream.write(file.getBytes());
			
			bufferedOutputStream.close();
		}
		
		System.out.println("Su archivo se ha guardado con exito en "+jFileChooser.getSelectedFile().getPath());
		
		serverSocket.close();
	}
}
