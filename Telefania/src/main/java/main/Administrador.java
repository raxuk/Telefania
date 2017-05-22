package main;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

import clientes.Cliente;
import excepciones.CodFactNotFound;
import excepciones.NifNotFoundException;
import facturas.Factura;
import facturas.Importe;
import informacion.CodigoUnico;
import informacion.PeriodoTiempo;
import llamadas.Llamada;
import menuGenericidad.ConjuntoGenerico;
import tarifa.Tarifa;
import utils.GetFecha;
import utils.Mensajes;

public class Administrador implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -433086464915797562L;
	private static volatile Administrador adminInstancia = null;
	private TreeMap<String, Cliente> listaClientes;
	private TreeMap<String, ArrayList<Llamada>> listaLlamadas;

	private Administrador() {
		super();
		this.listaClientes = new TreeMap<String, Cliente>();
		this.listaLlamadas = new TreeMap<String, ArrayList<Llamada>>();
	}

	// get
	public static Administrador getAdministradorInstancia() {
		if (adminInstancia == null)
			synchronized (Administrador.class) {
				if (adminInstancia == null)
					adminInstancia = new Administrador();
			}
		return adminInstancia;
	}

	// clientes
	public void altaCliente(Cliente cliente) {
		if (!listaClientes.containsKey(cliente.getNIF())) {
			listaClientes.put(cliente.getNIF(), cliente);
			ArrayList<Llamada> listaLlamadasTemp = new ArrayList<Llamada>();
			listaLlamadas.put(cliente.getNIF(), listaLlamadasTemp);
		} else {
			Mensajes.ERRORNIFEXISTENTE.getDescripcion();
		}
	}

	public void borrarCliente(String nif) {
		listaClientes.remove(nif);
		if (listaLlamadas.containsKey(nif))
			listaLlamadas.remove(nif);
	}

	public void cambiarTarifaCliente(String nif, Tarifa tarifa) {
		listaClientes.get(nif).setTarifa(tarifa);
	}

	public Cliente getCliente(String nif) throws NifNotFoundException {
		if (!listaClientes.containsKey(nif))
			throw new NifNotFoundException();
		return listaClientes.get(nif);
	}

	public TreeMap<String, Cliente> getListaClientes() {
		return listaClientes;
	}

	public CodigoUnico addFacturaACliente(String nif, Factura factura) {
		getCliente(nif).getListaFacturas().put(factura.getCodigoFactura().getSerie(), factura);
		return factura.getCodigoFactura();
	}

	public Factura crearFacturaCliente(String nif, PeriodoTiempo periodo, Administrador admin) {
		Factura factura = new Factura(getCliente(nif)).setPeriodoFacturacion(periodo);
		factura.setImporte(Importe.calcularImporte(factura, admin));
		return factura;
	}

	// llamadas
	public void altaLlamada(String nif, Llamada llamada) throws NifNotFoundException {
		ArrayList<Llamada> listaAux = new ArrayList<Llamada>();
		if (!listaClientes.containsKey(nif))
			throw new NifNotFoundException();
		else {
			if (!listaLlamadas.containsKey(nif)) {
				listaAux.add(llamada);
				this.listaLlamadas.put(nif, listaAux);
			} else {
				this.listaLlamadas.get(nif).add(llamada);
			}
		}
	}

	public ArrayList<Llamada> listaLlamadasCliente(String nif) throws NifNotFoundException {
		if (!listaClientes.containsKey(nif))
			throw new NifNotFoundException();
		else {
			return this.listaLlamadas.get(nif);
		}
	}

	public TreeMap<String, ArrayList<Llamada>> getListaLlamadas() {
		return listaLlamadas;
	}

	// facturas
	public CodigoUnico obtenerCodigo(Cliente cliente) {
		CodigoUnico temp = new CodigoUnico();
		temp.setIdentificador(cliente.getNIF());
		if (cliente.getListaFacturas().isEmpty()) {
			temp.setSerie(1);
		} else {
			temp.setSerie(cliente.getListaFacturas().lastKey() + 1);
		}
		return temp;
	}

	public CodigoUnico emitirFacturaCliente(String nif, PeriodoTiempo periodo) {
		Factura facturaAux = crearFacturaCliente(nif, periodo, this);
		facturaAux.setPeriodoFacturacion(periodo);
		return addFacturaACliente(nif, facturaAux);
	}

	public Factura recuperarFactura(CodigoUnico codigo) throws CodFactNotFound {
		if (listaClientes.containsKey(codigo.getIdentificador()))
			if (listaClientes.get(codigo.getIdentificador()).getListaFacturas().containsKey(codigo.getSerie()))
				return listaClientes.get(codigo.getIdentificador()).getListaFacturas().get(codigo.getSerie());
		throw new CodFactNotFound();
	}

	public TreeMap<Integer, Factura> recuperarFacturasClienteTodas(String nif) {
		return getCliente(nif).getListaFacturas();
	}

	public ArrayList<GetFecha> genClientes(Administrador admin, LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
		ArrayList<GetFecha> listaGenerica = ConjuntoGenerico.genericidad(admin.getListaClientes().values(), fechaInicio,
				fechaFinal);
		return listaGenerica;
	}

	public ArrayList<GetFecha> genFacturas(Administrador admin, String nif, LocalDateTime fechaInicio,
			LocalDateTime fechaFinal) {
		if (!admin.getListaClientes().containsKey(nif))
			throw new NifNotFoundException();
		else {
			ArrayList<GetFecha> listaGenerica = ConjuntoGenerico
					.genericidad(admin.getCliente(nif).getListaFacturas().values(), fechaInicio, fechaFinal);
			return listaGenerica;
		}
	}

	public ArrayList<GetFecha> genLlamadas(Administrador admin, String nif, LocalDateTime fechaInicio,
			LocalDateTime fechaFinal) {
		if (!admin.getListaClientes().containsKey(nif))
			throw new NifNotFoundException();
		else {
			ArrayList<GetFecha> listaGenerica = ConjuntoGenerico.genericidad(admin.getListaLlamadas().get(nif),
					fechaInicio, fechaFinal);
			return listaGenerica;
		}
	}

	//

	public String fecha(LocalDateTime dateTime) {
		StringBuilder builder = new StringBuilder();
		builder.append(dateTime.getDayOfMonth());
		builder.append("/");
		builder.append(dateTime.getMonthValue());
		builder.append("/");
		builder.append(dateTime.getYear());

		return builder.toString();
	}
}
