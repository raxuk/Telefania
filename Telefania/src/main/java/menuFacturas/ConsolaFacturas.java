package menuFacturas;

import excepciones.OpcionMenuException;
import main.Administrador;
import menu.EjecutarOpcion;
import utils.Input;
import utils.Output;

public class ConsolaFacturas implements EjecutarOpcion {

	public void ejecuta(Administrador admin) {
		MenuFacturas opcionMenu;

		do {
			Output.outPut(MenuFacturas.getMenu());
			while (true) {
				try {
					utils.Mensajes.OPCION.getDescripcion();
					opcionMenu = MenuFacturas.getOpcion(Input.IInput());
					break;
				} catch (OpcionMenuException e) {
					Output.outPut(e.getMessage());
				}
			}
			opcionMenu.ejecuta(admin);
		} while (opcionMenu != MenuFacturas.ATRAS);
	}
}