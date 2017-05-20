package mvc.modelo;

import java.util.Map.Entry;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import clientes.Cliente;
import clientes.Particular;
import main.Administrador;
import mvc.vista.InformaVista;
import serializacion.GuardarDatos;
import serializacion.RecuperarDatos;
import tarifa.Tarifa;

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

	public boolean existeCliente(String nif) {
		if (admin.getListaClientes().containsKey(nif)) {
			return true;
		}
		return false;
	}

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
		vista.actualizaClienteBorrado(); //Limpiar el mostrarCliente
		crearModeloTablaClientes(); //actualizar el modelo
		vista.actualizaTablaClientes(); //set el modelo en la tabla
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
		//
		String apellidos = "";
		
		boolean mostrar = false;
		if(clienteAux instanceof Particular) {
			apellidos=((Particular)clienteAux).getApellidos().toString();
			mostrar= true;
		}
		vista.setInfoClienteApellidos(apellidos, mostrar);
		}
}
