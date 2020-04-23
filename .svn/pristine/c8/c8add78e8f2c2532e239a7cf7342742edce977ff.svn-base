package mx.com.teclo.saicdmx.negocio.service.salidas;

import java.text.ParseException;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.ImgSalidasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.SalidasDTO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ConsultaTrasladoVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ConsultaVehiculoSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.FilesSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarTrasladoVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.InfoPlacaEmpVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.SalidaVehiculoBusqVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ValidarInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.busquedaCatSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.resultCatVO;

public interface SalidaVehiculoService {

	boolean buscarUserValido();
	
	List<FilterValuesVO> filterTipoBusq();

	List<SalidaVehiculoBusqVO> getConsultaSalidasVehiculosBean(String tipoBusqueda, String valorBusq);

	boolean isInfraccionTraslado(String infracNum);

	SalidaVehiculoBusqVO infoInfraccion(String numInfrac);

	List<FilterValuesVO> filterTipoSalida();

	

	List<FilterValuesVO> comboAdjunDestino();

	List<FilterValuesVO> comboFaseCompacta();

	ValidarInfraccionVO validaPlaca(String infracNum);

	List<FilterValuesVO> comboDepDestino(String depOrigen);

	InfoPlacaEmpVO validarPlacaOficial(String placaOficial);

	List<FilterValuesVO> filterComboTipoBusq();

	List<FilterValuesVO> filtroComboTipoOrden();

	List<FilterValuesVO> filtroComboTipoSalida();

	List<ConsultaVehiculoSalidaVO> busquedaVehiculoConsulta(String tipoBusq, String valorCombo, String tipoBusqSalida, String fechaInicio, String fechaFin, String tipoBusqEspecific);

	List<FilterValuesVO> tipoBusqCombo();

	List<FilterValuesVO> depOrigenCombo();

	List<ConsultaTrasladoVO> busquedaVehiculoConsultaTraslado(String tipoBusq, String depOrig, String depId);

	

	ConsultaTrasladoVO buscarInfoTrasladoVeh(String numInfraccion);

	String calcularTiempoTrslado(String tiempo) throws ParseException;

	
	String busquedaDepDestino(String tipoBusqueda, String datoBusq);

	void actualizarTablaIngresos(GuardarSalidaVO convertVO);

	SalidasDTO insertToSalida(GuardarSalidaVO convertVO);
	
	
	void guardarSalida(GuardarSalidaVO convertVO);

	List<ImgSalidasDTO> getExpediente(String idSalida, String tipo, String nombreImgComp);

	Long getIdSalida(String numinfrac);


	void insertToImgSalida(List<FilesSalidaVO> filesVO, GuardarSalidaVO convertVO, Long idMovimiento);

	String getNumInfraccionByIdSalida(Long idSalidas);

	/*INICIA CATALOGOS SALIDAS*/
	
	List<FilterValuesVO> filterComboCatSalidas();

	List<FilterValuesVO> filtroComboCatCompactacion();

	List<FilterValuesVO> filtroComboCatAdjudicacion();

	List<resultCatVO> getListCatSalidas(busquedaCatSalidaVO convertVO);

	resultCatVO getListCatSalidasByIdCat(Long idCat, Long tipoCat);

	List<FilterValuesVO> filtroComboActiveInactive();

	boolean getResultsUpdate(resultCatVO convertVO, Long tipoCat);

	boolean getResultsInsertSalida(resultCatVO convertVO, Long tipoCat);

	void updateIngresos(GuardarTrasladoVO convertVO);

	void insertToImgSalidaIngreso(List<FilesSalidaVO> filesVO, GuardarTrasladoVO convertVO, Long idMovimiento);

	String obtenerRutaLocalMovimiento(String numInfrac, String tipo, String idSalidas);

	Long getIdMovVeh(String numinfrac);

	void insertToBit(String infracNum, String oldStatus);

	String getOldStatus(String infracNum);

	void insertIntoBitac(GuardarSalidaVO convertVO, GuardarSalidaVO convertVO2) throws ParseException;

	GuardarSalidaVO getOriginalIngreso(String numinfrac);

}
