package serializacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.Administrador;
import utils.Mensajes;

public class RecuperarDatos {
	private static Administrador admin = Administrador.getAdministradorInstancia();

	public static Administrador recuperarDatos() {
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.home") + File.separator + ".telefania.tlf");

			ObjectInputStream ois = new ObjectInputStream(fis);
			admin = (Administrador) ois.readObject();
			ois.close();
		} catch (Exception e) {
			Mensajes.ERRORSAVENOTFOUND.getDescripcion();
		}
		return admin;
	}

	public static Administrador cargarDatos() {
		JFileChooser fileChooser = new JFileChooser();
		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos tlf", "tlf");
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

		fileChooser.setFileFilter(filtro);

		int ret = fileChooser.showDialog(null, "Elige un fichero");

		if (ret == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fileChooser.getSelectedFile();
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				admin = (Administrador) ois.readObject();
				ois.close();
			} catch (Exception e) {
				Mensajes.ERRORSAVENOTFOUND.getDescripcion();
			}
		}
		return admin;
	}
}
