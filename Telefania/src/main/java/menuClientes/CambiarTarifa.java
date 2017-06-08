package menuClientes;

import clientes.Cliente;
import excepciones.NifNotFoundException;
import main.Administrador;
import menu.EjecutarOpcion;
import pedirDatos.PedirDatosCliente;
import pedirDatos.PedirDatosOpciones;
import utils.Output;

public class CambiarTarifa implements EjecutarOpcion {
	Cliente clienteTarifa;

	@Override
	public void ejecuta(Administrador admin) {
		try {
			admin.cambiarTarifaCliente(PedirDatosOpciones.NIFCliente(), PedirDatosCliente.Tarifa());
			utils.Mensajes.TARIFACAMBIADA.getDescripcion();
		} catch (NifNotFoundException e) {
			Output.outPut(e.getMessage());
		}
	}
}
