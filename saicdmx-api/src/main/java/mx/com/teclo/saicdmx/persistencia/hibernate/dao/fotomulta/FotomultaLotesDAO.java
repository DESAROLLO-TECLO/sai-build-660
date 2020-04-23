package mx.com.teclo.saicdmx.persistencia.hibernate.dao.fotomulta;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta.FotomultaLotesDTO;

public interface FotomultaLotesDAO extends BaseDao<FotomultaLotesDTO>{

	/***
	 * @author Jesus Gutierrez
	 * @param tipoRadar
	 * @param archivoTipo
	 * @return
	 */
	public FotomultaLotesDTO buscarLotesEnCreacion(Integer tipoRadar, Integer archivoTipo);
}
