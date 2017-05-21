package serializacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import main.Administrador;
import utils.Mensajes;

public class RecuperarDatos {
	public static Administrador recuperarDatos() {
		Administrador admin = Administrador.getAdministradorInstancia();
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.home") + File.separator + ".telefania.bin");
			ObjectInputStream ois = new ObjectInputStream(fis);
			admin = (Administrador) ois.readObject();
			ois.close();
		} catch (Exception e) {
			Mensajes.ERRORSAVENOTFOUND.getDescripcion();
		}
		return admin;
	}
}
