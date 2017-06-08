package utils;

import java.time.LocalDate;
import java.util.Scanner;


public class Input {
	static Scanner scan = new Scanner(System.in);

	public static String SInput() {
		String SEntrada;
		SEntrada = scan.nextLine();
		if (SEntrada.length() == 0) {
			Mensajes.DATOVACIO.getDescripcion();
			SEntrada = SInput();
		}
		return SEntrada;
	}

	public static int IInput() {
		int IEntrada = -1;

		try {
			IEntrada = Integer.parseInt(scan.nextLine());
		} catch (Exception datoIncorrecto) {
			Mensajes.DATOINT.getDescripcion();
			IEntrada = IInput();
		}
		return IEntrada;
	}

	public static double DInput() {
		double DEntrada = -1;

		try {
			DEntrada = Double.parseDouble(scan.nextLine());
		} catch (Exception datoIncorrecto) {
			Mensajes.DATODOUBLE.getDescripcion();
			DEntrada = DInput();
		}
		return DEntrada;
	}

	public static LocalDate LDInput() {
		LocalDate fechaEntrada;
		try {
			String aux = scan.nextLine();
			String[] auxFecha = aux.split("/");
			fechaEntrada = LocalDate.of(Integer.parseInt(auxFecha[2]), Integer.parseInt(auxFecha[1]),
					Integer.parseInt(auxFecha[0]));
		} catch (Exception fechaIncorreca) {
			Mensajes.DATOFECHA.getDescripcion();
			fechaEntrada = LDInput();
		}
		return fechaEntrada;
	}
}