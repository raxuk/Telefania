package mvc.modelo;

import clientes.Cliente;
import tarifa.Tarifa;

public interface CambioModelo {
	//metodos del controlador
	void anyadirCliente(Cliente cliente);

	void cambiarTarifa(String nif, Tarifa tarifa);

	void borrarCliente(String nif);
}
