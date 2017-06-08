package pedirDatos;

import utils.Input;
import utils.Output;
import java.time.DayOfWeek;
import tarifa.Tarifa;
import tarifa.TarifaBasica;
import tarifa.TarifaPorDia;
import tarifa.TarifaPorHoras;

public class PedirDatosCliente {
	public static String Nombre() {
		Output.outPut("Nombre: ");
		return Input.SInput();
	}

	public static String Apellidos() {
		Output.outPut("Apellidos: ");
		return Input.SInput();
	}

	public static String NIF() {
		Output.outPut("NIF: ");
		return Input.SInput();
	}

	public static String Provincia() {
		Output.outPut("Provincia: ");
		return Input.SInput();
	}

	public static String Poblacion() {
		Output.outPut("Población: ");
		return Input.SInput();
	}

	public static int CodPostal() {
		Output.outPut("Codigo postal: ");
		return Input.IInput();
	}

	public static String Email() {
		Output.outPut("Email: ");
		return Input.SInput();
	}

	public static Tarifa Tarifa() {
		Output.outPut("Tarifa básica: 0.15€");
		Tarifa tarifa = new TarifaBasica(0.15);

		Output.outPut("¿Desea la tarifa TARDES REBAJADAS a 0.05€/min de 16:00 a 20:00?");

		boolean done = false;
		String aceptar;

		do {
			Output.outPut("Si/No ");
			aceptar = Input.SInput();
			switch (aceptar) {
			case "Si":
			case "S":
			case "si":
			case "s":
				tarifa = new TarifaPorHoras(tarifa, 16, 20);
				done = true;
				break;
			case "No":
			case "N":
			case "no":
			case "n":
				done = true;
				break;
			}
		} while (!done);

		Output.outPut("¿Desea la tarifa Domingos gratis?");

		done = false;
		aceptar = null;

		do {
			Output.outPut("Si/No ");
			aceptar = Input.SInput();
			switch (aceptar) {
			case "Si":
			case "S":
			case "si":
			case "s":
				tarifa = new TarifaPorDia(tarifa, DayOfWeek.SUNDAY);
				done = true;
				break;
			case "No":
			case "N":
			case "no":
			case "n":
				done = true;
				break;
			}
		} while (!done);

		return tarifa;
	}
}
