package menuClientes;

import java.util.Map;

import clientes.Cliente;
import main.Administrador;
import menu.EjecutarOpcion;
import utils.Output;

public class RecuperarTodosClientes implements EjecutarOpcion {

	@Override
	public void ejecuta(Administrador admin) {
		for (Map.Entry<String, Cliente> datoLista : admin.getListaClientes().entrySet()) {
			Output.outPut(datoLista.getValue().toString());
		}
	}

}
