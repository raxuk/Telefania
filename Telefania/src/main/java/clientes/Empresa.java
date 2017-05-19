package clientes;

import informacion.Direccion;
import tarifa.Tarifa;

public class Empresa extends Cliente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7383438443636438332L;

	public Empresa() {
		super();
	}

	public Empresa(String nombre, String NIF, Direccion direccion, String email, Tarifa tarifa) {
		super(nombre, NIF, direccion, email, tarifa);
	}
}
