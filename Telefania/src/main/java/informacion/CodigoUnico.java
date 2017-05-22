package informacion;

import java.io.Serializable;

public class CodigoUnico implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2870224310261857964L;
	// atributo
	private String identificador;
	private int serie;

	// constructor
	public CodigoUnico() {
		super();
	}

	public CodigoUnico(String id, int serie) {
		this.identificador = id;
		this.serie = serie;
	}

	// set
	public CodigoUnico setIdentificador(String id) {
		this.identificador = id;
		return this;
	}

	public CodigoUnico setSerie(int serie) {
		this.serie = serie;
		return this;
	}

	// get
	public String getIdentificador() {
		return this.identificador;
	}

	public int getSerie() {
		return this.serie;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nCódigo único de la factura: ");
		builder.append(this.getIdentificador());
		builder.append("-");
		builder.append(this.getSerie());
		builder.append("\n");

		return builder.toString();
	}
}