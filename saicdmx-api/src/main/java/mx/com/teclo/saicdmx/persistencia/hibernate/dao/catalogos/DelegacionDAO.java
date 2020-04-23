package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;


import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DelegacionDTO;

public interface DelegacionDAO extends BaseDao<DelegacionDTO> 
{
	 public DelegacionDTO buscarDelegacion(long idDelegacion, Long estadoDTO);

	 /**
	  * Retorna una lista de delegaciones filtradas por estado
	  * @param estadoId
	  * @return
	  */
	 public List<DelegacionDTO> buscarDelegacionPorEstado(Long estadoId);
	 
	 DelegacionDTO buscarDelegacionPorCod(String DelCod);
}
