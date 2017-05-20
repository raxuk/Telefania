package mvc.vista;


public interface InformaVista {
	//Modelo
	void actualizaVentana();
	void actualizaTablaClientes();
	void actualizaTarifaCliente(String tarifa);
	void actualizaClienteBorrado();
	void actualizarFalse();
	
	void setInfoClienteNombre(String nombre);
	void setInfoClienteApellidos(String apellidos, boolean mostrar);
	void setInfoClienteNIF(String nif);
	void setInfoClienteDireccion(String direccion);
	void setInfoClienteEmail(String email);
	void setInfoClienteTarifa(String tarifa);

}
