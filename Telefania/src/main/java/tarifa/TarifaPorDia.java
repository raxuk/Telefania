package tarifa;

import java.time.DayOfWeek;

import llamadas.Llamada;

public class TarifaPorDia extends Tarifa {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2030003020380046771L;

	private Tarifa tarifa;
	private DayOfWeek dia;
	
	public TarifaPorDia(Tarifa tarifa, DayOfWeek dia) {
		super(0);
		this.tarifa=tarifa;
		this.dia = dia;
	}
	
	public double getPrecio(Llamada llamada){
		if(llamada.getFecha().getDayOfWeek().equals(dia))
			return super.getPrecio();
		else{
			return tarifa.getPrecio();
		}
	}
	
	@Override
	public String toString(){
		return tarifa.toString() + ", " + dia.name() + " gratis";
	}
}
