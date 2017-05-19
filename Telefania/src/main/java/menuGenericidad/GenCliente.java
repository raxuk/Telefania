package menuGenericidad;

import java.time.LocalDateTime;
import java.util.ArrayList;
import main.Administrador;
import utils.GetFecha;

public class GenCliente {

	public ArrayList<GetFecha> ejecuta(Administrador admin, LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
		ArrayList<GetFecha> listaGenerica = ConjuntoGenerico.genericidad(admin.getListaClientes().values(), fechaInicio,
				fechaFinal);
		//
//		publicarLista(listaGenerica);
		return listaGenerica;
	}

//	public void publicarLista(ArrayList<GetFecha> lista) {
//		if (lista.isEmpty())
//			Mensajes.NOTFOUND.getDescripcion();
//		for (GetFecha cliente : lista)
//			Output.outPut(cliente.toString());
//	}
}
