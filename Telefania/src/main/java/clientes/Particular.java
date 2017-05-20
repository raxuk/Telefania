package clientes;

import informacion.Direccion;
import tarifa.Tarifa;

public class Particular extends Cliente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2387273860053535401L;
	// atributos
	private String apellidos;

	// constructor
	public Particular() {
		super();
		this.apellidos = "";
	}

	public Particular(String nombre, String NIF, Direccion direccion, String email, Tarifa tarifa) {
		super(nombre, NIF, direccion, email, tarifa);
		this.apellidos = "";
	}

	public String getApellidos() {
		return apellidos;
	}

	public Particular setApellidos(String apellidos) {
		this.apellidos = apellidos;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n =============== ");
		builder.append("\n Nombre: ");
		builder.append(super.getNombre());
		builder.append("\n Apellidos: ");
		builder.append(this.apellidos.toString());
		builder.append("\n NIF: ");
		builder.append(super.getNIF());
		builder.append("\n Dirección: ");
		builder.append(super.getDireccion());
		builder.append("\n Email: ");
		builder.append(super.getEmail());
		builder.append("\n Tarifa: ");
		builder.append(super.getTarifa().toString());
		builder.append("\n Fecha Alta: ");
		builder.append(super.getFecha().toString());
		builder.append("\n");

		return builder.toString();
	}

}
