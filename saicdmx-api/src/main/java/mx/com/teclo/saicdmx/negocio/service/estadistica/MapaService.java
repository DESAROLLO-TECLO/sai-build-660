package mx.com.teclo.saicdmx.negocio.service.estadistica;

import java.util.Map;

public interface MapaService {

	@SuppressWarnings("rawtypes")
	public Map tipoInfraccionPorFecha(String[] tipoInfraccion, String fechaini, String fechafin);

}
