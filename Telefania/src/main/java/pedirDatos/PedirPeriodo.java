package pedirDatos;

import java.time.LocalDate;
import java.time.LocalTime;
import informacion.PeriodoTiempo;
import utils.Input;

public class PedirPeriodo {
	static PeriodoTiempo periodo = new PeriodoTiempo();

	public static PeriodoTiempo pedirPeriodoTiempo() {
		utils.Mensajes.FECHAINICIO.getDescripcion();
		LocalDate fechaInAux = Input.LDInput();
		LocalTime horaInAux = LocalTime.of(0, 0, 1);
		periodo.setFechaInicio(fechaInAux, horaInAux);
		//
		utils.Mensajes.FECHAFINAL.getDescripcion();
		LocalDate fechaFinAux = Input.LDInput();
		LocalTime horaFinAux = LocalTime.of(0, 0, 1);
		periodo.setFechaFinal(fechaFinAux, horaFinAux);
		return periodo;
	}

}
