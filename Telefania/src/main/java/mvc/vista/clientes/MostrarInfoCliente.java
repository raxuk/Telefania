package mvc.vista.clientes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import mvc.controlador.Controlador;
import mvc.modelo.ImplementacionModelo;

public class MostrarInfoCliente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4217248123963525664L;
	private JTextArea nombre;
	private JTextArea apellidos;
	private JTextArea nif;
	private JTextArea direccion;
	private JTextArea email;
	private JTextArea tarifa;
	private JTextArea fechaAlta;
	private JLabel apellidosLabel;

	public MostrarInfoCliente(ImplementacionModelo modelo, Controlador controlador) {
		super();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.EAST;

		// JTextAreas
		nombre = new JTextArea("", 1, 16);
		JLabel nombreLabel = new JLabel("Nombre: ");
		apellidos = new JTextArea("", 1, 16);
		apellidosLabel = new JLabel("Apellidos: ");
		nif = new JTextArea("", 1, 16);
		JLabel nifLabel = new JLabel("NIF: ");
		direccion = new JTextArea("", 3, 1);
		JLabel direccionLabel = new JLabel("Dirección: ");
		email = new JTextArea("", 1, 16);
		JLabel emailLabel = new JLabel("E-mail: ");
		tarifa = new JTextArea("", 3, 16);
		JLabel tarifaLabel = new JLabel("Tarifa: ");
		fechaAlta = new JTextArea("", 1, 16);
		JLabel fechaAltaLabel = new JLabel("Fecha alta cliente:");

		nombre.setEditable(false);
		apellidos.setEditable(false);
		nif.setEditable(false);
		direccion.setEditable(false);
		email.setEditable(false);
		tarifa.setEditable(false);
		fechaAlta.setEditable(false);

		// JPanel
		JPanel cambioTarifa = new JPanel();
		cambioTarifa.setVisible(false);
		// Casillas tarifas
		JCheckBox tardes = new JCheckBox("Tarifa tardes rebajadas");
		JCheckBox diaGratis = new JCheckBox("Tarifa domingos gratis");

		JPanel eleccionTarifa = new JPanel(new GridLayout(2, 1));
		eleccionTarifa.add(tardes);
		eleccionTarifa.add(diaGratis);
		cambioTarifa.add(eleccionTarifa);

		// Botones
		JButton cambiarTarifa = new JButton("Cambiar Tarifa");
		JButton borrar = new JButton("BorrarCliente");
		JButton aceptar = new JButton("Aceptar");
		aceptar.setVisible(false);

		// Acciones de los botones
		cambiarTarifa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!nif.getText().isEmpty()) {
					cambioTarifa.setVisible(true);
					aceptar.setVisible(true);
				}
			}
		});
		borrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!nif.getText().isEmpty())
					controlador.borrarCliente(nif.getText());
			}
		});
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int tarifaTipo = 0;
				if (tardes.isSelected())
					tarifaTipo += 2;
				if (diaGratis.isSelected())
					tarifaTipo += 1;
				controlador.cambiarTarifa(nif.getText(), tarifaTipo);
				cambioTarifa.setVisible(false);
				aceptar.setVisible(false);
			}
		});

		// Anyadir elementos al panel principal
		add(nombreLabel, c);
		add(apellidosLabel, c);
		add(nifLabel, c);
		add(direccionLabel, c);
		add(emailLabel, c);
		add(fechaAltaLabel, c);
		add(tarifaLabel, c);
		add(cambiarTarifa, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;

		add(nombre, c);
		c.gridx = 1;
		c.gridy = GridBagConstraints.RELATIVE;
		add(apellidos, c);
		add(nif, c);
		add(direccion, c);
		add(email, c);
		add(fechaAlta, c);
		add(tarifa, c);
		add(borrar, c);
		add(cambioTarifa, c);
		add(aceptar, c);
	}

	public void limpiar() {
		nombre.setText("");
		apellidos.setText("");
		nif.setText("");
		direccion.setText("");
		email.setText("");
		tarifa.setText("");
	}

	public void setNombre(String nombre) {
		this.nombre.setText(nombre);
	}

	public void setApellidos(String apellidos, boolean mostrar) {
		if (!mostrar) {
			this.apellidos.setVisible(false);
			apellidosLabel.setVisible(false);
		} else {
			this.apellidos.setVisible(true);
			apellidosLabel.setVisible(true);
			this.apellidos.setText(apellidos);
		}
	}

	public void setNIF(String nif) {
		this.nif.setText(nif);
	}

	public void setDireccion(String direccion) {
		this.direccion.setText(direccion);
	}

	public void setEmail(String email) {
		this.email.setText(email);
	}

	public void setTarifa(String tarifa) {
		this.tarifa.setText(tarifa);
	}
	
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta.setText(fechaAlta);
	}
}
