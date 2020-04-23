package mx.com.teclo.saicdmx.persistencia.hibernate.dao.fm;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fm.FMLotesDTO;

public interface FMLotesDAO extends BaseDao<FMLotesDTO>{
	/***
	 * @author José Carmen Castillo Navarrete
	 * @param tipoRadar
	 * @param tipoDeteccion
	 * @param archivoTipo
	 * @return FMLotesDTO
	 */
	public FMLotesDTO buscarLotesEnCreacion();
	
	public List<FMLotesDTO> BuscarLotesCreados();
}
