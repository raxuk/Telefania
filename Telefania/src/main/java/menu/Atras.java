package menu;

import main.Administrador;
import serializacion.GuardarDatos;

public class Atras implements EjecutarOpcion {

	@Override
	public void ejecuta(Administrador admin) {
		GuardarDatos.guardarDatos(admin);
		utils.Mensajes.ATRAS.getDescripcion();
	}

}
