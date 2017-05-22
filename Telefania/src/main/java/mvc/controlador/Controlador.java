package mvc.controlador;

public interface Controlador {
	void anyadirCliente();

	void cambiarTarifa(String string, int tarifaTipo);

	void borrarCliente(String string);

	String emitirFactura(int mes, int año, String nif);
	
	String altaLlamada(String nif, String tlf, int dur);
}
