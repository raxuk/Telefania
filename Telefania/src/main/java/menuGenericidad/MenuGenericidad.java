package menuGenericidad;

import excepciones.NifNotFoundException;
import excepciones.OpcionMenuException;
import main.Administrador;
import menu.EjecutarOpcion;
import utils.Output;

public enum MenuGenericidad implements EjecutarOpcion {
	CLIENTE(new GenCliente()), 
	FACTURA(new GenFactura()), 
	LLAMADAS(new GenLlamada());

	private EjecutarOpcion ejecutaOpcion;

	private MenuGenericidad(EjecutarOpcion ejecutaOpcion) {
		this.ejecutaOpcion = ejecutaOpcion;
	}

	public static MenuGenericidad getOpcion(int posicion) {
		if (posicion < 0 || posicion > values().length - 1)
			throw new OpcionMenuException();
		return values()[posicion];
	}

	public void ejecuta(Administrador admin) {
		try {
			ejecutaOpcion.ejecuta(admin);
		} catch (NifNotFoundException e) {
			Output.outPut(e.getMessage());
		}
	}
}
