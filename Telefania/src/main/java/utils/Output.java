package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Output {
	public static void outPut(String cadena) {
		JFrame ventana = new JFrame("Mensaje sistema");
		JTextArea mensaje = new JTextArea(cadena);

		final Timer crono = new Timer(2500, null);
		crono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				crono.stop();
				ventana.setVisible(false);
			}
		});

		crono.start();

		ventana.getContentPane().add(mensaje);
		ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventana.setVisible(true);
	}
}