package menuAnyadirCliente;

import excepciones.OpcionMenuException;
import main.Administrador;
import menu.EjecutarOpcion;
import utils.Input;
import utils.Output;

public class ConsolaAltaCliente implements EjecutarOpcion {

	public void ejecuta(Administrador admin) {
		MenuAnyadirCliente opcionMenu;

		do {
			Output.outPut(MenuAnyadirCliente.getMenu());
			while (true) {
				try {
					utils.Mensajes.OPCION.getDescripcion();
					opcionMenu = MenuAnyadirCliente.getOpcion(Input.IInput());
					break;
				} catch (OpcionMenuException e) {
					Output.outPut(e.getMessage());
				}
			}
			opcionMenu.ejecuta(admin);
		} while (opcionMenu != MenuAnyadirCliente.ATRAS);
	}

}
