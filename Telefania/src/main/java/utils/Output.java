package utils;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Output {

	public static void outPut(String cadena) {
		JFrame ventana = new JFrame("Mensaje sistema");
		JTextArea mensaje = new JTextArea(cadena);
		
        ventana.getContentPane().add(mensaje);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setVisible(true);
	}
}