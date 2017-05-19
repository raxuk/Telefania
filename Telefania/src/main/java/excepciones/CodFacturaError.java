package excepciones;

@SuppressWarnings("serial")
public class CodFacturaError extends ArrayIndexOutOfBoundsException{

	public CodFacturaError(){
		super("Codigo de factura incorrecto.");
	}
}
