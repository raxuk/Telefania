package clientes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.TreeMap;

import facturas.Factura;
import informacion.Direccion;
import tarifa.Tarifa;
import utils.GetFecha;
import utils.ToString;

public abstract class Cliente implements GetFecha, ToString, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1187849638340539578L;
	private String nombre;
	private String NIF;
	private Direccion direccion;
	private String email;
	private LocalDateTime fechaAlta;
	private Tarifa tarifa;

	private TreeMap<Integer, Factura> listaFacturas;

	public Cliente() {
		super();
		this.nombre = "";
		this.NIF = "";
		this.direccion = new Direccion();
		this.email = "";
		this.fechaAlta = LocalDateTime.now();
		this.tarifa = null;
		this.listaFacturas = new TreeMap<Integer, Factura>();
	}

	public Cliente(String nombre, String nif, Direccion direccion, String email, Tarifa tarifa) {
		this.nombre = nombre;
		this.NIF = nif;
		this.direccion = direccion;
		this.email = email;
		this.tarifa = tarifa;
		this.fechaAlta = LocalDateTime.now();
		this.listaFacturas = new TreeMap<Integer, Factura>();
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getNIF() {
		return this.NIF;
	}

	public Direccion getDireccion() {
		return this.direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public Tarifa getTarifa() {
		return this.tarifa;
	}

	public TreeMap<Integer, Factura> getListaFacturas() {
		return this.listaFacturas;
	}
	
	public LocalDateTime getFecha() {
		return this.fechaAlta;
	}

	public void setTarifa(Tarifa tarifaNueva) {
		this.tarifa = tarifaNueva;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n =============== ");
		builder.append("\n Nombre: ");
		builder.append(this.nombre);
		builder.append("\n NIF: ");
		builder.append(this.NIF);
		builder.append("\n Dirección: ");
		builder.append(this.direccion);
		builder.append("\n Email: ");
		builder.append(this.email);
		builder.append("\n Tarifa: ");
		builder.append(this.tarifa.toString());
		builder.append("\n Fecha Alta: ");
		builder.append(this.getFecha().toString());
		builder.append("\n");

		return builder.toString();
	}

}
