package menuLlamadas;

import excepciones.OpcionMenuException;
import main.Administrador;
import menu.EjecutarOpcion;
import utils.Input;
import utils.Output;

public class ConsolaLlamadas implements EjecutarOpcion {

	public void ejecuta(Administrador admin) {
		MenuLlamadas opcionMenu;

		do {
			Output.outPut(MenuLlamadas.getMenu());
			while (true) {
				try {
					utils.Mensajes.OPCION.getDescripcion();
					opcionMenu = MenuLlamadas.getOpcion(Input.IInput());
					break;
				} catch (OpcionMenuException e) {
					Output.outPut(e.getMessage());
				}
			}
			opcionMenu.ejecuta(admin);
		} while (opcionMenu != MenuLlamadas.ATRAS);
	}
}