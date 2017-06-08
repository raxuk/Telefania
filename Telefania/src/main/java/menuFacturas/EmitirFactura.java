package menuFacturas;

import excepciones.NifNotFoundException;
import informacion.PeriodoTiempo;
import main.Administrador;
import menu.EjecutarOpcion;
import pedirDatos.PedirDatosCliente;
import pedirDatos.PedirPeriodo;
import utils.Output;

public class EmitirFactura implements EjecutarOpcion {

	@Override
	public void ejecuta(Administrador admin) {
		try {
			String nif = PedirDatosCliente.NIF();
			PeriodoTiempo periodo = PedirPeriodo.pedirPeriodoTiempo();

			Output.outPut(admin.emitirFacturaCliente(nif, periodo).toString());
			Output.outPut("Factura emitida satisfactoriamente\n");
		} catch (NifNotFoundException e) {
			Output.outPut(e.getMessage());
		}
	}

}
