package tarifa;

import llamadas.Llamada;

public class TarifaPorHoras extends Tarifa {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3144774212664241535L;

	private Tarifa tarifa;
	private int inicioLlamada;
	private int finalLlamada;

	public TarifaPorHoras(Tarifa tarifa, int inicioLlamada, int finalLlamada) {
		super(0.05);
		this.tarifa = tarifa;
		this.inicioLlamada = inicioLlamada;
		this.finalLlamada = finalLlamada;
	}

	public double getPrecio(Llamada llamada) {
		int horaLlamada = llamada.getFecha().getHour();
		if (horaLlamada >= inicioLlamada && horaLlamada <= finalLlamada) {
			return super.getPrecio();
		} else {
			return tarifa.getPrecio();
		}

	}

	@Override
	public String toString() {
		return tarifa.toString() + ", descuento de: " + inicioLlamada + "-" + finalLlamada;
	}
}
