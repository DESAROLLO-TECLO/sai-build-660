package mx.com.teclo.ms.service;

import java.util.List;

import mx.com.teclo.ms.persistencia.vo.TipoFechaVO;

public interface CatalogoMsService {
	List<TipoFechaVO> getCatalogoTipoFechasAll();
	List<TipoFechaVO> getCatalogoTipoFechasOp(Long[] opciones);
}
