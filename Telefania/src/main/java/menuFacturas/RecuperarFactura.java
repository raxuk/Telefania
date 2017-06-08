package menuFacturas;

import excepciones.CodFactNotFound;
import excepciones.CodFacturaError;
import main.Administrador;
import menu.EjecutarOpcion;
import pedirDatos.PedirDatosOpciones;
import informacion.CodigoUnico;
import utils.Output;

public class RecuperarFactura implements EjecutarOpcion {
	CodigoUnico codigoFact;

	@Override
	public void ejecuta(Administrador admin) {
		try {
			codigoFact = PedirDatosOpciones.CodigoFactura();
		} catch (CodFacturaError e) {
			Output.outPut(e.getMessage());
		}

		try {
			Output.outPut(admin.recuperarFactura(codigoFact).toString());
		} catch (CodFactNotFound e) {
			Output.outPut(e.getMessage());
		}
	}

}
