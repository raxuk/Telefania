package mvc.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import mvc.controlador.Controlador;
import mvc.modelo.ImplementacionModelo;

public class PestanyaClientes extends JPanel { // NUEVO

	/**
	 * 
	 */
	private static final long serialVersionUID = -554179932991063671L;
	private AnyadirCliente anyadirCliente;
	private MostrarInfoCliente clienteMostrar;
	private JTable tablaClientes;
	private AbstractTableModel tableModel;
	private ImplementacionModelo modelo;
	private boolean actualizar = true;

	public PestanyaClientes(ImplementacionModelo modelo, Controlador controlador) {
		this.modelo = modelo;
		anyadirCliente = new AnyadirCliente(modelo, controlador);
		JPanel clientesLista = new JPanel();
		clienteMostrar = new MostrarInfoCliente(modelo, controlador);

		// ClienteCrear //TODO gridbadlayout
		add(anyadirCliente);

		// ClienteLista
		tablaClientes = new JTable();
		actualizarTablaClientes();
		JScrollPane scroll = new JScrollPane(tablaClientes);
		clientesLista.add(scroll);
		add(scroll);

		// ClienteMostrar
		tablaClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() != true) {
					if (actualizar) {
						int fila = tablaClientes.convertRowIndexToModel(tablaClientes.getSelectedRow());
						modelo.mostrarInfoCliente(fila);
					}
					actualizar=true;
				}
			}
		});
		add(clienteMostrar);

	}

	// metodos
	public void actualizarTablaClientes() {
		tableModel = modelo.getClientes();
		tablaClientes.setModel(tableModel);
		tablaClientes.setDefaultEditor(Object.class, null);
		tablaClientes.getColumnModel().getColumn(0).setMaxWidth(150);
		tablaClientes.setAutoCreateRowSorter(true);
		tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public AnyadirCliente getAnyadirCliente() {
		return this.anyadirCliente;
	}

	public MostrarInfoCliente getClienteMostrar() {
		return this.clienteMostrar;
	}

	public void actualizaTarifaCliente(String tarifa) {
		clienteMostrar.setTarifa(tarifa);
	}

	public void actualizaClienteBorrado() {
		clienteMostrar.limpiar();
	}

	public void actualizarFalse() {
		actualizar = false;
	}
}
