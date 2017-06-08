package menuAnyadirCliente;

import clientes.Cliente;
import clientes.Particular;
import informacion.Direccion;
import main.Administrador;
import menu.EjecutarOpcion;
import pedirDatos.PedirDatosCliente;
import tarifa.Tarifa;

public class NuevoParticular implements EjecutarOpcion {
	Cliente clienteNuevo;

	@Override
	public void ejecuta(Administrador admin) {
		String nombre = PedirDatosCliente.Nombre();
		String apellidos = PedirDatosCliente.Apellidos();
		String nif = PedirDatosCliente.NIF();
		String provincia = PedirDatosCliente.Provincia();
		String poblacion = PedirDatosCliente.Poblacion();
		int codigoPostal = PedirDatosCliente.CodPostal();
		Direccion direccion = new Direccion(provincia, poblacion, codigoPostal);
		String email = PedirDatosCliente.Email();
		Tarifa tarifa = PedirDatosCliente.Tarifa();

		clienteNuevo = new Particular(nombre, nif, direccion, email, tarifa)
				.setApellidos(apellidos);

		admin.altaCliente(clienteNuevo);

		utils.Mensajes.CLIENTECREADO.getDescripcion();
	}

}
