package menuGenericidad;

import java.time.LocalDateTime;
import java.util.ArrayList;

import excepciones.NifNotFoundException;
import main.Administrador;
import utils.GetFecha;

public class GenFactura {

	public ArrayList<GetFecha> ejecuta(Administrador admin, String nif, LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
		if (!admin.getListaClientes().containsKey(nif))
			throw new NifNotFoundException();
		else {
			ArrayList<GetFecha> listaGenerica = ConjuntoGenerico
					.genericidad(admin.getCliente(nif).getListaFacturas().values(), fechaInicio, fechaFinal);
			//
//			publicarLista(listaGenerica);
			return listaGenerica;
		}
	}

//	public void publicarLista(ArrayList<GetFecha> lista) {
//		if (lista.isEmpty())
//			Mensajes.NOTFOUND.getDescripcion();
//		for (GetFecha cliente : lista)
//			Output.outPut(cliente.toString());
//	}
}
