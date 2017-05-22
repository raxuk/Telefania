package mvc.vista.llamadas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;

import mvc.controlador.ImplementacionControlador;
import mvc.modelo.ImplementacionModelo;
import utils.Mensajes;

public class PestanyaLlamadas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -932206717918006170L;

	public PestanyaLlamadas(ImplementacionModelo modelo, ImplementacionControlador controlador) {
		super();
		setLayout(new BorderLayout());
		JPanel altaLLamada = new JPanel(new BorderLayout());
		JPanel crearLlamada = new JPanel();
		JPanel mostrarLlamadaCreada = new JPanel();
		JPanel listLlamadasCliente = new JPanel(new BorderLayout());

		// altaLLamada
		crearLlamada.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.EAST;

		JLabel altaLlamadaLabelNIF = new JLabel("Introduce el NIF del cliente:");
		JLabel altaLlamadaLabelTelefono = new JLabel("Introduce el número de telefono llamado:");
		JLabel altaLlamadaLabelDuracion = new JLabel("Introduce la duración de la llamada en segundos:");

		JTextField jtfNIF = new JTextField(10);
		JTextField jtfTelf = new JTextField(10);
		JTextField jtfDur = new JTextField(10);
		Border borderDefault = jtfNIF.getBorder();

		JButton aceptarAlta = new JButton("Aceptar");

		// añadir al panel crearLlamada
		crearLlamada.add(altaLlamadaLabelNIF, c);
		crearLlamada.add(altaLlamadaLabelTelefono, c);
		crearLlamada.add(altaLlamadaLabelDuracion, c);
		crearLlamada.add(aceptarAlta, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;

		crearLlamada.add(jtfNIF, c);
		c.gridx = 1;
		c.gridy = GridBagConstraints.RELATIVE;
		crearLlamada.add(jtfTelf, c);
		crearLlamada.add(jtfDur, c);

		altaLLamada.add(crearLlamada, BorderLayout.NORTH);
		// mostrarLlamadaCreada
		JTextArea jtaMostrarLlamadaCreada = new JTextArea(
				"Los datos de la llamada se mostrarán aquí después de ser creada.");
		mostrarLlamadaCreada.add(jtaMostrarLlamadaCreada);
		altaLLamada.add(mostrarLlamadaCreada, BorderLayout.CENTER);

		// listarLlamadasCliente
		JLabel buscarLlamadaCliente = new JLabel("<html>Buscar llamadas de un cliente<br/>introduce el NIF:</html>");
		JTextField nifBuscar = new JTextField(10);
		JButton buscar = new JButton("Buscar");
		JPanel panelBuscarNif = new JPanel(new FlowLayout());
		panelBuscarNif.add(buscarLlamadaCliente);
		panelBuscarNif.add(nifBuscar);
		panelBuscarNif.add(buscar);
		listLlamadasCliente.add(panelBuscarNif, BorderLayout.NORTH);

		JTable tablaLlamadas = new JTable();
		JScrollPane scroll = new JScrollPane(tablaLlamadas);
		JPanel panelTabla = new JPanel();
		panelTabla.add(scroll);
		listLlamadasCliente.add(panelTabla, BorderLayout.CENTER);

		// actions
		aceptarAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!modelo.existeCliente(jtfNIF.getText())) {
					jtfNIF.setBorder(BorderFactory.createLineBorder(Color.red));
					jtfNIF.setText("");
					Mensajes.ERRORNIFNOTFOUND.getDescripcion();
				} else {
					boolean todoCorrecto = true;

					if (todoCorrecto && !jtfDur.getText().isEmpty()) {
						try {
							Integer.parseInt(jtfDur.getText());
						} catch (NumberFormatException dura) {
							todoCorrecto = false;
							jtfDur.setBorder(BorderFactory.createLineBorder(Color.red));
							jtfDur.setText("");
							Mensajes.CODIGOPOSTALINCORRECTO.getDescripcion();
						}
					}
					if (todoCorrecto)
						for (Component componente : crearLlamada.getComponents())
							if (componente instanceof JTextField) {
								if (((JTextField) componente).isEditable()
										&& !((JTextField) componente).getText().isEmpty())
									((JTextField) componente).setBorder(BorderFactory.createLineBorder(Color.green));
								if (((JTextField) componente).isEditable()
										&& ((JTextField) componente).getText().isEmpty()) {
									((JTextField) componente).setBorder(BorderFactory.createLineBorder(Color.red));
									todoCorrecto = false;
								}
							}

					if (todoCorrecto)
						jtaMostrarLlamadaCreada.setText(controlador.altaLlamada(jtfNIF.getText(), jtfTelf.getText(),
								Integer.parseInt(jtfDur.getText())));
					for (Component componente : crearLlamada.getComponents())
						if (componente instanceof JTextField) {
							((JTextField) componente).setBorder(borderDefault);
							((JTextField) componente).setText("");
						}
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
					AbstractTableModel tableModel = modelo.getLlamadasCliente(nifBuscar.getText());
					tablaLlamadas.setModel(tableModel);
					tablaLlamadas.setDefaultEditor(Object.class, null);
					tablaLlamadas.setAutoCreateRowSorter(true);
					tablaLlamadas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				}
			}

		});
		// añadir
		add(altaLLamada, BorderLayout.WEST);
		add(listLlamadasCliente, BorderLayout.CENTER);
	}

}
