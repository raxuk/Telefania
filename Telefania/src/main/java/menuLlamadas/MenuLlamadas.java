package menuLlamadas;

import excepciones.OpcionMenuException;
import main.Administrador;
import menu.Atras;
import menu.EjecutarOpcion;
import menuGenericidad.MenuGenericidad;

public enum MenuLlamadas {
	ATRAS("Atras", new Atras()), 
	ALTA_LLAMADA("Dar de alta una llamada", new AltaLlamada()), 
	LISTAR_LLAMADAS_CLIENTE("Listar todas las llamadas de un cliente, a partir de nif", new ListarLlamadasCliente()),
	GENERICIDAD("Mostrar un listado de llamadas de un cliente que fueron realizadas entre dos fechas.", MenuGenericidad.LLAMADAS);

	private String descripcion;
	private EjecutarOpcion ejecutaOpcion;

	private MenuLlamadas(String descripcion, EjecutarOpcion ejecutaOpcion) {
		this.descripcion = descripcion;
		this.ejecutaOpcion = ejecutaOpcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static MenuLlamadas getOpcion(int posicion) {
		if (posicion < 0 || posicion > values().length - 1)
			throw new OpcionMenuException();
		return values()[posicion];
	}

	public static String getMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("Opciones del menú: \n ==================================\n");
		for (MenuLlamadas opcion : MenuLlamadas.values()) {
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
