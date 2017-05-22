package mvc.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map.Entry;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import clientes.Cliente;
import clientes.Particular;
import excepciones.CodFactNotFound;
import facturas.Factura;
import informacion.CodigoUnico;
import informacion.PeriodoTiempo;
import llamadas.Llamada;
import main.Administrador;
import mvc.vista.InformaVista;
import serializacion.GuardarDatos;
import serializacion.RecuperarDatos;
import tarifa.Tarifa;
import utils.GetFecha;

public class ImplementacionModelo implements CambioModelo, InterrogaModelo {
	private InformaVista vista;
	private Administrador admin;
	private DefaultTableModel modeloTablaClientes;

	public ImplementacionModelo() {
		admin = RecuperarDatos.recuperarDatos();
	}

	public void setVista(InformaVista vista) {
		this.vista = vista;
	}

	public void guardarDatos() {
		GuardarDatos.guardarDatos(admin);
	}

	@Override
	public boolean existeCliente(String nif) {
		if (admin.getListaClientes().containsKey(nif)) {
			return true;
		}
		return false;
	}

	@Override
	public void crearModeloTablaClientes() {
		modeloTablaClientes = new DefaultTableModel(new Object[] { "NIF", "Nombre" }, 0);
		for (Entry<String, Cliente> cliente : admin.getListaClientes().entrySet()) {
			Cliente clienteAux = cliente.getValue();
			String nombre = clienteAux.getNombre();
			if (clienteAux instanceof Particular)
				nombre += " " + ((Particular) clienteAux).getApellidos();
			modeloTablaClientes.addRow(new Object[] { cliente.getKey(), nombre });
		}
	}

	@Override
	public AbstractTableModel getClientes() {
		crearModeloTablaClientes();
		return modeloTablaClientes;
	}

	@Override
	public void anyadirCliente(Cliente cliente) {
		admin.altaCliente(cliente);
		vista.actualizarFalse();
		crearModeloTablaClientes();
		vista.actualizaTablaClientes();
		guardarDatos();
	}

	@Override
	public void cambiarTarifa(String nif, Tarifa tarifa) {
		admin.cambiarTarifaCliente(nif, tarifa);
		vista.actualizaTarifaCliente(tarifa.toString());
		crearModeloTablaClientes();
		guardarDatos();
	}

	@Override
	public void borrarCliente(String nif) {
		admin.borrarCliente(nif);
		vista.actualizarFalse();
		vista.actualizaClienteBorrado(); // Limpiar el mostrarCliente
		crearModeloTablaClientes(); // actualizar el modelo
		vista.actualizaTablaClientes(); // set el modelo en la tabla
		guardarDatos();
	}

	@Override
	public void mostrarInfoCliente(int fila) {
		Cliente clienteAux = null;
		int i = 0;
		for (Cliente c : admin.getListaClientes().values()) {
			if (i == fila) {
				clienteAux = c;
				break;
			}
			i++;
		}
		vista.setInfoClienteNombre(clienteAux.getNombre().toString());
		vista.setInfoClienteNIF(clienteAux.getNIF().toString());
		vista.setInfoClienteDireccion(clienteAux.getDireccion().toString());
		vista.setInfoClienteEmail(clienteAux.getEmail().toString());
		vista.setInfoClienteTarifa(clienteAux.getTarifa().toString());
		vista.setInfoClienteFechaAlta(admin.fecha(clienteAux.getFecha()));
		//
		String apellidos = "";

		boolean mostrar = false;
		if (clienteAux instanceof Particular) {
			apellidos = ((Particular) clienteAux).getApellidos().toString();
			mostrar = true;
		}
		vista.setInfoClienteApellidos(apellidos, mostrar);
	}

	@Override
	public String emitirFactura(int mes, int año, String nif) {
		LocalDate inicioMes = LocalDate.of(año, mes, 1);
		LocalDate finalMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());
		LocalTime horas = LocalTime.of(0, 0);

		LocalDateTime auxInicio = LocalDateTime.of(inicioMes, horas);
		LocalDateTime auxFinal = LocalDateTime.of(finalMes, horas);

