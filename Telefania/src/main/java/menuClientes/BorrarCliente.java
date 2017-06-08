package menuClientes;

import excepciones.NifNotFoundException;
import main.Administrador;
import menu.EjecutarOpcion;
import pedirDatos.PedirDatosOpciones;
import utils.Output;

public class BorrarCliente implements EjecutarOpcion {
	Output output = new Output();

	@Override
	public void ejecuta(Administrador admin) {
		try {
			admin.borrarCliente(PedirDatosOpciones.NIFCliente());
			utils.Mensajes.CLIENTEBORRADO.getDescripcion();
		} catch (NifNotFoundException e) {
			Output.outPut(e.getMessage());
		}
	}
}
