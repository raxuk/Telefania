package mvc.vista.clientes;

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
import utils.Mensajes;

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
		super();
		this.controlador = controlador;
		this.modelo = modelo;
		// Principal
		setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = GridBagConstraints.RELATIVE;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.insets = new Insets(2, 2, 2, 2);
		cons.anchor = GridBagConstraints.EAST;

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
		add(particular, cons);
		add(nombreLabel, cons);
		add(apellidosLabel, cons);
		add(nifLabel, cons);
		add(provinciaLabel, cons);
		add(poblacionLabel, cons);
		add(codigoPostalLabel, cons);
		add(emailLabel, cons);
		add(tarifaLabel, cons);
		add(limpiar, cons);

		cons.gridx = 1;
		cons.gridy = 0;
		cons.weightx = 1.0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.CENTER;

		add(empresa, cons);
		cons.gridx = 1;
		cons.gridy = GridBagConstraints.RELATIVE;
		add(nombre, cons);
		add(apellidos, cons);
		add(nif, cons);
		add(provincia, cons);
		add(poblacion, cons);
		add(codigoPostal, cons);
		add(email, cons);
		add(eleccionTarifa, cons);
		add(anyadir, cons);
	}

	// Action
	private void anyadirClienteAccion() {
		boolean todoCorrecto = true;

		if (modelo.existeCliente(nif.getText())) {
			todoCorrecto = false;
			nif.setBorder(BorderFactory.createLineBorder(Color.red));
			nif.setText("");
			Mensajes.ERRORNIFEXISTENTE.getDescripcion();
		}

		if (todoCorrecto && !codigoPostal.getText().isEmpty()) {
			try {
				Integer.parseInt(codigoPostal.getText());
			} catch (NumberFormatException e) {
				todoCorrecto = false;
				codigoPostal.setBorder(BorderFactory.createLineBorder(Color.red));
				codigoPostal.setText("");
				Mensajes.CODIGOPOSTALINCORRECTO.getDescripcion();
			}
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
