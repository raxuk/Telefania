package serializacion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.Administrador;
import utils.Mensajes;

public class GuardarDatos {
	public static void guardarDatos(Administrador admin) {
		try {
			FileOutputStream fos = new FileOutputStream(
					System.getProperty("user.home") + File.separator + ".telefania.tlf");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(admin);
			oos.close();
			// Mensajes.SAVE.getDescripcion();
		} catch (IOException e) {
			Mensajes.ERRORSAVE.getDescripcion();
		}
	}

	public static void guardarArchivo(Administrador admin) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos tlf", "tlf");
		fileChooser.setFileFilter(filtro);
		if (fileChooser.showSaveDialog(new JFrame("Guardar como:")) == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();

			try {
				FileOutputStream fos;
				if (fileToSave.getAbsolutePath().endsWith("tlf"))
					fos = new FileOutputStream(fileToSave.getAbsolutePath());
				else
					fos = new FileOutputStream(fileToSave.getAbsolutePath() + ".tlf");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(admin);
				oos.close();
			} catch (IOException e) {
				Mensajes.ERRORSAVE.getDescripcion();
			}
		}
	}

}
