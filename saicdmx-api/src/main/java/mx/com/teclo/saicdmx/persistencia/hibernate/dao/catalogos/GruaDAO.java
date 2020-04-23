package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;


import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.GruaDTO;

public interface GruaDAO extends BaseDao<GruaDTO> {
	public GruaDTO buscarGrua(String status, String cod);

	public GruaDTO buscarGrua(Long idGrua);

	public String obtenerCobroArrastre(String idGrua);
	// public GruaDTO buscarGruaPorNumeroInfracc(String numeroInfraccion);
	
	public List<GruaDTO> buscarGruasPorConcesionaria(Long concesionariaId);
	
	GruaDTO buscarGrua(String gruaCod);
}
