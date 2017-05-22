package mvc.vista.barraMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
		JMenu menuAyuda = new JMenu("Ayuda");

		JMenuItem itemNuevo = new JMenuItem("Nuevo");
		JMenuItem itemGuardar = new JMenuItem("Guardar archivo");
		JMenuItem itemCargar = new JMenuItem("Cargar archivo");
		JMenuItem itemBuscarClientesFechas = new JMenuItem("Lista de clientes en un periodo de tiempo");
		JMenuItem itemBuscarFacturasFechas = new JMenuItem("Lista de facturas en un periodo de tiempo");
		JMenuItem itemBuscarLlamadasFechas = new JMenuItem("Lista de llamadas en un periodo de tiempo");
		JMenuItem itemTemas = new JMenuItem("Cambiar tema ventana");
		JMenuItem itemAcercaDe = new JMenuItem("Acerca de");

		add(menuArchivo);
		menuArchivo.add(itemNuevo);
		menuArchivo.add(itemGuardar);
		menuArchivo.add(itemCargar);
		add(menuBuscar);
		menuBuscar.add(subMenuBuscaPeriodo);
		subMenuBuscaPeriodo.add(itemBuscarClientesFechas);
		subMenuBuscaPeriodo.add(itemBuscarFacturasFechas);
		subMenuBuscaPeriodo.add(itemBuscarLlamadasFechas);
		add(menuAyuda);
		menuAyuda.add(itemTemas);
		menuAyuda.add(itemAcercaDe);

		// ActionListener
		ActionListener nuevo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Holi ;)");
			}
		};
		itemNuevo.addActionListener(nuevo);

		ActionListener guardar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Holi ;)");
			}
		};
		itemGuardar.addActionListener(guardar);

		ActionListener cargar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Holi ;)");
			}
		};
		itemCargar.addActionListener(cargar);

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

		ActionListener temas = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Holi ;)");
			}
		};
		itemTemas.addActionListener(temas);

		ActionListener acercaDe = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Holi ;)");
			}
		};
		itemAcercaDe.addActionListener(acercaDe);
	}

}
