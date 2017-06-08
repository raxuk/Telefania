package menuFacturas;

import excepciones.OpcionMenuException;
import main.Administrador;
import menu.Atras;
import menu.EjecutarOpcion;
import menuGenericidad.MenuGenericidad;

public enum MenuFacturas {
	ATRAS("Atras", new Atras()), 
	EMITIR_FACTURA("Emitir la factura de un cliente", new EmitirFactura()), 
	RECUPERAR_FACTURA("Recuperar una factura a partir del codigo", new RecuperarFactura()), 
	RECUPERAR_FACTURAS_CLIENTE("Recuperar todas las facturas de un cliente, a partir de nif", new RecuperarFacturasCliente()),
	GENERICIDAD("Mostrar un listado de facturas de un cliente emitidas entre dos fechas.", MenuGenericidad.FACTURA);

	private String descripcion;
	private EjecutarOpcion ejecutaOpcion;


	private MenuFacturas(String descripcion, EjecutarOpcion ejecutaOpcion) {
		this.descripcion = descripcion;
		this.ejecutaOpcion=ejecutaOpcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static MenuFacturas getOpcion(int posicion) {
		if (posicion < 0 || posicion > values().length - 1)
			throw new OpcionMenuException();
		return values()[posicion];
	}

	public static String getMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("Opciones del menú: \n ==================================\n");
		for (MenuFacturas opcion : MenuFacturas.values()) {
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
