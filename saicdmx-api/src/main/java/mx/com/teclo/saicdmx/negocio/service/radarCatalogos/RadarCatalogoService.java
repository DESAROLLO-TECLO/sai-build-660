package mx.com.teclo.saicdmx.negocio.service.radarCatalogos;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarCatConsultaCPVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarCatConsultaUTVO;


public interface RadarCatalogoService {

	List<FilterValuesVO> getComboCatalgos();

	List<FilterValuesVO> getComboTipoBusquedaCatUT();

	List<FilterValuesVO> getComboTipoBusquedaCatCP();
	
	List<FilterValuesVO> getComboCatEstadosUT();
	
	List<FilterValuesVO> getComboCatEstados();

	List<FilterValuesVO> getComboCatDelegacionEstado();

	List<FilterValuesVO> getComboCatSectores();

	List<FilterValuesVO> getComboCatDelegaciones();

	List<RadarCatConsultaUTVO> getConsultaUnidadTerritorial(String tipoCatalogo, String valor, Long archivoTipoUT, Long tipoRadarUT, Long estatusUT);

	RadarCatConsultaUTVO getUnidadTerritorial(String tipoCatalogo, String valor, Long archivoTipoUT, Long tipoRadarUT, Long estatusUT);
	
	List<RadarCatConsultaCPVO> getConsultaCodigosPostales(String tipoBusqueda, String valor);

	List<FilterValuesVO> getComboEstatus();
	
	// UT
	List<FilterValuesVO> getComboEstatusUT();

	List<FilterValuesVO> getComboCatSectoresPorDelgac(String idDelegacion);

	List<FilterValuesVO> getComboZonasCP();

	List<FilterValuesVO> getComboCatDelegaciones(String idEstado);

	RadarCatConsultaUTVO guardarNewUT(RadarCatConsultaUTVO convertVO);

	

	RadarCatConsultaCPVO guardarNewCP(RadarCatConsultaCPVO convertVO);

	RadarCatConsultaUTVO buscarUnidadTerritorialPorUT(String valorId);

	RadarCatConsultaCPVO buscarUnidadTerritorialPorCP(String valorId);

	RadarCatConsultaUTVO guardarUpdateUT(RadarCatConsultaUTVO convertVO) throws ParseException;

	RadarCatConsultaCPVO guardarUpdateCP(RadarCatConsultaCPVO convertVO) throws ParseException;

	List<FilterValuesVO> getComboArchivoTipo();
	
	// UT Busqueda
	List<FilterValuesVO> getComboArchivoTipoUTBusq();
	
	// UT
	List<FilterValuesVO> getComboArchivoTipoUT();

	List<FilterValuesVO> getComboTipoRadar();
	
	// UT Busqueda
	List<FilterValuesVO> getComboTipoRadarUTBusq();
		
	// UT 
	List<FilterValuesVO> getComboTipoRadarUT();

	//boolean comprobacionUT(RadarCatConsultaUTVO convertVO);
	int comprobacionUT(RadarCatConsultaUTVO convertVO);

	boolean comprobacionCPAll();

	List<RadarCatConsultaCPVO> consultaOpcionalAll();

	boolean cambiarEstatus(String accion,int idUT);
}
