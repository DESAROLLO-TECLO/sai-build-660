package mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDTO;
  



public interface GarantiasDAO extends BaseDao<GarantiaDTO> {

	public List<GarantiaDTO> getGarantiasSinProcesar(Long empId);
	
	/**
	 * @author genunt
	 * @param parametroBusqueda
	 * @param parametro
	 * @return List<GarantiaDTO>
	 */
	List<GarantiaDTO> busquedaGarantiasGenerales(Integer parametroBusqueda, String parametro);
	
	/**
	 * @author genunt
	 * @param parametroBusqueda Integer
	 * @param parametro String
	 * @param procesoId Integer
	 * @return List<GarantiaDTO>
	 */
	List<GarantiaDTO> buscarGarantiasEntrega(Integer parametroBusqueda, String parametro, Integer procesoId);
	
	List<GarantiaDTO> buscarGarantiasSinRecibir();
	
	/**
	 * @author Fernando Campero
	 * @param Numero infracción String
	 * @param Boolean opcion / fisica o asociada
	 * @return List<GarantiaDTO>
	 */
	List<GarantiaDTO> buscarGarantiasFisicas(String infracNum, String infracPlaca, Boolean op);
	
	List<GarantiaDTO> buscarListGarantLote(Long idLote);
}
