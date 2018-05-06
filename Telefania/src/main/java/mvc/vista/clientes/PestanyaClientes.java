package mvc.vista.clientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import mvc.controlador.Controlador;
import mvc.modelo.ImplementacionModelo;
import utils.Mensajes;

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
		super();
		this.modelo = modelo;
		anyadirCliente = new AnyadirCliente(modelo, controlador);
		clienteMostrar = new MostrarInfoCliente(modelo, controlador);
		setLayout(new BorderLayout());

		// ClienteCrear //
		JPanel jpAnyadirCliente = new JPanel();
		jpAnyadirCliente.add(anyadirCliente);
		add(jpAnyadirCliente, BorderLayout.WEST);

		// ClienteLista
		tablaClientes = new JTable();
		actualizarTablaClientes();
		JScrollPane scroll = new JScrollPane(tablaClientes);
		add(scroll, BorderLayout.CENTER);

		// ClienteMostrar
		JPanel jpInfoCliente = new JPanel(new BorderLayout());
		jpInfoCliente.add(clienteMostrar, BorderLayout.CENTER);
		
		JLabel jlBuscarCliente = new JLabel("<html>Buscar cliente, introduce el NIF:</html>");
		JTextField nifBuscar = new JTextField(10);
		Border borderDefault = nifBuscar.getBorder();

		JButton buscar = new JButton("Buscar");
		JPanel buscarCliente = new JPanel(new FlowLayout());
		buscarCliente.add(jlBuscarCliente);
		buscarCliente.add(nifBuscar);
		buscarCliente.add(buscar);
		
		jpInfoCliente.add(buscarCliente, BorderLayout.NORTH);
		
		add(jpInfoCliente, BorderLayout.EAST);

		
		//action
		tablaClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tablaClientes.getModel().getRowCount() == 1)
					modelo.mostrarInfoCliente(0);
				if (e.getValueIsAdjusting() != true) {
					if (actualizar) {
						int fila = tablaClientes.convertRowIndexToModel(tablaClientes.getSelectedRow());
						modelo.mostrarInfoCliente(fila);
					}
					actualizar = true;
				}
			}
		});
		
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!modelo.existeCliente(nifBuscar.getText())) {
					nifBuscar.setBorder(BorderFactory.createLineBorder(Color.red));
					nifBuscar.setText("");
					Mensajes.ERRORNIFNOTFOUND.getDescripcion();

				} else {
					nifBuscar.setBorder(borderDefault);
					modelo.mostrarInfoClienteBusqueda(nifBuscar.getText());
				}
			}

		});
		
		
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
