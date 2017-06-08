package menuAnyadirCliente;

import excepciones.OpcionMenuException;
import main.Administrador;
import menu.Atras;
import menu.EjecutarOpcion;

public enum MenuAnyadirCliente {
	ATRAS("Atras", new Atras()), 
	PARTICULAR("Añadir un cliente particular", new NuevoParticular()), 
	EMPRESA("Añadir un cliente empresa", new NuevaEmpresa());

	private String descripcion;
	private EjecutarOpcion ejecutaOpcion;

	private MenuAnyadirCliente(String descripcion, EjecutarOpcion ejecutaOpcion) {
		this.descripcion = descripcion;
		this.ejecutaOpcion=ejecutaOpcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static MenuAnyadirCliente getOpcion(int posicion) {
		if (posicion < 0 || posicion > values().length - 1)
			throw new OpcionMenuException();
		return values()[posicion];
	}

	public static String getMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("Opciones del menú: \n ==================================\n");
		for (MenuAnyadirCliente opcion : MenuAnyadirCliente.values()) {
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
