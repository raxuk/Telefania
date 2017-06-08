package menu;

import excepciones.OpcionMenuException;
import main.Administrador;
import menuClientes.ConsolaClientes;
import menuFacturas.ConsolaFacturas;
import menuLlamadas.ConsolaLlamadas;

public enum MenuPrincipal {
	SALIR("Salir del programa.", new Salir()), CLIENTES("Operaciones con clientes.", new ConsolaClientes()), LLAMADAS(
			"Operaciones con llamadas.",
			new ConsolaLlamadas()), FACTURAS("Operaciones con facturas.", new ConsolaFacturas());

	private String descripcion;
	private EjecutarOpcion ejecutaOpcion;

	private MenuPrincipal(String descripcion, EjecutarOpcion ejecutaOpcion) {
		this.descripcion = descripcion;
		this.ejecutaOpcion = ejecutaOpcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static MenuPrincipal getOpcion(int posicion) {
		if (posicion < 0 || posicion > values().length - 1)
			throw new OpcionMenuException();
		return values()[posicion];
	}

	public static String getMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("Opciones del menú: \n ==================================\n");
		for (MenuPrincipal opcion : MenuPrincipal.values()) {
			sb.append(opcion.ordinal());
			sb.append(".- ");
			sb.append(opcion.getDescripcion());
			sb.append("\n");
		}
		return sb.toString();
	}

	public void ejecuta(Administrador admin) {
		ejecutaOpcion.ejecuta(admin);
	}
}
