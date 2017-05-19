package excepciones;

@SuppressWarnings("serial")
public class CodFactNotFound extends NullPointerException {

	public CodFactNotFound(){
		super("Factura no encontrada.");
	}
}
