package excepciones;

@SuppressWarnings("serial")
public class OpcionMenuException extends ArrayIndexOutOfBoundsException {

	public OpcionMenuException(){
		super("Opción de menú intoducida incorrecta.");
	}
}
