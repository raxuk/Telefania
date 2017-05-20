package main;

import mvc.controlador.ImplementacionControlador;
import mvc.modelo.ImplementacionModelo;
import mvc.vista.ImplementacionVista;
import mvc.vista.PestanyaClientes;

public class Principal {
	public static void main(String args[]){
		//
		ImplementacionModelo modelo = new ImplementacionModelo();
		ImplementacionVista vista = new ImplementacionVista();
		ImplementacionControlador controlador = new ImplementacionControlador();
		//
		PestanyaClientes clientesPestanya = new PestanyaClientes(modelo,controlador);
		//
		modelo.setVista(vista);
		//
		vista.setModelo(modelo);
		vista.setControlador(controlador);
		vista.setClientePestanya(clientesPestanya);
		//
		controlador.setVista(vista);
		controlador.setModelo(modelo);	
		//
		vista.creaGUI();
	}
}
