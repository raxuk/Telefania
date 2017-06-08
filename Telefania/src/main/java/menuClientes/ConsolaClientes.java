package menuClientes;

import excepciones.OpcionMenuException;
import main.Administrador;
import menu.EjecutarOpcion;
import utils.Input;
import utils.Output;

public class ConsolaClientes implements EjecutarOpcion {

	public void ejecuta(Administrador admin) {
		MenuClientes opcionMenu;

		do {
			Output.outPut(MenuClientes.getMenu());
			while (true) {
				try {
					utils.Mensajes.OPCION.getDescripcion();
					opcionMenu = MenuClientes.getOpcion(Input.IInput());
					break;
				} catch (OpcionMenuException e) {
					Output.outPut(e.getMessage());
				}
			}
			opcionMenu.ejecuta(admin);
		} while (opcionMenu != MenuClientes.ATRAS);
	}
}