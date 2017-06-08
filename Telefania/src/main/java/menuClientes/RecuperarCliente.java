package menuClientes;

import excepciones.NifNotFoundException;
import main.Administrador;
import menu.EjecutarOpcion;
import pedirDatos.PedirDatosOpciones;
import utils.Output;

public class RecuperarCliente implements EjecutarOpcion {

	@Override
	public void ejecuta(Administrador admin) {
		try {
			Output.outPut(admin.getCliente(PedirDatosOpciones.NIFCliente()).toString());
		} catch (NifNotFoundException e) {
			Output.outPut(e.getMessage());
		}
	}

}
