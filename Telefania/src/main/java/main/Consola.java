package main;

import excepciones.OpcionMenuException;
import menu.MenuPrincipal;
import serializacion.RecuperarDatos;
import utils.Input;
import utils.Output;

public class Consola {
	protected Administrador admin = RecuperarDatos.recuperarDatos();

	protected Consola() {
		super();
	}

	private void ejecuta() {
		MenuPrincipal opcionMenu;

		do {
			Output.outPut(MenuPrincipal.getMenu());
			while (true) {
				try {
					utils.Mensajes.OPCION.getDescripcion();
					opcionMenu = MenuPrincipal.getOpcion(Input.IInput());
					break;
				} catch (OpcionMenuException e) {
					Output.outPut(e.getMessage());
				}
			}
			opcionMenu.ejecuta(admin);
		} while (opcionMenu != MenuPrincipal.SALIR);
	}

	public static void main(String[] args) {
		new Consola().ejecuta();
	}
}
