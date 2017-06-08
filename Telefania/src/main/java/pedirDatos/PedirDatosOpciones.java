package pedirDatos;

import java.util.regex.Pattern;

import clientes.Cliente;
import excepciones.CodFacturaError;
import main.Administrador;
import informacion.CodigoUnico;
import utils.Input;

public class PedirDatosOpciones {

	public static Cliente NIFCliente(Administrador admin) {
		utils.Mensajes.NIF.getDescripcion();
		Cliente aux = admin.getCliente(Input.SInput());
		if (aux.equals(null))
			return null;
		return aux;
	}

	public static String NIFCliente() {
		utils.Mensajes.NIF.getDescripcion();
		return Input.SInput();
	}

	public static CodigoUnico CodigoFactura() {
		String codigo;
		String[] token;
		String codigoId;
		int codigoSe = 0;
		utils.Mensajes.CODFACT.getDescripcion();
		codigo = Input.SInput();
		token = codigo.split(Pattern.quote("-"));
		codigoId = token[0];
		try {
			codigoSe = Integer.parseInt(token[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CodFacturaError();
		}

		return new CodigoUnico().setIdentificador(codigoId).setSerie(codigoSe);
	}

	public static String NumeroTelf() {
		utils.Mensajes.NUMTELF.getDescripcion();
		return Input.SInput();
	}

	public static int DuracionLlamada() {
		utils.Mensajes.DURACIONLLAMADA.getDescripcion();
		return Input.IInput();
	}
}
