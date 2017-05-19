package menuGenericidad;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import utils.GetFecha;

public abstract class ConjuntoGenerico implements GetFecha {
	public ConjuntoGenerico() {
		super();
	}

	public static ArrayList<GetFecha> genericidad(Collection<? extends GetFecha> lista, LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
		ArrayList<GetFecha> listaGenericidad = new ArrayList<GetFecha>();

		for (GetFecha dato : lista) {
			if (dato.getFecha().isAfter(fechaInicio) && dato.getFecha().isBefore(fechaFinal))
				listaGenericidad.add(dato);
		}
		return listaGenericidad;
	}

}