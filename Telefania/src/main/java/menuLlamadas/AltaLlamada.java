package menuLlamadas;

import excepciones.NifNotFoundException;
import llamadas.Llamada;
import main.Administrador;
import menu.EjecutarOpcion;
import pedirDatos.PedirDatosCliente;
import pedirDatos.PedirDatosOpciones;
import utils.Output;

public class AltaLlamada implements EjecutarOpcion {

	@Override
	public void ejecuta(Administrador admin) {
		try {
			admin.altaLlamada(PedirDatosCliente.NIF(), crearLlamada());
		} catch (NifNotFoundException e) {
			Output.outPut(e.getMessage());
		}
	}

	public Llamada crearLlamada() {
		return new Llamada(PedirDatosOpciones.NumeroTelf(), PedirDatosOpciones.DuracionLlamada());
	}

}
