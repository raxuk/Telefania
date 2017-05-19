package excepciones;

@SuppressWarnings("serial")
public class NifNotFoundException extends NullPointerException {
	
	public NifNotFoundException(){
		super("NIF de cliente no encontrado");
	}
}
