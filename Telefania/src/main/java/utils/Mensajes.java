package utils;

public enum Mensajes {
	//MENUS
	SALIR("Hasta luego, cara huevo."), 
	ATRAS("Atras"), 
	OPCION("Introduce una opción: "),
//	OPCIONINCORRECTA("Opción de menú intoducida incorrecta."),
	//CLIENTES
	NIF("Introduce el NIF del cliente: \n"),
	CLIENTECREADO("Cliente añadido con existo."),
	CLIENTEBORRADO("Cliente borrado de la base de datos"),
//	ERRORNIFNOTFOUND("NIF de cliente no encontrado"), 
	ERRORNIFEXISTENTE("ERROR: NIF ya introducido. Cliente nuevo no añadido."),
	//TARIFA
	TARIFACAMBIADA("Tarifa del cliente cambiada con éxito"),
	//FACTURA
	CODFACT("Introduzca el código de la factura \n (ejemplo: 15747875L-3): "),
	CODFACTINCORRECTO("Codigo de factura incorrecto."),
	ERRORFACTNOTFOUND("Factura no encontrada."),
	//LLAMADAS
	NUMTELF("Introduzca el número de telefono llamado: "),
	DURACIONLLAMADA("Introduzca la duración de la llamada en segundos: "),
	//SERIALIZACION
	SAVE("Datos almacenados"),
	ERRORSAVE("ERROR: de escritura de datos"),
	ERRORSAVENOTFOUND("Archivo de guardado no encontrado.\n Creando uno nuevo.\n"),
	//FECHA
	FECHAINICIO("La fecha de inicio (formato: DD/MM/YYYY): "),
	FECHAFINAL("La fecha de final (formato: DD/MM/YYYY): "),
//	ERRORFECHANOTFOUND("Fecha introducida no encontrada o formato incorrecto. \n"),
	//INPUT
	DATOVACIO("No se ha introducido ningún dato. No dejar vacio.\nVuelva a introducir el dato, o '-' para dejar en blanco: "),
	DATOINT("Dato introducido incorrecto, introduzca un número entero: \n"),
	DATODOUBLE("Dato introducido incorrecto, introduzca un número con decimales: \n"),
	DATOFECHA("Fecha o formato de fecha introducido incorrecto. \n Introducir de nuevo: "),
	//GENERAL
	NOTFOUND("No encontrado.");

	private String descripcion;

	private Mensajes(String descripcion) {
		this.descripcion = descripcion;
	}

	public void getDescripcion() {
		Output.outPut(descripcion);
	}
}