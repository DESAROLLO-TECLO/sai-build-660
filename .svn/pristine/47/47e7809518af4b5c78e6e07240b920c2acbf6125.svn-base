package mx.com.teclo.saicdmx.persistencia.hibernate.dao.atencionCiudadana;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.atencionCiudadana.AtencionCiudadanaTramitesDTO;



public interface TramitesAtencionCiudadanaDAO extends  BaseDao<AtencionCiudadanaTramitesDTO> {
	
	/** 
	 * Obtine la  entidad tramitesAtencionCiudadana por diferentes parametros de busqueda.
	 * @param String fechaInicio
     * @param String fechaFin
     * @param Integer paramBusqueda
     * @param Sring valorBusqueda
     * @param Integer Atendido
	 * @return AtencionCiudadanaTramitesDTO
	 */
	public List<AtencionCiudadanaTramitesDTO> obtieneTramite(String fechaInicio, String fechaFin, Integer paramBusqueda,
		    String valorBusqueda, Integer Atendido,String avanzadoNombre,String avanzadoAP, String avanzadoAM,
			String avanzadoTel, String avanzadoCorreo,
            String avanzadoEmpresa, Boolean busquedaAvanzada, Integer cantidaMaxRegistros);
	
	/** 
	 * Consulta los tramites sin expediente en los ultumos n días.
	 * @param Integer nuDias
	 */
	public List<AtencionCiudadanaTramitesDTO> consultaDefaultModificacion(Integer nuDias, Boolean op);

}
