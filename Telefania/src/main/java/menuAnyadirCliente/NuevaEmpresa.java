package menuAnyadirCliente;

import clientes.Cliente;
import clientes.Empresa;
import informacion.Direccion;
import main.Administrador;
import menu.EjecutarOpcion;
import pedirDatos.PedirDatosCliente;
import tarifa.Tarifa;

public class NuevaEmpresa implements EjecutarOpcion {
	Cliente clienteNuevo;

	@Override
	public void ejecuta(Administrador admin) {
		String nombre = PedirDatosCliente.Nombre();
		String nif = PedirDatosCliente.NIF();
		String provincia = PedirDatosCliente.Provincia();
		String poblacion = PedirDatosCliente.Poblacion();
		int codigoPostal = PedirDatosCliente.CodPostal();
		Direccion direccion = new Direccion(provincia, poblacion, codigoPostal);
		String email = PedirDatosCliente.Email();
		Tarifa tarifa = PedirDatosCliente.Tarifa();

		clienteNuevo = new Empresa(nombre, nif, direccion, email, tarifa);

		admin.altaCliente(clienteNuevo);
		
		utils.Mensajes.CLIENTECREADO.getDescripcion();
	}

}
