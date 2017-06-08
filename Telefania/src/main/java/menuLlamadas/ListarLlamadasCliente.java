package menuLlamadas;

import excepciones.NifNotFoundException;
import llamadas.Llamada;
import main.Administrador;
import menu.EjecutarOpcion;
import pedirDatos.PedirDatosOpciones;
import utils.Output;

public class ListarLlamadasCliente implements EjecutarOpcion {
	String NIF;

	@Override
	public void ejecuta(Administrador admin) {
		try {
			for (Llamada llamada : admin.listaLlamadasCliente(PedirDatosOpciones.NIFCliente())) {
				Output.outPut(llamada.toString());
			}
		} catch (NifNotFoundException e) {
			Output.outPut(e.getMessage());
		}
	}

}
