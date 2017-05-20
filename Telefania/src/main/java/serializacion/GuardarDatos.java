package serializacion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import main.Administrador;
import utils.Mensajes;

public class GuardarDatos {
	public static void guardarDatos(Administrador admin) {
		try {
			FileOutputStream fos = new FileOutputStream("telefania.bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(admin);
			oos.close();
//			Mensajes.SAVE.getDescripcion();
		} catch (IOException e) {
			Mensajes.ERRORSAVE.getDescripcion();
		}
	}
}
