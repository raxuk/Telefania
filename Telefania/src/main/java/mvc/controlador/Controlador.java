package mvc.controlador;

public interface Controlador {
	void anyadirCliente();

	void cambiarTarifa(String string, int tarifaTipo);

	void borrarCliente(String string);
}
