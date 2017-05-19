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

	//get
	
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	public CodigoUnico getCodigoFactura() {
		return this.codigoFactura;
	}

	public Tarifa getTarifa() {
		return this.tarifa;
	}

	public LocalDateTime getFechaInicioPeriodo() {
		return this.periodoTiempo.getFechaInicio();
	}

	public LocalDateTime getFechaFinalPeriodo() {
		return this.periodoTiempo.getFechaFinal();
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
		builder.append("\n =============== ");
		builder.append("\n Código de factura: ");
		builder.append(this.codigoFactura);
		builder.append("\n Fecha de emisión de la factura: ");
		builder.append(this.fechaEmisionFactura.toString());
		builder.append("\n Periodo de facturación: ");
		builder.append(this.periodoTiempo.getFechaInicio());
		builder.append(" - ");
		builder.append(this.periodoTiempo.getFechaFinal());
		builder.append("\n Tarifa básica: ");
		builder.append(this.tarifa);
		builder.append("\n Importe: ");
		builder.append(this.importe);

		return builder.toString();
	}

}
