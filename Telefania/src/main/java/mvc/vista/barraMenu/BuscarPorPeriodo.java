package mvc.vista.barraMenu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

import clientes.Cliente;
import facturas.Factura;
import llamadas.Llamada;
import mvc.modelo.ImplementacionModelo;
import utils.GetFecha;

public class BuscarPorPeriodo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -170929225116124309L;
	private ImplementacionModelo modelo;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFinal;
	private JTextField nif;
	private ArrayList<GetFecha> lista;
	private String[] filas = new String[3];
	private JPanel panel;

	public BuscarPorPeriodo(int tipoBusqueda, ImplementacionModelo modelo) {
		this.modelo = modelo;
		panel = new JPanel(new BorderLayout());

		Date hoy = new Date();
		JSpinner spinnerFechaInicio = new JSpinner(new SpinnerDateModel(hoy, null, hoy, Calendar.DATE));
		JSpinner.DateEditor dateEditorIncio = new JSpinner.DateEditor(spinnerFechaInicio, "dd/MM/yyyy");
		spinnerFechaInicio.setEditor(dateEditorIncio);

		JSpinner spinnerFechaFinal = new JSpinner(new SpinnerDateModel(hoy, null, hoy, Calendar.DATE));
		JSpinner.DateEditor dateEditorFinal = new JSpinner.DateEditor(spinnerFechaFinal, "dd/MM/yyyy");
		spinnerFechaFinal.setEditor(dateEditorFinal);

		JPanel panelNif = new JPanel();
		JLabel nifLabel = new JLabel("NIF Cliente:");
		nif = new JTextField(10);
		panelNif.add(nifLabel);
		panelNif.add(nif);

		JButton buscar = new JButton("Buscar");

		// action
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LocalTime horas = LocalTime.of(0, 0);

				Date dateInicio = (Date) spinnerFechaInicio.getValue();
				LocalDate localDateInicio = dateInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				Date dateFinal = (Date) spinnerFechaFinal.getValue();
				LocalDate localDateFinal = dateFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				fechaInicio = LocalDateTime.of(localDateInicio, horas);
				fechaFinal = LocalDateTime.of(localDateFinal, horas);

				if (tipoBusqueda == 0) {
					genericidadClientes();
				}
				if (tipoBusqueda == 1) {
					genericidadFacturas();
				}
				if (tipoBusqueda == 2) {
					genericidadLlamadas();
				}

				mostrarLista();
			}
		});

		if (tipoBusqueda == 0) {
			setTitle("Busqueda clientes por fechas");
			filas[0] = "NIF";
			filas[1] = "Nombre";
		}
		if (tipoBusqueda == 1) {
			setTitle("Busqueda facturas por fechas");
			panel.add(panelNif, BorderLayout.NORTH);
			filas[0] = "Código Factura";
			filas[1] = "Importe";
		}
		if (tipoBusqueda == 2) {
			setTitle("Busqueda llamadas por fechas");
			panel.add(panelNif, BorderLayout.NORTH);
			filas[0] = "Número de telefono";
			filas[1] = "Duración";
		}

		// añadir y conf
		JPanel spinnerPanel = new JPanel();
		spinnerPanel.add(spinnerFechaInicio);
		spinnerPanel.add(spinnerFechaFinal);
		spinnerPanel.add(buscar);

		panel.add(spinnerPanel,BorderLayout.CENTER);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void genericidadClientes() {
		lista = modelo.genClientes(fechaInicio, fechaFinal);
	}

	public void genericidadFacturas() {
		lista = modelo.genFacturas(nif.getText(), fechaInicio, fechaFinal);
	}

	public void genericidadLlamadas() {
		lista = modelo.genLlamadas(nif.getText(), fechaInicio, fechaFinal);
	}

	public void mostrarLista() { // arreglar mostrar lista //añadir mostrar info
		JTable tableLista = new JTable();
		filas[2] = "Fecha";
						
		DefaultTableModel modeloTablaClientes = new DefaultTableModel(new Object[] { filas[0],filas[1],filas[2] }, 0);
		for (GetFecha coso : lista) {
			if(coso instanceof Cliente) 
			modeloTablaClientes.addRow(new Object[] {((Cliente)coso).getNIF(),((Cliente)coso).getNombre(),((Cliente)coso).getFecha()});
			if(coso instanceof Factura) 
			modeloTablaClientes.addRow(new Object[] {((Factura)coso).getCodigoFactura(),((Factura)coso).getImporte(),((Factura)coso).getFecha()});
			if(coso instanceof Llamada) 
			modeloTablaClientes.addRow(new Object[] {((Llamada)coso).getNumeroTelefonoLlamado(),((Llamada)coso).getduracionLlamadaSegundos(),((Llamada)coso).getFecha()});
		}
		
		tableLista.setModel(modeloTablaClientes);
		tableLista.setDefaultEditor(Object.class, null);
		tableLista.setAutoCreateRowSorter(true);
		tableLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel jpLista = new JPanel();
		jpLista.add(tableLista);
		add(jpLista,BorderLayout.SOUTH);
		pack();
	}
}
