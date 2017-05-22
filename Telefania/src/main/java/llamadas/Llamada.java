package llamadas;

import java.io.Serializable;
import java.time.LocalDateTime;

import utils.GetFecha;
import utils.ToString;

public class Llamada implements GetFecha, ToString, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9208436364358500434L;
	private String numeroTelefonoLlamado;
	private LocalDateTime fechaLlamada;
	private int duracionLlamadaSegundos;

	// Constructor
	public Llamada() {
		this.numeroTelefonoLlamado = "";
		this.duracionLlamadaSegundos = 0;
		this.fechaLlamada = LocalDateTime.now();
	}

	public Llamada(String numeroTelefono, int duracionLlamadaSegundos) {
		this.numeroTelefonoLlamado = numeroTelefono;
		this.duracionLlamadaSegundos = duracionLlamadaSegundos;
		this.fechaLlamada = LocalDateTime.now();
	}

	// get
	public String getNumeroTelefonoLlamado() {
		return this.numeroTelefonoLlamado;
	}

	public int getduracionLlamadaSegundos() {
		return this.duracionLlamadaSegundos;
	}

	public LocalDateTime getFecha() {
		return this.fechaLlamada;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("=============== ");
		builder.append("\nNúmero de telefono llamado: ");
		builder.append(this.numeroTelefonoLlamado);
		builder.append("\nFecha de la llamada: ");
		builder.append(fechaLlamada.getDayOfMonth());
		builder.append("/");
		builder.append(fechaLlamada.getMonthValue());
		builder.append("/");
		builder.append(fechaLlamada.getYear());
		builder.append("\nDuración de la llamada: ");
		builder.append(this.duracionLlamadaSegundos);
		builder.append(" seg");

		return builder.toString();
	}
}
