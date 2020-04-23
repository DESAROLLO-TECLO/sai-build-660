package mx.com.teclo.ms.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.ms.persistencia.dto.TipoFechasDTO;

public interface CatalogosMsDAO {
	
	List<TipoFechasDTO> getCatalogoTipoFechasAll();
	List<TipoFechasDTO> getCatalogoTipoFechasOpcion(Long[] opciones);
}
