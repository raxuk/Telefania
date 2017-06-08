package facturas;

import java.io.Serializable;
import java.time.LocalDateTime;

import clientes.Cliente;
import informacion.CodigoUnico;
import informacion.PeriodoTiempo;
import main.Administrador;
import tarifa.Tarifa;
import utils.GetFecha;
import utils.ToString;

public class Factura implements GetFecha, ToString,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4398475423113101433L;
	private CodigoUnico codigoFactura;
	private Tarifa tarifa;
	private LocalDateTime fechaEmisionFactura;
	private PeriodoTiempo periodoTiempo;
	private double importe;

	public Factura(Cliente cliente) {
		super();
		this.codigoFactura = Administrador.getAdministradorInstancia().obtenerCodigo(cliente);
		this.tarifa = cliente.getTarifa();
		this.fechaEmisionFactura = LocalDateTime.now();
		this.periodoTiempo = null;
		this.importe = -1;
	}

	// set
	public Factura setPeriodoFacturacion(PeriodoTiempo periodo) {
		this.periodoTiempo = periodo;
		return this;
	}
	
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	//get
	public CodigoUnico getCodigoFactura() {
		return this.codigoFactura;
	}

	public Tarifa getTarifa() {
		return this.tarifa;
	}

	public PeriodoTiempo getPeriodoTiempo() {
		return this.periodoTiempo;
	}
	
	public double getImporte() {
		return this.importe;
	}

	public LocalDateTime getFecha() {
		return this.fechaEmisionFactura;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("=============== ");
		builder.append(this.codigoFactura);
		builder.append("Fecha de emisión de la factura: ");
		builder.append(fechaEmisionFactura.getDayOfMonth());
		builder.append("/");
		builder.append(fechaEmisionFactura.getMonthValue());
		builder.append("/");
		builder.append(fechaEmisionFactura.getYear());
		builder.append("\n\nPeriodo de facturación: ");
		builder.append(this.periodoTiempo.toString());
		builder.append("\n\nTarifa: ");
		builder.append(this.tarifa);
		builder.append("\n\nImporte: ");
		builder.append(this.importe);
		builder.append(" €");

		return builder.toString();
	}

}
