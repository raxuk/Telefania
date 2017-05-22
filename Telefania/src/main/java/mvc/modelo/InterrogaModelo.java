package mvc.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import utils.GetFecha;

public interface InterrogaModelo {
	// metodos modelo para la vista
	boolean existeCliente(String nif);
	void crearModeloTablaClientes();
	
	AbstractTableModel getClientes();
	void mostrarInfoCliente(int fila);
	void mostrarInfoClienteBusqueda(String nif);
	
	AbstractTableModel getFacturasCliente(String nif);
	String mostrarInfoFactura(String nif, int fila);
	
	AbstractTableModel getLlamadasCliente(String nif);
	
	String getFactura(String cod);
	
	ArrayList<GetFecha> genClientes(LocalDateTime fechaInicio,LocalDateTime fechaFinal);
	ArrayList<GetFecha> genFacturas(String nif, LocalDateTime fechaInicio,LocalDateTime fechaFinal);
	ArrayList<GetFecha> genLlamadas(String nig, LocalDateTime fechaInicio,LocalDateTime fechaFinal);
}
