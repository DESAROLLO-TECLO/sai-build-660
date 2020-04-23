package mx.com.teclo.saicdmx.negocio.service.lineadecaptura;

import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.InfraccionLoteLCMVO;

public interface ConsultaLCMasivaService {
	
	/***
	 * Consulta el estatus de los lotes.
	 * @author Jesus Ponce
	 * @return List<InfraccionLoteLCMVO>
	 */
	public Map<String, Object> consultaLotes(String fechaInicio, String fechaFin, Integer cbCampoBusqueda, Integer idLote, String nameLote, Integer cbTipoFecha, Integer cbEstatusLotes);
	
	/***
	 * descarga un reporte del lote especifico.
	 * @author Jesus Ponce
	 * @return Map
	 */
	public Map descargarReporteLote(String lote);
}
