package mvc.controlador;

import java.time.DayOfWeek;
import clientes.Cliente;
import clientes.Empresa;
import clientes.Particular;
import informacion.Direccion;
import mvc.modelo.CambioModelo;
import mvc.vista.InterrogaVista;
import tarifa.Tarifa;
import tarifa.TarifaBasica;
import tarifa.TarifaPorDia;
import tarifa.TarifaPorHoras;

public class ImplementacionControlador implements Controlador {
	private InterrogaVista vista;
	private CambioModelo modelo;

	public ImplementacionControlador() {
	}

	public void setVista(InterrogaVista vista) {
		this.vista = vista;
	}

	public void setModelo(CambioModelo modelo) {
		this.modelo = modelo;
	}

	@Override
	public void anyadirCliente() {
		Cliente clienteNuevo;
		Tarifa tarifa = new TarifaBasica(0.15);

		String nombre = vista.getNombre();
		String nif = vista.getNIF();
		String provincia = vista.getProvincia();
		String poblacion = vista.getPoblacion();
		int codigoPostal = vista.getCodigoPostal();
		Direccion direccion = new Direccion(provincia, poblacion, codigoPostal);
		String email = vista.getEmail();
		String tipoCliente = vista.tipoCliente();
		int tarifaTipo = vista.tipoTarifa();

		if (tarifaTipo == 1) {
			tarifa = new TarifaPorDia(tarifa, DayOfWeek.SUNDAY); // 
		}
		if (tarifaTipo == 2) {
			tarifa = new TarifaPorHoras(tarifa, 16, 20); // 
		}
		if (tarifaTipo == 3) {
			tarifa = new TarifaPorDia(tarifa, DayOfWeek.SUNDAY); // 
			tarifa = new TarifaPorHoras(tarifa, 16, 20); // 
		}

		if (tipoCliente.equals("empresa")) {
			clienteNuevo = new Empresa(nombre, nif, direccion, email, tarifa);
		} else {
			String apellidos = vista.getApellidos();
			clienteNuevo = new Particular(nombre, nif, direccion, email, tarifa).setApellidos(apellidos);
		}
		modelo.anyadirCliente(clienteNuevo);
	}

	@Override
	public void cambiarTarifa(String nif, int tarifaTipo) {
		Tarifa tarifa = new TarifaBasica(0.15);
		if (tarifaTipo == 1) {
			tarifa = new TarifaPorDia(tarifa, DayOfWeek.SUNDAY); // 
		}
		if (tarifaTipo == 2) {
			tarifa = new TarifaPorHoras(tarifa, 16, 20); // 
		}
		if (tarifaTipo == 3) {
			tarifa = new TarifaPorDia(tarifa, DayOfWeek.SUNDAY); // 
			tarifa = new TarifaPorHoras(tarifa, 16, 20); // 
		}
		modelo.cambiarTarifa(nif, tarifa);
	}

	@Override
	public void borrarCliente(String nif) {
		modelo.borrarCliente(nif);
	}

	@Override
	public String emitirFactura(int mes, int año, String nif) {
		return modelo.emitirFactura(mes,año, nif);
	}

	@Override
	public String altaLlamada(String nif, String tlf, int dur) {
		return modelo.altaLlamada(nif, tlf, dur);
	}
}
