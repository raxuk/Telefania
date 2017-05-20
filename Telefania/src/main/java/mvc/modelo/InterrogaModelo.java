package mvc.modelo;

import javax.swing.table.AbstractTableModel;

public interface InterrogaModelo {
	// metodos modelo para la vista
	AbstractTableModel getClientes();
	void mostrarInfoCliente(int fila);
}
