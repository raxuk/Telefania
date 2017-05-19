package facturas;

import informacion.PeriodoTiempo;
import llamadas.Llamada;
import main.Administrador;

public class Importe {

	public static double calcularImporte(Factura factura, Administrador admin) {
		double sumaMinutos = 0;
		for (Llamada llamada : factura.getPeriodoTiempo().listaLlamadasEnPeriodo(admin.listaLlamadasCliente(factura.getCodigoFactura().getIdentificador()))) {
			sumaMinutos += llamada.getduracionLlamadaSegundos();
		}
		sumaMinutos = Math.ceil(sumaMinutos / 60);
		return sumaMinutos * factura.getTarifa().getPrecio();
	}
	
	public static double calcularImporte(Factura factura, PeriodoTiempo periodo, Administrador admin){
		double sumaMinutos = 0;
		for (Llamada llamada : periodo.listaLlamadasEnPeriodo(admin.listaLlamadasCliente(factura.getCodigoFactura().getIdentificador()))) {
			sumaMinutos += llamada.getduracionLlamadaSegundos();
		}
		sumaMinutos = Math.ceil(sumaMinutos / 60);
		return sumaMinutos * factura.getTarifa().getPrecio();
	}
}
