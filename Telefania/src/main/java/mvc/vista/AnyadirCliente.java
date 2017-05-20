package mvc.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import mvc.controlador.Controlador;
import mvc.modelo.ImplementacionModelo;

public class AnyadirCliente extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6289861228081864742L;
	private JTextField nombre;
	private JTextField apellidos;
	private JTextField nif;
	private JTextField provincia;
	private JTextField poblacion;
	private JTextField codigoPostal;
	private JTextField email;
	//
	private Border borderDefault;
	//
	private ButtonGroup grupoTipoCliente;
	private JCheckBox tardes;
	private JCheckBox diaGratis;
	//
	private Controlador controlador;
	private ImplementacionModelo modelo;

	public AnyadirCliente(ImplementacionModelo modelo, Controlador controlador) {
		this.controlador = controlador;
		this.modelo = modelo;
		// Principal
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.EAST;

		// Botones
		JButton anyadir = new JButton("Añadir");
		JButton limpiar = new JButton("Limpiar");

		JRadioButton particular = new JRadioButton("Particular");
		particular.setActionCommand("particular");
		JRadioButton empresa = new JRadioButton("Empresa");
		empresa.setActionCommand("empresa");
		empresa.setSelected(true);
		grupoTipoCliente = new ButtonGroup();
		grupoTipoCliente.add(particular);
		grupoTipoCliente.add(empresa);

		// Casillas tarifas
		tardes = new JCheckBox("Tarifa tardes rebajadas");
		diaGratis = new JCheckBox("Tarifa domingos gratis");

		JPanel eleccionTarifa = new JPanel(new GridLayout(2, 1));
		eleccionTarifa.add(tardes);
		eleccionTarifa.add(diaGratis);

		// JTextFields
		nombre = new JTextField(10);
		JLabel nombreLabel = new JLabel("Nombre: ");
		apellidos = new JTextField(10);
		apellidos.setEditable(false);
		JLabel apellidosLabel = new JLabel("Apellidos: ");
		nif = new JTextField(10);
		JLabel nifLabel = new JLabel("NIF: ");
		provincia = new JTextField(10);
		JLabel provinciaLabel = new JLabel("Provincia: ");
		poblacion = new JTextField(10);
		JLabel poblacionLabel = new JLabel("Población: ");
		codigoPostal = new JTextField(10);
		JLabel codigoPostalLabel = new JLabel("Código postal: ");
		email = new JTextField(10);
		JLabel emailLabel = new JLabel("E-mail: ");
		JLabel tarifaLabel = new JLabel("Tarifa: ");

		// Borde default de los jtextfield
		borderDefault = nombre.getBorder();

		// Acciones de los botones
		particular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				apellidos.setEditable(true);
			}
		});
		empresa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				apellidos.setEditable(false);
			}
		});
		limpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Component componente : getComponents())
					if (componente instanceof JTextField) {
						((JTextField) componente).setBorder(borderDefault);
						((JTextField) componente).setText("");
					}
			}
		});
		anyadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				anyadirClienteAccion();
			}
		});

		// Anyadir elementos al panel principal
		add(particular, c);
		add(nombreLabel, c);
		add(apellidosLabel, c);
		add(nifLabel, c);
		add(provinciaLabel, c);
		add(poblacionLabel, c);
		add(codigoPostalLabel, c);
		add(emailLabel, c);
		add(tarifaLabel, c);
		add(limpiar, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;

		add(empresa, c);
		c.gridx = 1;
		c.gridy = GridBagConstraints.RELATIVE;
		add(nombre, c);
		add(apellidos, c);
		add(nif, c);
		add(provincia, c);
		add(poblacion, c);
		add(codigoPostal, c);
		add(email, c);
		add(eleccionTarifa, c);
		add(anyadir, c);
	}

	// Action
	private void anyadirClienteAccion() {
		boolean todoCorrecto = true;

		if (modelo.existeCliente(nif.getText())) {
			todoCorrecto = false;
			nif.setBorder(BorderFactory.createLineBorder(Color.red));
			nif.setText("NIF de cliente ya existente");
		}

		if (todoCorrecto) {
			for (Component componente : getComponents())
				if (componente instanceof JTextField) {
					if (((JTextField) componente).isEditable() && !((JTextField) componente).getText().isEmpty())
						((JTextField) componente).setBorder(BorderFactory.createLineBorder(Color.green));
					if (((JTextField) componente).isEditable() && ((JTextField) componente).getText().isEmpty()) {
						((JTextField) componente).setBorder(BorderFactory.createLineBorder(Color.red));
						todoCorrecto = false;
					}
				}
		}

		if (todoCorrecto) {
			controlador.anyadirCliente();
			tardes.setSelected(false);
			diaGratis.setSelected(false);

			for (Component componente : getComponents())
				if (componente instanceof JTextField) {
					((JTextField) componente).setBorder(borderDefault);
					((JTextField) componente).setText("");
				}
		}
	}

	// get
	public String getNombre() {
		return this.nombre.getText();
	}

	public String getApellidos() {
		return apellidos.getText();
	}

	public String getNIF() {
		return nif.getText();
	}

	public String getProvincia() {
		return provincia.getText();
	}

	public String getPoblacion() {
		return poblacion.getText();
	}

	public int getCodigoPostal() {
		return Integer.parseInt(codigoPostal.getText());
	}

	public String getEmail() {
		return email.getText();
	}

	public String getTipoCliente() {
		return grupoTipoCliente.getSelection().getActionCommand();
	}

	public int getTipoTarifa() {
		int tarifa = 0;
		if (tardes.isSelected())
			tarifa += 2;
		if (diaGratis.isSelected())
			tarifa += 1;
		return tarifa;
	}

}
