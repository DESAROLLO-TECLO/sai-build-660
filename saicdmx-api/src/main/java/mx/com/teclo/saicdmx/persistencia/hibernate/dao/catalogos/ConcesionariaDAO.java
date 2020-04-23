package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;


import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ConcesionariaDTO;

public interface ConcesionariaDAO extends BaseDao<ConcesionariaDTO> {

	public ConcesionariaDTO getConcesionariaGruas(Long concesionariaId, String gruaId);

	public void actualizarConcesionaria(ConcesionariaDTO concesionariaDTO);

	public ConcesionariaDTO buscarConcesionariaPorId(Long concesionariaId);

	public List<ConcesionariaDTO> buscarConcesionariasOrdenadas();

	ConcesionariaDTO buscarconcesionariaPorCod(String concesionariaCod);
}
