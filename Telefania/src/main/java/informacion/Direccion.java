package informacion;

import java.io.Serializable;

import utils.ToString;

public class Direccion implements ToString, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1537977209825526406L;
	private String poblacion;
	private int codigoPostal;
	private String provincia;

	public Direccion() {
		super();
	}

	public Direccion(String provincia, String poblacion, int codPostal) {
		this.provincia = provincia;
		this.poblacion = poblacion;
		this.codigoPostal = codPostal;

	}

	public String getProvincia() {
		return provincia;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Provincia: ");
		builder.append(provincia);
		builder.append("\nPoblación: ");
		builder.append(poblacion);
		builder.append("\nCódigo Postal: ");
		builder.append(codigoPostal);
		return builder.toString();
	}
}
