package informacion; //DONE

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

import llamadas.Llamada;

public class PeriodoTiempo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 893323757472211179L;
	//atributos
	private LocalDateTime fechaPeriodoInicio;
	private LocalDateTime fechaPeriodoFinal;

	// constructor
	public PeriodoTiempo() {
		super();
		this.fechaPeriodoInicio = null;
		this.fechaPeriodoFinal = null;
	}

	public PeriodoTiempo(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
		this.fechaPeriodoInicio = fechaInicio;
		this.fechaPeriodoFinal = fechaFinal;
	}

	// set
	public PeriodoTiempo setFechaInicio(LocalDate fecha, LocalTime hora) {
		this.fechaPeriodoInicio = LocalDateTime.of(fecha, hora);
		return this;
	}
	public PeriodoTiempo setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaPeriodoInicio = fechaInicio;
		return this;
	}

	public PeriodoTiempo setFechaFinal(LocalDate fecha, LocalTime hora) {
		this.fechaPeriodoFinal = LocalDateTime.of(fecha, hora);
		return this;
	}
	
	public PeriodoTiempo setFechaFinal(LocalDateTime fechaFinal) {
		this.fechaPeriodoInicio = fechaFinal;
		return this;
	}


	// get
	public LocalDateTime getFechaInicio() {
		return this.fechaPeriodoInicio;
	}

	public LocalDateTime getFechaFinal() {
		return this.fechaPeriodoFinal;
	}

	// main
	public ArrayList<Llamada> listaLlamadasEnPeriodo(Collection<Llamada> collection) {
		ArrayList<Llamada> listaLlamadaTemp = new ArrayList<Llamada>();
		for (Llamada llamada : collection) {			
			if (llamada.getFecha().isAfter(this.fechaPeriodoInicio.minusDays(1))
					&& llamada.getFecha().isBefore(this.fechaPeriodoFinal.plusDays(1)))
				listaLlamadaTemp.add(llamada);
		}
		return listaLlamadaTemp;
	}

}
