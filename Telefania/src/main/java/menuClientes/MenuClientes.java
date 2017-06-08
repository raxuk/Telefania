package menuClientes;

import excepciones.OpcionMenuException;
import main.Administrador;
import menu.Atras;
import menu.EjecutarOpcion;
import menuAnyadirCliente.ConsolaAltaCliente;
import menuGenericidad.MenuGenericidad;

public enum MenuClientes {
	ATRAS("Atras", new Atras()), 
	DAR_ALTA_CLIENTE("Dar de alta un nuevo cliente", new ConsolaAltaCliente()), 
	BORRAR_CLIENTE("Borrar un cliente a partir de nif", new BorrarCliente()), 
	CAMBIAR_TARIFA("Cambiar tarifa de un cliente", new CambiarTarifa()), 
	RECUPERAR_CLIENTE("Recuperar datos de un cliente a partir de nif", new RecuperarCliente()), 
	RECUPERAR_TODOS_CLIENTES("Recuperar listado con todos los clientes", new RecuperarTodosClientes()),
	GENERICIDAD("Mostrar un listado de clientes que fueron dados de alta entre dos fechas.",  MenuGenericidad.CLIENTE);

	private String descripcion;
	private EjecutarOpcion ejecutaOpcion;

	private MenuClientes(String descripcion, EjecutarOpcion ejecutaOpcion) {
		this.descripcion = descripcion;
		this.ejecutaOpcion=ejecutaOpcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static MenuClientes getOpcion(int posicion) {
		if (posicion < 0 || posicion > values().length - 1)
			throw new OpcionMenuException();
		return values()[posicion];
	}

	public static String getMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("Opciones del menú: \n ==================================\n");
		for (MenuClientes opcion : MenuClientes.values()) {
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
