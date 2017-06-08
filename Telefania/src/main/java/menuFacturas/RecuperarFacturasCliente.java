package menuFacturas;

import facturas.Factura;
import main.Administrador;
import menu.EjecutarOpcion;
import pedirDatos.PedirDatosCliente;
import utils.Output;

public class RecuperarFacturasCliente implements EjecutarOpcion { 

	@Override
	public void ejecuta(Administrador admin) {
		for (Factura factAux : admin.recuperarFacturasClienteTodas(PedirDatosCliente.NIF()).values()) {
			Output.outPut(factAux.toString());
		}
	}

}