		PeriodoTiempo periodoAux = new PeriodoTiempo(auxInicio, auxFinal);

		String infoFact = admin.recuperarFactura(admin.emitirFacturaCliente(nif, periodoAux)).toString();
		guardarDatos();
		return infoFact;
	}

	@Override
	public AbstractTableModel getFacturasCliente(String nif) {
		DefaultTableModel modeloTablaFacturas = new DefaultTableModel(new Object[] { "Código Fact", "Importe" }, 0);
		for (Entry<Integer, Factura> factura : admin.getListaClientes().get(nif).getListaFacturas().entrySet()) {
			String codigoFact = nif + "-" + factura.getKey().toString();
			String importe = String.valueOf(factura.getValue().getImporte());
			modeloTablaFacturas.addRow(new Object[] { codigoFact, importe });
		}
		return modeloTablaFacturas;
	}

	@Override
	public String mostrarInfoFactura(String nif, int fila) {
		int i = 0;
		for (Entry<Integer, Factura> factura : admin.getListaClientes().get(nif).getListaFacturas().entrySet()) {
			if (i == fila) {
				return factura.toString();
			}
			i++;
		}
		return "ERROR";
	}

	@Override
	public String altaLlamada(String nif, String tlf, int dur) {
		Llamada llamada = new Llamada(tlf, dur);
		admin.altaLlamada(nif, llamada);
		guardarDatos();
		return llamada.toString();
	}

	@Override
	public AbstractTableModel getLlamadasCliente(String nif) {
		DefaultTableModel modeloTablaFacturas = new DefaultTableModel(
				new Object[] { "Núm. Telf.", "Duración llamada", "Fecha llamada" }, 0);
		for (Llamada llamada : admin.getListaLlamadas().get(nif)) {
			String numTel = llamada.getNumeroTelefonoLlamado();
			String duracion = String.valueOf(llamada.getduracionLlamadaSegundos());
			String fechaLlamada = admin.fecha(llamada.getFecha());
			modeloTablaFacturas.addRow(new Object[] { numTel, duracion, fechaLlamada });
		}
		return modeloTablaFacturas;
	}

	@Override
	public void mostrarInfoClienteBusqueda(String nif) {
		Cliente clienteAux = admin.getCliente(nif);

		vista.setInfoClienteNombre(clienteAux.getNombre().toString());
		vista.setInfoClienteNIF(clienteAux.getNIF().toString());
		vista.setInfoClienteDireccion(clienteAux.getDireccion().toString());
		vista.setInfoClienteEmail(clienteAux.getEmail().toString());
		vista.setInfoClienteTarifa(clienteAux.getTarifa().toString());
		vista.setInfoClienteFechaAlta(admin.fecha(clienteAux.getFecha()));
		//
		String apellidos = "";

		boolean mostrar = false;
		if (clienteAux instanceof Particular) {
			apellidos = ((Particular) clienteAux).getApellidos().toString();
			mostrar = true;
		}
		vista.setInfoClienteApellidos(apellidos, mostrar);
	}

	@Override
	public String getFactura(String cod) {
		String aux = "";
		String[] codigoSplit = cod.split("-");
		if (codigoSplit.length == 2) {
			int serie = Integer.parseInt(codigoSplit[1]);
			CodigoUnico codigo = new CodigoUnico(codigoSplit[0], serie);
			try {
				aux = admin.recuperarFactura(codigo).toString();
			} catch (CodFactNotFound e) {
				aux = "";
			}
		}
		return aux;
	}

	@Override
	public ArrayList<GetFecha> genClientes(LocalDateTime fechaInicio,LocalDateTime fechaFinal) {
		return admin.genClientes(admin, fechaInicio, fechaFinal);
	}

	@Override
	public ArrayList<GetFecha> genFacturas(String nif, LocalDateTime fechaInicio,LocalDateTime fechaFinal) {
		return admin.genFacturas(admin, nif, fechaInicio, fechaFinal);
	}

	@Override
	public ArrayList<GetFecha> genLlamadas(String nif, LocalDateTime fechaInicio,LocalDateTime fechaFinal) {
		return admin.genLlamadas(admin, nif, fechaInicio, fechaFinal);
	}

}
