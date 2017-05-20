package mvc.vista;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import mvc.controlador.Controlador;
import mvc.modelo.ImplementacionModelo;
import mvc.vista.PestanyaClientes;
import mvc.vista.PestanyaFacturas;
import mvc.vista.PestanyaLlamadas;

public class ImplementacionVista implements InterrogaVista, InformaVista {
	private PestanyaClientes clientesPestanya;
	private PestanyaFacturas facturasPestanya = new PestanyaFacturas();
	private PestanyaLlamadas llamadasPestanya = new PestanyaLlamadas();
	private JFrame ventana = new JFrame("Telefania");
	//
	private Controlador controlador;
	private ImplementacionModelo modelo;

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModelo(ImplementacionModelo modelo) {
		this.modelo = modelo;
	}

	public void setClientePestanya(PestanyaClientes clientePestanya) {
		this.clientesPestanya = clientePestanya;
	}

	public void creaGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI();
			}
		});
	}

	public void GUI() {
		JTabbedPane pestanyas = new JTabbedPane();
		pestanyas.addTab("Clientes", clientesPestanya);
		clientesPestanya.setLayout(new BoxLayout(clientesPestanya, BoxLayout.X_AXIS));

		pestanyas.addTab("Facturas", facturasPestanya);
		pestanyas.addTab("Llamadas", llamadasPestanya);
		//
		ventana.getContentPane().add(pestanyas);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	// Action
	@Override
	public void actualizaVentana() {
		ventana.pack();
	}

	@Override
	public void actualizaTarifaCliente(String tarifa) {
		clientesPestanya.actualizaTarifaCliente(tarifa);
	}

	@Override
	public void actualizaTablaClientes() {
		clientesPestanya.actualizarTablaClientes();
	}

	@Override
	public void actualizaClienteBorrado() {
		clientesPestanya.actualizaClienteBorrado();
	}
	
	@Override
	public void actualizarFalse() {
		clientesPestanya.actualizarFalse();
	}

	// gets
	@Override
	public String getNombre() {
		return clientesPestanya.getAnyadirCliente().getNombre();
	}

	@Override
	public String getApellidos() {
		return clientesPestanya.getAnyadirCliente().getApellidos();
	}

	@Override
	public String getNIF() {
		return clientesPestanya.getAnyadirCliente().getNIF();
	}

	@Override
	public String getProvincia() {
		return clientesPestanya.getAnyadirCliente().getProvincia();
	}

	@Override
	public String getPoblacion() {
		return clientesPestanya.getAnyadirCliente().getPoblacion();
	}

	@Override
	public int getCodigoPostal() {
		return clientesPestanya.getAnyadirCliente().getCodigoPostal();
	}

	@Override
	public String getEmail() {
		return clientesPestanya.getAnyadirCliente().getEmail();
	}

	@Override
	public String tipoCliente() {
		return clientesPestanya.getAnyadirCliente().getTipoCliente();
	}

	@Override
	public int tipoTarifa() {
		return clientesPestanya.getAnyadirCliente().getTipoTarifa();
	}

	// sets
	@Override
	public void setInfoClienteNombre(String nombre) {
		clientesPestanya.getClienteMostrar().setNombre(nombre);
	}

	@Override
	public void setInfoClienteApellidos(String apellidos, boolean mostrar) {
		clientesPestanya.getClienteMostrar().setApellidos(apellidos, mostrar);
	}

	@Override
	public void setInfoClienteNIF(String nif) {
		clientesPestanya.getClienteMostrar().setNIF(nif);
	}

	@Override
	public void setInfoClienteDireccion(String direccion) {
		clientesPestanya.getClienteMostrar().setDireccion(direccion);
	}

	@Override
	public void setInfoClienteEmail(String email) {
		clientesPestanya.getClienteMostrar().setEmail(email);
	}

	@Override
	public void setInfoClienteTarifa(String tarifa) {
		clientesPestanya.getClienteMostrar().setTarifa(tarifa);
	}

}
