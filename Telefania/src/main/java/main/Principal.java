package main;

import mvc.controlador.ImplementacionControlador;
import mvc.modelo.ImplementacionModelo;
import mvc.vista.ImplementacionVista;
import mvc.vista.clientes.PestanyaClientes;
import mvc.vista.facturas.PestanyaFacturas;
import mvc.vista.llamadas.PestanyaLlamadas;

public class Principal {
	public static void main(String args[]){
		//
		ImplementacionModelo modelo = new ImplementacionModelo();
		ImplementacionVista vista = new ImplementacionVista();
		ImplementacionControlador controlador = new ImplementacionControlador();
		//
		PestanyaClientes clientesPestanya = new PestanyaClientes(modelo,controlador);
		PestanyaFacturas facturasPestanya = new PestanyaFacturas(modelo,controlador);
		PestanyaLlamadas llamadasPestanya = new PestanyaLlamadas(modelo,controlador);
		//
		modelo.setVista(vista);
		//
		vista.setModelo(modelo);
		vista.setControlador(controlador);
		vista.setClientePestanya(clientesPestanya);
		vista.setFacturasPestanya(facturasPestanya);
		vista.setLlamadasPestanya(llamadasPestanya);
		//
		controlador.setVista(vista);
		controlador.setModelo(modelo);	
		//
		vista.creaGUI();
	}
}
