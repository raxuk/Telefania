package tarifa; //DONE

import java.io.Serializable;

import utils.ToString;

public abstract class Tarifa implements Serializable, ToString {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8639436948877628966L;
	// atributos
	private double precioEurosMin;

	// constructor
	public Tarifa(double precio) {
		this.precioEurosMin = precio;
	}

	// get
	public double getPrecio() {
		return precioEurosMin;
	}

	@Override
	public String toString() {
		return null;
	}
}
