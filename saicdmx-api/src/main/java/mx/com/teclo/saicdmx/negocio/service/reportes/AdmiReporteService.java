package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ComponentesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.FormatoDescargaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.PropiedadesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoParametroDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoReportesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoTitulosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.ConfigParamVO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.ParamValueVO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.ReporteVO;


public interface AdmiReporteService {
	
	List<TipoReportesDTO> obtenerListaTipoReporte();
	
	List<FormatoDescargaDTO> obtenerListaFormatoDescarga();
	
	List<ComponentesDTO> obtenerListaComponentes();
	
	List<TipoParametroDTO> obtenerListaParametros();
	
	List<PropiedadesDTO> obtenerPropiedades();
	
	List<TipoTitulosDTO> obtenerTipoTitulo();
	
	List<ConfigParamVO> identificacionParametro(String cadena);
	
	String formarQuery(String query, String cadena);
	
	public void saveReporte(ReporteVO rVo); 
	
	public void saveConfigparam(ConfigParamVO ConfigParamVO);

}
