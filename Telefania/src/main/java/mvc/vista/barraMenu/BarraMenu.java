package mvc.vista.barraMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import mvc.controlador.Controlador;
import mvc.modelo.ImplementacionModelo;

public class BarraMenu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 862946678227256318L;

	public BarraMenu(ImplementacionModelo modelo, Controlador controlador) {
		super();
		JMenu menuArchivo = new JMenu("Archivo");
		JMenu menuBuscar = new JMenu("Buscar");
		JMenu subMenuBuscaPeriodo = new JMenu("Buscar por periodo de tiempo");
//		JMenu subMenuTemas = new JMenu("Cambiar tema ventana");

		JMenu menuAyuda = new JMenu("Ayuda");

		JMenuItem itemNuevo = new JMenuItem("Nuevo");
		JMenuItem itemCargar = new JMenuItem("Cargar archivo");
		JMenuItem itemGuardar = new JMenuItem("Guardar archivo");
		JMenuItem itemBuscarClientesFechas = new JMenuItem("Lista de clientes en un periodo de tiempo");
		JMenuItem itemBuscarFacturasFechas = new JMenuItem("Lista de facturas en un periodo de tiempo");
		JMenuItem itemBuscarLlamadasFechas = new JMenuItem("Lista de llamadas en un periodo de tiempo");
//		JMenuItem temaWindows = new JMenuItem("Windows");
		JMenuItem itemAcercaDe = new JMenuItem("Acerca de");

		add(menuArchivo);
		menuArchivo.add(itemNuevo);
		menuArchivo.add(itemCargar);
		menuArchivo.add(itemGuardar);
		add(menuBuscar);
		menuBuscar.add(subMenuBuscaPeriodo);
		subMenuBuscaPeriodo.add(itemBuscarClientesFechas);
		subMenuBuscaPeriodo.add(itemBuscarFacturasFechas);
		subMenuBuscaPeriodo.add(itemBuscarLlamadasFechas);
		add(menuAyuda);
//		menuAyuda.add(subMenuTemas);
//		subMenuTemas.add(temaWindows);
		menuAyuda.add(itemAcercaDe);

		// ActionListener
		ActionListener nuevo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.nuevosDatos();
			}
		};
		itemNuevo.addActionListener(nuevo);

		ActionListener cargar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.cargarDatosFichero();
			}
		};
		itemCargar.addActionListener(cargar);

		ActionListener guardar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.guardarDatosFichero();
			}
		};
		itemGuardar.addActionListener(guardar);

		ActionListener buscarClientesFechas = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BuscarPorPeriodo(0, modelo);
			}
		};
		itemBuscarClientesFechas.addActionListener(buscarClientesFechas);

		ActionListener buscarFacturasFechas = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BuscarPorPeriodo(1, modelo);
			}
		};
		itemBuscarFacturasFechas.addActionListener(buscarFacturasFechas);

		ActionListener buscarLlamadasFechas = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BuscarPorPeriodo(2, modelo);
			}
		};
		itemBuscarLlamadasFechas.addActionListener(buscarLlamadasFechas);

//		ActionListener windows = new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				LFWindows();
//			}
//		};
//		temaWindows.addActionListener(windows);

		ActionListener acercaDe = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Holi ;)");
			}
		};
		itemAcercaDe.addActionListener(acercaDe);

//	}
//
//	public void LFWindows() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
}
