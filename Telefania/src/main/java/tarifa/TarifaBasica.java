package tarifa; //DONE


public class TarifaBasica extends Tarifa {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5070186829316566235L;


	// constructor
	public TarifaBasica(double precio) {
		super(precio);
	}

	@Override
	public String toString(){
		return "Tarifa básica 0.15 €";
	}
}
