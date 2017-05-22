package mvc.vista.facturas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import mvc.controlador.ImplementacionControlador;
import mvc.modelo.ImplementacionModelo;
import utils.Mensajes;

public class PestanyaFacturas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 166287590810877523L;

	public PestanyaFacturas(ImplementacionModelo modelo, ImplementacionControlador controlador) {
		super();
		setLayout(new BorderLayout());
		JPanel emitir = new JPanel(new BorderLayout());
		JPanel emitirFactura = new JPanel();
		JPanel mostrarFactEmitida = new JPanel();
		JPanel listFactCliente = new JPanel(new BorderLayout());
		JPanel mostrarFactCliente = new JPanel(new BorderLayout());

		// emitir factura
		emitirFactura.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.EAST;

		JLabel emitirFacturaLabelFecha = new JLabel(
				"<html>Introducir el mes y el año<br/>correspondiente a la factura:</html>");
		JLabel emitirFacturaLabelNIF = new JLabel("Introduce el NIF del cliente:");
		//
		Date hoy = new Date();
		JSpinner spinnerFecha = new JSpinner(new SpinnerDateModel(hoy, null, hoy, Calendar.MONTH));
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFecha, "MM/yyyy");
		spinnerFecha.setEditor(dateEditor);
		//
		JTextField nif = new JTextField(10);
		JButton emitirButton = new JButton("Emitir");

		// añadir al panel emitir factura
		emitirFactura.add(emitirFacturaLabelFecha, c);
		emitirFactura.add(emitirFacturaLabelNIF, c);
		emitirFactura.add(emitirButton, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		emitirFactura.add(spinnerFecha, c);
		c.gridx = 1;
		c.gridy = GridBagConstraints.RELATIVE;
		emitirFactura.add(nif, c);

		// mostrar Factura Creada
		JTextArea mostrarFacturaEmitidaDatos = new JTextArea(
				"Los datos de la factura se mostrarán aquí después de ser emitida.");

		// añadir a panel emitir
		emitir.add(emitirFactura, BorderLayout.NORTH);
		mostrarFactEmitida.add(mostrarFacturaEmitidaDatos);
		emitir.add(mostrarFactEmitida, BorderLayout.CENTER);

		// listFactCliente
		JLabel buscarFacturaLabelFecha = new JLabel("<html>Buscar facturas de un cliente<br/>introduce el NIF:</html>");
		JTextField nifBuscar = new JTextField(10);
		JButton buscar = new JButton("Buscar");
		JPanel panelBuscarNif = new JPanel(new FlowLayout());
		panelBuscarNif.add(buscarFacturaLabelFecha);
		panelBuscarNif.add(nifBuscar);
		panelBuscarNif.add(buscar);
		listFactCliente.add(panelBuscarNif, BorderLayout.NORTH);

		JTable tablaFacturas = new JTable();
		JScrollPane scroll = new JScrollPane(tablaFacturas);
		JPanel panelTabla = new JPanel();
		panelTabla.add(scroll);
		listFactCliente.add(panelTabla, BorderLayout.CENTER);

		// ClienteMostrar
		JLabel jlBuscarFactura = new JLabel("<html>Buscar factura por código <br/> ej: 'NIF-NúmeroFactura' </html>");
		JTextField factBuscar = new JTextField(10);
		Border borderDefault = factBuscar.getBorder();

		JButton buscarFact = new JButton("Buscar");
		JPanel buscarFactura = new JPanel(new FlowLayout());
		buscarFactura.add(jlBuscarFactura);
		buscarFactura.add(factBuscar);
		buscarFactura.add(buscarFact);

		mostrarFactCliente.add(buscarFactura, BorderLayout.NORTH);

		JTextArea mostrarFacturaDatos = new JTextArea(
				"Los datos de la factura se mostrarán aquí después de ser seleccionada.");
		JPanel jpMostrarDatos = new JPanel();
		jpMostrarDatos.add(mostrarFacturaDatos);

		mostrarFactCliente.add(jpMostrarDatos, BorderLayout.CENTER);

		// actions
		tablaFacturas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tablaFacturas.getModel().getRowCount() == 1)
					mostrarFacturaDatos.setText(modelo.mostrarInfoFactura(nifBuscar.getText(), 0));
				if (e.getValueIsAdjusting() != true) {
					int fila = tablaFacturas.convertRowIndexToModel(tablaFacturas.getSelectedRow());
					mostrarFacturaDatos.setText(modelo.mostrarInfoFactura(nifBuscar.getText(), fila));

				}
			}
		});
		emitirButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!modelo.existeCliente(nif.getText())) {
					nif.setBorder(BorderFactory.createLineBorder(Color.red));
					nif.setText("");
					Mensajes.ERRORNIFNOTFOUND.getDescripcion();

				} else {
					nif.setBorder(borderDefault);
					Date aux = (Date) spinnerFecha.getValue();
					LocalDate localDate = aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int mes = localDate.getMonthValue();
					int año = localDate.getYear();

					mostrarFacturaEmitidaDatos.setText(controlador.emitirFactura(mes, año, nif.getText()));
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
					AbstractTableModel tableModel = modelo.getFacturasCliente(nifBuscar.getText());
					tablaFacturas.setModel(tableModel);
					tablaFacturas.setDefaultEditor(Object.class, null);
					tablaFacturas.setAutoCreateRowSorter(true);
					tablaFacturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				}
			}

		});

		buscarFact.addActionListener(new ActionListener() { // TERMINAR
			@Override
			public void actionPerformed(ActionEvent e) {
				String facturaInfo = modelo.getFactura(factBuscar.getText());
				if (facturaInfo == "") {
					factBuscar.setBorder(BorderFactory.createLineBorder(Color.red));
					factBuscar.setText("");
					Mensajes.ERRORFACTNOTFOUND.getDescripcion();

				} else {
					factBuscar.setBorder(borderDefault);
					mostrarFacturaDatos.setText(modelo.getFactura(factBuscar.getText()));
				}
			}

		});

		//
		add(emitir, BorderLayout.WEST);
		add(listFactCliente, BorderLayout.CENTER);
		add(mostrarFactCliente, BorderLayout.EAST);
	}

}
