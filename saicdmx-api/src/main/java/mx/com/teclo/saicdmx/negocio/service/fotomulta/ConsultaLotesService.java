package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ConsultaLotesVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;

public interface ConsultaLotesService {

	/***
	 * @author Jesus Gutierrez
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoRadar
	 * @param estatusProceso
	 * @param tipoFecha
	 * @param archivoTipo
	 * @return
	 */
	public List<ConsultaLotesVO> consultaLotes(String fechaInicio, String fechaFin, Long tipoRadar, 
												Integer estatusProceso, Integer tipoFecha, Integer archivoTipo);
}
